package net.foreworld.utils.rcp.core.internal.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class LayoutUtils {

	/**
	 * Create a new FillLayout with the given parameters
	 * 
	 * @param $horizontal
	 *            If TRUE, align Layout horizontally
	 * @param $marginWidth
	 *            Margin width in pixel
	 * @param $marginHeight
	 *            Margin height in pixel
	 * @return FillLayout New FillLayout with the given parameters
	 */
	public static FillLayout createFillLayout(boolean $horizontal, int $marginWidth, int $marginHeight) {
		FillLayout __f = new FillLayout($horizontal ? SWT.HORIZONTAL : SWT.VERTICAL);
		__f.marginHeight = $marginHeight;
		__f.marginWidth = $marginWidth;
		return __f;
	}

	/**
	 * Create a new GridLayout with the given parameters
	 * 
	 * @param $cols
	 *            The number of columns
	 * @return GridLayout New GridLayout with the given parameters
	 */
	public static GridLayout createGridLayout(int $cols) {
		return createGridLayout($cols, 5, 5, 5, 5, false);
	}

	/**
	 * Create a new GridLayout with the given parameters
	 * 
	 * @param $cols
	 *            The number of columns
	 * @param $marginWidth
	 *            Margin width in pixel
	 * @return GridLayout New GridLayout with the given parameters
	 */
	public static GridLayout createGridLayout(int $cols, int $marginWidth) {
		return createGridLayout($cols, $marginWidth, 5, 5, 5, false);
	}

	/**
	 * Create a new GridLayout with the given parameters
	 * 
	 * @param $cols
	 *            The number of columns
	 * @param $marginWidth
	 *            Margin width in pixel
	 * @param $marginHeight
	 *            Margin height in pixel
	 * @return GridLayout New GridLayout with the given parameters
	 */
	public static GridLayout createGridLayout(int $cols, int $marginWidth, int $marginHeight) {
		return createGridLayout($cols, $marginWidth, $marginHeight, 5, 5, false);
	}

	/**
	 * Create a new GridLayout with the given parameters
	 * 
	 * @param $cols
	 *            The number of columns
	 * @param $marginWidth
	 *            Margin width in pixel
	 * @param $marginHeight
	 *            Margin height in pixel
	 * @param $makeColumnsEqualWidth
	 *            TRUE if columns should be equals in size
	 * @return GridLayout New GridLayout with the given parameters
	 */
	public static GridLayout createGridLayout(int $cols, int $marginWidth, int $marginHeight, boolean $makeColumnsEqualWidth) {
		return createGridLayout($cols, $marginWidth, $marginHeight, 5, 5, $makeColumnsEqualWidth);
	}

	/**
	 * Create a new GridLayout with the given parameters
	 * 
	 * @param $cols
	 *            The number of columns
	 * @param $marginWidth
	 *            Margin width in pixel
	 * @param $marginHeight
	 *            Margin height in pixel
	 * @param $verticalSpacing
	 *            Vertical spacing in pixel
	 * @return GridLayout New GridLayout with the given parameters
	 */
	public static GridLayout createGridLayout(int $cols, int $marginWidth, int $marginHeight, int $verticalSpacing) {
		return createGridLayout($cols, $marginWidth, $marginHeight, $verticalSpacing, 5, false);
	}

	/**
	 * Create a new GridLayout with the given parameters
	 * 
	 * @param $cols
	 *            The number of columns
	 * @param $marginWidth
	 *            Margin width in pixel
	 * @param $marginHeight
	 *            Margin height in pixel
	 * @param $verticalSpacing
	 *            Vertical spacing in pixel
	 * @param $horizontalSpacing
	 *            Horizontal spacing in pixel
	 * @param $makeColumnsEqualWidth
	 *            TRUE if columns should be equals in size
	 * @return GridLayout New GridLayout with the given parameters
	 */
	public static GridLayout createGridLayout(int $cols, int $marginWidth, int $marginHeight, int $verticalSpacing, int $horizontalSpacing, boolean $makeColumnsEqualWidth) {
		GridLayout __g = new GridLayout($cols, $makeColumnsEqualWidth);
		__g.marginHeight = $marginHeight;
		__g.marginWidth = $marginWidth;
		__g.verticalSpacing = $verticalSpacing;
		__g.horizontalSpacing = $horizontalSpacing;
		return __g;
	}

	/**
	 * Sets the initial location to use for the shell. The default
	 * implementation centers the shell horizontally (1/2 of the difference to
	 * the left and 1/2 to the right) and vertically (1/3 above and 2/3 below)
	 * relative to the parent shell
	 * 
	 * @param $shell
	 *            The shell to set the location
	 */
	public static void positionShell(Shell $shell) {
		Rectangle __containerBounds = $shell.getParent().getBounds();
		Point __initialSize = $shell.getSize();
		int __x = Math.max(0, __containerBounds.x + (__containerBounds.width - __initialSize.x) / 2);
		int __y = Math.max(0, __containerBounds.y + (__containerBounds.height - __initialSize.y) / 3);
		$shell.setLocation(__x, __y);
	}
}
