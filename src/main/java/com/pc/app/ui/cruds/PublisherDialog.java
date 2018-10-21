package com.pc.app.ui.cruds;

import com.pc.app.ui.dialog.MDialog;
import com.pc.entity.Publisher;
import com.pc.enums.DialogAction;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class PublisherDialog extends MDialog
{
    Binder<Publisher> pBinder = new Binder<>(Publisher.class);
    
    public PublisherDialog(final Publisher pBean, DialogAction action, GenericCRUD.OnAction onaction)
    {
        super(action);
        switch (action) {
            case NEW:
                setHeader(new H1("New Publisher"));
                break;
            case EDIT:
                setHeader(new H1("Edit Publisher"));
                break;
            case DELETE:
                setHeader(new H1("Delete Publisher"));
                break;
            case VIEW:
                setHeader(new H1("View Publisher"));
                break;
        }
    
        Div publisherUI = new Div();
        
        pBinder.setBean(new Publisher());
    
        TextField publisherName = new TextField("Publisher Name");
        publisherName.getElement().setAttribute("colspan", "2").setAttribute("theme", "small");
        publisherName.getStyle().set("minHeight", "12em");
        pBinder.forField(publisherName).asRequired("Please enter Publisher Name").bind("publisherName");
    
        TextField publisherWebsite = new TextField("Publisher Website");
        pBinder.forField(publisherWebsite).bind("publisherWebsite");
    
        TextArea copyright = new TextArea("Copyright");
        pBinder.forField(copyright).bind("copyright");
    
        TextField wikiLink = new TextField("WikiLink");
        pBinder.forField(wikiLink).bind("wikiLink");
    
        TextField imageUrl = new TextField("Image Url");
        pBinder.forField(imageUrl).bind("imageUrl");
    
        FormLayout publisherForm = new FormLayout();
        publisherForm.add(publisherName, publisherWebsite, copyright, wikiLink, imageUrl);
        publisherForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        publisherForm.setWidth("500px");
        setContents(publisherForm);
    
        //publisherUI.add(publisherForm);
    
        pBinder.readBean(pBean);
    
        // all fields will be locked if viewing or in delete mode
        pBinder.setReadOnly(action == DialogAction.DELETE || action == DialogAction.VIEW);
    
        if (action != DialogAction.VIEW) {
        }
    }
}
