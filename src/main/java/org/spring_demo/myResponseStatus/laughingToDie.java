package org.spring_demo.myResponseStatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNSUPPORTED_MEDIA_TYPE,reason="笑死了")
public class laughingToDie extends Exception {

}
