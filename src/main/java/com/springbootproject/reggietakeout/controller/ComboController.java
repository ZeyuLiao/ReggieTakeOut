package com.springbootproject.reggietakeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springbootproject.reggietakeout.common.Result;
import com.springbootproject.reggietakeout.dto.ComboDto;
import com.springbootproject.reggietakeout.entity.Category;
import com.springbootproject.reggietakeout.entity.Combo;
import com.springbootproject.reggietakeout.service.CategoryService;
import com.springbootproject.reggietakeout.service.ComboDishService;
import com.springbootproject.reggietakeout.service.ComboService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/setmeal")
public class ComboController {
    @Autowired
    private ComboService comboService;
    @Autowired
    private ComboDishService comboDishService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result<String> save(@RequestBody ComboDto comboDto) {
        log.info("Combo Info：{}", comboDto);
        comboService.saveWithDish(comboDto);
        return Result.success("Combo added!");
    }

    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, String name) {
        Page<Combo> pageInfo = new Page<>(page, pageSize);
        Page<ComboDto> dtoPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Combo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Combo::getName, name);
        queryWrapper.orderByDesc(Combo::getUpdateTime);
        comboService.page(pageInfo, queryWrapper);
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Combo> records = pageInfo.getRecords();
        List<ComboDto> list = records.stream().map((item) -> {
            ComboDto comboDto = new ComboDto();
            BeanUtils.copyProperties(item, comboDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                comboDto.setCategoryName(category.getName());
            }
            return comboDto;
        }).collect(Collectors.toList());
        dtoPage.setRecords(list);
        return Result.success(dtoPage);
    }

    @DeleteMapping
    public Result<String> deleteByIds(@RequestParam List<Long> ids) {
        log.info("要删除的套餐id为：{}",ids);
        comboService.removeWithDish(ids);
        return Result.success("删除成功");
    }

}
