
package net.newcapec.sca.user.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;
import net.newcapec.sca.user.UserPhoto;
import net.newcapec.sca.user.das.UserPhotoDAS;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Service;


@Service(Servlet.class)
public class ShowP_UserPhotoServlet extends HttpServlet implements Servlet {
    private static final long serialVersionUID = 3365029111478717864L;
    private static Logger showP_UserPhotoServletLog = Logger.getLogger(ShowP_UserPhotoServlet.class);
    private SessionService sessionService;
    @Reference(name = "sessionService", required = true)
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    private UserPhotoDAS userPhotoDAS;
    @Reference(name = "userPhotoDAS", required = true)
    public void setUserPhotoDAS(UserPhotoDAS userPhotoDAS) {
        this.userPhotoDAS = userPhotoDAS;
    }
    
  //据测试，request接受到的编码是utf-8格式，response缺省编码格式是ISO8859-1
    public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException
    {
         String sessionId = request.getParameter("sessionId");
         
         Session session = sessionService.getSession(sessionId);
         if(!isSessionVaild(session))
         {
             showP_UserPhotoServletLog.debug("无效session");
             throw new NullPointerException("无效session");
         }
         String user_code = request.getParameter("userPhoto_Id");
         System.out.println(user_code);
         UserPhoto userPhoto = new UserPhoto();
         userPhoto = this.userPhotoDAS.getUserPhotoById(Integer.valueOf(user_code));
         byte data[] = userPhoto.getPhoto();
         response.setContentType("image/jpg"); //设置返回的文件类型   
         OutputStream os = response.getOutputStream();  
         os.write(data);  
         os.flush();  
         os.close();    
    }

   
    public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException
    {
        doPost(request, response);
    }
    protected boolean isSessionVaild(Session session)
    {
        boolean sessionVaild = true;

        try
        {
            if (null == session.getId() && session.getState() == 0)
            {
                sessionVaild = false;
            }
        }
        catch (Exception e)
        {
            sessionVaild = false;
        }
        return sessionVaild;
    }
}
