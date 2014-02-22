package net.foreworld.rss2.utils;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;

/**
 * TableView������
 * @author ����
 * @Nov 14, 2007 8:31:02 AM
 */
public class TableUtil {

	/**
	 * ��������
	 * @param table ��Ķ���
	 * @param sortListener �Ƿ���Ӽ���
	 * @param text �б���
	 * @param width �п�
	 * @param move ���Ƿ���ƶ�
	 * @return ���ж���
	 */
	public static TableColumn createColumn(Table table,Listener sortListener,String text,int width,boolean move){
		TableColumn column = new TableColumn(table, SWT.LEFT);
        column.setText(text);
        column.setWidth(width);
        column.setToolTipText(text);
        column.setMoveable(move);
        if(sortListener != null)
        	column.addListener(SWT.Selection, sortListener);
        return column;
	}
	

	/**
	 * ��ȡ�����ڱ������λֵ
	 * @param table �����
	 * @param column �ж���
	 * @return ����λֵ
	 */
    public static int getColumnIdx(Table table, TableColumn column){
        TableColumn[] columns = table.getColumns();
        for (int i = 0; i < columns.length; i++){
            if (columns[i] == column){
                return i;
            }
        }
        throw new IllegalArgumentException("Can't find column: " + column.getText());
    }
    
	/**
	 * �����table��ͼ��ĳһ������ʱ����״̬����ʾ�� 10:100 ��10Ϊ��������������100Ϊtable��ͼ������������
	 * @param viewer ����ͼ����
	 * @param statuslinemanager ״̬������
	 */
    public static void updateTablePositionField(TableViewer viewer,IStatusLineManager statuslinemanager){
        if (viewer != null){
            Table table = viewer.getTable();
            int selIndex = table.getSelectionIndex() + 1;
            int cnt = table.getItemCount();
            IStatusLineManager statusline = statuslinemanager;
            statusline.setMessage(selIndex + ":" + cnt);
        }
    }
}
