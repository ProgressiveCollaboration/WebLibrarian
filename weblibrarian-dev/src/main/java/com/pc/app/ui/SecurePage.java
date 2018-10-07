package com.pc.app.ui;

import com.pc.app.ui.HtmlC.IconText;
import com.pc.app.ui.HtmlC.NavigationBar;
import com.pc.app.ui.frag.Account;
import com.pc.app.ui.frag.AudioVideoManager;
import com.pc.app.ui.frag.InventoryManagment;
import com.pc.app.ui.frag.MailBox;
import com.pc.app.ui.frag.PublicationsManager;
import com.pc.app.ui.frag.ServiceRequest;
import com.pc.app.ui.frag.UserRegistration;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@HtmlImport("styles/shared-styles.html")
@StyleSheet("css/maincss.css")
@Viewport("user-scalable=no, initial-scale=1.0, shrink-to-fit=no")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@Push
public class SecurePage extends Div implements RouterLayout {

	private static final long serialVersionUID = 5873104864223432186L;

	private NavigationBar sidenavbar = new NavigationBar();

	public SecurePage() {
		addClassNames("securepage");

		Span applicationLogo = new Span("WebLibrarian");
		applicationLogo.addClassNames("applicationlogo", "navpad");

		sidenavbar.addHeader(applicationLogo);

		Div dashboardtxt = new Div(new Span("ENTITY"));
		dashboardtxt.addClassNames("vnav-sectionheader", "navpad");

		RouterLink q = new RouterLink("", PublicationsManager.class);
		q.add(new IconText("book.svg", "Publications"));
		q.setHighlightCondition(HighlightConditions.locationPrefix());
		q.addClassName("routerlink");

		RouterLink t = new RouterLink("", AudioVideoManager.class);
		//
		t.add(new IconText("film.svg", "Audio / Video"));
		t.setHighlightCondition(HighlightConditions.locationPrefix());
		t.addClassName("routerlink");

		sidenavbar.addSection(dashboardtxt, t, q);

		Div administrationtxt = new Div(new Span("ADMINISTRATION"));
		administrationtxt.addClassNames("vnav-sectionheader", "navpad");

		RouterLink x = new RouterLink("", UserRegistration.class);
		x.add(new IconText("users.svg", "User Registration"));
		x.setHighlightCondition(HighlightConditions.locationPrefix());
		x.addClassName("routerlink");

		RouterLink y = new RouterLink("", InventoryManagment.class);
		y.add(new IconText("layers-light.svg", "Inventory"));
		y.setHighlightCondition(HighlightConditions.locationPrefix());
		y.addClassName("routerlink");

		RouterLink z = new RouterLink("", Account.class);
		z.add(new IconText("dollar-sign-light.svg", "Accounts"));
		z.setHighlightCondition(HighlightConditions.locationPrefix());
		z.addClassNames("routerlink");

		RouterLink r = new RouterLink("", ServiceRequest.class);
		r.add(new IconText("alert-octagon-light.svg", "Requests / Returns"));
		r.setHighlightCondition(HighlightConditions.locationPrefix());
		r.addClassNames("routerlink");

		RouterLink m = new RouterLink("", MailBox.class);
		m.add(new IconText("mail-light.svg", "MailBox"));
		m.setHighlightCondition(HighlightConditions.locationPrefix());
		m.addClassNames("routerlink");

		sidenavbar.addSection(administrationtxt, x, y, z, r, m);

		Div blockspacer = new Div();
		blockspacer.addClassName("blockspacer");
		sidenavbar.addSection(blockspacer);

		Anchor signout = new Anchor("logout", "");
		signout.add(new IconText("log-out-light.svg", "Sign Out"));
		signout.addClassNames("routerlink");
		sidenavbar.addFooter(signout);

		add(sidenavbar);
	}

}
