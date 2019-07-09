package com.mengxuegu.springboot.mapper;

import com.mengxuegu.springboot.entities.Provider;

import java.util.List;


public interface ProviderMapper {


    List<Provider> getProviders(Provider provider);


    Provider getProviderByPid(Integer pid);


    int addProvider(Provider provider);

    int deleteProviderByPid(Integer pid);

    int updateProvider(Provider provider);
}
