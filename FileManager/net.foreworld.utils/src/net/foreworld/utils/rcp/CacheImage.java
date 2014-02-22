package net.foreworld.utils.rcp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public final class CacheImage {
	final Logger _logger = Logger.getLogger(CacheImage.class.getName());
	private final HashMap<String, ImageDescriptor> _imageDescMap = new HashMap<String, ImageDescriptor>();
	private final HashMap<String, Image> _imageMap = new HashMap<String, Image>();

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
	public ImageDescriptor getImageDescriptor(String $pluginId, String $imageFilePath) {
		if ($imageFilePath == null)
			return null;
		ImageDescriptor __imageDescriptor = (ImageDescriptor) _imageDescMap.get($imageFilePath);
		if (__imageDescriptor == null) {
			__imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin($pluginId, $imageFilePath);
			if (__imageDescriptor == null) {
				_logger.warning("加载图片失败: " + $pluginId + " " + $imageFilePath);//$NON-NLS-1$ //$NON-NLS-1$
			}
			_imageDescMap.put($imageFilePath, __imageDescriptor);
			_imageMap.put($imageFilePath, __imageDescriptor.createImage());
		}
		return __imageDescriptor;
	}

	public Image getImage(String $pluginId, String $imageFilePath) {
		Image __image = _imageMap.get($imageFilePath);
		if (__image == null) {
			this.getImageDescriptor($pluginId, $imageFilePath);
		}
		return _imageMap.get($imageFilePath);
	}

	/**
	 * 释放图像资源
	 */
	public void dispose() {

		Iterator<Image> __iterator = _imageMap.values().iterator();
		while (__iterator.hasNext()) {
			((Image) __iterator.next()).dispose();
		}
		_imageMap.clear();
		_imageDescMap.clear();
	}
}
