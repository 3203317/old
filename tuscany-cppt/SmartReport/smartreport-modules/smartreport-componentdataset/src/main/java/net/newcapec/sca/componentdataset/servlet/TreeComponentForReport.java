/**
 *
 */
package net.newcapec.sca.componentdataset.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.newcapec.sca.componentdataset.das.TreeItemForReportDAS;
import net.newcapec.sca.session.service.SessionService;

import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Service;

/**
 * @author Administrator
 *
 */
@Service(Servlet.class)
public class TreeComponentForReport extends HttpServlet implements Servlet{

    private TreeItemForReportDAS treeItemForReportDAS;

    @Reference(name = "treeItemForReportDAS", required = true)
    public void setTreeItemForReportDAS(TreeItemForReportDAS treeItemForReportDAS) {
        this.treeItemForReportDAS = treeItemForReportDAS;
    }
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
         doPost(request, response);
     }

     public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

         response.setCharacterEncoding("utf-8");
         String dataSource_code = request.getParameter("datasource_code");//数据源id
         String treeId = request.getParameter("treeKey");//树形控件节点唯一标识
         String treeLabel = request.getParameter("treeLable");//树形控件节点名称
         System.out.println("treeLable       ==========="+treeLabel);
         String treeIdValue = request.getParameter(treeId);//节点id值
         System.out.println("treeIdValue         "+treeIdValue);
         String treeNodeMark = request.getParameter("treeNodeName");//root、children，区分是根或子节点
         String super_code = request.getParameter("super_code");//父级节点
         System.out.println("super_code ================== " +super_code);
         String parentInfo = request.getParameter("parentInfo");//父级节点名称
         String rtnStr = treeItemForReportDAS.getTreeItemsByComponentDSCode1(Integer.valueOf(dataSource_code),treeId,treeLabel, super_code, treeIdValue);
         if("root".equals(treeNodeMark)){
        	 rtnStr = "{'"+treeLabel+"': '"+parentInfo+"','"+treeId+"': 'root','children': [" +rtnStr+"]}";
         }else if("children".equals(treeNodeMark)){
        	 rtnStr = "{'children': [" +rtnStr+"]}";
         }
         PrintWriter out= response.getWriter();
         out.print(rtnStr);
         out.flush();
         out.close();
     }
}
