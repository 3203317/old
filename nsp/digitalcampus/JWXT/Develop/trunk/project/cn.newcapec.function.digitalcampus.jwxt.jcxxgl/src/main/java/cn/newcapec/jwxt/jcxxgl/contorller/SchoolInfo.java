package cn.newcapec.jwxt.jcxxgl.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.newcapec.framework.core.handler.MultiViewResource;

@Controller
@RequestMapping(value="/schoolInfo")
public class SchoolInfo extends MultiViewResource {

	
	@RequestMapping(value="/index")
	public ModelAndView index(ModelMap modelMap){
		
		return super.toView("function/jwxt/jcxxgl/schoolInfo/schoolInfo", null);
	}
}
