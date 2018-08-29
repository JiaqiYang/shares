package com.yjq.shares.controller;

import com.yjq.shares.model.AmapShop;
import com.yjq.shares.service.AmapShopService;
import com.yjq.shares.service.ReptilianAmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AmapShopController {
    @Autowired
    ReptilianAmapService reptilianAmapService;

    @Autowired
    AmapShopService amapShopService;


    @GetMapping("/amap")
    public String openAmap(){
        return "amap";
    }

    @PostMapping("/queryShops")
    public String queryShopData(Model model, double longitude, double latitude, int range, int city, String keywords){
        List<AmapShop> amapShopList = amapShopService.queryShop();
        if (amapShopList.size()>0){
            model.addAttribute("shopList",amapShopList);
        }else{
            reptilianAmapService.init(longitude,latitude,range,city,keywords);
            amapShopList = amapShopService.queryShop();
            model.addAttribute("shopList",amapShopList);
        }
        return "amap";
    }
}
