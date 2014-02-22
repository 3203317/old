/**
 * 
 */
package net.newcapec.sca.report.classic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oasisopen.sca.annotation.Service;

/**
 * @author zzy
 *
 */
@Service(Servlet.class)
public class BusLinePriceReportDemo extends HttpServlet implements Servlet  {

     //据测试，request接受到的编码是utf-8格式，response缺省编码格式是ISO8859-1
    public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=utf-8");//不加这行，返回给客户端的内容是按照ISO8859-1格式编码
        //request.setCharacterEncoding("utf-8");//加这行代码并不能改变request已经接受到的http请求的数据格式
        PrintWriter out = response.getWriter();
        ManipulateXMLFile mx = new ManipulateXMLFile();

        List<List> list = mx.initBaseDemoInfo();

        org.dom4j.Document doc = mx.manipulateListInfo2Report(list);

        out.print(doc.asXML());
    }

   
    public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException
    {
        doPost(request, response);
    }   

}
