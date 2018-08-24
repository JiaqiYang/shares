package com.yjq.shares.controller;

import com.yjq.shares.service.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SharesInfoController {
    @Autowired
    SharesService sharesService;

    @RequestMapping("/getShares")
    public String selectShares(){
        return sharesService.selectSharesInfo();
    }

    @RequestMapping("/getSharesId")
    public String selectSharesById(){
        return sharesService.selectSharesId();
    }

    @RequestMapping("/getSharesById")
    public String getSharesById(int id){
        return sharesService.selectSharesById(id);
    }
}
