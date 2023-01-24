package com.springbootproject.reggietakeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootproject.reggietakeout.dto.DishDto;
import com.springbootproject.reggietakeout.entity.Dish;
import com.springbootproject.reggietakeout.entity.DishFlavor;
import com.springbootproject.reggietakeout.mapper.DishMapper;
import com.springbootproject.reggietakeout.service.DishFlavorService;
import com.springbootproject.reggietakeout.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    @Override
    public void saveWithFlavor(DishDto dishDto) {
        //将菜品数据保存到dish表
        this.save(dishDto);
        //获取dishId
        Long dishId = dishDto.getId();
        //将获取到的dishId赋值给dishFlavor的dishId属性
        List<DishFlavor> flavors = dishDto.getFlavors();
        for (DishFlavor dishFlavor : flavors) {
            dishFlavor.setDishId(dishId);
        }
        //同时将菜品口味数据保存到dish_flavor表
        dishFlavorService.saveBatch(flavors);
    }
}
