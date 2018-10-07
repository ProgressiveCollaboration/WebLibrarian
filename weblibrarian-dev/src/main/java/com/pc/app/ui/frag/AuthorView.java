package com.pc.app.ui.frag;

import org.apache.commons.lang3.StringUtils;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC.SVGIcon;
import com.pc.db.MDB;
import com.pc.entity.Author;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "author", layout = BaseFragment.class)
public class AuthorView extends Fragment implements HasDynamicTitle, HasUrlParameter<String> {

	private static final long serialVersionUID = 1L;

	private String pageTitle = "";
	private Div content = new Div();

	public AuthorView() {
		content.addClassName("font-size-s");
		addContent(content);
	}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		if (StringUtils.isBlank(parameter)) {

		} else {
			Author p = MDB.getDS().find(Author.class).filter("_id", parameter).get();
			if (p != null) {
				pageTitle = p.getFirstName();
				buildUIfor(p);
			}
		}
	}

	private void buildUIfor(Author p) {

		RouterLink y = new RouterLink("", InventoryManagment.class, InventoryManagment._authors);
		y.addClassName("btn-mr");
		SVGIcon icon = new SVGIcon("chevron-left.svg");
		y.add(icon);

		setHeaderComponent(new Div(new Text(p.getFirstName() + " " + p.getLastName())), y);
		content.removeAll();

		{
			String img = p.getImageURL() != null ? p.getImageURL() : "";
			Image image = new Image(img, "");
			image.setWidth("170px");

			Label label = new Label("Wiki");
			Div wikiText = new Div(label, new Div(new Span(p.getBioGraphy())));
			wikiText.getStyle().set("flexGrow", "1").set("marginLeft", "1em");

			FlexLayout flex = new FlexLayout(new Div(new Label("Display Image"), image), wikiText);
			flex.addClassName("sectionblock");
			flex.getStyle().set("marginTop", "1em");
			content.add(flex);
		}

		content.add(new Hr());

		{
			pB("Website", p.getWebsite());
			pB("AKA", p.getAka());
			pB("Gender", p.getGender());
		}

		content.add(new Hr());

	}

	private void pB(String labeltxt, String description) {
		Label label = new Label(labeltxt);
		label.getStyle().set("float", "right");
		Div a = new Div(label);
		a.setWidth("170px");
		Div b = new Div(new Span(description));
		b.getStyle().set("flexGrow", "1").set("marginLeft", "1em");
		FlexLayout sectionblock = new FlexLayout(a, b);
		sectionblock.addClassName("sectionblock");
		content.add(sectionblock);
	}

	@Override
	public String getPageTitle() {
		return pageTitle;
	}

}
