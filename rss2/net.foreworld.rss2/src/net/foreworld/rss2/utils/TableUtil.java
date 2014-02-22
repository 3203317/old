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
 * TableView工具类
 * @author 黄鑫
 * @Nov 14, 2007 8:31:02 AM
 */
public class TableUtil {

	/**
	 * 创建表列
	 * @param table 表的对象
	 * @param sortListener 是否添加监听
	 * @param text 列标题
	 * @param width 列宽
	 * @param move 列是否可移动
	 * @return 表列对象
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
	 * 获取列所在表的索引位值
	 * @param table 表对象
	 * @param column 列对象
	 * @return 索引位值
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
	 * 当点击table视图的某一行数据时，在状态栏显示如 10:100 ，10为点击对象的行树，100为table视图的行数据总数
	 * @param viewer 表视图对象
	 * @param statuslinemanager 状态栏对象
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
