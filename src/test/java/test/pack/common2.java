package test.pack;
import lombok.Data;
import org.junit.Test;

class test<T> {
    T t;
    void tststs() {
    }
}

public class common2 {
    @Test
    public void client() throws Exception{
        ClassScanner.test();
    }

    void scanClass(){}
    @Test
    public void cl() throws Exception{
    }

    public static <T> T getModelByClass(Class lcz, PageParamVO model) {
        T t = (T) model;
        return t;
    }

}

@Data
class PageParamVO {
    private Integer pageNum = 0; // 默认起始页
    private Integer pageSize = 10; // 默认分页大小
}

class Obj extends PageParamVO{

}