package org.spring_demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("tab_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1649736992341L;
    @TableId
    Integer uid;
    @Version
    String username;
    String password;
    String telephone;
    String salt;
    String url;
    Short deleted;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createTime;
}
