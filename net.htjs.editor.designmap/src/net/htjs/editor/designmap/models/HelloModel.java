package net.htjs.editor.designmap.models;

import org.eclipse.draw2d.geometry.Rectangle;

public class HelloModel extends AbstractModel{
	private String text = "Hello World!";
	private Rectangle constraint;
	public static final String P_CONSTRAINT = "_constraint";

	public Rectangle getConstraint() {
		return constraint;
	}

	public void setConstraint(Rectangle constraint) {
		this.constraint = constraint;
		this.firePropertyChange(P_CONSTRAINT, null, this.constraint);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
