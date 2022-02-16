package com.example.test2.Config;

import com.example.test2.POJO.Area;
import com.example.test2.Service.PrimaryService.AreaStoreService;
import com.example.test2.Service.PrimaryService.Impl.AreaStoreServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Component
public class cityTree {

    @Resource
    private AreaStoreService areaStoreService;

    @Bean("areaTree")
    public Area areaTree(){
        return areaStoreService.convertToArea(1L);
    }

}
