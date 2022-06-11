package org.spring_demo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class Student extends Model {


    private Integer age;

    private String name;

    private String address;

    private User user;
    List<MultipartFile> file;
}
