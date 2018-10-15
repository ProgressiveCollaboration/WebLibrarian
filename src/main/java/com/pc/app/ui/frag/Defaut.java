package com.pc.app.ui.frag;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class Defaut extends Div implements BeforeEnterObserver {

	private static final long serialVersionUID = 3837883965132583616L;

	public Defaut() {
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		event.rerouteTo(InventoryManagment.class);
	}

}
