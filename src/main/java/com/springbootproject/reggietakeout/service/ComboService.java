package com.springbootproject.reggietakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springbootproject.reggietakeout.dto.ComboDto;
import com.springbootproject.reggietakeout.entity.Combo;
import com.springbootproject.reggietakeout.entity.Dish;

import java.util.List;

public interface ComboService extends IService<Combo> {
    void saveWithDish(ComboDto comboDto);
    void removeWithDish(List<Long> ids);
}
