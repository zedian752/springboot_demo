package org.spring_demo;

import lombok.extern.slf4j.Slf4j;
import org.spring_demo.entity.DataResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;



/**
 * 统一处理返回值的切面类
 * 这个切面会作用所有controller里面的方法
 */
@Slf4j
@ControllerAdvice(basePackages = {"org.spring_demo"})
public class restAdvice implements ResponseBodyAdvice<Object> {

    void test () throws OutOfMemoryError{
        throw new ArrayIndexOutOfBoundsException();
    }
    public restAdvice() {
        System.out.println(1);
    }
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果body就是RestResult对象，直接返回
        // 这里有2个使用场景，1-异常处理后会生成RestResult再进入这个方法
        // 2-某些业务可能需要自己封装RestResult对象直接返回，例如请求成功，但是业务需要success返回false的情况
        String sysClientType = request.getHeaders().getFirst("sys-client-type");
        String webClientType = request.getHeaders().getFirst("web-client-type");
        log.debug("sys-client-type: " + sysClientType);
        log.debug("web-client-type: "+ webClientType);

        // 设置跟踪ID
        String reqTraceId = request.getHeaders().getFirst("reqTraceId");
//        log.info("Header reqTraceId: "+ reqTraceId);

        return new DataResponse(body, 200);
    }

}
