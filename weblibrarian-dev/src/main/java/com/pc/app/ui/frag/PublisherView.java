package com.pc.app.ui.frag;

import org.apache.commons.lang3.StringUtils;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC.SVGIcon;
import com.pc.db.MDB;
import com.pc.entity.Publisher;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "publishers", layout = BaseFragment.class)
public class PublisherView extends Fragment implements HasDynamicTitle, HasUrlParameter<String> {

	private static final long serialVersionUID = 1L;

	private String pageTitle = "";
	private Div content = new Div();

	public PublisherView() {
		content.addClassName("font-size-s");
		addContent(content);
	}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		if (StringUtils.isBlank(parameter)) {

		} else {
			Publisher p = MDB.getDS().find(Publisher.class).filter("_id", parameter).get();
			if (p != null) {
				pageTitle = p.getPublisherName();
				buildUIfor(p);
			}
		}
	}

	private void buildUIfor(Publisher p) {

		RouterLink y = new RouterLink("", InventoryManagment.class, InventoryManagment._publishers);
		y.addClassName("btn-mr");
		SVGIcon icon = new SVGIcon("chevron-left.svg");
		y.add(icon);

		setHeaderComponent(new Div(new Text(p.getPublisherName())), y);
		content.removeAll();

		{
			Image image = new Image(p.getImageUrl(" "), "");
			image.setWidth("150px");

			Label label = new Label("Wiki");
			Div wikiText = new Div(label, new Div(new Span(p.getWikiLink())));
			wikiText.getStyle().set("flexGrow", "1").set("marginLeft", "1em");

			FlexLayout flex = new FlexLayout(new Div(image), wikiText);
			flex.addClassName("sectionblock");
			flex.getStyle().set("marginTop", "1em");
			content.add(flex);
		}

		content.add(new Hr());

		{
			Label label = new Label("Copyright");
			label.getStyle().set("float", "right");

			Div a = new Div(label);
			a.setWidth("150px");

			Div b = new Div(new Span(p.getCopyright()));
			b.getStyle().set("flexGrow", "1").set("marginLeft", "1em");

			FlexLayout sectionblock = new FlexLayout(a, b);
			sectionblock.addClassName("sectionblock");
			content.add(sectionblock);
		}

		{
			Label label = new Label("Website");
			label.getStyle().set("float", "right");

			Div a = new Div(label);
			a.setWidth("150px");

			Anchor anchor = new Anchor(p.getPublisherWebsite(), p.getPublisherWebsite());
			Div b = new Div(anchor);
			b.getStyle().set("flexGrow", "1").set("marginLeft", "1em");

			FlexLayout sectionblock = new FlexLayout(a, b);
			sectionblock.addClassName("sectionblock");
			content.add(sectionblock);
		}

		content.add(new Hr());

		// books / authors

		Tab tab2 = new Tab("Authors");
		Tab tab3 = new Tab("Publications");
		Tabs tabs = new Tabs();
		tabs.add(tab2, tab3);
		tabs.getElement().setAttribute("theme", "small");

		content.add(tabs);
	}

	@Override
	public String getPageTitle() {
		return pageTitle;
	}

}
