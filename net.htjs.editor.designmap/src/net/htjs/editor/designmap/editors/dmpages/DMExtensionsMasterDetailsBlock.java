package net.htjs.editor.designmap.editors.dmpages;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.htjs.build.mapping.CacheDesigns;
import net.htjs.build.mapping.MapDesignPojo;
import net.htjs.build.mapping.MapDesignPojoProperty;
import net.htjs.editor.designmap.Activator;
import net.htjs.editor.designmap.DMXsd;
import net.htjs.editor.designmap.GMFConsole;
import net.htjs.editor.designmap.RssXml2;
import net.htjs.editor.designmap.editors.DMMutiForm;
import net.htjs.util.UtilBase;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

public class DMExtensionsMasterDetailsBlock extends MasterDetailsBlock
{

	public class ImportLabelProvider extends Action {
		public ImportLabelProvider(){
		      this.setText("Import LabelProvider");
		      this.setImageDescriptor(Activator.getImageDescriptor("icons/import.gif"));
		}
		public void run(){
			Object o = getFirstElement();
			if (o instanceof Element){
		        Element ele = (Element) o;
		        List list = ele.selectNodes("//labelProviders/labelProvider");
		        if(list != null){
		    		  for(int i=0;i<list.size();i++){
		    			  Element newele2 = (Element)list.get(i);
//		    			  GMFConsole.println("---------"+newele2);
		    			  
		    			  Element newele3 = newele2.addElement("input");
		    			  newele3.addAttribute("id", ele.attributeValue("name"));
		    		  }
		    		  treeViewer.refresh();
				        dmMutiForm.xmlEditorDataReload();
		    	  }
			}
		}

	}

