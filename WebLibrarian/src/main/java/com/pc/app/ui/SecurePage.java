package com.pc.app.ui;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLayout;

@Push
@Viewport("user-scalable=no, initial-scale=1.0, shrink-to-fit=no")
@HtmlImport("styles/shared-styles.html")
@RoutePrefix("secure")
@Tag("app-webl")
public class SecurePage extends Div implements RouterLayout {
 
	private static final long serialVersionUID = 1L;

	public SecurePage() { 
		add(new Span("SecurePage"));
	}

}
