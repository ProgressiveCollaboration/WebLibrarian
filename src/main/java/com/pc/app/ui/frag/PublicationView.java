package com.pc.app.ui.frag;

import org.apache.commons.lang3.StringUtils;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC;
import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.db.MDB;
import com.pc.entity.Publication;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "publication", layout = BaseFragment.class)
public class PublicationView extends Fragment implements HasDynamicTitle, HasUrlParameter<String> {

	private static final long serialVersionUID = 1L;

	private String pageTitle = "";
	private FormLayout content = new FormLayout();

	public PublicationView() {
		content.addClassNames("font-size-s");
		content.getStyle().set("overflowY", "scroll");
		content.setResponsiveSteps(new ResponsiveStep("0em", 1));
		content.addClassName("padded1");
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

	boolean editmode = false;

	private void buildUIfor(Publication p) {

		Binder<Publication> binder = new Binder<>(Publication.class);

		RouterLink y = new RouterLink("Publishers", InventoryManagment.class, InventoryManagment._publications);
		y.getStyle().set("marginRight", "1em").set("color", "rgb(123, 92, 92)");

		setHeaderComponent(new Div(new Text(p.getTitle())), y);
		content.removeAll();

		{
			content.add(new Div(new Label("Display Images")));

			// 4 images
			String img = p.getImageURL().size() > 0 ? p.getImageURL().get(0) : "";
			Image image = new Image(img, "");
			image.setWidth("170px");

			// authors
//			p.getAuthorId().forEach(a -> {
//				wikiText.add(new Div(new Label("Authors")));
//				Author authr = MDB.getDS().find(Author.class).filter("_id", a).get();
//				if (authr != null) {
//					RouterLink g = new RouterLink("", AuthorView.class, authr.getId());
//					g.add(new Text(authr.getFirstName() + " " + authr.getLastName()));
//					g.addClassName("btn-mr");
//					wikiText.add(g);
//				}
//			});

			FlexLayout flex = new FlexLayout(new Div(image));
			flex.addClassName("sectionblock");
			flex.getStyle().set("marginTop", "1em");
			content.add(flex);
		}
		content.add(new Hr());
		{
			TextArea wikiTextarea = new TextArea("Wiki Text");
			binder.forField(wikiTextarea).bind("description");
			wikiTextarea.addClassName("sectionblock");
			content.add(wikiTextarea);
		}
		content.add(new Hr());
		{
			FormLayout fl = new FormLayout();
			fl.setResponsiveSteps(HtmlC.steps421);

			TextField language = new TextField("Language");
			binder.forField(language).bind("language");
			fl.add(language);

			TextField edition = new TextField("Edition");
			binder.forField(edition).bind("edition");
			fl.add(edition);

			TextField genre = new TextField("Genre");
			binder.forField(genre).bind("genre");
			fl.add(genre);

			TextField ISBN_10 = new TextField("ISBN 10");
			binder.forField(ISBN_10).bind("ISBN_10");
			fl.add(ISBN_10);

			TextField ISBN_13 = new TextField("ISBN 13");
			binder.forField(ISBN_13).bind("ISBN_13");
			fl.add(ISBN_13);

			TextField binding = new TextField("Binding");
			binder.forField(binding).bind("binding");
			fl.add(binding);

			TextField releaseCycle = new TextField("Release Cycle");
			binder.forField(releaseCycle).bind("releaseCycle");
			fl.add(releaseCycle);

			content.add(fl);

//			pB("Language", p.getLanguage());
//			pB("Edition", p.getEdition());
//			pB("Genre", p.getGenre());
//			pB("ISBN-10", p.getISBN_10());
//			pB("ISBN-13", p.getISBN_13());
			pB("NumberOfPages", "" + p.getNumberOfPages());
//			pB("Binding", p.getBinding());
//			pB("ReleaseCycle", p.getReleaseCycle());

			DatePicker firstPublishedDate = new DatePicker("Published Date");
			binder.forField(firstPublishedDate).bind("firstPublishedDate");
			fl.add(firstPublishedDate);

//			if (p.getShippingInformation() != null) {
//				ShippingInformation ship = p.getShippingInformation();
//				pB("Dimensions", StringUtils.isNotEmpty(ship.getDimensions()) ? ship.getDimensions() : "");
//				pB("Weight", ship.getWeight(WeightUnit.LB) + " lb");
//			}

		}

		content.add(new Hr());

		{
			content.add(new Div(new Label("Shipping Information")));
			FormLayout fl = new FormLayout();
			fl.setResponsiveSteps(HtmlC.steps421);

//			ShippingInformation ship = p.getShippingInformation();
//			pB("Dimensions", StringUtils.isNotEmpty(ship.getDimensions()) ? ship.getDimensions() : "");
//			pB("Weight", ship.getWeight(WeightUnit.LB) + " lb");

			TextField dimensions = new TextField("Dimensions");
			binder.forField(dimensions).bind("shippingInformation.dimensions");
			fl.add(dimensions);

//			TextField weight = new TextField("Weight");
//			binder.forField(weight).bind("shippingInformation.weight");
//			fl.add(weight);

			content.add(fl);

		}

		content.add(new Hr());

		SmallButton edittttt = new SmallButton("Edit Listing");
		edittttt.theme("primary");
		edittttt.addClassName("btn-mr");
		edittttt.addClickListener(clk -> {
			if (editmode) {
				if (binder.writeBeanIfValid(p))
					p.save();
			}
			editmode = !editmode;
			edittttt.setText(editmode ? "Save" : "Edit Listing");
			binder.setReadOnly(!editmode);

		});

		SmallButton lostbtn = new SmallButton("Add Purchase Order");
		lostbtn.addClassName("btn-mr");

		SmallButton issuedbtn = new SmallButton("Setup Discontinuation");
		issuedbtn.addClassName("btn-mr");

		Div block2 = new Div(edittttt, lostbtn, issuedbtn);
		block2.getStyle().set("padding", "1em");
		addFooterComponent(block2);

		binder.readBean(p);
		binder.setReadOnly(true);
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
