package com.yjq.shares.mapper;

import com.yjq.shares.model.SharesPrice;
import com.yjq.shares.model.SharesPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SharesPriceMapper {
    int countByExample(SharesPriceExample example);

    int deleteByExample(SharesPriceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SharesPrice record);

    int insertSelective(SharesPrice record);

    List<SharesPrice> selectByExample(SharesPriceExample example);

    SharesPrice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SharesPrice record, @Param("example") SharesPriceExample example);

    int updateByExample(@Param("record") SharesPrice record, @Param("example") SharesPriceExample example);

    int updateByPrimaryKeySelective(SharesPrice record);

    int updateByPrimaryKey(SharesPrice record);
}