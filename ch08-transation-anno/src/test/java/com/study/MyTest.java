package com.study;

import com.study.service.BuyService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void testBuy(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        BuyService buyService = (BuyService) ac.getBean("buyServiceImp");

        buyService.buy(1001,1);
    }
}
