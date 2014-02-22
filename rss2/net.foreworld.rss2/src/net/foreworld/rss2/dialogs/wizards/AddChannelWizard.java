package net.foreworld.rss2.dialogs.wizards;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import net.foreworld.rss2.db.RssXml2;
import net.foreworld.rss2.db.UpdateTime;
import net.foreworld.rss2.utils.MyRcpUtil;
import net.foreworld.rss2.utils.StringUtils;
import net.foreworld.rss2.views.ChannelNavigatorView;

import org.dom4j.Element;
import org.eclipse.jface.wizard.Wizard;

/**
 * 
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 7, 2008 10:02:34 PM
 */
public class AddChannelWizard extends Wizard {
	private AddChannelWizardPage1 page1;
	private AddChannelWizardPage2 page2;
	private String xmlName;

	public AddChannelWizard(){
		this.setWindowTitle("添加频道");
		this.page1 = new AddChannelWizardPage1(this.getWindowTitle());
		this.page2 = new AddChannelWizardPage2(this.getWindowTitle());
		this.addPage(this.page1);
		this.addPage(this.page2);
	}

	//点击完成
	public boolean performFinish() {
		//判断
		if(StringUtils.getURLString(this.page1.getXmlUrl_Text().getText().trim()).equals("")){
			this.page1.setErrorMessage("不是有效的URL地址！");
			return false;
		}
		else{
			this.page1.setErrorMessage(null);
		}
		//判断数据是否为空,如果为空，说明当前页为第一页，如果不为空，则直接进入保存函数
		if(this.page1.getNewChannel() == null){
			return false;
		}
		if(!this.step1()){
			return false;
		}
		this.step2();
		return true;
	}
	
	/**
	 * 第一步先创建rss文件，成功和失败
	 * @return
	 */
	private boolean step1(){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    	//生成xml的文件名 step2需要用到
		this.xmlName = dateFormat.format(new Date()) + ".xml";
		
		//添加频道名
		this.page1.getNewChannel().getRootElement().element("channel").addAttribute("name", this.xmlName);
		
		//添加读未读属性
		Iterator it = this.page1.getNewChannel().selectNodes("//item").iterator();
		while(it.hasNext()){
			Element element = (Element)it.next();
			element.addAttribute("read", "false");
		}
		
//		return RssXml.createFile(this.xmlName, this.page1.getNewChannel());
		RssXml2.createXmlFile(this.page1.getNewChannel(), this.xmlName);
		return true;
	}
	
	/**
	 * 第一步如果成功，则在rsschannel.xml文件中添加节点
	 * @return
	 */
	private boolean step2(){
    	//创建新的频道
    	HashMap attributeMap = new HashMap();
    	attributeMap.put("id", this.xmlName);
    	attributeMap.put("title", this.page2.getChannelName_Text().getText().trim());
    	attributeMap.put("type", "rss");
    	attributeMap.put("saveCount", Integer.toString(this.page2.getSaveCount_Spinner().getSelection()));
    	attributeMap.put("xmlUrl", this.page1.getXmlUrl_Text().getText().trim());
    	
    	attributeMap.put("updateTime", Integer.toString(UpdateTime.getUpdateTime(this.page2.getUpdateTime_Combo().getSelectionIndex())));
    	attributeMap.put("itemCount", Integer.toString(this.page1.getNewChannel().getRootElement().element("channel").elements("item").size()));
    	attributeMap.put("unReadCount", attributeMap.get("itemCount"));
    	
    	
    	//获取视图
    	ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
    	//获取选中的元素
    	Object o = channelNavigatorView.getFirstElement();
    	Element outline = null;
    	if(o instanceof Element){//判断对象是否能转换成元素
    		outline = (Element)o;
    		//如果选中元素是频道，则
    		if(outline.attribute("type") != null){
    			outline = outline.getParent();
    		}
    	}
    	else{//说明没有选中任何元素，此时获取body元素
    		outline = channelNavigatorView.getDoc().getRootElement().element("body");
    	}
    	//创建新元素并返回
    	Element newOutline = RssXml2.addElement(outline, "outline", attributeMap);
    	//保存文档
    	channelNavigatorView.saveXmlFile();
    	//刷新频道视图
    	channelNavigatorView.refreshAll();
    	//定位新元素
    	channelNavigatorView.setSelection(newOutline);
    	
		return true;
	}

}
