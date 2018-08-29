package com.yjq.shares.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yjq.shares.mapper.AmapShopMapper;
import com.yjq.shares.model.AmapShop;
import com.yjq.shares.util.HttpClientPartner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 爬取高德地图该点附近的店铺等等信息
 */
@Service
public class ReptilianAmapService {
    Logger log = LogManager.getLogger(ReptilianAmapService.class);


    @Autowired
    AmapShopMapper amapShopMapper;

    private double longitude;
    private double latitude;
    private int range;
    private int city;
    private String keywords;

    private HttpClientPartner httpClientPartner;

    /**
     * @param longitude 经度
     * @param latitude  纬度
     * @param range     范围
     * @param city      城市
     * @param keywords  关键字
     */
    public void init(double longitude, double latitude, int range, int city, String keywords) {
        httpClientPartner = new HttpClientPartner();
        this.longitude = longitude;
        this.latitude = latitude;
        this.range = range;
        this.city = city;
        this.keywords = httpClientPartner.gbEncoding(keywords);

        request(1);
        httpClientPartner.close();
    }

    /**
     * @param longitude 经度
     * @param latitude  纬度
     * @param range     范围
     * @param city      城市
     * @param keywords  关键字
     * @param ip        使用代理爬取  代理ip
     * @param port      端口
     */
    public void init(double longitude, double latitude, int range, int city, String keywords, String ip, int port) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.range = range;
        this.city = city;
        this.keywords = httpClientPartner.gbEncoding(keywords);
        httpClientPartner = new HttpClientPartner(ip, port);

        request(1);
        httpClientPartner.close();
    }

    public void request(int page) {
        String url = "https://www.amap.com/service/poiInfo?" +
                "query_type=RQBXY" +
                "&pagesize=30" +
                "&pagenum=" + page +
                "&qii=true" +
                "&cluster_state=5" +
                "&need_utd=true" +
                "&utd_sceneid=1000" +
                "&div=PC1000" +
                "&addr_poi_merge=true" +
                "&is_classify=true" +
                "&zoom=12.05" +
                "&longitude=" + longitude +
                "&latitude=" + latitude +
                "&range=" + range +
                "&city=" + city +
                "&keywords=" + keywords;

        try {
            log.info("请求链接：" + url);
            String json = httpClientPartner.get(url);
            if (json != null) {
                int i = analysis(json);
                if (i == 30)
                    request(++page);
            } else {
                log.error("返回Null...");
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    //人均消费
    Pattern pccPattern = Pattern.compile("<font color='#90969a'>人均:</font><font color='#f84b57'>￥</font><font color='#f84b57'>[0-9]{0,8}</font>");
    //特色菜
    Pattern cPattern = Pattern.compile("<font color='#666666'>.*</font>");
    //菜类
    Pattern typePattern = Pattern.compile("<font color='#999999'>.*</font>");


    public int analysis(String json){
        if (json != null && !"".equals(json) && json.contains("poi_list")) {
            JSONObject data = (JSONObject) JSONObject.parse(json);
            JSONArray poiList = data.getJSONObject("data").getJSONArray("poi_list");
            if (poiList != null) {
                for (Object object : poiList) {
                    JSONObject poi = (JSONObject) object;
                    if (poi != null) {
                        AmapShop as = new AmapShop();
                        as.setRating(poi.getDouble("rating"));
                        as.setTel(poi.getString("tel"));
                        as.setAreacode(poi.getString("areacode"));
                        as.setCityname(poi.getString("cityname"));
                        as.setAddress(poi.getString("address"));
                        as.setName(poi.getString("name"));
                        as.setLongitude(poi.getDouble("longitude"));
                        as.setLatitude(poi.getDouble("latitude"));
                        as.setaId(poi.getString("id"));

                        String poiJson = poi.toString();

                        Matcher pccMatcher = pccPattern.matcher(poiJson);
                        if (pccMatcher.find()) {
                            String pcc = pccMatcher.group().replaceAll("<font color='#90969a'>人均:</font><font color='#f84b57'>￥</font><font color='#f84b57'>|</font>", "");
                            as.setPerCapitaConsumption(Double.valueOf(pcc));
                        }

                        Matcher cMatcher = cPattern.matcher(poiJson);
                        if (cMatcher.find()) {
                            String c = cMatcher.group().replaceAll("<font color='#666666'>", "");
                            as.setCharacteristic(c.substring(0, c.indexOf("</font>")));
                        }

                        Matcher typeMatcher = typePattern.matcher(poiJson);
                        if (typeMatcher.find()) {
                            String type = typeMatcher.group().replaceAll("<font color='#999999'>", "");
                            as.setType(type.substring(0, type.indexOf("</font>")));
                        }

                        try {
                            amapShopMapper.insert(as);
                        }catch (Exception e){
                            log.error(e);
                        }
                    }
                }
            }
            return poiList.size();
        }
        return 0;
    }
}
