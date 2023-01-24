package com.springbootproject.reggietakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springbootproject.reggietakeout.entity.Category;

import java.util.Locale;

public interface CategoryService extends IService<Category> {

    void remove(Long id);
}