	public class LabelProviderImportAction extends Action {
		public LabelProviderImportAction(){
	      this.setText("Import");
	      this.setImageDescriptor(Activator.getImageDescriptor("icons/import.gif"));
		}
		public void run(){
		  
		  
		  Object o = getFirstElement();
      if (o instanceof Element)
      {
        GMFConsole.println("--in---");
        Element ele = (Element) o;
        if(ele.elements().size()>0)
        {
          GMFConsole.println("-please delete data， after import");
          return ;
        }
        GMFConsole.println(o);
        String namespace = ele.getDocument().getRootElement().attributeValue("namespace");
        try
        {
          CacheDesigns.reload();
          CacheDesigns.setRootPath("");
          CacheDesigns.getMapDesigns();
          MapDesignPojo pojo = null;
          //List list = ele.getDocument().selectNodes("/designMap/pojos");
          Iterator it = CacheDesigns.getMapDesigns().getDesign(namespace).getPojos().getPojos();
          while(it.hasNext())
          {
            pojo = (MapDesignPojo)it.next();
            if(pojo.getIsDefault() != null && pojo.getIsDefault().equals("true"))
            {
              break;
            }
          }
          GMFConsole.println("----------------"+pojo);
          if(pojo != null)
          {
            GMFConsole.println("------3------------"+pojo);
            Iterator it1 = pojo.getProperties();
            while(it1.hasNext())
            {
              MapDesignPojoProperty mdpp = (MapDesignPojoProperty)it1.next();
              GMFConsole.println(mdpp.getName()+"-----");
              Element newele = ele.addElement("input");
              newele.addAttribute("id", mdpp.getColumn().toLowerCase());
              newele.addAttribute("javaName", mdpp.getColumn().toLowerCase());
              newele.addAttribute("javaType", mdpp.getType());
              newele.addAttribute("htmlName", mdpp.getName());
              newele.addAttribute("htmlType", "text");
              if(mdpp.getDescription()!=null)
              {
                newele.addAttribute("label", mdpp.getDescription());
              }
              else
              {
                newele.addAttribute("label", "");
              }
            }
            treeViewer.refresh(ele);
            treeViewer.setExpandedState(getFirstElement(), !treeViewer
                .getExpandedState(getFirstElement()));
            dmMutiForm.xmlEditorDataReload();
            GMFConsole.println("Import label success..");
          }
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
			GMFConsole.println(LabelProviderImportAction.class);
		}
	}

public class RefreshAction extends Action
  {
    public RefreshAction()
    {
      this.setText("Refresh");
      this.setImageDescriptor(Activator
          .getImageDescriptor("icons/nav_refresh.gif"));
    }

    public void run()
    {
      treeViewer.refresh(getFirstElement());
    }
  }

	/**
	 * 判断是否存在相同元素
	 * @param list
	 * @param propertyName
	 * @return
	 */
	private boolean checkSamePropertyExists(List list,String propertyName){
		if(list == null || list.size() == 0){
			return false;
		}
		for (Iterator it = list.iterator(); it.hasNext();){
			Element ele = (Element)it.next();
			if(ele.attributeValue("name").trim().equals(propertyName)){
				return true;
			}
		}
		return false;
	}

  public class ImportAction extends Action
  {
    public ImportAction()
    {
      this.setText("Import");
      this.setImageDescriptor(Activator.getImageDescriptor("icons/import.gif"));
    }

    public void run()
    {
      Object o = getFirstElement();
      if (o instanceof Element)
      {
        GMFConsole.println("ImportAction");
        Element ele = (Element) o;
        
        //子列表
        List childrenlist = ele.elements();
        
        if(ele.elements().size()>0)
        {
          GMFConsole.println("-please delete data,after import");
          return ;
        }
        if (ele.attribute("dataset") == null || ele.attributeValue("dataset").trim().equals(""))
        {
          GMFConsole.println("Dataset is null...");
          return;
        }
        try
        {
          CacheDesigns.reload();
          CacheDesigns.setRootPath("");
          CacheDesigns.getMapDesigns();
          List list = UtilModel.getTableColumns(ele.attributeValue("dataset").trim());
          if (list != null)
          {
            
            for (int i = 0; i < list.size(); i++)
            {
                Map map = (Map) list.get(i);
                String name = map.get("name").toString().toLowerCase().trim();
                /**
                 * 判断是否存在相同的属性
                 */
            	if(checkSamePropertyExists(childrenlist, name)) break;
            	
              Element newele = ele.addElement("property");
              /*
              if (name.indexOf("_") > 0)
              {
                name = name.substring(name.lastIndexOf("_") + 1);
              }*/
              newele.addAttribute("name", name.toLowerCase());
              String type = map.get("type").toString().trim();
              String type1 = null;
              if (type.equals("NUMBER"))
              {
                type = "Long";
                type1 = "DECIMAL";
              }
              else if (type.equals("DATE"))
              {
                type = "Date";
                type1 = "DATE";
              }
              else
              {
                type = "String";
                type1 = "VARCHAR";
              }
              Object def = map.get("defaultValue");
              newele.addAttribute("type", type);
              newele.addAttribute("column", map.get("name").toString().trim());
              newele.addAttribute("columnType", type1);
              String key = (String)map.get("isPk");
              if(key.equals("Y"))
              {
                newele.addAttribute("isPrimaryKey", "true");
              }
              newele.addAttribute("initValue", (def == null || ((String)def).equalsIgnoreCase("sysdate"))?"":def.toString().replaceAll("\"", ""));
              
              if(map.get("comments")!=null)
              {
                String des = UtilBase.dbToJava(map.get("comments").toString());
                newele.addAttribute("description", des);
              }
              else
              {
                newele.addAttribute("description", "");
              }
            }
            treeViewer.refresh(ele);
            treeViewer.setExpandedState(getFirstElement(), !treeViewer
                .getExpandedState(getFirstElement()));
            dmMutiForm.xmlEditorDataReload();
            GMFConsole.println("Import property success..");
          }
          else
          {
            GMFConsole.println("Table property is null..");
          }
        }
        catch (Exception e)
        {
          // TODO Auto-generated catch block
          GMFConsole.println(e);
        }
      }
    }
  }

  /**
   * 创建动作
   * 
   * @author hx
   */
  public class CreateAction extends Action
  {
    private Element element;
    private Element parent;

    /**
     * @param parent 父元素
     * @param element 欲添加的子元素
     * @param img
     */
    public CreateAction(Element parent, Element element, String img)
    {
      this.parent = parent;
      this.element = element;
      this.setText(this.element.attributeValue("name"));
      this.setImageDescriptor(Activator.getImageDescriptor(img));
    }

    public void run()
    {
      Element newele = null;
      //为labelprovider添加同样的属性
      if("pojo".equals(this.parent.getName().trim())){
    	  //GMFConsole.println("---------"+this.parent.getName());
    	  

//    	  List list = this.parent.selectNodes("//labelProviders/labelProvider");
//    	  if(list != null){
//    		  for(int i=0;i<list.size();i++){
//    			  Element newele2 = (Element)list.get(i);
//    			  GMFConsole.println("---------"+newele2);
//    		  }
//    	  }
      }
      newele = this.parent.addElement(this.element
    	          .attributeValue("name"));
      treeViewer.refresh(newele.getParent());
      treeViewer.setSelection(new StructuredSelection(newele), true);
      dmMutiForm.xmlEditorDataReload();
    }
  }

  /**
   * 删除动作
   * 
   * @author hx
   */
  public class DelAction extends Action
  {
    public DelAction()
    {
      this.setText("&Delete");
      this.setImageDescriptor(Activator
          .getImageDescriptor("icons/delete_obj.gif"));
    }

    public void run()
    {
      GMFConsole.println(treeViewer.getSelection().toString());
      
      StructuredSelection ss = ((StructuredSelection)treeViewer.getSelection());
      
      for (Iterator it = ss.iterator(); it.hasNext();){
          Object obj = it.next();
          if (obj instanceof Element)
          {
            Element objele = (Element) obj;
            // 父元素
            Element parentele = objele.getParent();
            
            if(parentele != null){
            	parentele.remove(objele);
	            treeViewer.refresh(parentele);
	            treeViewer.setSelection(new StructuredSelection(parentele), true);
	            dmMutiForm.xmlEditorDataReload();
            }
            GMFConsole.println("Delete element success...");
          }
      }
    }
  }

  public class TreeLabelProvider implements ILabelProvider, IFontProvider
  {

    public Image getImage(Object arg0)
    {
      if (arg0 instanceof Element)
      {
        Element ele = (Element) arg0;
        if (ele.getParent() == ele.getDocument().getRootElement())
          return Activator.getImageDescriptor("icons/elements.gif")
              .createImage();
        return Activator.getImageDescriptor("icons/element.gif").createImage();
      }
      return null;
    }

    public String getText(Object arg0)
    {
      if (arg0 instanceof Element)
      {
        Element ele = (Element) arg0;
        if (ele.attribute("id") == null)
        {
          if (ele.attribute("name") == null)
          {
            return ele.getName() + "()";
          }
          return ele.getName() + "(" + ele.attributeValue("name") + ")";
        }
        return ele.getName() + "(" + ele.attributeValue("id") + ")";
      }
      return null;
    }

    public void addListener(ILabelProviderListener arg0)
    {
      // TODO Auto-generated method stub

    }

    public void dispose()
    {
      // TODO Auto-generated method stub

    }

    public boolean isLabelProperty(Object arg0, String arg1)
    {
      // TODO Auto-generated method stub
      return false;
    }

    public void removeListener(ILabelProviderListener arg0)
    {
      // TODO Auto-generated method stub

    }

    public Font getFont(Object arg0)
    {
      // TODO Auto-generated method stub
      return null;
    }

  }

  public class TreeContentProvider implements ITreeContentProvider
  {

    public Object[] getChildren(Object arg0)
    {
      if (arg0 instanceof Element)
      {
        Element ele = (Element) arg0;
        return ele.elements().toArray();
      }
      return new Object[0];
    }

    public Object getParent(Object arg0)
    {
      if (arg0 instanceof Element)
      {
        Element ele = (Element) arg0;
        return ele.getParent();
      }
      return null;
    }

    public boolean hasChildren(Object arg0)
    {
      if (arg0 instanceof Element)
      {
        Element ele = (Element) arg0;
        List list = ele.elements();
        if (list == null)
          return false;
        return list.size() > 0;
      }
      return false;
    }

    public Object[] getElements(Object arg0)
    {
      return this.getChildren(arg0);
    }

    public void dispose()
    {
      //
    }

    public void inputChanged(Viewer arg0, Object arg1, Object arg2)
    {
      // TODO Auto-generated method stub

    }

  }

  private TreeViewer treeViewer;

  private Document xmlDoc;

  private Tree tree;

  private Button add_Button;
  private Button del_Button;
  private Button up_Button;
  private Button down_Button;

  private DMMutiForm dmMutiForm;
  private FormEditor editor;

  public DMExtensionsMasterDetailsBlock(FormPage page, FormEditor editor)
  {
    this.editor = editor;
    this.dmMutiForm = (DMMutiForm) editor;
  }

  // 父类中的抽象方法，创建Master部分
  protected void createMasterPart(final IManagedForm managedForm,
      Composite parent)
  {
    FormToolkit toolkit = managedForm.getToolkit();
    // 创建一个内容区
    Section section = toolkit.createSection(parent, Section.DESCRIPTION
        | Section.TITLE_BAR | Section.EXPANDED);
    section.setText("All Extensions");
    section
        .setDescription("Define extensions for this desionMap in the following section.");
    section.marginWidth = 6;
    section.marginHeight = 12;
    // 创建内容区的面板
    Composite client = toolkit.createComposite(section, SWT.WRAP);
    // 绘制该面板的边框，与表单的风格一致
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    layout.marginWidth = 2;
    layout.marginHeight = 2;
    client.setLayout(layout);
    //创建带图标的超链接
	ImageHyperlink imageHyperlink =  toolkit.createImageHyperlink(section,SWT.CENTER);
	//设置超链接的图标
	imageHyperlink.setImage(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD).createImage());
	//将该图标超链接添加到内容区的工具栏中
	section.setTextClient(imageHyperlink);
    // 创建一个树，使用toolkit对象创建
    this.tree = toolkit.createTree(client, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    GridData gd = new GridData(GridData.FILL_BOTH);
    gd.heightHint = 20;
    gd.widthHint = 100;
    gd.verticalSpan = 4;
    this.tree.setLayoutData(gd);
    section.setClient(client);
    toolkit.paintBordersFor(client);
    /*
     * IFormPart管理了整个Part的dirty state, saving, commit, focus, selection
     * changes等等这样的事件。
     * 并不是表单中的每一个-空间站都需要成为一个IFormPart，最好将一组control通过实现IFormPart变成一个Part.
     * 一般来说Section就是一个自然形成的组，所以Eclipse Form提供了一个SectionPart的实现， 它包含一个Section的对象。
     */
    final SectionPart spart = new SectionPart(section);
    // 注册该对象到IManagedForm表单管理器中
    managedForm.addPart(spart);
    // 将普通的树包装成MVC的树
    this.treeViewer = new TreeViewer(this.tree);
    // 注册树的选择事件监听器
    this.treeViewer.addSelectionChangedListener(new ISelectionChangedListener()
    {
      // 当单击树中某一个节点时
      public void selectionChanged(SelectionChangedEvent event)
      {
        // 通过IManagedForm来发布IFormPart所对应的事件
        managedForm.fireSelectionChanged(spart, event.getSelection());
      }
    });
    // 设置树的内容
    this.treeViewer.setContentProvider(new TreeContentProvider());
    // 设置树的标签
    this.treeViewer.setLabelProvider(new TreeLabelProvider());
    //
    gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);

    this.add_Button = toolkit.createButton(client, "Add", SWT.PUSH);
    this.add_Button.setLayoutData(gd);
    this.add_Button.setEnabled(false);

    this.del_Button = toolkit.createButton(client, "Del", SWT.PUSH);
    this.del_Button.setLayoutData(gd);
    this.del_Button.addSelectionListener(new SelectionListener()
    {
      public void widgetDefaultSelected(SelectionEvent arg0)
      {
      }

      public void widgetSelected(SelectionEvent arg0)
      {
        GMFConsole.println(treeViewer.getSelection().toString());
      }
    });

    this.up_Button = toolkit.createButton(client, "Up", SWT.PUSH);
    this.up_Button.setLayoutData(gd);
    this.up_Button.setEnabled(false);

    this.down_Button = toolkit.createButton(client, "Down", SWT.PUSH);
    this.down_Button.setLayoutData(new GridData(
        GridData.VERTICAL_ALIGN_BEGINNING));
    this.down_Button.setEnabled(false);

    /**
     * 双击节点展开关闭功能
     */
    this.treeViewer.addDoubleClickListener(new IDoubleClickListener()
    {
      public void doubleClick(DoubleClickEvent arg0)
      {
        treeViewer.setExpandedState(getFirstElement(), !treeViewer
            .getExpandedState(getFirstElement()));
      }
    });

    this.createTreeViewerMenu();
  }

