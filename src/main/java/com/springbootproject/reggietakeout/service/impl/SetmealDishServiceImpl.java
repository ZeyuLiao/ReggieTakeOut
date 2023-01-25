package com.springbootproject.reggietakeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootproject.reggietakeout.entity.SetmealDish;
import com.springbootproject.reggietakeout.mapper.SetmealDishMapper;
import com.springbootproject.reggietakeout.service.SetmealDishService;
import com.springbootproject.reggietakeout.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService{
}
