package net.foreworld.utils.rcp.core.utils;

import java.util.ArrayList;
import java.util.List;

import net.foreworld.utils.rcp.core.persist.IEntity;

import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class ModelUtils {

	/**
	 * 
	 * @param selection
	 * @return
	 */
	public static List<IEntity> getEntities(IStructuredSelection $selection) {
		if ($selection.isEmpty()) {
			return new ArrayList<IEntity>();
		}

		List<?> __elements = $selection.toList();
		List<IEntity> __entities = new ArrayList<IEntity>(__elements.size());

		for (Object __object : __elements) {
			if (__object instanceof IEntity && !__entities.contains(__object)) {
				__entities.add((IEntity) __object);
			}
		}

		return __entities;
	}
}
