package com.springbootproject.reggietakeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootproject.reggietakeout.common.CustomException;
import com.springbootproject.reggietakeout.dto.ComboDto;
import com.springbootproject.reggietakeout.entity.Combo;
import com.springbootproject.reggietakeout.entity.ComboDish;
import com.springbootproject.reggietakeout.entity.Dish;
import com.springbootproject.reggietakeout.mapper.ComboMapper;
import com.springbootproject.reggietakeout.mapper.DishMapper;
import com.springbootproject.reggietakeout.service.ComboDishService;
import com.springbootproject.reggietakeout.service.ComboService;
import com.springbootproject.reggietakeout.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ComboServiceImpl extends ServiceImpl<ComboMapper, Combo> implements ComboService {

    @Autowired
    protected ComboDishService comboDishService;

    @Override
    public void saveWithDish(ComboDto comboDto) {
        this.save(comboDto);
        List<ComboDish> comboDishes = comboDto.getComboDishes();
        comboDishes = comboDishes.stream().map((item) -> {
            item.setComboId(comboDto.getId());
            return item;
        }).collect(Collectors.toList());
        comboDishService.saveBatch(comboDishes);
    }

    @Override
    public void removeWithDish(List<Long> ids) {
        //先判断一下能不能删，如果status为1，则套餐在售，不能删
        //select * from combo where id in (ids) and status = 1
        LambdaQueryWrapper<Combo> comboLambdaQueryWrapper = new LambdaQueryWrapper<>();
        comboLambdaQueryWrapper.in(Combo::getId, ids);
        comboLambdaQueryWrapper.eq(Combo::getStatus, 1);
        int count = this.count(comboLambdaQueryWrapper);
        //下面两行是我debug输出的日志，没啥用
        List<Combo> list = this.list(comboLambdaQueryWrapper);
        log.info("查询到的数据为：{}",list);
        if (count > 0) {
            throw new CustomException("套餐正在售卖中，请先停售再进行删除");
        }
        //如果没有在售套餐，则直接删除
        this.removeByIds(ids);
        //继续删除
        LambdaQueryWrapper<ComboDish> comboDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        comboDishLambdaQueryWrapper.in(ComboDish::getComboId, ids);
        comboDishService.remove(comboDishLambdaQueryWrapper);
    }
}
