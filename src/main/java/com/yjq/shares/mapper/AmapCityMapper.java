package com.yjq.shares.mapper;

import com.yjq.shares.model.AmapCity;
import com.yjq.shares.model.AmapCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AmapCityMapper {
    int countByExample(AmapCityExample example);

    int deleteByExample(AmapCityExample example);

    int deleteByPrimaryKey(Integer cityId);

    int insert(AmapCity record);

    int insertSelective(AmapCity record);

    List<AmapCity> selectByExample(AmapCityExample example);

    AmapCity selectByPrimaryKey(Integer cityId);

    int updateByExampleSelective(@Param("record") AmapCity record, @Param("example") AmapCityExample example);

    int updateByExample(@Param("record") AmapCity record, @Param("example") AmapCityExample example);

    int updateByPrimaryKeySelective(AmapCity record);

    int updateByPrimaryKey(AmapCity record);
}