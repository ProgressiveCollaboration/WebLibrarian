package com.pc.app.ui.frag;

import com.pc.app.ui.HtmlC.FlexMe;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public class Fragment extends FlexLayout {

	private static final long serialVersionUID = 1L;

	private FlexMe headerflx = new FlexMe();
	private Div titlebar = new Div();
	private Div midcontent = new Div();
	private Div toolbar = new Div();
	private Div footer = new Div();

	public Fragment() {
		getStyle().set("flexGrow", "1").set("flexShrink", "0").set("flexDirection", "column");

		titlebar.addClassName("fragmenttitle");
		headerflx.add(titlebar);

		midcontent.getStyle().set("flexGrow", "1");

		add(headerflx, toolbar, midcontent, footer);
	}

	void setHeaderText(String title) {
		titlebar.setText(title);
	}

	void setHeaderComponent(Component... component) {
		titlebar.removeAll();
		titlebar.add(component);
	}

	void addToolbarComponent(Component... component) {
		toolbar.add(component);
	}

	void addContent(Component... component) {
		midcontent.add(component);
	}

	void addFooterComponent(Component... component) {
		footer.add(component);
	}

}
