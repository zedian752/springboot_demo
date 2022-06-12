package test;

import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Go.class)
//@ComponentScan({"org.spring_demo.**", "Service.**"})
public class AppTest {
//    @Autowired
//    testService testService;

    public <T> void g2(abc<T> a) {
    }

    @Test
    public void go1() {
    }
}

class abc<T> {
    T a;
}

class abc2{
    abc2() {
        System.out.println("hahah");
    }


}
