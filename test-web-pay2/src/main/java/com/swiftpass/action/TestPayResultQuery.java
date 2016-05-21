package com.swiftpass.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <一句话功能简述>
 * <功能详细描述>本地查询订单支付状态
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TestPayResultQuery extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //System.out.println("订单查询");
        String out_trade_no = req.getParameter("out_trade_no");
        
        String res = null;//0表示未支付，1表示已支付
        
        if(TestPayServlet.orderResult != null && TestPayServlet.orderResult.containsKey(out_trade_no)){
        	
            res = TestPayServlet.orderResult.get(out_trade_no);
            
        }else{
            res = "0";
        }
        if(res.startsWith("<")){
            resp.setHeader("Content-type", "text/xml;charset=UTF-8");
        }else{
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
        }
        resp.getWriter().print(res);
    }
}
