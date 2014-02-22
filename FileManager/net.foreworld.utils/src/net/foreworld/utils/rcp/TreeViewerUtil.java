package net.foreworld.utils.rcp;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class TreeViewerUtil {

	/**
	 * 添加双击事件，展开或收缩
	 * 
	 * @param $treeViewer
	 */
	public final static void expand_or_contract(final TreeViewer $treeViewer) {

		$treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent $event) {
				Object __element = ((StructuredSelection) $event.getSelection()).getFirstElement();

				$treeViewer.setExpandedState(__element, !$treeViewer.getExpandedState(__element));
			}
		});
	}

	/**
	 * 视图区空白处禁止右键展现菜单
	 * 
	 * @param $treeViewer
	 */
	public final static void right_key_blank_control_menu(final TreeViewer $treeViewer) {

		$treeViewer.getTree().addMouseListener(new MouseListener() {

			public void mouseDoubleClick(MouseEvent $event) {

			}

			public void mouseDown(MouseEvent $event) {

			}

			public void mouseUp(MouseEvent $event) {
				// 3 == 右键
				if ($event.button == 3) {
					Object __element = ((StructuredSelection) $event.getSource()).getFirstElement();

					$treeViewer.getControl().getMenu().setVisible(__element != null);
				}
			}
		});
	}

}
