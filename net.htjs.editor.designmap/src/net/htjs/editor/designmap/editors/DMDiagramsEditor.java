package net.htjs.editor.designmap.editors;

import net.htjs.editor.designmap.GMFConsole;
import net.htjs.editor.designmap.models.ContentsModel;
import net.htjs.editor.designmap.models.HelloModel;
import net.htjs.editor.designmap.parts.PartFactory;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.parts.GraphicalEditor;

public class DMDiagramsEditor extends GraphicalEditor {

	public static final String ID = DMDiagramsEditor.class.getName();
	

	private GraphicalViewer viewer;
	
	public DMDiagramsEditor(){
		this.setEditDomain(new DefaultEditDomain(this));
		
		GMFConsole.println("DMDiagramsEditor");
	}

	protected void initializeGraphicalViewer() {
		ContentsModel parent = new ContentsModel();

		HelloModel child1 = new HelloModel();
		child1.setConstraint(new Rectangle(0,0,-1,-1));
		parent.addChild(child1);

		HelloModel child2 = new HelloModel();
		child2.setConstraint(new Rectangle(10,10,-1,-1));
		parent.addChild(child2);

		HelloModel child3 = new HelloModel();
		child3.setConstraint(new Rectangle(120,20,80,50));
		parent.addChild(child3);
		
		this.viewer.setContents(parent);
	}

	public void doSave(IProgressMonitor arg0) {
		// TODO Auto-generated method stub
		
	}

	
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		
		this.viewer = this.getGraphicalViewer();
		this.viewer.setEditPartFactory(new PartFactory());
	}

}
