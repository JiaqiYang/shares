package com.yjq.shares.mapper;

import com.yjq.shares.model.SharesInfo;
import com.yjq.shares.model.SharesInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SharesInfoMapper {
    int countByExample(SharesInfoExample example);

    int deleteByExample(SharesInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SharesInfo record);

    int insertSelective(SharesInfo record);

    List<SharesInfo> selectByExample(SharesInfoExample example);

    SharesInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SharesInfo record, @Param("example") SharesInfoExample example);

    int updateByExample(@Param("record") SharesInfo record, @Param("example") SharesInfoExample example);

    int updateByPrimaryKeySelective(SharesInfo record);

    int updateByPrimaryKey(SharesInfo record);
}