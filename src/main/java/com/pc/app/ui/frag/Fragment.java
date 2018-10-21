package com.pc.app.ui.frag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pc.app.ui.HtmlC.FlexMe;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public class Fragment extends FlexLayout {

	private static final long serialVersionUID = 1L;

	private Div titleA = new Div();
	private FlexLayout titleB = new FlexLayout();
	private FlexMe headerflx = new FlexMe(titleA, titleB);
	private FlexLayout midcontent = new FlexLayout();
	private Div toolbar = new Div();
	private Div footer = new Div();

	public static Logger log = LoggerFactory.getLogger(Fragment.class);

	public Fragment() {
		getStyle().set("flexGrow", "1").set("flexDirection", "column");

		titleA.getStyle().set("flexGrow", "1");
		titleB.getStyle().set("flexGrow", "1");
		titleB.setJustifyContentMode(JustifyContentMode.END);

		headerflx.addClassName("fragmenttitle");

		midcontent.getStyle().set("flexGrow", "1").set("flexDirection", "column");

		// add(toolbar, midcontent, footer);
		add(headerflx, toolbar, midcontent, footer);
	}

	void setHeaderText(String title) {
		titleA.setText(title);
	}

	void setHeaderComponent(Component... component) {
		titleA.removeAll();
		titleA.add(component);
	}

	void addHeaderComponent(Component... component) {
		titleA.add(component);
	}

	void setHeaderBComponent(Component... component) {
		titleB.removeAll();
		titleB.add(component);
	}

	void addHeaderBComponent(Component... component) {
		titleB.add(component);
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
