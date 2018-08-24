package com.yjq.shares.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yjq.shares.mapper.SharesInfoMapper;
import com.yjq.shares.mapper.SharesPriceMapper;
import com.yjq.shares.model.SharesInfo;
import com.yjq.shares.model.SharesInfoExample;
import com.yjq.shares.model.SharesPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SharesService {
    @Autowired
    SharesInfoMapper sharesInfoMapper;
    @Autowired
    SharesPriceMapper sharesPriceMapper;

    public String selectSharesInfo() {
        SharesInfoExample se = new SharesInfoExample();
        se.createCriteria().andIdIsNotNull();
        List<SharesInfo> sis = sharesInfoMapper.selectByExample(se);
        return JSONArray.toJSON(sis).toString();
    }

    public String selectSharesId(){
        SharesInfoExample se = new SharesInfoExample();
        se.createCriteria().andIdIsNotNull();
        List<SharesInfo> sis = sharesInfoMapper.selectByExample(se);
        List<Integer> ids = new ArrayList<>();
        for (SharesInfo si: sis) {
            ids.add(si.getId());
        }
        return JSONArray.toJSON(ids).toString();
    }

    public String selectSharesById(int id){
        return JSONObject.toJSON(sharesInfoMapper.selectByPrimaryKey(id)).toString();
    }




    /**
     * 添加 股票详细信息
     * @param sharesInfo
     * @return 1成功 0失败
     */
    public int addSharesInfo(SharesInfo sharesInfo){
        return sharesInfoMapper.insert(sharesInfo);
    }

    /**
     * 添加股票价格信息
     * @param sharesPrice
     * @return 1成功 0失败
     */
    public int addSharesPrice(SharesPrice sharesPrice){
        return sharesPriceMapper.insert(sharesPrice);
    }

}