  /**
   * 创建右键菜单
   */
  private void createTreeViewerMenu()
  {
    Control control = this.treeViewer.getControl();
    MenuManager menuManager = new MenuManager();
    Menu menu = menuManager.createContextMenu(control);
    control.setMenu(menu);
    menuManager.addMenuListener(new IMenuListener()
    {
      public void menuAboutToShow(IMenuManager arg0)
      {
        arg0.removeAll();

        Object obj = getFirstElement();

        List list = null;

        if (obj != null)
        {
          // 创建新菜单
          Element objele = (Element) obj;
          MenuManager newMenu = new MenuManager("&New", "new");

          list = RssXml2.getNewMenu(Activator.getXSD(), objele.getPath());

          for (Iterator it = list.iterator(); it.hasNext();)
          {
            Element ele = (Element) it.next();
            newMenu.add(new CreateAction(objele, ele, "icons/element.gif"));
          }
          arg0.add(newMenu);

          if (objele.getName().equals("pojo"))
            arg0.add(new ImportAction());
          
//          GMFConsole.println("++++"+objele.getPath());
          /**
           * 给pojo的property添加action，用于将当前对象创建到labelProviders/labelprovider中
           */
          if(objele.getPath().trim().equals("/designMap/pojos/pojo/property")){
        	  arg0.add(new ImportLabelProvider());
          }
          
          if(objele.getName().equals("labelProvider"))
        	  arg0.add(new LabelProviderImportAction());

          arg0.add(new RefreshAction());
          arg0.add(new DelAction());
          arg0.add(new Separator());
        }

        list = DMXsd.getXSDRootElement();
        // 循环列出根节点
        for (Iterator it = list.iterator(); it.hasNext();)
        {
          Element ele = (Element) it.next();
          arg0.add(new CreateAction(xmlDoc.getRootElement(), ele,
              "icons/elements.gif"));
        }
      }
    });
  }

