package com.pc.app.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
  
@Route(value = "management", layout = SecurePage.class)
public class ManagementBase extends Div {
 
	private static final long serialVersionUID = 1L;

	public ManagementBase() {
		add(new Span("ManagementBase"));
	}

}
