package com.yangtzeu.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("blog")
@Data
public class Blog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
}
