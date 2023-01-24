package com.springbootproject.reggietakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springbootproject.reggietakeout.dto.DishDto;
import com.springbootproject.reggietakeout.entity.Dish;

public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);
    DishDto getByIdWithFlavor(Long id);
    void updateWithFlavor(DishDto dishDto);
}
