package net.htjs.editor.designmap.commands;

import net.htjs.editor.designmap.models.HelloModel;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

public class ChangeConstraintCommand extends Command {
	private HelloModel helloModel;
	private Rectangle constraint;
	private Rectangle oldConstraint;
	
	public void execute(){
		this.helloModel.setConstraint(constraint);
	}
	
	public void setConstraint(Rectangle constraint){
		this.constraint = constraint;
	}
	
	public void setModel(Object model){
		this.helloModel = (HelloModel)model;
		this.oldConstraint = this.helloModel.getConstraint();
	}
	
	public void undo(){
		this.helloModel.setConstraint(this.oldConstraint);
	}

}
