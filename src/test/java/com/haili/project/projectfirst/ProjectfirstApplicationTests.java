package com.haili.project.projectfirst;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.TimeZone;

@SpringBootTest
class ProjectfirstApplicationTests {
    @Test
    void contextLoads() {

        //数据库中存的时间是毫秒
        long current=System.currentTimeMillis();    //当前时间毫秒数
        long zeroT=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();  //今天零点零分零秒的毫秒数
        //格式化时间
        //String zero = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(zeroT);
        long endT=zeroT+24*60*60*1000-1;  //今天23点59分59秒的毫秒数
        System.out.println(zeroT);
        System.out.println(endT);
    }

}
