package com.xmcc.service.impl;

import com.xmcc.common.JsonData;
import com.xmcc.dto.ProductCategoryDto;
import com.xmcc.dto.ProductInfoDto;
import com.xmcc.entity.ProductCategory;
import com.xmcc.entity.ProductInfo;
import com.xmcc.repository.ProductCategoryRepository;
import com.xmcc.repository.ProductInfoRepository;
import com.xmcc.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Resource
    ProductCategoryRepository productCategoryRepository;

    @Resource
    ProductInfoRepository productInfoRepository;

    @Override
    public JsonData list() {

        //获取所有的商品类目
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();

        List<ProductCategoryDto> productCategoryDtoList = productCategoryList.stream()
                .map(productCategory -> ProductCategoryDto.getCategoryDto(productCategory)).collect(Collectors.toList());

        //根据商品类目的type和商品的状态查询所有商品
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatusAndCategoryTypeIn(0,
                productCategoryDtoList.stream().map(productCategoryDto -> productCategoryDto.getCategoryType()).collect(Collectors.toList()));

        //封装菜品列表
        /**
         * 1.productCategoryDtoList 开启并发流进行遍历
         * 2.设置 ProductInfoList
         * 3.productInfoList 使用流进行过滤获取productCategoryDto的CategoryType与productInfo的categoryType相同的productInfo
         * 4.流转换，将productInfo 转换成productInfoDto
         */
        List<ProductCategoryDto> categoryDtos =  productCategoryDtoList.parallelStream().map(productCategoryDto -> {
            productCategoryDto.setProductInfoList(
                    productInfoList.stream().filter(productInfo -> productInfo.getCategoryType() == productCategoryDto.getCategoryType())
                            .map(productInfo -> ProductInfoDto.build(productInfo)).collect(Collectors.toList()));
            return productCategoryDto;
        }).collect(Collectors.toList());

        return JsonData.success(categoryDtos);
    }
}
