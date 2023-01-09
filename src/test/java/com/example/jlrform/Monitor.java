package com.example.jlrform;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.USER_AGENT;


public class Monitor {
    public static void main(String[] args) throws Exception {


        String rawData = "[{\"cdsid\":\"WRAN\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"YWU12\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"JCHEN8\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"LZHENG\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"TWAN\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"KLU\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"ZZHENG3\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"QZHOU1\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"XCAO8\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"JYU2\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"ZZHANG6\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"SCHEN8\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"XWANG17\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"YZHOU16\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"SZHU2\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"FDI\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"WCHEN5\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"LFANG1\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"WWANG12\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"BWANG19\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"ZZHONG3\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"XHU2\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"XSONG1\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"XXU11\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"GCHU1\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"XGU3\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"CYAO1\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"ZDUAN3\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"PWANG4\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"YXU19\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"WDONG1\",\"answer\":\"1,2,3,4,1,2,3\"},\n" +
                "{\"cdsid\":\"XCAI3\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JZHOU8\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"SXU15\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"WSHU\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"KTHOMERS\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"SXIANG3\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"HQU2\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"TYING\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"SATKIN18\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"GWILSO26\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"WKANG1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"HWANG25\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"XDENG1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"WHUANG6\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"LGU6\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"XHUAN\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YWANG78\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YYANG54\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"BSIEGLER\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZMOK\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JZHAO25\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YSONG16\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"DROSS56\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"WOUYANG1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YZHAO40\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZLIU32\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"BXING1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"WWEI3\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"LZHANG56\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JWEN5\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YYANG57\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YLIANG3\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZPAN2\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YWANG88\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YJING4\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"TZHENG1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"NLIN3\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JYING1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"SCAO2\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"LLIU12\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"SLI38\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ALI9\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"SZHANG28\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"DOU\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"TTAO4\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YWEI4\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JFEI3\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"HQIU1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YZHAO42\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"THUANG8\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JXIAO5\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"MYU2\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZFENG5\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"SZHAO13\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JHUANG9\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZYANG19\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"NLIU5\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YZHAO43\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"RWANG13\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JSUN19\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"PXU3\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZZHU12\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YLI82\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JLIU25\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JZHOU13\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YJIANG27\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YZHAN140\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"HSHENG2\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YZHAN141\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JZHANG46\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"HMAO4\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"BZHOU5\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"LWANG37\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YMENG3\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YLIU97\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JHE3\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"WSHI8\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"WYE1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"XZHANG52\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZXIA2\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"LYIN5\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZCAI1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"SREN\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZXIA3\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JHE4\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JSUN20\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YWU41\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"QYOU1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JWANG52\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"CMAO2\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JXIONG\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZGUO8\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YZHOU49\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"HDU1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"QZHOU7\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JSHEN19\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"BFAN3\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JHUANG12\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"BSUN4\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JZHU39\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JMA9\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"CLU5\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YQIAN7\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"LSHA1\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YQIN8\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"PXIN\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"MXU12\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"HMAO5\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ELI2\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"PHUANG\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"QLI14\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"LYU10\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"XHUO\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"QLI15\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"LZHANG75\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JZHA\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"DLIU9\",\"answer\":\"1,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"BXU8\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JWANG60\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"SYIN2\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"LCHEN47\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YLI86\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZWANG35\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"TBAI1\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"BWU6\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"RJIA\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"WWU7\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YXUE1\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YLIU101\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"XLI42\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"YYU18\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"TZHANG26\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JCHEN39\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"SWANG37\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"LLI29\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"XCHEN59\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ZWU8\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"JFU6\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"TSHI1\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"HZHANG60\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"SZOU1\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"pgiantag\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"serbeyl1\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"wli34\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"hgao3\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"ydu8\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"qdi\",\"answer\":\"3,3,3,3,3,3,3\"},\n" +
                "{\"cdsid\":\"lzhao9\",\"answer\":\"3,3,3,3,3,3,3\"}]";
        List<Map> hashMaps = JSONObject.parseArray(rawData, Map.class);

        for (int i = 0; i < hashMaps.size(); i++) {

            String url = "http://localhost:8080/peoplesrunning?cdsid={{cdsid}}&answer={{answer}}";

//            String url = "http://69.230.253.37:8080/peoplesrunning?cdsid={{cdsid}}&answer={{answer}}";
            System.out.println(hashMaps.get(i).get("cdsid"));
            System.out.println(hashMaps.get(i).get("answer"));
            url = url.replace("{{cdsid}}", (String) hashMaps.get(i).get("cdsid"));
            url = url.replace("{{answer}}", (String) hashMaps.get(i).get("answer"));
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //默认值我GET
            con.setRequestMethod("GET");

            //添加请求头
            con.setRequestProperty("User-Agent", USER_AGENT);


            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //打印结果
            System.out.println(response.toString());

            if (i % 10 == 0){
                Thread.sleep(3000);
            }
        }

    }
}
