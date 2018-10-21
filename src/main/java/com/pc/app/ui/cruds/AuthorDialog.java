package com.pc.app.ui.cruds;

import com.pc.app.ui.dialog.MDialog;
import com.pc.entity.Author;
import com.pc.enums.DialogAction;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class AuthorDialog extends MDialog
{
    Binder<Author> pBinder = new Binder<>(Author.class);
    VerticalLayout vLayout = new VerticalLayout();
    
    public AuthorDialog(final Author pBean, DialogAction action, GenericCRUD.OnAction onaction)
    {
        super(action);
        
        switch (action)
        {
            case NEW:
                setHeader(new H1("New Author"));
                break;
            case EDIT:
                setHeader(new H1("Edit Author"));
                break;
            case DELETE:
                setHeader(new H1("Delete Author"));
                break;
            case VIEW:
                setHeader(new H1("View Author"));
                break;
        }
        
        FormLayout authorForm = new FormLayout();
        pBinder.setBean(new Author());
        
        TextField firstName = new TextField("First Name");
        firstName.getElement().setAttribute("colspan", "2").setAttribute("theme", "small");
        firstName.getStyle().set("minHeight", "12em");
        pBinder.forField(firstName).asRequired("Please enter Author First Name").bind("firstName");
        
        TextField lastName = new TextField("Last Name");
        pBinder.forField(lastName).bind("LastName");
        
        TextArea gender = new TextArea("Gender");
        pBinder.forField(gender).bind("gender");
        
        TextField aka = new TextField("Also Known As");
        pBinder.forField(aka).bind("aka");
        
        TextField email = new TextField("Email");
        pBinder.forField(email).bind("Email");
        
        TextField website = new TextField("Website");
        pBinder.forField(website).bind("website");
        
        TextField imageURL = new TextField("Image URL");
        pBinder.forField(imageURL).bind("imageURL");
        
        TextField wikiLink = new TextField("WikiLink");
        pBinder.forField(wikiLink).bind("wikiLink");
        
        TextArea bioGraphy = new TextArea("Biography");
        pBinder.forField(bioGraphy).bind("bioGraphy");
        
        DatePicker dateOfBirth = new DatePicker("DateOfBirth");
        pBinder.forField(dateOfBirth).bind("dateOfBirth");
        
        authorForm.add(firstName, lastName, gender, aka, email, website, imageURL, wikiLink, bioGraphy, dateOfBirth);
        authorForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        authorForm.setWidth("500px");
        setContents(authorForm);
        //authorUI.add(authorForm);
        
        pBinder.readBean(pBean);
        
        // all fields will be locked if viewing or in delete mode
        pBinder.setReadOnly(action == DialogAction.DELETE || action == DialogAction.VIEW);
        
        if (action != DialogAction.VIEW)
        {
        
        }
    }
    
}
