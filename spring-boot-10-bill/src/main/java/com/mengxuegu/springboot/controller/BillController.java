package com.mengxuegu.springboot.controller;


import com.mengxuegu.springboot.entities.Bill;
import com.mengxuegu.springboot.entities.BillProvider;
import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.mapper.BillMapper;
import com.mengxuegu.springboot.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class BillController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProviderMapper providerMapper;

    @Autowired
    BillMapper billMapper;
//    @RequestMapping(value = "/providers",method = RequestMethod.GET)

    @GetMapping("/bills")
    public String list(Map<String,Object> map, Bill bill){

        logger.info("账单列表查询。。。"+bill);

        List<BillProvider> billProviders = billMapper.getBills(bill);
        List<Provider> providers = providerMapper.getProviders(null);
        map.put("billProviders",billProviders);
        map.put("providers",providers);
        map.put("billName",bill.getBillName());
        map.put("pid",bill.getPid());
        map.put("pay",bill.getPay());
        return "bill/list";

    }

    @GetMapping("/bill/{bid}")
    public String view(@PathVariable("bid") Integer bid, @RequestParam(value = "type",defaultValue = "view") String type, Map<String,Object> map){
        List<BillProvider> billProviders = billMapper.getBillByBid(bid);
        List<Provider> providers = providerMapper.getProviders(null);
        map.put("billProvider",billProviders);
        map.put("providers",providers);
        return "bill/"+type;

    }

    @PutMapping("/bill")
    public String update(Bill bill){
        billMapper.updateBill(bill);
        return "redirect:/bills";
    }

    @GetMapping("/bill")
    public String toAddpage(Map<String,Object> map){
        List<Provider> providers = providerMapper.getProviders(null);
        map.put("providers",providers);

        return "bill/add";

    }

    @PostMapping("/bill")
    public String add(Bill bill){
        billMapper.addBill(bill);
        return "redirect:/bills";
    }


    @DeleteMapping("/bill/{bid}")
    public String delete(@PathVariable("bid") Integer bid){

        billMapper.deleteBillByBid(bid);
        return "redirect:/bills";

    }


}
