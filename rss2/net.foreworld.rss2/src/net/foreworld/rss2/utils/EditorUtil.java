package net.foreworld.rss2.utils;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * 
 * @author ����
 * @Nov 14, 2007 3:49:14 PM
 */
public class EditorUtil {
	/**
	 * �ж��Ƿ��ڱ༭�����ظ���
	 * @param window window
	 * @param o ����
	 * @return true��ʾ���ڣ�false��ʾ������
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
