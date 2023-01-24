package com.springbootproject.reggietakeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springbootproject.reggietakeout.common.Result;
import com.springbootproject.reggietakeout.entity.Category;
import com.springbootproject.reggietakeout.entity.Employee;
import com.springbootproject.reggietakeout.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result<String> save(@RequestBody Category category) {
        log.info("category:{}", category);
        categoryService.save(category);
        return Result.success(category.getType() == 1 ? "添加菜品分类成功！" : "添加套餐分类成功！");
    }

    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize){
        //分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //条件查询器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件
        queryWrapper.orderByDesc(Category::getSort);
        //分页查询
        categoryService.page(pageInfo, queryWrapper);
        return Result.success(pageInfo);
    }

    @DeleteMapping
    private Result<String> delete(Long id) {
        log.info("将被删除的id：{}", id);
        categoryService.remove(id);
        return Result.success("Delete Successfully");
    }

    @PutMapping
    private Result<String> update(@RequestBody Category category){
        log.info("修改分类信息为：{}", category);
        categoryService.updateById(category);
        return Result.success("修改分类信息成功");

    }

    @GetMapping("/list")
    public Result<List<Category>> list(Category category) {
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件，这里只需要判断是否为菜品（type为1是菜品，type为2是套餐）
        queryWrapper.eq(category.getType() != null,Category::getType,category.getType());
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        //查询数据
        List<Category> list = categoryService.list(queryWrapper);
        //返回数据
        return Result.success(list);
    }
}
