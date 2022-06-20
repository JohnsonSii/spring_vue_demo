package com.yangtzeu.server.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangtzeu.server.common.Result;
import com.yangtzeu.server.entity.Blog;
import com.yangtzeu.server.mapper.BlogMapper;

import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/data")
public class BlogController {
    @Resource
    BlogMapper blogMapper;

    @PostMapping
    public Result<?> save(@RequestBody Blog blog) {
        blogMapper.insert(blog);
        return Result.success();
    }


    @PutMapping
    public Result<?> update(@RequestBody Blog blog) {
        blogMapper.updateById(blog);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Integer id) {
        blogMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Blog> wrapper = Wrappers.<Blog>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Blog::getTitle, search);
        }
        Page<Blog> blogPage = blogMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(blogPage);
    }
}


