package com.springboot.chapter15.dao;

import com.springboot.chapter15.pojo.ProductPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductDao {
    public ProductPo getProduct(Long id);

    public int decreaseProduct(@Param("id") Long id,@Param("quantity") int quantity,@Param("version") int version);
    public int decreaseProduct1(@Param("id") Long id,@Param("quantity") int quantity);
}
