package com.springbootproject.reggietakeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springbootproject.reggietakeout.common.Result;
import com.springbootproject.reggietakeout.dto.DishDto;
import com.springbootproject.reggietakeout.entity.Category;
import com.springbootproject.reggietakeout.entity.Dish;
import com.springbootproject.reggietakeout.service.CategoryService;
import com.springbootproject.reggietakeout.service.DishFlavorService;
import com.springbootproject.reggietakeout.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;

    @PostMapping
    public Result<String> save(@RequestBody DishDto dishDto) {
        log.info("接收到的数据为：{}",dishDto);
        return null;
    }
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, String name){
        Page<Dish> pageInfo = new Page<>(page,pageSize);


    }

}