  // 注册详细页面部分
  protected void registerPages(DetailsPart detailsPart)
  {
    // 将DirectoryDetailPage对象注册
    detailsPart.registerPage(DefaultElement.class, new DMExtensionsDetailsPage(
        this.editor));
  }

  // 创建表单区的Action
  protected void createToolBarActions(IManagedForm managedForm)
  {
    final ScrolledForm form = managedForm.getForm();
    // 水平布局操作
    Action hAction = new Action("horizon", Action.AS_RADIO_BUTTON)
    {
      public void run()
      {
        sashForm.setOrientation(SWT.HORIZONTAL);
        form.reflow(true);
      }
    };
    hAction.setChecked(true);
    // hAction.setToolTipText("水平布局");
    hAction.setToolTipText("\u6c34\u5e73\u5e03\u5c40");
    hAction.setImageDescriptor(Activator.getImageDescriptor("icons/hor.gif"));
    // 垂直布局操作
    Action vAction = new Action("vertical", Action.AS_RADIO_BUTTON)
    {
      public void run()
      {
        sashForm.setOrientation(SWT.VERTICAL);
        form.reflow(true);
      }
    };
    vAction.setChecked(false);
    // vAction.setToolTipText("垂直布局"); //$NON-NLS-1$
    vAction.setToolTipText("\u5782\u76f4\u5e03\u5c40"); //$NON-NLS-1$
    vAction.setImageDescriptor(Activator.getImageDescriptor("icons/ver.gif"));
    // 将这两个操作添加到表单的工具栏管理器中
    form.getToolBarManager().add(hAction);
    form.getToolBarManager().add(vAction);
  }

  /**
   * 加载数据
   */
  public void onload()
  {
    if (this.treeViewer == null)
      return;
    if (this.xmlDoc == null)
      return;
    this.treeViewer.setInput(this.xmlDoc.getRootElement());
  }

  /**
   * 刷新数据
   */
  public void refresh()
  {
    if (this.treeViewer == null)
      return;
    this.treeViewer.refresh();
  }

  /**
   * 获取当前树所选对象
   * 
   * @return
   */
  private Object getFirstElement()
  {
    return ((StructuredSelection) this.treeViewer.getSelection())
        .getFirstElement();
  }
  

  public Document getXmlDoc()
  {
    return xmlDoc;
  }

  public void setXmlDoc(Document xmlDoc)
  {
    this.xmlDoc = xmlDoc;
  }

}
