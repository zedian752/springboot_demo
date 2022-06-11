package org.spring_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableConfigurationProperties(Fortest.class)
@MapperScan(basePackages = "org.spring_demo.dao")
public class Go {
    public static void main(String args[]){
        SpringApplication.run(Go.class,args);
    }
}

abstract class base {
    public abstract  void test();
}
class ex extends base{
    @Override
    public void test() {
    }
}