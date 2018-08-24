package com.yjq.shares;

import com.yjq.shares.service.SharesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SharesApplicationTests {

    @Autowired
    SharesService sharesService;

    @Test
    public void contextLoads() {
        System.out.println(sharesService.selectSharesInfo());
    }

}
