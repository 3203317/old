package net.foreworld.rss2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 */
public class CacheImage {
	private final Map imageMap = new HashMap();

	private static CacheImage instance;

	private CacheImage() {
	}

	// 单例模式，获得CacheImage实例
	public static CacheImage getInstance() {
		if (instance == null)
			instance = new CacheImage();
		return instance;
	}

	// 获得图像
	public Image getImage(String applicationID,String imageName) {
		if (imageName == null)
			return null;
		Image image = (Image) imageMap.get(imageName);
		if (image == null) {
			image =AbstractUIPlugin.imageDescriptorFromPlugin(
					applicationID,imageName).createImage();
			imageMap.put(imageName, image);
		}
		return image;
	}

	// 获得图像2
	public Image getImage(String imageName) {
		if (imageName == null)
			return null;
		Image image = (Image) imageMap.get(imageName);
		if (image == null) {
			image =AbstractUIPlugin.imageDescriptorFromPlugin(
					Activator.PLUGIN_ID,imageName).createImage();
			imageMap.put(imageName, image);
		}
		return image;
	}

	// 释放图像资源
	public void dispose() {
		Iterator iterator = imageMap.values().iterator();
		while (iterator.hasNext())
			((Image) iterator.next()).dispose();
		imageMap.clear();
	}
}