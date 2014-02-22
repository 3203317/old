
package net.newcapec.sca.user.servlet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;
import net.newcapec.sca.user.UserBaseInfo;
import net.newcapec.sca.user.UserPhoto;
import net.newcapec.sca.user.das.UserBaseInfoDAS;
import net.newcapec.sca.user.das.UserPhotoDAS;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Service;


@Service(Servlet.class)
public class UploadUserPhotoServlet extends HttpServlet implements Servlet {
    
    private static Logger UploadUserPhotoLog = Logger.getLogger(UploadUserPhotoServlet.class);
    private static final long serialVersionUID = 8240260033930060726L;
    private int maxPostSize = 100 * 1024 * 1024; //设置内存缓冲大小
    private int maxMemHoldSize = 1024 * 1024; // 定义文件的上传路径
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
    
    private UserBaseInfoDAS userBaseInfoDAS;
    @Reference(name = "userBaseInfoDAS", required = true)
    public void setUserBaseInfoDAS(UserBaseInfoDAS userBaseInfoDAS) {
        this.userBaseInfoDAS = userBaseInfoDAS;
    }
    private SequenceService sequenceService;
    @Reference(name = "sequenceService", required = true)
    public void setSequenceService(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }
    
  //据测试，request接受到的编码是utf-8格式，response缺省编码格式是ISO8859-1
    public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");//不加这行，返回给客户端的内容是按照ISO8859-1格式编码
        //request.setCharacterEncoding("utf-8");//加这行代码并不能改变request已经接受到的http请求的数据格式
        PrintWriter out = response.getWriter();
        Session session = sessionService.getSession(request.getParameter("sessionId"));
        UploadUserPhotoLog.debug(request.getParameter("sessionId"));
        if(!this.isSessionVaild(session))
        {
           UploadUserPhotoLog.debug("session 过期");
           throw new NullPointerException("-500");
        }
        UploadUserPhotoLog.debug("........................开始上传文件..............................");
        try
        {
            //上传文件的路径
            String uploadDir = "E:"+File.separator+"filetemp"+File.separator;
           
            File fUploadDir = new File(uploadDir);
            if(!fUploadDir.exists())
            {
                fUploadDir.mkdir();//确保这个目录的确存在
            }
            if (!ServletFileUpload.isMultipartContent(request))
            {
                out.println("只能处理multipart/form-data类型的数据!");
                return ;
            }

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(this.maxMemHoldSize);//设置缓冲上传文件内存的大小
            factory.setRepository(fUploadDir);//设置上传文件大小超过内存缓冲时的缓存目录
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(this.maxPostSize);//设置上传文件大小上限
            upload.setHeaderEncoding("utf-8");
            List<FileItem> items = upload.parseRequest(request);//获取form中的元素组在服务端的映射
            UploadUserPhotoLog.debug(items.size());
            UserPhoto userPhoto = new UserPhoto();
            
            getFileNameByFirstFileItem((FileItem)items.get(0), userPhoto);
            
            UploadUserPhotoLog.debug("--------------第"+ 2 +"个内容  处理上传内容--------------");
            FileItem fileItem = (FileItem)items.get(1);   
            String fieldName = fileItem.getFieldName();
            //String fileName = new String(fileItem.getName().getBytes(), "utf-8");//因为这是上传文件的在客户端的完整绝对路径
            String fileName = fileItem.getName();
            UploadUserPhotoLog.debug("--fileName:"+ fileName);
            String[] splits = fileName.split("\\\\");//所以用\作为分隔符将路径拆分
            fileName = splits[splits.length - 1];//拆分出的最后一个即是上传文件自己的文件名
            UploadUserPhotoLog.debug("上传文件为:"+ fileName);
            String contentType = fileItem.getContentType();
            boolean isInMemory = fileItem.isInMemory();
            long sizeInByte = fileItem.getSize();
            if(sizeInByte < 1) 
                return;//说明这个文件元素里没内容，那就继续处理下一项
            Image srcImage = javax.imageio.ImageIO.read(fileItem.getInputStream());
            BufferedImage manipulateImageTools = new BufferedImage(240, 240,
                    BufferedImage.TYPE_INT_RGB);
            manipulateImageTools.getGraphics().drawImage(srcImage, 0, 0, 240, 240, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            javax.imageio.ImageIO.write(manipulateImageTools,"jpg",baos);
            String realFile = fUploadDir + "\\" + fileName;//上传文件在服务端的绝对路径
            
            userPhoto.setCreate_user_account_id(session.getUser_code());
            String photoCode = this.sequenceService.getNextValue("getMaxUserPhotoID").toString();
            userPhoto.setCode(photoCode);
            userPhoto.setPhoto_file_path(realFile);
            userPhoto.setPhoto_no(photoCode);
            userPhoto.setUser_unit_code(session.getUnit_code());
            userPhoto.setVer("1");
            UploadUserPhotoLog.debug("上传照片大小  ："+baos.toByteArray().length);
            userPhoto.setPhoto(baos.toByteArray());
            userPhotoDAS.delUserPhotoByCust_code(userPhoto.getCust_code());
            userPhotoDAS.insertUserPhoto(userPhoto);
            
            UploadUserPhotoLog.debug(" fieldName:"+ fieldName);
            UploadUserPhotoLog.debug(" contentType:"+ contentType);
            UploadUserPhotoLog.debug(" isInMemory:"+ isInMemory);
            UploadUserPhotoLog.debug(" sizeInByte:"+ sizeInByte); 
           
            File uploadedFile = new File(realFile);//创建此文件对象
            fileItem.write(uploadedFile);//将文件内容从fileItem缓冲区写到最终文件中
            UploadUserPhotoLog.debug("文件:"+ realFile +"已经创建成功");
            out.println("文件"+ uploadedFile +"已经成功上传");
            UploadUserPhotoLog.debug("........................结束上传文件..............................");
        }
        catch(Exception ex)
        {
            UploadUserPhotoLog.debug(ex.getMessage(),ex);
            out.println("异常"+ ex.toString());
        }
    }

    private void getFileNameByFirstFileItem(FileItem item,
            UserPhoto userPhoto) throws UnsupportedEncodingException {
        UploadUserPhotoLog.debug("--------------  处理文件名--------------");
        FileItem filedescItem = item;
        String name = filedescItem.getFieldName();
        String value = new String(filedescItem.get(), "utf-8");//将文件名转换为字节码后用utf-8重新识别
        UploadUserPhotoLog.debug("普通元素 name:"+ name +" value:"+ value);
        String user_code = value.split(".jpg")[0]; 
        userPhoto.setCust_code(user_code);
        UploadUserPhotoLog.debug("user code :"+ user_code);
        UserBaseInfo userBaseInfo = this.userBaseInfoDAS.getUserBaseInfoByCode(userPhoto.getCust_code());
        if(null == userBaseInfo)
        {
           UploadUserPhotoLog.debug("无对应的用户");
           throw new NullPointerException("-490");
        }
        userPhoto.setAccount_id(userBaseInfo.getAccount_id());
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
