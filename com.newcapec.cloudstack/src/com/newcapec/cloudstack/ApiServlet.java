package com.newcapec.cloudstack;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 4037195439774426795L;

	public ApiServlet() {
	}

	public void init() {
		System.out.println(12121);

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");

		String __command = req.getParameter("command");
		

		PrintWriter out;

		out = resp.getWriter();
		// out.print("<HTML><HEAD><TITLE>12312312</TITLE>");
		// out.print("</HEAD><BODY>");
		// out.println("<h1><p>sdfsdf sd:您好</h1>");
		// out.println("<br/>");
		// out.println("command: " + __command);
		// out.print("</BODY></HTML>");

		String temp = "";

		if ("login".equals(__command)) {
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?command=login&username=admin&password=888888&response=json", new HashMap(), "UTF-8");
		}else if("listCapabilities".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listCapabilities&response=json&signature=GibuecSnIJbg2tFRNhCOHsZ8aY4%3D", new HashMap(), "UTF-8");
		}else if("listSwifts".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listSwifts&response=json&signature=dbflYfdMM1PtyVkzNrmfaArxiUg%3D", new HashMap(), "UTF-8");
		}else if("listZones".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listZones&response=json&signature=f85uKosz5ZWRbi%2FkOCzphcfi4P0%3D", new HashMap(), "UTF-8");
		}else if("listProjectInvitations".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listProjectInvitations&response=json&signature=%2FFarTUS9FVaj%2FOJWAOtqxrHelCo%3D", new HashMap(), "UTF-8");
		}else if("listCapacity".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listCapacity&page=0&pagesize=8&response=json&sortBy=usage&signature=mRaoRI5eZudPGN1ETFgmSLwwrgo%3D", new HashMap(), "UTF-8");
		}else if("listAlerts".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listAlerts&response=json&page=1&pageSize=4&signature=UfZBrFoY6lE22MEhLmD2PEXQPbk%3D", new HashMap(), "UTF-8");
		}else if("listHosts".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listHosts&response=json&page=1&pageSize=4&state=Alert&signature=g71XTvq5xJwAkH%2BhGJTF%2FqOZlX4%3D", new HashMap(), "UTF-8");
		}else if("listVirtualMachines".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listVirtualMachines&response=json&signature=80ZVJPrubtcfBjGJpnnoWprZixc%3D", new HashMap(), "UTF-8");
		}else if("listEvents".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listEvents&listAll=true&page=1&pageSize=4&response=json&signature=fzF0OKUODOrZWa%2FGcK6Ygpjf0WQ%3D", new HashMap(), "UTF-8");
		}else if("listNetworks".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listNetworks&listAll=true&response=json&supportedServices=SourceNat&type=isolated&signature=752cYXyp0IZtdrY7vs%2Bcd7M%2FQUQ%3D", new HashMap(), "UTF-8");
		}else if("listPublicIpAddresses".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listPublicIpAddresses&response=json&signature=4z%2BkLjDUE%2FBTmdOnuDUN97RnyQ8%3D", new HashMap(), "UTF-8");
		}else if("listAccounts".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listAccounts&listAll=true&response=json&signature=e2iGc5T4qfYAMq9k3ji0GPAOeuY%3D", new HashMap(), "UTF-8");
		}else if("listDomains".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listDomains&listAll=true&response=json&signature=oRqydVnfLQnqCMfN%2BN%2Bh8fLbpuU%3D", new HashMap(), "UTF-8");
		}else if("createAccount".equals(__command)){
			
			System.out.println("post");

			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");

			String domainid = req.getParameter("domainid");
			String account = req.getParameter("account");
			String accounttype = req.getParameter("accounttype");
			String timezone = req.getParameter("timezone");
			String networkdomain = req.getParameter("networkdomain");
			
			String dd = 
						"account="+account+"&accounttype="+accounttype+"&apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag" +
							"&command=createAccount" +
//							"&domainid="+domainid+"&" +
							"&email="+email+
							"&firstname="+firstname+
							"&lastname="+lastname+
//							"&networkdomain="+networkdomain+
							"&password="+password+
//							"&response=json&" +
//							"timezone="+timezone+
							"&username="+username;
			
			//System.out.println(dd);
			
			String data = dd;
			String key = "KFD85H9SmyZd8FSopX_CxxG5VgLFW71LiYc35PxZWXABX9BsANvPUQpLBCrPz25JpSy2_bt2Z0gWRCA6ePsKww";   
			
			String rr = standard(data,key);
			
			System.out.println("http://192.168.102.21:8080/client/api?"+data+"&signature="+rr);
			
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?"+data+"&signature="+rr, new HashMap(), "UTF-8");
			
			temp = "{'m':'http://192.168.102.21:8080/client/api?"+data+"&signature="+rr+"'";
		}else if("createDomain".equals(__command)){
			String name = req.getParameter("name");
			
			String data  = "apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&" +
  			"command=createDomain&" +
   			"name="+ name;
			
			String key = "KFD85H9SmyZd8FSopX_CxxG5VgLFW71LiYc35PxZWXABX9BsANvPUQpLBCrPz25JpSy2_bt2Z0gWRCA6ePsKww";   
			

			String rr = standard(data,key);
			
			
			System.out.println("http://192.168.102.21:8080/client/api?"+data+"&signature="+rr);
			
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?"+data+"&signature="+rr, new HashMap(), "UTF-8");
			
			//temp = "{'m':'http://192.168.102.21:8080/client/api?"+data+"&signature="+rr+"'";
		}else if("deleteDomain".equals(__command)){
			String id = req.getParameter("id");
			
			String data  = "apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&" +
  			"command=deleteDomain&" +
   			"id="+ id;
			
			String key = "KFD85H9SmyZd8FSopX_CxxG5VgLFW71LiYc35PxZWXABX9BsANvPUQpLBCrPz25JpSy2_bt2Z0gWRCA6ePsKww";   
			

			String rr = standard(data,key);
			
			
			System.out.println("http://192.168.102.21:8080/client/api?"+data+"&signature="+rr);
			
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?"+data+"&signature="+rr, new HashMap(), "UTF-8");
			
			//temp = "{'m':'http://192.168.102.21:8080/client/api?"+data+"&signature="+rr+"'";
		}

		out.println(temp);

		out.close();

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");

		String __command = req.getParameter("command");

		PrintWriter out;

		out = resp.getWriter();
		// out.print("<HTML><HEAD><TITLE>12312312</TITLE>");
		// out.print("</HEAD><BODY>");
		// out.println("<h1><p>sdfsdf sd:您好</h1>");
		// out.println("<br/>");
		// out.println("command: " + __command);
		// out.print("</BODY></HTML>");

		String temp = "";

		if ("login".equals(__command)) {
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?command=login&username=admin&password=888888&response=json", new HashMap(), "UTF-8");
		}else if("listCapabilities".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listCapabilities&response=json&signature=GibuecSnIJbg2tFRNhCOHsZ8aY4%3D", new HashMap(), "UTF-8");
		}else if("listSwifts".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listSwifts&response=json&signature=dbflYfdMM1PtyVkzNrmfaArxiUg%3D", new HashMap(), "UTF-8");
		}else if("listZones".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listZones&response=json&signature=f85uKosz5ZWRbi%2FkOCzphcfi4P0%3D", new HashMap(), "UTF-8");
		}else if("listProjectInvitations".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listProjectInvitations&response=json&signature=%2FFarTUS9FVaj%2FOJWAOtqxrHelCo%3D", new HashMap(), "UTF-8");
		}else if("listCapacity".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listCapacity&page=0&pagesize=8&response=json&sortBy=usage&signature=mRaoRI5eZudPGN1ETFgmSLwwrgo%3D", new HashMap(), "UTF-8");
		}else if("listAlerts".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listAlerts&response=json&page=1&pageSize=4&signature=UfZBrFoY6lE22MEhLmD2PEXQPbk%3D", new HashMap(), "UTF-8");
		}else if("listHosts".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listHosts&response=json&page=1&pageSize=4&state=Alert&signature=g71XTvq5xJwAkH%2BhGJTF%2FqOZlX4%3D", new HashMap(), "UTF-8");
		}else if("listVirtualMachines".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listVirtualMachines&response=json&signature=80ZVJPrubtcfBjGJpnnoWprZixc%3D", new HashMap(), "UTF-8");
		}else if("listEvents".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listEvents&listAll=true&page=1&pageSize=4&response=json&signature=fzF0OKUODOrZWa%2FGcK6Ygpjf0WQ%3D", new HashMap(), "UTF-8");
		}else if("listNetworks".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listNetworks&listAll=true&response=json&supportedServices=SourceNat&type=isolated&signature=752cYXyp0IZtdrY7vs%2Bcd7M%2FQUQ%3D", new HashMap(), "UTF-8");
		}else if("listPublicIpAddresses".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listPublicIpAddresses&response=json&signature=4z%2BkLjDUE%2FBTmdOnuDUN97RnyQ8%3D", new HashMap(), "UTF-8");
		}else if("listAccounts".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listAccounts&listAll=true&response=json&signature=e2iGc5T4qfYAMq9k3ji0GPAOeuY%3D", new HashMap(), "UTF-8");
		}else if("listDomains".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=listDomains&listAll=true&response=json&signature=oRqydVnfLQnqCMfN%2BN%2Bh8fLbpuU%3D", new HashMap(), "UTF-8");
		}else if("createAccount".equals(__command)){
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=createAccount&response=json&signature=OA7BI8ul61Qc8FyFPPyAuPEmDco%3D", new HashMap(), "UTF-8");
		}else if("createAccount".equals(__command)){

			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");

			String domainid = req.getParameter("domainid");
			String account = req.getParameter("account");
			String accounttype = req.getParameter("accounttype");
			String timezone = req.getParameter("timezone");
			String networkdomain = req.getParameter("networkdomain");
			
			String dd = "account="+account+"&accounttype="+accounttype+"&apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=createAccount&domainid="+domainid+"&email="+email+"&firstname="+firstname+"&lastname="+lastname+"&networkdomain="+networkdomain+"&password="+password+"&response=json&timezone="+timezone+"&username="+username;
			
			//System.out.println(dd);
			
			String data = "" + dd;
			String key = "KFD85H9SmyZd8FSopX_CxxG5VgLFW71LiYc35PxZWXABX9BsANvPUQpLBCrPz25JpSy2_bt2Z0gWRCA6ePsKww";   
			
			String rr = standard(data,key);
			
			System.out.println("http://192.168.102.21:8080/client/api?"+data+"&signature="+rr);
			
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?"+data+"&signature="+rr, new HashMap(), "UTF-8");
		}else if("createDomain".equals(__command)){
			String name = req.getParameter("name");
			
			String data  = "apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&" +
  			"command=createDomain&" +
   			"name="+ name +
  			"response=json";
			
			String key = "KFD85H9SmyZd8FSopX_CxxG5VgLFW71LiYc35PxZWXABX9BsANvPUQpLBCrPz25JpSy2_bt2Z0gWRCA6ePsKww";   
			

			String rr = standard(data,key);
			
			
			System.out.println("http://192.168.102.21:8080/client/api?"+data+"&signature="+rr);
			
			temp = HttpRequestProxy.doPost("http://192.168.102.21:8080/client/api?"+data+"&signature="+rr, new HashMap(), "UTF-8");
		}

		out.println(temp);

		out.close();

	}

	/*
	 * private void createRole() { Date __date = new Date(); SimpleDateFormat
	 * __sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * 
	 * Object __sysmanage_role = BaseAction.getBean("sysmanage_role"); try {
	 * Ognl.setValue("fields", __sysmanage_role, "*"); Ognl.setValue("append",
	 * __sysmanage_role, "'" + __sdf.format(__date) + "' between allow_startdate
	 * and allow_enddate"); } catch (OgnlException $ex) { $ex.printStackTrace(); }
	 * 
	 * IBean __sysmanage_roleBean = (IBean)
	 * BaseAction.getBean("sysmanage_roleBean");
	 * 
	 * List __list = __sysmanage_roleBean.select(__sysmanage_role); }
	 */

	public void destroy() {
	}
	

    private static String standard(String data, String key) {   
        byte[] byteHMAC = null;   
        try {   
            Mac mac = Mac.getInstance("HmacSHA1");   
            SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");   
            mac.init(spec);   
            byteHMAC = mac.doFinal(data.toLowerCase().getBytes());   
        } catch (InvalidKeyException e) {   
            e.printStackTrace();   
        } catch (NoSuchAlgorithmException ignore) {   
        }   
        String oauth = new BASE64Encoder().encode(byteHMAC); 
        
        return java.net.URLEncoder.encode(oauth);
//        System.out.println("standard = "+oauth);   
//        
//        System.out.println("http://192.168.102.21:8080/client/api?apiKey=J4_EFO3ZlBZynJC7dACIFiivoCniAvJlLr-H_dIex-eAdyz1ykGgMtrvcJ7PBCrPKsJRuPaiRKdDuL5LTL_Jag&command=createAccount&response=json&signature="+java.net.URLEncoder.encode(oauth));
    }  
}
