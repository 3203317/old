package net.htjs.editor.designmap.policies;

import net.htjs.editor.designmap.commands.ChangeConstraintCommand;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

public class CustomXYLayoutEditPolicy extends XYLayoutEditPolicy {

	protected Command createChangeConstraintCommand(EditPart arg0, Object arg1) {
		ChangeConstraintCommand command = new ChangeConstraintCommand();
		command.setModel(arg0.getModel());
		command.setConstraint((Rectangle)arg1);
		return command;
	}

	protected Command getCreateCommand(CreateRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Command getCommand(Request request){
		System.out.println(request.getType());
		return super.getCommand(request);
	}

}
