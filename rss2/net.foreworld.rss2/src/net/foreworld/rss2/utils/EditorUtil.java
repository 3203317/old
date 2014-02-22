package net.foreworld.rss2.utils;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * 
 * @author 黄鑫
 * @Nov 14, 2007 3:49:14 PM
 */
public class EditorUtil {
	/**
	 * 判断是否在编辑区中重复打开
	 * @param window window
	 * @param o 对象
	 * @return true表示存在，false表示不存在
	 */
	public static boolean isEditorAreaExists(IWorkbenchWindow window,Object o){
		IEditorPart[] iep = window.getActivePage().getEditors();
		IEditorInput input = null;
		for(int i=0;i<iep.length;i++){
			input = iep[i].getEditorInput();
//			if(o instanceof TelRecord){
//				if(input instanceof CorpTelRecordEditorInput){
//					if(((CorpTelRecordEditorInput)input).getTr() == (TelRecord)o){
//						return true;
//					}				
//				}
//			}
//			else if(o instanceof User){
//				if(input instanceof UserTelRecordEditorInput){
//					if(((UserTelRecordEditorInput)input).getUser() == (User)o){
//						return true;
//					}				
//				}
//			}
		}
		return false;
	}
}
