package org.spring_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataResponse<T> {
    public static int c =5;
    T data;
    Integer Code;
}
