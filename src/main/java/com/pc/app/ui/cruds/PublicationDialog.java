package com.pc.app.ui.cruds;

import com.pc.app.ui.NumberTextField;
import com.pc.app.ui.dialog.MDialog;
import com.pc.db.MDB;
import com.pc.entity.Author;
import com.pc.entity.Publication;
import com.pc.enums.DialogAction;
import com.pc.enums.PublicationType;
import com.pc.enums.ReleaseCycle;
import com.pc.utils.ComboBoxManager;
import com.pc.utils.GetLanguages;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PublicationDialog extends MDialog
{
    String gid;
    Binder<Publication> pBinder = new Binder<>(Publication.class);
    VerticalLayout authorsLayout = new VerticalLayout();
    private Publication pBean;
    
    public PublicationDialog(final Publication pBean, DialogAction action, GenericCRUD.OnAction onaction)
    {
        super(action);
        
        
        //gid = IdGenerator.generateId("publication");
        switch (action)
        {
            case NEW:
                setHeader(new H1("New Publication"));
                //(pBean).setUuid(gid);
                break;
            case EDIT:
                setHeader(new H1("Edit Publication"));
                break;
            case DELETE:
                setHeader(new H1("Delete Publication"));
                break;
            case VIEW:
                setHeader(new H1("View Publication"));
                break;
        }
        
        this.pBean = pBean;
        
        pBinder.setBean(new Publication());
    
        TextField title = new TextField("Title");
        title.getElement().setAttribute("colspan", "2").setAttribute("theme", "small");
        title.getStyle().set("minHeight", "12em");
        pBinder.forField(title).asRequired("Please enter Title").bind("title");
    
        TextField binding = new TextField("Binding");
        pBinder.forField(binding).bind("binding");
    
        TextArea description = new TextArea("Description");
        pBinder.forField(description).bind("description");
    
        TextField edition = new TextField("Edition");
        pBinder.forField(edition).bind("edition");
    
        DatePicker firstPublishedDate = new DatePicker("First Published Date");
        pBinder.forField(firstPublishedDate).bind("firstPublishedDate");
    
    
        ComboBox genre = new ComboBox("Genre", ComboBoxManager.getGenres());
        pBinder.forField(genre).bind("genre");
    
        TextField ISBN10 = new TextField("ISBN10");
        pBinder.forField(ISBN10).bind("ISBN_10");
    
        TextField ISBN13 = new TextField("ISBN13");
        pBinder.forField(ISBN13).bind("ISBN_13");
    
        NumberTextField numberOfPages = new NumberTextField("Number of Pages");
        pBinder.forField(numberOfPages).bind("numberOfPages");
    
        Dialog pricingInformation = new Dialog();
    
        ComboBox publicationType = new ComboBox("Publication Type", PublicationType.values());
    
    
        refreshAuthors();
    
        Button authorBtn = new Button("Add Author");
        authorBtn.addClickListener(buttonClickEvent -> {
            new AddEntityDiv(new Author(), action, GenericCRUD.EntityAction.AUTHOR, i -> {
                i.persist(i);
                pBean.getAuthorId().add(i.getId());
                pBean.save();
                refreshAuthors();
            });
        });
        //MDialog authorModal = new MDialog()
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
    
        ComboBox releaseCycle = new ComboBox("Release Cycle");
        List<ReleaseCycle> rcList = Arrays.asList(ReleaseCycle.values());
        List<String> rcString = rcList.stream().map(i -> i.getDisplay()).collect(Collectors.toList());
        releaseCycle.setItems(rcString);
        pBinder.forField(releaseCycle).bind("releaseCycle");
    
        ComboBox language = new ComboBox("Language");
        language.setItems(GetLanguages.getLanguages());
        pBinder.forField(language).bind("language");
    
        FormLayout publicationForm = new FormLayout();
    
        publicationForm.add(title, binding, description, edition, firstPublishedDate, genre, ISBN10, ISBN13, numberOfPages,pricingInformation, publicationType, releaseCycle, language, authorsLayout);
        publicationForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        publicationForm.setWidth("500px");
        setContents(publicationForm);
    
        //publicationUI.add(publicationForm);
    
        pBinder.readBean(pBean);
    
        // all fields will be locked if viewing or in delete mode
        pBinder.setReadOnly(action == DialogAction.DELETE || action == DialogAction.VIEW);
    
        if (action != DialogAction.VIEW)
        {
        
        }
    }
    
    private void refreshAuthors()
    {
        authorsLayout.removeAll();
        
        for (Author a : MDB.getAuthorsInPublication(pBean))
        {
            Button btn = new Button(a.getFirstName() + " " + a.getLastName());
            btn.addClickListener(click -> {
                new AddEntityDiv(a, DialogAction.EDIT, GenericCRUD.EntityAction.AUTHOR, i ->
                {
                    pBean.save();
                    refreshAuthors();
                }).open();
            });
            authorsLayout.add(btn);
        }
    }
    
    private void openAuthorDialog(Author a, DialogAction action, Binder<Publication> pBinder)
    {
        if (action == DialogAction.NEW)
        {
        
        } else
        {
            new AddEntityDiv(pBean.getAuthorId(), action, GenericCRUD.EntityAction.AUTHOR, i -> {
                refreshAuthors();
                i.persist(i);
                pBean.getAuthorId().add(i.getId());
                pBean.save();
            });
        }
        
    }
}
