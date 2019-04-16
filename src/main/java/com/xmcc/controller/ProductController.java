package com.xmcc.controller;

import com.xmcc.common.JsonData;
import com.xmcc.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/buyer/product")
@Api(value = "商品信息接口")
public class ProductController {

    @Resource
    ProductCategoryService productCategoryService;

    @GetMapping("/list")
    @ApiOperation(value = "获取商品信息列表")
    public JsonData list(){
        return productCategoryService.list();
    }
}
