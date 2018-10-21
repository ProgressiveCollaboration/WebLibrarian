package com.pc.app.ui.dialog;

import com.pc.app.ui.dialog.BasicDialog.BeanAction;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;

public class ActionConfirmDialog extends MDialog {

	private static final long serialVersionUID = -4136556525370459308L;

	public ActionConfirmDialog(String header, String body, BeanAction action, OnAction onaction) {
		super(action);
		setHeader(header);

		Span bodys = new Span(body);
		bodys.getStyle().set("wordWrap", "break-word");

		Div form = new Div(bodys);
		form.getStyle().set("paddingTop", "1em").set("paddingBottom", "1em");
		form.setWidth("400px");
		setContents(form);
		otherstuff(onaction, action == BeanAction.DELETE);
	}

	public ActionConfirmDialog(String header, Component body, BeanAction action, OnAction onaction) {
		super(action);
		setHeader(header);
		Div form = new Div(body);
		form.getStyle().set("paddingTop", "1em").set("paddingBottom", "1em");
		form.setWidth("400px");
		setContents(form);
		otherstuff(onaction, action == BeanAction.DELETE);
	}

	public ActionConfirmDialog(Component header, Component body, BeanAction action, OnAction onaction) {
		super(action);
		setHeader(header);
		Div form = new Div(body);
		form.getStyle().set("paddingTop", "1em").set("paddingBottom", "1em");
		form.setWidth("400px");
		setContents(form);
		otherstuff(onaction, action == BeanAction.DELETE);
	}

	private void otherstuff(OnAction onaction, boolean deleteaction) {
		Button submit = new Button("Yes");
		submit.getElement().setAttribute("theme", deleteaction ? "small error primary" : "small primary");
		submit.addClickListener(e -> {
			onaction.action();
			close();
		});

		// LumoButton cancel = new LumoButton("Cancel", "small");
		// cancel.addClickListener(e -> close());
		addTerminalActions(submit);
	}

	public interface OnAction {
		void action();
	}

}
