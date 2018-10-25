package com.pc.app.ui.dialog;

import java.util.List;

import com.pc.api.OCLCHelper;
import com.pc.api.OCLCHelper.OCLCWork;
import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.app.ui.NumberTextField;
import com.pc.entity.Publication;
import com.pc.enums.PublicationType;
import com.pc.utils.ComboBoxManager;
import com.pc.utils.GetLanguages;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;

public class ISBNPublicationFinderDialog extends BasicDialog {
	private static final long serialVersionUID = 1L;

	Binder<Publication> pBinder = new Binder<>(Publication.class);

	public ISBNPublicationFinderDialog(Action<Publication> action) {
		super(BeanAction.EDIT);

		setTitle("ISBN Publication Search");

		Publication bean = new Publication();

		pBinder.setBean(bean);

		TextField ISBN10 = new TextField();
		ISBN10.setPattern("[0-9]*");
		ISBN10.addClassName("btn-mr");
		ISBN10.setPlaceholder("ISBN");
		ISBN10.getElement().setAttribute("theme", "small");
		pBinder.forField(ISBN10).asRequired()
				.withValidator(new StringLengthValidator("Please enter a valid ISBN Number", 10, 13)).bind("ISBN_10");

		SmallButton searchISBN = new SmallButton(new Icon("lumo", "search"));
		searchISBN.theme("primary");
		searchISBN.addClickListener(clk -> {

			if (pBinder.validate().isOk()) {
				String value = ISBN10.getValue();
				if (value.length() == 10) {
					// isbn10
					bean.setISBN_10(value);
					bean.setISBN_13(null);
					List<OCLCWork> res = OCLCHelper.findByISBN(value);
					if (res != null && res.size() > 0) {
						OCLCWork w = res.get(0);
						Publication p = new Publication();
						p.setISBN_10(value);
						populateBean(w, p);
						pBinder.readBean(p);
					}

				} else if (value.length() == 13) {
					// isbn13
					bean.setISBN_13(value);
					bean.setISBN_10(null);
					List<OCLCWork> res = OCLCHelper.findByISBN(value);
					if (res != null && res.size() > 0) {
						OCLCWork w = res.get(0);
						Publication p = new Publication();
						p.setISBN_13(value);
						populateBean(w, p);
						pBinder.readBean(p);
					}
				}
			}

		});

		addHeaderBComponent(new Div(ISBN10, searchISBN));

		TextField title = new TextField("Title");
		title.getElement().setAttribute("colspan", "2");
		pBinder.forField(title).bind("title");

		TextField binding = new TextField("Binding");
		pBinder.forField(binding).bind("binding");

		TextArea description = new TextArea("Description");
		description.getElement().setAttribute("colspan", "2");
		pBinder.forField(description).bind("description");

		TextField edition = new TextField("Edition");
		pBinder.forField(edition).bind("edition");

		DatePicker firstPublishedDate = new DatePicker("First Published Date");
		pBinder.forField(firstPublishedDate).bind("firstPublishedDate");

		ComboBox<String> genre = new ComboBox<>("Genre", ComboBoxManager.getGenres());
		pBinder.forField(genre).bind("genre");

		NumberTextField numberOfPages = new NumberTextField("Number of Pages");
		pBinder.forField(numberOfPages).bind("numberOfPages");

		ComboBox<PublicationType> publicationType = new ComboBox<>("Publication Type", PublicationType.values());
		publicationType.setItemLabelGenerator(PublicationType::getDisplayText);
		pBinder.forField(publicationType).bind("publicationType");

		ComboBox<String> language = new ComboBox<>("Language");
		language.setItems(GetLanguages.getLanguages());
		pBinder.forField(language).bind("language");

		FormLayout publicationForm = new FormLayout();

		publicationForm.add(publicationType, title, edition, genre, language, binding, description, firstPublishedDate,
				numberOfPages);
		publicationForm.getElement().setAttribute("theme", "small");
		publicationForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
		setContent(publicationForm);

		addFormComponent(new SmallButton("Clear").onclick(() -> pBinder.readBean(new Publication())));

		SmallButton submit = new SmallButton("Confirm");
		submit.theme("primary");
		submit.addClickListener(e -> {
			pBinder.writeBeanIfValid(bean);
			action.action(pBinder.getBean());
			close();
		});
		addTerminalComponent(submit);

	}

	private void populateBean(OCLCWork w, Publication p) {
		System.out.println("populateBean()" + w.toString());
		p.setTitle(w.title);
		p.setEdition(w.editions);
//		p.setAuthorId(authorId);(w.author);
		p.setPublicationType(w.format);
		p.setDescription(w.toString());

	}
}
