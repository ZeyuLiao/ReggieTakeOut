package com.springbootproject.reggietakeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootproject.reggietakeout.common.CustomException;
import com.springbootproject.reggietakeout.common.Result;
import com.springbootproject.reggietakeout.entity.Category;
import com.springbootproject.reggietakeout.entity.Setmeal;
import com.springbootproject.reggietakeout.entity.Dish;
import com.springbootproject.reggietakeout.mapper.CategoryMapper;
import com.springbootproject.reggietakeout.service.CategoryService;
import com.springbootproject.reggietakeout.service.SetmealService;
import com.springbootproject.reggietakeout.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    DishService dishService;

    @Autowired
    SetmealService setmealService;


    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加dish查询条件，根据分类id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        //方便Debug用的
        int count1 = dishService.count(dishLambdaQueryWrapper);
        log.info("dish查询条件，查询到的条目数为：{}",count1);
        //查看当前分类是否关联了菜品，如果已经关联，则抛出异常
        if (count1 > 0){
            //已关联菜品，抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加dish查询条件，根据分类id进行查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        //方便Debug用的
        log.info("setmeal查询条件，查询到的条目数为：{}",count2);
        //查看当前分类是否关联了套餐，如果已经关联，则抛出异常
        if (count2 > 0){
            //已关联套餐，抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }
        //正常删除
        super.removeById(id);
    }
}
