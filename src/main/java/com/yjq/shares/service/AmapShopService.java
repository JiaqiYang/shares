package com.yjq.shares.service;

import com.yjq.shares.mapper.AmapShopMapper;
import com.yjq.shares.model.AmapShop;
import com.yjq.shares.model.AmapShopExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AmapShopService {

    @Autowired
    AmapShopMapper amapShopMapper;

    public List<AmapShop> queryShop(){
        AmapShopExample amapShopExample = new AmapShopExample();
        amapShopExample.createCriteria().andIdIsNotNull();
        return  amapShopMapper.selectByExample(amapShopExample);
    }

    public List<AmapShop> queryShop(String type){
        AmapShopExample amapShopExample = new AmapShopExample();
        amapShopExample.createCriteria().andTypeEqualTo(type);
        return  amapShopMapper.selectByExample(amapShopExample);
    }
}
