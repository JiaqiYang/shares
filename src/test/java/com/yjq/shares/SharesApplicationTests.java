package com.yjq.shares;

import com.yjq.shares.model.AmapShop;
import com.yjq.shares.service.AmapShopService;
import com.yjq.shares.service.ReptilianAmapService;
import com.yjq.shares.service.SharesService;
import com.yjq.shares.util.DistanceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SharesApplicationTests {

    @Autowired
    SharesService sharesService;

    @Autowired
    ReptilianAmapService reptilianAmapService;

    @Autowired
    AmapShopService amapShopService;

    @Test
    public void contextLoads() {
        System.out.println(sharesService.selectSharesInfo());
    }

    @Test
    public void reptilianTest(){
        reptilianAmapService.init(106.149845, 38.492309, 5000, 640100, "商城");
    }

    @Test
    public void TestQuert(){
        List<AmapShop> amapShopList =  amapShopService.queryShop("商场");
        int i = 0;
        for (AmapShop amapShop: amapShopList) {
            double km = DistanceUtil.getDistance(amapShop.getLongitude(),amapShop.getLatitude(),106.149845,38.492309);
            if (km<3){
                System.out.println(amapShop.getName()+","+km);
                i++;
            }
        }
        System.out.println(i);
    }
}
