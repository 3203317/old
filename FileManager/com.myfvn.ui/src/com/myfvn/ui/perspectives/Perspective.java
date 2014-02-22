package com.myfvn.ui.perspectives;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.myfvn.ui.FvnUI;
import com.myfvn.ui.views.FileNavigatorView;
import com.myfvn.ui.views.HistoryView;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class Perspective implements IPerspectiveFactory {
	private static final float WIDE_SCREEN_RATIO = 0.22f;
	private static final float NORMAL_SCREEN_RATIO = 0.25f;

	public void createInitialLayout(IPageLayout $layout) {
		$layout.setFixed(false);

		$layout.addView(FileNavigatorView.class.getName(), IPageLayout.LEFT, this.getRatio(), $layout.getEditorArea());
		$layout.getViewLayout(FileNavigatorView.class.getName()).setCloseable(false);

		IFolderLayout __rightBottom = $layout.createFolder("rightbottom", IPageLayout.BOTTOM, .6f, $layout.getEditorArea());
		__rightBottom.addView(HistoryView.ID);
		__rightBottom.addPlaceholder(HistoryView.ID);

		$layout.addShowViewShortcut(FileNavigatorView.class.getName());

		$layout.addShowInPart(FileNavigatorView.class.getName());

	}

	private float getRatio() {
		Point __size = FvnUI.getFirstMonitorSize();
		if (__size != null && __size.y != 0) {
			float ___screenRatio = (float) __size.x / (float) __size.y;
			return ___screenRatio > 1.5 ? WIDE_SCREEN_RATIO : NORMAL_SCREEN_RATIO;
		}
		return NORMAL_SCREEN_RATIO;
	}

	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();

		// 添加上下文绑定数据
		SimpleBindings bindings = new SimpleBindings();
		bindings.put("aaa_av", new Double(10000.1));
		bindings.put("bbb_av", new Double(20000.2));

		ScriptEngine engine = sem.getEngineByExtension("js");
		engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
		try {
			// 直接解析
			Object res = engine.eval(" res =  Math.max(aaa_av , bbb_av) + aaa_av");
			System.out.println(res);

			// 创建脚本
			String script = "function getMax() " + "{ return Math.max(aaa_av , bbb_av)}";
			// 执行脚本
			engine.eval(script);
			Invocable inv = (Invocable) engine;
			// 执行方法并传递参数
			Object obj = inv.invokeFunction("getMax", null);
			// 打印结果
			System.out.println(obj);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
