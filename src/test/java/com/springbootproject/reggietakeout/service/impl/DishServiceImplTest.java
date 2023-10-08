package com.springbootproject.reggietakeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springbootproject.reggietakeout.entity.Dish;
import com.springbootproject.reggietakeout.entity.DishFlavor;
import com.springbootproject.reggietakeout.service.DishFlavorService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class DishServiceImplTest {

    @Injectable
    private DishFlavorService dishFlavorService;

    @Tested
    DishServiceImpl dishService;

    @Test
    void getByIdWithFlavor() {
        Long id = 1L;
        Dish dish  = new Dish();
        dish.setName("Dish");
        new Expectations(){
            {
                dishService.getById(id);
                result  = dish;
            }
        };
        List<DishFlavor> list = new ArrayList<>();
        DishFlavor flavor = new DishFlavor();
        flavor.setName("Flavor");

        list.add(new DishFlavor());
        new Expectations(){
            {
                dishFlavorService.list(any(LambdaQueryWrapper.class));
                result  = list;
            }
        };

        String expected = dishService.getByIdWithFlavor(id).getFlavors().get(0).getName();

        Assertions.assertEquals(expected,"Flavor");
    }

    @Test
    void updateWithFlavor() {
    }
}