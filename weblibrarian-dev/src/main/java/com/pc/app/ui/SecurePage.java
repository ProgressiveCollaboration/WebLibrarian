package com.pc.app.ui;

import com.pc.app.ui.frag.Account;
import com.pc.app.ui.frag.AudioVideoReport;
import com.pc.app.ui.frag.InventoryManagment;
import com.pc.app.ui.frag.MailBox;
import com.pc.app.ui.frag.PublicationsReport;
import com.pc.app.ui.frag.ServiceRequest;
import com.pc.app.ui.frag.UserRegistration;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@HtmlImport("styles/shared-styles.html")
@Viewport("user-scalable=no, initial-scale=1.0, shrink-to-fit=no")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@Push
public class SecurePage extends FlexLayout implements RouterLayout {

	private static final long serialVersionUID = 5873104864223432186L;

	private Div sidenavbar = new Div();

	public SecurePage() {
		addClassNames("securepage");

		sidenavbar.addClassNames("sidenav");

		Div headerbanner = new Div();
		headerbanner.addClassNames("headerbanner");

		Span applicationLogo = new Span("WebLibrarian");
		applicationLogo.addClassNames("applicationlogo", "navpad");

		headerbanner.add(applicationLogo);

		sidenavbar.add(headerbanner);

		Span dashboardtxt = new Span("DASHBOARDS");
		dashboardtxt.addClassNames("sectionheader", "navpad");

		RouterLink q = new RouterLink("Publications", PublicationsReport.class);
		q.setHighlightCondition(HighlightConditions.locationPrefix());
		q.addClassName("routerlink");

		RouterLink t = new RouterLink("Audio / Video", AudioVideoReport.class);
		t.setHighlightCondition(HighlightConditions.locationPrefix());
		t.addClassName("routerlink");

		sidenavbar.add(dashboardtxt, t, q);

		Span administrationtxt = new Span("ADMINISTRATION");
		administrationtxt.addClassNames("sectionheader", "navpad");

		RouterLink x = new RouterLink("User Registration", UserRegistration.class);
		x.setHighlightCondition(HighlightConditions.locationPrefix());
		x.addClassName("routerlink");

		RouterLink y = new RouterLink("Inventory", InventoryManagment.class);
		y.setHighlightCondition(HighlightConditions.locationPrefix());
		y.addClassName("routerlink");

		RouterLink z = new RouterLink("Accounts", Account.class);
		z.setHighlightCondition(HighlightConditions.locationPrefix());
		z.addClassNames("routerlink");

		RouterLink r = new RouterLink("Requests / Returns", ServiceRequest.class);
		r.setHighlightCondition(HighlightConditions.locationPrefix());
		r.addClassNames("routerlink");

		RouterLink m = new RouterLink("MailBox", MailBox.class);
		m.setHighlightCondition(HighlightConditions.locationPrefix());
		m.addClassNames("routerlink");

		sidenavbar.add(administrationtxt, x, y, z, r, m);

		Div blockspacer = new Div();
		blockspacer.addClassName("blockspacer");
		sidenavbar.add(blockspacer);

		Button signout = new Button("Sign Out");
		signout.getElement().setAttribute("theme", "tertiary error");
		sidenavbar.add(signout);

		add(sidenavbar);
	}

}
