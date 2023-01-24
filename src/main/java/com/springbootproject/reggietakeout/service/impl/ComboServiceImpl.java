package com.springbootproject.reggietakeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootproject.reggietakeout.entity.Combo;
import com.springbootproject.reggietakeout.entity.Dish;
import com.springbootproject.reggietakeout.mapper.ComboMapper;
import com.springbootproject.reggietakeout.mapper.DishMapper;
import com.springbootproject.reggietakeout.service.ComboService;
import com.springbootproject.reggietakeout.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class ComboServiceImpl extends ServiceImpl<ComboMapper, Combo> implements ComboService {
}
