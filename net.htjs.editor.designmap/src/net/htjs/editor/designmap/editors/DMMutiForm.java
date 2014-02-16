package net.htjs.editor.designmap.editors;

import net.htjs.build.buildcodes.BuildBase;
import net.htjs.build.mapping.CacheDesigns;
import net.htjs.editor.designmap.GMFConsole;
import net.htjs.editor.designmap.editors.dmpages.DMExtensionsPage;
import net.htjs.editor.designmap.editors.dmpages.DMOverviewPage;
import net.htjs.util.UtilBase;
import net.htjs.util.property.PropertyConfigFactory;
import net.htjs.util.xml.XMLDocument;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.jdom.output.Format;

public class DMMutiForm extends FormEditor implements IResourceChangeListener{
	
	public static final String ID = DMMutiForm.class.getName();
	
	private DMOverviewPage dmopage;
	private DMExtensionsPage dmepage;
//	private DMDiagramsPage dmdpage;
	
	private DMDiagramsEditor2 xmlEditor;
	
	private Document xmlDoc = null; 
	
	private boolean error = false;
	
	private String projectPath;
	
	public DMMutiForm(){
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
		this.dmopage = new DMOverviewPage(this,"Overview");
		this.dmepage = new DMExtensionsPage(this,"Extensions");
//		this.dmdpage = new DMDiagramsPage(this,"Diagrams");
		GMFConsole.println("DesignMap Editor...");
	}
	
	private void addEditorPage(){
		this.xmlEditor = new DMDiagramsEditor2();
		try {
			int index = this.addPage(this.xmlEditor,this.getEditorInput());
			this.setPageText(index,this.xmlEditor.getTitle());
			
			String xml = this.xmlEditor.getDocumentProvider().getDocument(this.xmlEditor.getEditorInput()).get();

//			SAXReader reader = new SAXReader();
//			ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));				
//			this.xmlDoc = reader.read(is);
			System.setProperty("org.xml.sax.driver", "org.apache.crimson.parser.XMLReaderImpl");
			this.xmlDoc = DocumentHelper.parseText(xml);
			
			
		} catch (PartInitException e) {
			GMFConsole.println(e.getMessage());
		} catch (Exception e){
			GMFConsole.println(e.getMessage());
		}
	}
	
	/**
	 * 获取文件内容
	 */
	public void init(IEditorSite site, IEditorInput input) throws PartInitException{
		if (!(input instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, input);
		this.setPartName("DesignMap Editor - "+input.getName());
		GMFConsole.println("Init DesignMap Data...");

		FileEditorInput fei = (FileEditorInput)input;
		this.projectPath = fei.getFile().getProject().getLocation().toString();
		GMFConsole.println(this.projectPath);
		PropertyConfigFactory pcf1 = PropertyConfigFactory.loadFile(projectPath + "/src/config.properties");
		UtilBase.setConfigFactory(pcf1);
		PropertyConfigFactory pcf = PropertyConfigFactory.loadFile(projectPath + "/src/buildCode.properties");
		BuildBase.setConfigFactory(pcf);
		CacheDesigns.setRootPath(projectPath + "/src/");
	}

	protected void addPages() {
		GMFConsole.println("Create DesignMap Pages...");		
		try{
			this.addPage(this.dmopage);
			this.addPage(this.dmepage);
//			this.addPage(this.dmdpage);
			this.addPage(new DMDiagramsEditor2(), this.getEditorInput());
			this.setPageText(2, "Diagrams");
			this.addEditorPage();
			
			super.pageChange(0);
			this.dmopage.setXmlDoc(this.xmlDoc);
			this.dmopage.onload();
			GMFConsole.println("Initial overviewpage data success...");

			//初始化Extensions页			
			super.pageChange(1);
			this.dmepage.getMasterDetailsBlock().setXmlDoc(this.xmlDoc);
			this.dmepage.getMasterDetailsBlock().onload();
			GMFConsole.println("Initial extensionspage data success...");
		}
		catch(PartInitException e){
			e.printStackTrace();
		}
	}

	/**
	 * 保存
	 */
	public void doSave(IProgressMonitor arg0) {
		if(this.isError()){
			GMFConsole.println("Xml is error...");
		}
		else{
			this.getEditor(3).doSave(arg0);
			GMFConsole.println("Xml Save success...");
		}
	}

	/**
	 * 另存为
	 */
	public void doSaveAs() {
		IEditorPart editor = this.getEditor(3);
		editor.doSaveAs();
		this.setPageText(3, editor.getTitle());
		this.setInput(editor.getEditorInput());
	}

	/**
	 * 不允许另存为
	 */
	public boolean isSaveAsAllowed() {
		return false;
	}

	public void resourceChanged(IResourceChangeEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	public void dispose(){
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
	
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
	}
	
	/**
	 * 编辑器数据重载
	 */
	public void xmlEditorDataReload(){
		XMLDocument xmldoc = new XMLDocument();
		Format format = Format.getCompactFormat();
		format.setIndent("\t");
		format.setLineSeparator("\r\n");
		format.setExpandEmptyElements(true);
		format.setEncoding("UTF-8");
		format.setOmitDeclaration(false);
		format.setOmitEncoding(false);
		String xml = null;
		try {
			xmldoc.loadXMLForString(this.xmlDoc.asXML());
			xml = xmldoc.getXML("UTF-8", format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.xmlEditor.getDocumentProvider().getDocument(this.xmlEditor.getEditorInput()).set(xml);
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	
}
