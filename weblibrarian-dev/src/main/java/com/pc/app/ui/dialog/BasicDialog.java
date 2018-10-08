package com.pc.app.ui.dialog;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;

public class BasicDialog extends Dialog {

	private static final long serialVersionUID = -2520361391158861741L;

	Div a = new Div();
	Div b = new Div();
	Div c = new Div();

	public BasicDialog() {
		add(a, b, c);

	}

	void setTitle(String title) {

	}

	void setContent(Component component) {

	}

	void addFormComponent(Component... components) {

	}

	void addTerminalComponent(Component... components) {

	}

}
