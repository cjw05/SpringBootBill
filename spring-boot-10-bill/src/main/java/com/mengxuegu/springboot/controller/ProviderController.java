package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.dao.ProviderDao;
import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class ProviderController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProviderMapper providerMapper;
//    @RequestMapping(value = "/providers",method = RequestMethod.GET)

    @GetMapping("/providers")
    public String list(Map<String,Object> map,Provider provider){

        logger.info("供应商列表查询。。。"+provider.getProviderName());

        List<Provider> providers = providerMapper.getProviders(provider);


        map.put("providers",providers);
        map.put("providerName",provider.getProviderName());
        return  "provider/list";

    }

    @GetMapping("/provider/{pid}")
    public String view(@PathVariable("pid") Integer pid,@RequestParam(value = "type",defaultValue = "view") String type,Map<String,Object> map){
        logger.info("查询"+pid+"的供应商详细信息");
        Provider provider = providerMapper.getProviderByPid(pid);

        map.put("provider",provider);
        return "provider/"+type;

    }

    @PutMapping("/provider")
    public String update(Provider provider){
        providerMapper.updateProvider(provider);
        System.out.println(provider.getProviderName());

        return "redirect:/providers";
    }

    @GetMapping("/provider")
    public String toAddpage(){


        return "provider/add";

    }

    @PostMapping("/provider")
    public String add(Provider provider){
        providerMapper.addProvider(provider);
        return "redirect:/providers";
    }


    @DeleteMapping("/provider/{pid}")
    public String delete(@PathVariable("pid") Integer pid){
        logger.info("111111");
        providerMapper.deleteProviderByPid(pid);
        return "redirect:/providers";

    }
}
