package net.htjs.editor.designmap.popup.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.xml.parsers.SAXParserFactory;

import net.htjs.build.buildcodes.BuildBase;
import net.htjs.build.buildcodes.CheckDesign;
import net.htjs.build.buildcodes.IBuild;
import net.htjs.build.mapping.CacheDesigns;
import net.htjs.build.mapping.MapDesigns;
import net.htjs.editor.designmap.Activator;
import net.htjs.editor.designmap.GMFConsole;
import net.htjs.util.property.PropertyConfigFactory;
import net.htjs.util.xml.FormatHandler;
import net.htjs.util.xml.XMLDocument;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.util.BundleUtility;
import org.jdom.output.XMLOutputter;
import org.osgi.framework.Bundle;

public class BuilderAction implements IObjectActionDelegate
{
  private ISelection selection;
  private Document doc = null;
  private Project project = null;

  /**
   * Constructor for Action1.
   */
  public BuilderAction()
  {
    super();
  }

  /**
   * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
   */
  public void setActivePart(IAction action, IWorkbenchPart targetPart)
  {
  }

  /**
   * @see IActionDelegate#run(IAction)
   */
  public void run(IAction action)
  {
    // Shell shell = new Shell();
    // MessageDialog.openInformation(
    // shell,
    // "DesignMap Plug-in",
    // action.getText());

    String val = action.getText().replaceAll("&", "").toLowerCase();

    if (this.selection == null)
      return;

    if (!(this.selection instanceof IStructuredSelection))
      return;

    GMFConsole.println("Start " + val + "...");

    IStructuredSelection structured = (IStructuredSelection) this.selection;
    TreeSelection s1 = (TreeSelection) this.selection;
    File f=(File)s1.getFirstElement();
    
    IFile file = (IFile) structured.getFirstElement();
    this.project = (Project)file.getProject();

    String projectPath = this.project.getLocation().toString();

    GMFConsole.println("ProjectPath: " + projectPath);
    GMFConsole.println("FilePath: " + file.getFullPath().toString());

    /*PropertyConfigFactory pcf = PropertyConfigFactory.loadFile(projectPath + "/src/buildCode.properties");
    BuildBase.setConfigFactory(pcf);*/
    BuildBase.setResource(projectPath + "/src/buildCode.properties");
    
    try
    {
      SAXReader saxReader = new SAXReader();
      InputStreamReader utf8in = new InputStreamReader(file.getContents(),
          "utf-8");
      System.setProperty("org.xml.sax.driver", "org.apache.crimson.parser.XMLReaderImpl");
      this.doc = saxReader.read(utf8in);
    }
    catch (Exception ex)
    {
      GMFConsole.println(ex.getMessage());
      GMFConsole
          .println("Window->Preferences->General->Workspace->Refresh automatically");
      return;
    }
    String namespace = this.doc.getRootElement().attributeValue("namespace");
    int errflag = 0;
    try
    {
      XMLOutputter outputter = new XMLOutputter();
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      outputter.output(XMLDocument.convert(XMLDocument.convert(doc)), bos);
      SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setNamespaceAware(true);
      factory.setValidating(true);
      javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
      saxParser.setProperty(
          "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
          "http://www.w3.org/2001/XMLSchema");
      saxParser.setProperty(
          "http://java.sun.com/xml/jaxp/properties/schemaSource", getSchema());
      FormatHandler formatHandler = new FormatHandler();
      saxParser.parse(new ByteArrayInputStream(bos.toByteArray()),
          formatHandler);
      bos.close();
      errflag = formatHandler.getErrNum();
      if (errflag > 0)
      {
        GMFConsole.println(formatHandler.getErrMessage());
      }
    }
    catch (Exception x)
    {
      errflag = 1;
      GMFConsole.println("Your SAX parser is not JAXP 1.2 compliant.");
      x.printStackTrace();
    }

    try
    {
      if(errflag >0) 
      {
        MessageDialog.openInformation(new Shell(), "running", new String("文件编写不正确！".getBytes("GBK"),"UTF-8"));
        return ;
      }
      
      CacheDesigns.reload();
      CacheDesigns.setRootPath("");
      MapDesigns mapDesigns = CacheDesigns.getMapDesigns();
      CacheDesigns.setRootPath(projectPath + "/src/");
      if (val.equals("rtf"))
      {
        String className = BuildBase.getConfig().getProperty("defaultRTFClass",
            "net.htjs.designs.buildcodes.BuildRTF");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build rtf .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("check"))
      {
        String className = BuildBase.getConfig().getProperty("defaultCheckClass",
            "net.htjs.designs.buildcodes.CheckDesign");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build check .......");
          CheckDesign build = (CheckDesign) Class.forName(className).newInstance();
          
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("pojo") || val.equals("buildall"))
      {
        String className = BuildBase.getConfig().getProperty("defaultPojoClass",
            "net.htjs.designs.buildcodes.BulidPojo");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build pojo .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("dao") || val.equals("buildall"))
      {
        String className = BuildBase.getConfig().getProperty("defaultDaoClass",
            "net.htjs.designs.buildcodes.BulidDao");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build dao .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e.toString());
        }
      }
      if (val.equals("service") || val.equals("buildall"))
      {
        String className = BuildBase.getConfig().getProperty("defaultServiceClass",
            "net.htjs.designs.buildcodes.BulidService");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build service .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("ejb"))
      {
        String className = BuildBase.getConfig().getProperty("defaultEjbClass",
            "net.htjs.designs.buildcodes.BulidEjb");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build ejb .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("bean") || val.equals("buildall"))
      {
        String className = BuildBase.getConfig().getProperty("defaultBeanClass",
            "net.htjs.designs.buildcodes.BulidBean");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build bean .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e.toString());
        }
      }
      if (val.equals("action") || val.equals("buildall"))
      {
        String className = BuildBase.getConfig().getProperty("defaultActionClass",
            "net.htjs.designs.buildcodes.BulidAction");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build action .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("page") || val.equals("buildall"))
      {
        String className = BuildBase.getConfig().getProperty("defaultPageClass",
            "net.htjs.designs.buildcodes.BulidPage");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build page .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("js") || val.equals("buildall"))
      {
        String className = BuildBase.getConfig().getProperty("defaultJavaScriptClass",
            "net.htjs.designs.buildcodes.BuildJs");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build JavaScript .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("sqlmap") || val.equals("buildall"))
      {
        String className = BuildBase.getConfig().getProperty("defaultIbatisClass",
            "net.htjs.designs.buildcodes.BulidIbatis");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build ibatis .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("hbm"))
      {
        String className = BuildBase.getConfig().getProperty("defaultHbmClass",
            "net.htjs.designs.buildcodes.BulidHibernate");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build ibatis .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("spring") || val.equals("buildall"))
      {
        String className = BuildBase.getConfig().getProperty("defaultSpringClass",
            "net.htjs.designs.buildcodes.BulidSpring");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build spring .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("struts") || val.equals("buildall"))
      {
        String className = BuildBase.getConfig().getProperty("defaultStrutsClass",
            "net.htjs.designs.buildcodes.BulidStruts");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start build struts .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("splitproject"))
      {
        String className = BuildBase.getConfig().getProperty("defaultSplitProject",
            "net.htjs.designs.buildcodes.SplitProject");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start splist project .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("checkejb"))
      {
        String className = BuildBase.getConfig().getProperty("defaultCheckEjb",
            "net.htjs.designs.buildcodes.CheckEjb");
        GMFConsole.println(className);
        try
        {
          GMFConsole.println("start Ejb Checking ..."+className);
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      if (val.equals("staticdm")|| val.equals("buildall"))
      {
        try
        {
          String className = BuildBase.getConfig().getProperty("defaultBuildLabelProviders","net.htjs.designs.buildcodes.BuildLabelProviders");
          GMFConsole.println(className);
          GMFConsole.println("start BuildLabelProviders .......");
          IBuild build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
          className = BuildBase.getConfig().getProperty("defaultBuildActionMethods","net.htjs.designs.buildcodes.BuildActionMethods");
          GMFConsole.println(className);
          GMFConsole.println("start BuildActionMethods .......");
          build = (IBuild) Class.forName(className).newInstance();
          build.build(mapDesigns.getDesign(namespace),GMFConsole.getOutputStream());
        }
        catch (Exception e)
        {
          GMFConsole.println(e);
        }
      }
      GMFConsole.flush();
    }
    catch (Exception e)
    {
      GMFConsole.println(e);
    }
    GMFConsole.println("End " + val + "...");
    try
    {
      MessageDialog.openInformation(new Shell(), "running", new String("文件生成完毕！".getBytes("GBK"),"UTF-8"));
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * @see IActionDelegate#selectionChanged(IAction, ISelection)
   */
  public void selectionChanged(IAction action, ISelection selection)
  {
    this.selection = selection;
  }

  /**
   * 获取xsd
   * 
   * @return
   * @throws IOException
   */
  public static final InputStream getSchema() throws IOException
  {
    Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
    if (!BundleUtility.isReady(bundle))
    {

    }
    // look for the image (this will check both the plugin and fragment folders
    URL fullPathString = BundleUtility.find(bundle, "/designMapSchema.xsd");
    return fullPathString.openStream();
  }
}
