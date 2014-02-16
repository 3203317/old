package net.htjs.editor.designmap.wizards;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import net.htjs.build.buildcodes.BuildBase;
import net.htjs.editor.designmap.GMFConsole;
import net.htjs.util.UtilBase;
import net.htjs.util.io.UtilFile;
import net.htjs.util.property.PropertyConfigFactory;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "dm". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class DesignMapNewWizard extends Wizard implements INewWizard {
	private DesignMapNewWizardPage page;
	private ISelection selection;

	/**
	 * Constructor for DesignMapNewWizard.
	 */
	public DesignMapNewWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		page = new DesignMapNewWizardPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		
		/**
		 * 添加判断文件是否存在的验证
		 */
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		
//		GMFConsole.println(containerName+":"+fileName);
		
		if (file.exists()) {
			MessageDialog.openError(getShell(), "Error", fileName+" is exists...");
			return false;
		}
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(
		String containerName,
		String fileName,
		IProgressMonitor monitor)
		throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
    String projectPath = file.getProject().getLocation().toString();
    
		try {
		  PropertyConfigFactory pcf = PropertyConfigFactory.loadFile(projectPath
	        + "/src/buildCode.properties");
	    BuildBase.setConfigFactory(pcf);
			InputStream stream = openContentStream(file.getName());
			if (file.exists()) {
				//file.setContents(stream, true, true, monitor);
				GMFConsole.println(fileName+" is exist...");
			} else {
				file.create(stream, true, monitor);
				String path = file.getFullPath().toString();
        path = path.substring(path.indexOf("/",2));
        path = file.getProject().getLocation().toString() + path;
        GMFConsole.println("addToBaseDesign file=" + path);
				BuildBase.addToBaseDesign(path);
			}
			stream.close();
		} 
		catch (Exception e) {
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
		monitor.worked(1);
	}
	
	/**
	 * We will initialize file contents with a sample text.
	 */

	public static InputStream openContentStream(String mkmc) {
	  String projectName = BuildBase.getProjectName();
	    mkmc = UtilFile.removeFileExt(mkmc);
	  String clsName = UtilBase.firstToUpper(mkmc.trim());
	  String paramClsName = "Object";
	  String idName = mkmc.trim();
	    StringBuffer strBuf = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
	    strBuf.append("<designMap validate=\"false\" namespace=\""+mkmc+"\">\r\n");
	    strBuf.append("\t<pojos>\r\n");
	    strBuf.append("\t<pojo id=\""+idName+"\" class=\""+BuildBase.getPojoPackagePath(mkmc)+"."+clsName+"\" dataset=\"\">");
      strBuf.append("\t</pojo>");
	    strBuf.append("\t</pojos>\r\n");
	    strBuf.append("\t<daos>\r\n");
	    strBuf.append("\t<dao id=\""+idName+"DAO\" class=\""+BuildBase.getDaoPackagePath(mkmc)+"."+clsName+"DAO\" dataset=\"\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<method id=\"selectByPOJO\" methodName=\"selectByPOJO\">");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"PaginatedList\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method methodName=\"selectByPrimaryKey\" id=\"selectByPrimaryKey\">");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\"primaryKey\" type=\"Object\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"Object\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"insert\" methodName=\"insert\">");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"Object\" description=\"\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"updateByPrimaryKey\" methodName=\"updateByPrimaryKey\">");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"deleteByPrimaryKey\" methodName=\"deleteByPrimaryKey\">");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\"primaryKey\" type=\"Object\" description=\"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"updateByPrimaryKeySelective\" methodName=\"updateByPrimaryKeySelective\">");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"selectAllByPOJO\" methodName=\"selectAllByPOJO\">");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"List\" description=\"\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t</dao>");
	    strBuf.append("\t</daos>\r\n");
	    strBuf.append("\t<services>\r\n");
	    strBuf.append("\t<service id=\""+idName+"Service\" class=\""+BuildBase.getServicePackagePath(mkmc)+"."+clsName+"Service\">");
	    strBuf.append("\t<description></description>");
	    strBuf.append("\t<method id=\"selectByPOJO\" methodName=\"selectByPOJO\" ref=\""+idName+"DAO.selectByPOJO\">");
	    strBuf.append("\t<description></description>");
	    strBuf.append("\t<params>");
	    strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
	    strBuf.append("\t</params>");
	    strBuf.append("\t<return description=\"\" type=\"PaginatedList\"></return>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"selectByPrimaryKey\" methodName=\"selectByPrimaryKey\" ref=\""+idName+"DAO.selectByPrimaryKey\">");
	    strBuf.append("\t<description></description>");
	    strBuf.append("\t<params>");
	    strBuf.append("\t<param name=\"primaryKey\" type=\"Object\" description=\"\"></param>");
	    strBuf.append("\t</params>");
	    strBuf.append("\t<return type=\"Object\" description=\"\"></return>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"insert\" methodName=\"insert\" ref=\""+idName+"DAO.insert\">");
	    strBuf.append("\t<description></description>");
	    strBuf.append("\t<params>");
	    strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
	    strBuf.append("\t</params>");
	    strBuf.append("\t<return type=\"Object\" description=\"\"></return>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"updateByPrimaryKey\" methodName=\"updateByPrimaryKey\" ref=\""+idName+"DAO.updateByPrimaryKey\">");
	    strBuf.append("\t<description></description>");
	    strBuf.append("\t<params>");
	    strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
	    strBuf.append("\t</params>");
	    strBuf.append("\t<return type=\"int\" description=\"\"></return>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"deleteByPrimaryKey\" methodName=\"deleteByPrimaryKey\" ref=\""+idName+"DAO.deleteByPrimaryKey\">");
	    strBuf.append("\t<description></description>");
	    strBuf.append("\t<params>");
	    strBuf.append("\t<param name=\"primaryKey\" type=\"Object\" description=\"\"></param>");
	    strBuf.append("\t</params>");
	    strBuf.append("\t<return type=\"int\" description=\"\"></return>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"deleteByPrimaryKeyList\" methodName=\"deleteByPrimaryKeyList\">");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\"primaryKeyList\" type=\"List\" description=\"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
      strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"updateByPrimaryKeySelective\" methodName=\"updateByPrimaryKeySelective\" ref=\""+idName+"DAO.updateByPrimaryKeySelective\">");
	    strBuf.append("\t<description></description>");
	    strBuf.append("\t<params>");
	    strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
	    strBuf.append("\t</params>");
	    strBuf.append("\t<return type=\"int\"></return>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"selectAllByPOJO\" methodName=\"selectAllByPOJO\" ref=\""+idName+"DAO.selectAllByPOJO\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"List\"></return>");
      strBuf.append("\t</method>");
	    strBuf.append("\t</service>");
	    strBuf.append("\t</services>\r\n");
	    strBuf.append("\t<ejbs>\r\n");
	    strBuf.append("\t</ejbs>\r\n");
	    strBuf.append("\t<contentProviders>\r\n");
	    strBuf.append("\t<contentProvider id=\""+idName+"Bean\" class=\""+BuildBase.getBeanPackagePath(mkmc)+"."+clsName+"Bean\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<method id=\"selectByPOJO\" methodName=\"selectByPOJO\" ref=\""+idName+"Service.selectByPOJO\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return description=\"\" type=\"PaginatedList\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"selectByPrimaryKey\" methodName=\"selectByPrimaryKey\" ref=\""+idName+"Service.selectByPrimaryKey\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\"primaryKey\" type=\"Object\" description=\"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"Object\" description=\"\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"insert\" methodName=\"insert\" ref=\""+idName+"Service.insert\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"Object\" description=\"\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"updateByPrimaryKey\" methodName=\"updateByPrimaryKey\" ref=\""+idName+"Service.updateByPrimaryKey\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\" description=\"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"deleteByPrimaryKey\" methodName=\"deleteByPrimaryKey\" ref=\""+idName+"Service.deleteByPrimaryKey\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\"primaryKey\" type=\"Object\" description=\"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"deleteByPrimaryKeyList\" methodName=\"deleteByPrimaryKeyList\" ref=\""+idName+"Service.deleteByPrimaryKeyList\">");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\"primaryKeyList\" type=\"List\" description=\"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"int\" description=\"\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"updateByPrimaryKeySelective\" methodName=\"updateByPrimaryKeySelective\" ref=\""+idName+"Service.updateByPrimaryKeySelective\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"int\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t<method id=\"selectAllByPOJO\" methodName=\"selectAllByPOJO\" ref=\""+idName+"Service.selectAllByPOJO\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<params>");
      strBuf.append("\t<param name=\""+idName+"\" type=\""+paramClsName+"\"></param>");
      strBuf.append("\t</params>");
      strBuf.append("\t<return type=\"List\"></return>");
      strBuf.append("\t</method>");
      strBuf.append("\t</contentProvider>");
	    strBuf.append("\t</contentProviders>\r\n");
	    strBuf.append("\t<labelProviders>\r\n");
	    strBuf.append("\t<labelProvider id=\"viewMain\" name=\"viewMain\"></labelProvider>");
	    strBuf.append("\t<labelProvider id=\"viewAdd\" name=\"viewAdd\"></labelProvider>");
	    strBuf.append("\t<labelProvider id=\"viewEdit\" name=\"viewEdit\" extends=\"viewAdd\"></labelProvider>");
	    strBuf.append("\t<labelProvider id=\"select\" name=\"select\"></labelProvider>");
	    strBuf.append("\t</labelProviders>\r\n");
	    strBuf.append("\t<action id=\""+idName+"Action\" class=\""+BuildBase.getActionPackagePath(mkmc)+"."+clsName+"Action\" path=\"/server/"+projectName+"/"+idName+"/\">");
	    strBuf.append("\t<description></description>");
	    strBuf.append("\t<method id=\"viewMain\" methodName=\"viewMain\" page=\"index.html\" resultType=\"json\">");
	    strBuf.append("\t<data id=\"d1\">");
	    /*strBuf.append("\t<description></description>");
	    strBuf.append("\t<contentProvider ref=\"module.moduleBean.selectUserOperateByPid\" store=\"security\"></contentProvider>");
	    strBuf.append("\t<labelProvider ref=\"module.getChildrenModule\" refStore=\"security\" prefix=\"model\"></labelProvider>");
	    strBuf.append("\t</data>");
	    strBuf.append("\t<data id=\"d2\">");*/
      strBuf.append("\t<description></description>");
      strBuf.append("\t<labelProvider ref=\"viewMain\" prefix=\"model\"></labelProvider>");
      strBuf.append("\t</data>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"viewAdd\" methodName=\"viewAdd\" page=\"viewAdd.html\" resultType=\"json\">");
	    strBuf.append("\t<data id=\"d1\">");
	    strBuf.append("\t<description></description>");
	    strBuf.append("\t<labelProvider ref=\"viewAdd\" prefix=\"model\"></labelProvider>");
	    strBuf.append("\t</data>");
	    strBuf.append("\t<params>");
      strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
      strBuf.append("\t</params>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"add\" methodName=\"add\" resultType=\"json\">");
	    strBuf.append("\t<data id=\"d1\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<contentProvider ref=\""+idName+"Bean.insert\"></contentProvider>");
      strBuf.append("\t</data>");
	    strBuf.append("\t<params>");
	    strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
	    strBuf.append("\t</params>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"viewEdit\" methodName=\"viewEdit\" page=\"viewEdit.html\" resultType=\"json\">");
	    strBuf.append("\t<data id=\"d1\">");
	    strBuf.append("\t<contentProvider store=\"data\" ref=\""+idName+"Bean.selectByPrimaryKey\"></contentProvider>");
	    strBuf.append("\t<labelProvider ref=\"viewEdit\" refStore=\"data\" prefix=\"model\"></labelProvider>");
	    strBuf.append("\t</data>");
	    strBuf.append("\t<params>");
      strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
      strBuf.append("\t</params>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"edit\" methodName=\"edit\" resultType=\"json\">");
	    strBuf.append("\t<data id=\"d1\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<contentProvider ref=\""+idName+"Bean.updateByPrimaryKey\"></contentProvider>");
      strBuf.append("\t</data>");
	    strBuf.append("\t<params>");
	    strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
	    strBuf.append("\t</params>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"select\" methodName=\"select\" page=\"select.html\" resultType=\"json\">");
	    strBuf.append("\t<data id=\"d1\">");
	    strBuf.append("\t<contentProvider store=\"data\" ref=\""+idName+"Bean.selectByPOJO\"></contentProvider>");
	    strBuf.append("\t<labelProvider ref=\"select\" refStore=\"data\"></labelProvider>");
	    strBuf.append("\t</data>");
	    strBuf.append("\t<params>");
	    strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
	    strBuf.append("\t</params>");
	    strBuf.append("\t</method>");
	    strBuf.append("\t<method id=\"delete\" methodName=\"delete\" resultType=\"json\">");
	    strBuf.append("\t<data id=\"d1\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<contentProvider ref=\""+idName+"Bean.deleteByPrimaryKey\"></contentProvider>");
      strBuf.append("\t</data>");
	    strBuf.append("\t<params>");
      strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
      strBuf.append("\t</params>");
	    strBuf.append("</method>");
	    strBuf.append("\t<method id=\"enabled\" methodName=\"enabled\" resultType=\"json\">");
	    strBuf.append("\t<data id=\"d1\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<contentProvider ref=\""+idName+"Bean.updateByPrimaryKeySelective\"></contentProvider>");
      strBuf.append("\t</data>");
	    strBuf.append("\t<params>");
      strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
      strBuf.append("\t</params>");
	    strBuf.append("</method>");
	    strBuf.append("\t<method id=\"disabled\" methodName=\"disabled\" resultType=\"json\">");
	    strBuf.append("\t<data id=\"d1\">");
      strBuf.append("\t<description></description>");
      strBuf.append("\t<contentProvider ref=\""+idName+"Bean.updateByPrimaryKeySelective\"></contentProvider>");
      strBuf.append("\t</data>");
	    strBuf.append("\t<params>");
      strBuf.append("\t<param target=\"model\" ref=\""+idName+"\"></param>");
      strBuf.append("\t</params>");
	    strBuf.append("</method>");
	    strBuf.append("\t</action>\r\n");
	    strBuf.append("</designMap>");
		try {
			return new ByteArrayInputStream(strBuf.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			GMFConsole.println(e.getMessage());
			return null;
		}
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "net.htjs.editor.designmap", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}