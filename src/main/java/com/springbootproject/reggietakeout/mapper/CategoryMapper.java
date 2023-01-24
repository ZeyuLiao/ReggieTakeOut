package com.springbootproject.reggietakeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springbootproject.reggietakeout.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
