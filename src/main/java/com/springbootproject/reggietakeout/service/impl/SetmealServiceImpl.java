package com.springbootproject.reggietakeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootproject.reggietakeout.common.CustomException;
import com.springbootproject.reggietakeout.dto.SetmealDto;
import com.springbootproject.reggietakeout.entity.Setmeal;
import com.springbootproject.reggietakeout.entity.SetmealDish;
import com.springbootproject.reggietakeout.mapper.SetmealMapper;
import com.springbootproject.reggietakeout.service.SetmealDishService;
import com.springbootproject.reggietakeout.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    protected SetmealDishService setmealDishService;

    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public void removeWithDish(List<Long> ids) {
        //先判断一下能不能删，如果status为1，则套餐在售，不能删
        //select * from setmeal where id in (ids) and status = 1
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.in(Setmeal::getId, ids);
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus, 1);
        int count = this.count(setmealLambdaQueryWrapper);
        //下面两行是我debug输出的日志，没啥用
        List<Setmeal> list = this.list(setmealLambdaQueryWrapper);
        log.info("查询到的数据为：{}",list);
        if (count > 0) {
            throw new CustomException("You have to disable a combo before delete it");
        }
        //如果没有在售套餐，则直接删除
        this.removeByIds(ids);
        //继续删除
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(setmealDishLambdaQueryWrapper);
    }
}
