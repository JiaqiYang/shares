package com.yjq.shares.mapper;

import com.yjq.shares.model.AmapShop;
import com.yjq.shares.model.AmapShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AmapShopMapper {
    int countByExample(AmapShopExample example);

    int deleteByExample(AmapShopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AmapShop record);

    int insertSelective(AmapShop record);

    List<AmapShop> selectByExample(AmapShopExample example);

    AmapShop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AmapShop record, @Param("example") AmapShopExample example);

    int updateByExample(@Param("record") AmapShop record, @Param("example") AmapShopExample example);

    int updateByPrimaryKeySelective(AmapShop record);

    int updateByPrimaryKey(AmapShop record);
}