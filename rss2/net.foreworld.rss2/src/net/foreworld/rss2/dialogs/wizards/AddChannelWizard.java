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
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 7, 2008 10:02:34 PM
 */
public class AddChannelWizard extends Wizard {
	private AddChannelWizardPage1 page1;
	private AddChannelWizardPage2 page2;
	private String xmlName;

	public AddChannelWizard(){
		this.setWindowTitle("���Ƶ��");
		this.page1 = new AddChannelWizardPage1(this.getWindowTitle());
		this.page2 = new AddChannelWizardPage2(this.getWindowTitle());
		this.addPage(this.page1);
		this.addPage(this.page2);
	}

	//������
	public boolean performFinish() {
		//�ж�
		if(StringUtils.getURLString(this.page1.getXmlUrl_Text().getText().trim()).equals("")){
			this.page1.setErrorMessage("������Ч��URL��ַ��");
			return false;
		}
		else{
			this.page1.setErrorMessage(null);
		}
		//�ж������Ƿ�Ϊ��,���Ϊ�գ�˵����ǰҳΪ��һҳ�������Ϊ�գ���ֱ�ӽ��뱣�溯��
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
	 * ��һ���ȴ���rss�ļ����ɹ���ʧ��
	 * @return
	 */
	private boolean step1(){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    	//����xml���ļ��� step2��Ҫ�õ�
		this.xmlName = dateFormat.format(new Date()) + ".xml";
		
		//���Ƶ����
		this.page1.getNewChannel().getRootElement().element("channel").addAttribute("name", this.xmlName);
		
		//��Ӷ�δ������
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
	 * ��һ������ɹ�������rsschannel.xml�ļ�����ӽڵ�
	 * @return
	 */
	private boolean step2(){
    	//�����µ�Ƶ��
    	HashMap attributeMap = new HashMap();
    	attributeMap.put("id", this.xmlName);
    	attributeMap.put("title", this.page2.getChannelName_Text().getText().trim());
    	attributeMap.put("type", "rss");
    	attributeMap.put("saveCount", Integer.toString(this.page2.getSaveCount_Spinner().getSelection()));
    	attributeMap.put("xmlUrl", this.page1.getXmlUrl_Text().getText().trim());
    	
    	attributeMap.put("updateTime", Integer.toString(UpdateTime.getUpdateTime(this.page2.getUpdateTime_Combo().getSelectionIndex())));
    	attributeMap.put("itemCount", Integer.toString(this.page1.getNewChannel().getRootElement().element("channel").elements("item").size()));
    	attributeMap.put("unReadCount", attributeMap.get("itemCount"));
    	
    	
    	//��ȡ��ͼ
    	ChannelNavigatorView channelNavigatorView = (ChannelNavigatorView)MyRcpUtil.getView(ChannelNavigatorView.ID);
    	//��ȡѡ�е�Ԫ��
    	Object o = channelNavigatorView.getFirstElement();
    	Element outline = null;
    	if(o instanceof Element){//�ж϶����Ƿ���ת����Ԫ��
    		outline = (Element)o;
    		//���ѡ��Ԫ����Ƶ������
    		if(outline.attribute("type") != null){
    			outline = outline.getParent();
    		}
    	}
    	else{//˵��û��ѡ���κ�Ԫ�أ���ʱ��ȡbodyԪ��
    		outline = channelNavigatorView.getDoc().getRootElement().element("body");
    	}
    	//������Ԫ�ز�����
    	Element newOutline = RssXml2.addElement(outline, "outline", attributeMap);
    	//�����ĵ�
    	channelNavigatorView.saveXmlFile();
    	//ˢ��Ƶ����ͼ
    	channelNavigatorView.refreshAll();
    	//��λ��Ԫ��
    	channelNavigatorView.setSelection(newOutline);
    	
		return true;
	}

}
