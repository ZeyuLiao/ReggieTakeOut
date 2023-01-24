package com.springbootproject.reggietakeout.controller;

import com.springbootproject.reggietakeout.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @PostMapping("upload")
    public Result<String> upload(MultipartFile file){
        log.info("Get file:{}",file.toString());
        return null;
    }
}
