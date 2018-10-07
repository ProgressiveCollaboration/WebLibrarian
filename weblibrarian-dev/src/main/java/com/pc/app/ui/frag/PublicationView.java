package com.pc.app.ui.frag;

import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC.SVGIcon;
import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.db.MDB;
import com.pc.entity.Author;
import com.pc.entity.Publication;
import com.pc.model.ShippingInformation;
import com.pc.model.ShippingInformation.WeightUnit;
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

@Route(value = "publication", layout = BaseFragment.class)
public class PublicationView extends Fragment implements HasDynamicTitle, HasUrlParameter<String> {

	private static final long serialVersionUID = 1L;

	private String pageTitle = "";
	private Div content = new Div();

	public PublicationView() {
		content.addClassNames("font-size-s");
		content.getStyle().set("overflowY", "scroll");
		addContent(content);
	}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		if (StringUtils.isBlank(parameter)) {

		} else {
			Publication p = MDB.getDS().find(Publication.class).filter("_id", parameter).get();
			if (p != null) {
				pageTitle = p.getTitle();
				buildUIfor(p);
			}
		}
	}

	private void buildUIfor(Publication p) {

		RouterLink y = new RouterLink("", InventoryManagment.class, InventoryManagment._publishers);
		y.addClassName("btn-mr");
		SVGIcon icon = new SVGIcon("chevron-left.svg");
		y.add(icon);

		setHeaderComponent(new Div(new Text(p.getTitle())), y);
		content.removeAll();

		{
			String img = p.getImageURL().size() > 0 ? p.getImageURL().get(0) : "";
			Image image = new Image(img, "");
			image.setWidth("170px");

			Label label = new Label("Wiki");
			Div wikiText = new Div(label, new Div(new Span(p.getDescription())));
			wikiText.getStyle().set("flexGrow", "1").set("marginLeft", "1em");

			// authors
			p.getAuthorId().forEach(a -> {
				wikiText.add(new Div(new Label("Authors")));
				Author authr = MDB.getDS().find(Author.class).filter("_id", a).get();
				if (authr != null) {
					RouterLink g = new RouterLink("", AuthorView.class, authr.getId());
					g.add(new Text(authr.getFirstName() + " " + authr.getLastName()));
					g.addClassName("btn-mr");
					wikiText.add(g);
				}
			});

			FlexLayout flex = new FlexLayout(new Div(new Label("Display Image"), image), wikiText);
			flex.addClassName("sectionblock");
			flex.getStyle().set("marginTop", "1em");
			content.add(flex);
		}

		content.add(new Hr());

		{
			pB("Language", p.getLanguage());
			pB("Edition", p.getEdition());
			pB("Genre", p.getGenre());
			pB("ISBN-10", p.getISBN_10());
			pB("ISBN-13", p.getISBN_13());
			pB("NumberOfPages", "" + p.getNumberOfPages());
			pB("Binding", p.getBinding());
			pB("ReleaseCycle", p.getReleaseCycle());
			if (p.getFirstPublishedDate() != null)
				pB("PublishedDate", p.getFirstPublishedDate().format(DateTimeFormatter.ofPattern("MMM d, yyyy")));
			if (p.getShippingInformation() != null) {
				ShippingInformation ship = p.getShippingInformation();
				pB("Dimensions", StringUtils.isNotEmpty(ship.getDimensions()) ? ship.getDimensions() : "");
				pB("Weight", ship.getWeight(WeightUnit.LB) + " lb");
			}

		}

		content.add(new Hr());

		SmallButton discontbtn = new SmallButton("Edit Listing Information");
		discontbtn.addClickListener(clk -> {
			// publication editor
		});

		discontbtn.addClassName("btn-mr");
		SmallButton lostbtn = new SmallButton("Add Purchase Order");
		lostbtn.addClassName("btn-mr");
		SmallButton issuedbtn = new SmallButton("Setup Discontinuation");
		issuedbtn.addClassName("btn-mr");

		Div block2 = new Div(discontbtn, lostbtn, issuedbtn);
		block2.addClassName("sectionblock");
		content.add(block2);

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
