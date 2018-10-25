package com.pc.app.ui.dialog;

import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.app.ui.NumberTextField;
import com.pc.app.ui.cruds.AddEntityDiv;
import com.pc.app.ui.cruds.GenericCRUD;
import com.pc.entity.Author;
import com.pc.entity.Publication;
import com.pc.enums.PublicationType;
import com.pc.enums.ReleaseCycle;
import com.pc.utils.ComboBoxManager;
import com.pc.utils.GetLanguages;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class PublicationDialog extends BasicDialog {
	private static final long serialVersionUID = 1L;
	String gid;
	Binder<Publication> pBinder = new Binder<>(Publication.class);
	VerticalLayout authorsLayout = new VerticalLayout();
	private Publication bean;

	public PublicationDialog(Publication bean, BeanAction beanAction, Action<Publication> action) {
		super(beanAction);

		switch (beanAction) {
		case NEW:
			setTitle("New Publication");
			break;
		case EDIT:
			setTitle("Edit " + bean.getTitle());
			break;
		case DELETE:
			setTitle("Delete " + bean.getTitle());
			break;
		case VIEW:
			setTitle(bean.getTitle());
			break;
		}

		pBinder.setBean(new Publication());

		SmallButton searchISBN = new SmallButton("Find...");
//		searchISBN.theme("primary");
		searchISBN.addClickListener(clk -> {
			new ISBNPublicationFinderDialog(nbean -> {
				pBinder.readBean(nbean);
				return true;
			}).open();

		});
		addHeaderBComponent(searchISBN);

		this.bean = bean;

		TextField title = new TextField("Title");
		title.getElement().setAttribute("colspan", "2");
		pBinder.forField(title).asRequired("Please enter A Title").bind("title");

		TextField binding = new TextField("Binding");
		pBinder.forField(binding).bind("binding");

		TextArea description = new TextArea("Description");
		pBinder.forField(description).bind("description");

		TextField edition = new TextField("Edition");
		pBinder.forField(edition).bind("edition");

		DatePicker firstPublishedDate = new DatePicker("First Published Date");
		pBinder.forField(firstPublishedDate).bind("firstPublishedDate");

		ComboBox<String> genre = new ComboBox<>("Genre", ComboBoxManager.getGenres());
		pBinder.forField(genre).bind("genre");

		TextField ISBN10 = new TextField("ISBN");
		pBinder.forField(ISBN10).bind("ISBN_10");

		TextField ISBN13 = new TextField("ISBN13");
		pBinder.forField(ISBN13).bind("ISBN_13");

		NumberTextField numberOfPages = new NumberTextField("Number of Pages");
		pBinder.forField(numberOfPages).bind("numberOfPages");

		Dialog pricingInformation = new Dialog();

		ComboBox<PublicationType> publicationType = new ComboBox<>("Publication Type", PublicationType.values());
		publicationType.setItemLabelGenerator(PublicationType::getDisplayText);

		refreshAuthors();

		Button authorBtn = new Button("Add Author");
//		authorBtn.addClickListener(buttonClickEvent -> {
//			new AddEntityDiv(new Author(), action, GenericCRUD.EntityAction.AUTHOR, i -> {
//				i.persist(i);
//				bean.getAuthorId().add(i.getId());
//				bean.save();
//				refreshAuthors();
//			});
//		});
		// MDialog authorModal = new MDialog()
//        ComboBox author = new ComboBox("Author");
//        List<Author> authors = MDB.getAuthors();
//        List<String> authorCombo = authors.stream().map(a -> a.getFirstName() + " " + a.getLastName()).collect(Collectors.toList());
//        author.setItems(authorCombo);
//        pBinder.forField(author).bind("authorId");

//        ComboBox publisher = new ComboBox("Publisher");
//        List<Publisher> publishers = MDB.getPublishers();
//        List<String> publishersCombo = publishers.stream().map(a -> a.getPublisherName()).collect(Collectors.toList());
//        publisher.setItems(publishersCombo);
//        pBinder.forField(publisher).bind("publisherId");

		ComboBox<ReleaseCycle> releaseCycle = new ComboBox<>("Release Cycle", ReleaseCycle.values());
		releaseCycle.setItemLabelGenerator(ReleaseCycle::getDisplayText);

		pBinder.forField(releaseCycle).bind("releaseCycle");

		ComboBox<String> language = new ComboBox<>("Language");
		language.setItems(GetLanguages.getLanguages());
		pBinder.forField(language).bind("language");

		FormLayout publicationForm = new FormLayout();

		publicationForm.add(publicationType, ISBN10, ISBN13, title, edition, genre, language, binding, description,
				firstPublishedDate, numberOfPages, pricingInformation, releaseCycle, authorsLayout);
		publicationForm.getElement().setAttribute("theme", "small");
		publicationForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
		setContent(publicationForm);

		// publicationUI.add(publicationForm);

		pBinder.readBean(bean);

		// all fields will be locked if viewing or in delete mode
		pBinder.setReadOnly(beanAction == BeanAction.DELETE || beanAction == BeanAction.VIEW);

		if (beanAction != BeanAction.VIEW) {

		}

		setSize(DialogSize.LARGE);

	}

	private void refreshAuthors() {
//		authorsLayout.removeAll();
//
//		for (Author a : MDB.getAuthorsInPublication(bean)) {
//			Button btn = new Button(a.getFirstName() + " " + a.getLastName());
//			btn.addClickListener(click -> {
//				new AddEntityDiv(a, BeanAction.EDIT, GenericCRUD.EntityAction.AUTHOR, i -> {
//					bean.save();
//					refreshAuthors();
//				}).open();
//			});
//			authorsLayout.add(btn);
//		}
	}

	private void openAuthorDialog(Author a, BeanAction action, Binder<Publication> pBinder) {
		if (action == BeanAction.NEW) {

		} else {
			new AddEntityDiv(bean.getAuthorId(), action, GenericCRUD.EntityAction.AUTHOR, i -> {
				refreshAuthors();
				i.persist(i);
				bean.getAuthorId().add(i.getId());
				bean.save();
			});
		}

	}
}
