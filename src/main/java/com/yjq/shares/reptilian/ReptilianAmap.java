package com.yjq.shares.reptilian;

import com.yjq.shares.util.HttpClientPartner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 爬取高德地图该点附近的店铺等等信息
 */
public class ReptilianAmap {
    Logger log = LogManager.getLogger(ReptilianAmap.class);

    private double longitude;
    private double latitude;
    private int range;
    private int city;
    private String keywords;

    private HttpClientPartner httpClientPartner = new HttpClientPartner();

    public static void main(String[] args) {
        new ReptilianAmap(106.149845,38.492309,5000,640100,"美食");
    }

    public ReptilianAmap(double longitude, double latitude, int range, int city, String keywords) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.range = range;
        this.city = city;
        this.keywords = httpClientPartner.gbEncoding(keywords);

        request(1);

        httpClientPartner.close();
    }

    public void request(int page){
        String url = "https://www.amap.com/service/poiInfo?" +
                "query_type=RQBXY" +
                "&pagesize=30" +
                "&pagenum=" +page+
                "&qii=true" +
                "&cluster_state=5" +
                "&need_utd=true" +
                "&utd_sceneid=1000" +
                "&div=PC1000" +
                "&addr_poi_merge=true" +
                "&is_classify=true" +
                "&zoom=12.05" +
                "&longitude=" +longitude+
                "&latitude=" +latitude+
                "&range=" +range+
                "&city=" +city+
                "&keywords="+keywords;

        try {
            log.info("请求链接："+url);
            String json = httpClientPartner.get(url);
            if (json!=null){
                analysis(json);
//                request(++page);
            }else{
                log.error("返回Null...");
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void analysis(String json){
        log.info(json);
    }
}
