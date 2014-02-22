package net.foreworld.utils.rcp.core.persist.events.runnables;

import java.util.HashSet;
import java.util.Set;

import net.foreworld.utils.rcp.core.persist.events.AbstractModelEvent;
import net.foreworld.utils.rcp.core.persist.events.IEntityListener;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 * @param <T>
 * @param <E>
 */
public abstract class AbstractEventRunnable<T extends IEntityListener, E extends AbstractModelEvent>
		implements Runnable {

	protected Set<T> _listeners;

	protected AbstractEventRunnable() {
		this._listeners = new HashSet<T>(3);
	}

	public final void addListener(T $event) {
		_listeners.add($event);
	}

	public final void removeListener(T $event) {
		_listeners.remove($event);
	}

	public final Set<T> getListeners() {
		return _listeners;
	}

	protected AbstractModelEvent _modelEvent;

	public void setModelEvent(AbstractModelEvent $modelEvent) {
		_modelEvent = $modelEvent;
	}

	public AbstractModelEvent getModelEvent() {
		return _modelEvent;
	}
}
