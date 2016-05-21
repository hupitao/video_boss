package com.swiftpass.action;

import com.swiftpass.config.SwiftpassConfig;
import com.swiftpass.util.SignUtils;
import com.swiftpass.util.XmlUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>通知
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TestPayResultSerlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
            String resString = XmlUtils.parseRequst(req);
            System.out.println("通知内容：" + resString);
            
            String respString = "fail";
            if(resString != null && !"".equals(resString)){
                Map<String,String> map = XmlUtils.toMap(resString.getBytes(), "utf-8");
                String res = XmlUtils.toXml(map);
                System.out.println("通知内容：" + res);
                if(map.containsKey("sign")){
                    if(!SignUtils.checkParam(map, SwiftpassConfig.key)){
                        res = "验证签名不通过";
                        respString = "fail";
                    }else{
                        String status = map.get("status");
                        if(status != null && "0".equals(status)){
                            String result_code = map.get("result_code");
                            if(result_code != null && "0".equals(result_code)){
                                if(TestPayServlet.orderResult == null){
                                    TestPayServlet.orderResult = new HashMap<String,String>();
                                }
                                String out_trade_no = map.get("out_trade_no");
                                TestPayServlet.orderResult.put(out_trade_no, "1");
                                //System.out.println(TestPayServlet.orderResult);
                            } 
                        } 
                        respString = "success";
                    }
                }
            }
            resp.getWriter().write(respString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
