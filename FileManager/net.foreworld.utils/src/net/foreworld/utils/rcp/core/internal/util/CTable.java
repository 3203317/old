package net.foreworld.utils.rcp.core.internal.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class CTable {

	/* ID to store Layout-Data with TableColumns */
	private static final String LAYOUT_DATA = "net.foreworld.ui.CTreeLayoutData"; //$NON-NLS-1$

	private Table _table;
	private List<TableColumn> _cols = new ArrayList<TableColumn>();

	private boolean _windows;
	private boolean _linux;
	private boolean _mac;

	/**
	 * 
	 * @param $parent
	 * @param $style
	 * @param $windows
	 * @param $linux
	 * @param $mac
	 */
	public CTable(Composite $parent, int $style, boolean $windows, boolean $linux, boolean $mac) {
		_windows = $windows;
		_linux = $linux;
		_mac = $mac;
		_table = new Table($parent, $style);
		$parent.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event $event) {
				onTableResize();
			}
		});
	}

	/**
	 * @return Table
	 */
	public Table getControl() {
		return _table;
	}

	/**
	 * Force layout of all columns.
	 */
	public void update() {
		onTableResize();
	}

	/**
	 * Dispose and clear all columns.
	 */
	public void clear() {
		for (TableColumn __cols : _cols) {
			__cols.dispose();
		}

		_cols.clear();
	}

	/**
	 * @param $col
	 * @param $layoutData
	 * @param $text
	 * @param $tooltip
	 * @param $image
	 * @param $moveable
	 * @param $resizable
	 * @return TableColumn
	 */
	public TableColumn manageColumn(TableColumn $col, CColumnLayoutData $layoutData, String $text, String $tooltip, Image $image, boolean $moveable, boolean $resizable) {
		$col.setData(LAYOUT_DATA, $layoutData);
		$col.setMoveable($moveable);
		$col.setResizable($resizable);

		if ($text != null)
			$col.setText($text);

		if ($tooltip != null)
			$col.setToolTipText($tooltip);

		if ($image != null)
			$col.setImage($image);

		_cols.add($col);

		return $col;
	}

	private void onTableResize() {
		int __totalWidth = _table.getParent().getClientArea().width;
		__totalWidth -= _table.getBorderWidth() * 2;

		ScrollBar __verticalBar = _table.getVerticalBar();
		if (__verticalBar != null) {
			int ___barWidth = __verticalBar.getSize().x;
			if (_mac && ___barWidth == 0)
				___barWidth = 16; // Can be 0 on Mac

			__totalWidth -= ___barWidth;
		}

		/* Bug on Mac: Width is too big */
		if (_mac) {
			__totalWidth -= 3;

			if ((_table.getStyle() & SWT.CHECK) != 0)
				__totalWidth -= 24;
		}

		/* Bug on Linux: Margin from Bar to TableItem not returned */
		else if (_linux)
			__totalWidth -= 3;

		int __freeWidth = __totalWidth;
		int __occupiedWidth = 0;

		/* Foreach TableColumn */
		int __totalFillSum = 0;
		for (TableColumn ___column : _cols) {
			CColumnLayoutData ____data = (CColumnLayoutData) ___column.getData(LAYOUT_DATA);

			/* Fixed with Default Width Hint */
			if (____data.getSize() == CColumnLayoutData.Size.FIXED && ____data.getWidthHint() == CColumnLayoutData.DEFAULT) {
				___column.pack();
				int _____width = ___column.getWidth();
				__freeWidth -= _____width;
				__occupiedWidth += _____width;
			}

			/* Fixed with actual Width Hint */
			else if (____data.getSize() == CColumnLayoutData.Size.FIXED) {
				__freeWidth -= ____data.getWidthHint();
				__occupiedWidth += ____data.getWidthHint();

				/* Only apply if changed */
				if (___column.getWidth() != ____data.getWidthHint())
					___column.setWidth(____data.getWidthHint());
			}

			/* Sum up the fill ratios for later use */
			else if (____data.getSize() == CColumnLayoutData.Size.FILL) {
				__totalFillSum += ____data.getWidthHint();
			}
		}

		/* Foreach TableColumn */
		for (TableColumn ___column : _cols) {
			CColumnLayoutData ____data = (CColumnLayoutData) ___column.getData(LAYOUT_DATA);

			/* Fill available space with ratio */
			if (____data.getSize() == CColumnLayoutData.Size.FILL) {
				int _____colWidth = (__freeWidth * ____data.getWidthHint()) / __totalFillSum;

				/* Trim if necessary */
				if (__occupiedWidth + _____colWidth >= __totalWidth)
					_____colWidth = __totalWidth - __occupiedWidth;

				__occupiedWidth += _____colWidth;

				/* Only apply if changed */
				if (___column.getWidth() != _____colWidth)
					___column.setWidth(_____colWidth);
			}
		}
	}
}
