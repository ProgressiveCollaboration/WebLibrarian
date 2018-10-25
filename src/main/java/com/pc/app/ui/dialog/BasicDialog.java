package com.pc.app.ui.dialog;

import com.pc.app.ui.HtmlC.FlexMe;
import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.entity.BaseEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public class BasicDialog extends Dialog {

	private static final long serialVersionUID = -2520361391158861741L;

	final Span header = new Span();
	final Div headerB = new Div();
	final FlexMe headerflex = new FlexMe(header, headerB);
	final Div contentdiv = new Div();
	final FlexLayout formComponentFlex = new FlexLayout();
	final FlexLayout footdiv = new FlexLayout(formComponentFlex);

	public BasicDialog(BeanAction actiontype) {

		header.getStyle().set("fontWeight", "600").set("fontSize", "var(--lumo-font-size-l)");

		formComponentFlex.getStyle().set("flexGrow", "1");
		footdiv.addClassName("mt-2");
		footdiv.setJustifyContentMode(JustifyContentMode.END);
		footdiv.setAlignItems(Alignment.CENTER);

		if (actiontype != BeanAction.VIEW) {
			SmallButton cancel = new SmallButton("Cancel");
			cancel.addClassName("ml-2");
			cancel.addClickListener(e -> close());
			footdiv.add(cancel);
		}

		setSize(DialogSize.MID);
		add(headerflex, contentdiv, footdiv);
	}

	protected void setSize(DialogSize size) {
		switch (size) {
		case LARGE:
			contentdiv.getStyle().set("maxWidth", "900px");
			break;
		case MID:
			contentdiv.getStyle().set("maxWidth", "500px");
			break;
		default:
			contentdiv.getStyle().set("maxWidth", "300px");
			break;
		}
	}

	public void setTitle(String text) {
		header.setText(text);
	}

	public void addHeaderBComponent(Component... c) {
		headerB.add(c);
	}

	public void setContent(Component component) {
		contentdiv.add(component);
	}

	public void addFormComponent(Component... components) {
		for (Component component : components) {
			Div dv = new Div(component);
			dv.addClassName("btn-mr");
			formComponentFlex.add(dv);
		}
	}

	public void addTerminalComponent(Component... components) {
		for (Component component : components) {
			Div dv = new Div(component);
			dv.addClassName("btn-ml");
			footdiv.add(dv);
		}
	}

	public enum BeanAction {
		NEW, EDIT, DELETE, VIEW
	}

	public enum DialogSize {
		SMALL, MID, LARGE
	}

	public interface Action<T extends BaseEntity> {
		boolean action(T bean);
	}

}
