package com.swiftpass.action;

import com.swiftpass.config.SwiftpassConfig;
import com.swiftpass.util.MD5;
import com.swiftpass.util.SignUtils;
import com.swiftpass.util.XmlUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

/**
 * <一句话功能简述>
 * <功能详细描述>订单查询
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TestQueryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        SortedMap<String,String> map = XmlUtils.getParameterMap(req);
        System.out.println(XmlUtils.toXml(map));
        map.put("mch_id", SwiftpassConfig.mch_id);
        String key = SwiftpassConfig.key;
        String res = null;
        String reqUrl = SwiftpassConfig.req_url;
        map.put("nonce_str", String.valueOf(new Date().getTime()));
        Map<String,String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + key, "utf-8");
        map.put("sign", sign);
        
        System.out.println("reqUrl:" + reqUrl);
        
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if(response != null && response.getEntity() != null){
                Map<String,String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                res = XmlUtils.toXml(resultMap);
                System.out.println("请求结果：" + res);
                
                if(resultMap.containsKey("sign") && !SignUtils.checkParam(resultMap, key)){
                    res = "验证签名不通过";
                }
            }else{
                res = "操作失败!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = "操作失败";
        } finally {
            if(response != null){
                response.close();
            }
            if(client != null){
                client.close();
            }
        }
        if(res.startsWith("<")){
            resp.setHeader("Content-type", "text/xml;charset=UTF-8");
        }else{
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
        }
        resp.getWriter().write(res);
    }
}
