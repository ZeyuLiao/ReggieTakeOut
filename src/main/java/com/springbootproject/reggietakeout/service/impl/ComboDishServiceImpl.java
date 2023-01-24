package com.springbootproject.reggietakeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootproject.reggietakeout.entity.ComboDish;
import com.springbootproject.reggietakeout.mapper.ComboDishMapper;
import com.springbootproject.reggietakeout.service.ComboDishService;
import com.springbootproject.reggietakeout.service.ComboService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ComboDishServiceImpl extends ServiceImpl<ComboDishMapper, ComboDish> implements ComboDishService{
}
