package com.pc.app.ui.cruds;

import com.pc.app.ui.dialog.BasicDialog.BeanAction;
import com.pc.app.ui.dialog.MDialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AddEntityDiv extends MDialog {

	private static final long serialVersionUID = -5309795792612958170L;

	String gid;

	VerticalLayout vLayout = new VerticalLayout();

	public AddEntityDiv(Object obj, BeanAction action, GenericCRUD.EntityAction entityAction,
			GenericCRUD.OnAction onaction) {
		super(action);

		setContents(vLayout);
	}
}
