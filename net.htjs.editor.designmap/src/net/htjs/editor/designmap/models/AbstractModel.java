package net.htjs.editor.designmap.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AbstractModel {
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		this.listeners.addPropertyChangeListener(listener);
	}
	
	public void firePropertyChange(String propertyName,Object oldValue,Object newValue){
		this.listeners.firePropertyChange(propertyName, oldValue, newValue);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		this.listeners.removePropertyChangeListener(listener);
	}
}
