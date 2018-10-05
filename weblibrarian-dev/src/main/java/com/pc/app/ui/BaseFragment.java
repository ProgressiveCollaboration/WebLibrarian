package com.pc.app.ui;

import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;

@ParentLayout(SecurePage.class) 
public class BaseFragment extends FlexLayout implements RouterLayout {

	private static final long serialVersionUID = 1L;

	public BaseFragment() {
		getStyle().set("padding", "1em").set("flexGrow", "1").set("flexDirection", "column");
	}
}
