package com.springbootproject.reggietakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springbootproject.reggietakeout.dto.SetmealDto;
import com.springbootproject.reggietakeout.entity.Setmeal;
import com.springbootproject.reggietakeout.entity.Dish;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);
    void removeWithDish(List<Long> ids);
}
