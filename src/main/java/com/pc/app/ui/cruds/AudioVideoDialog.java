package com.pc.app.ui.cruds;

import com.pc.app.ui.dialog.MDialog;
import com.pc.entity.AudioVideo;
import com.pc.enums.ContentRating;
import com.pc.enums.DialogAction;
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

public class AudioVideoDialog extends MDialog
{
    String gid;
    Binder<AudioVideo> pBinder = new Binder<>(AudioVideo.class);
    private AudioVideo pBean;
    
    public AudioVideoDialog(final AudioVideo pBean, DialogAction action, GenericCRUD.OnAction onaction)
    {
        super(action);
        
            //gid = IdGenerator.generateId("audioVideo");
            switch (action) {
                case NEW:
                    setHeader(new H1("New AudioVideo"));
                    break;
                case EDIT:
                    setHeader(new H1("Edit AudioVideo"));
                    break;
                case DELETE:
                    setHeader(new H1("Delete AudioVideo"));
                    break;
                case VIEW:
                    setHeader(new H1("View AudioVideo"));
                    break;
            }
        
        pBinder.setBean(new AudioVideo());
    
        TextField title = new TextField("Title");
        title.getElement().setAttribute("colspan", "2").setAttribute("theme", "small");
        title.getStyle().set("minHeight", "12em");
        pBinder.forField(title).asRequired("Please enter Title").bind("title");
    
        TextArea description = new TextArea("Description");
        pBinder.forField(description).bind("description");
    
        DatePicker releaseDate = new DatePicker("Release Date");
        pBinder.forField(description).bind("description");
        
        FormLayout audioVideoForm = new FormLayout();
        audioVideoForm.add(title, description, releaseDate);
        audioVideoForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        audioVideoForm.setWidth("500px");
        setContents(audioVideoForm);
    
        Dialog metaData = new Dialog();
        FormLayout metaDataForm = new FormLayout();
        TextField season = new TextField("Season");
        pBinder.forField(season).bind("metadata.season");
    
        TextField episode = new TextField("Episode");
        pBinder.forField(episode).bind("metadata.episode");
    
        TextField mediaDuration = new TextField("Media Duration");
        pBinder.forField(mediaDuration).bind("metadata.mediaDuration");
    
        TextField genre = new TextField("Genre");
        pBinder.forField(genre).bind("metadata.genre");
    
        ComboBox contentRating = new ComboBox("Content Rating");
        contentRating.setItems(ContentRating.values());
        pBinder.forField(contentRating).bind("metadata.contentRating");
    
        ComboBox mediaRating = new ComboBox("Media Rating");
        Integer [] ratings = {1,2,3,4,5};
        mediaRating.setItems(Arrays.asList(ratings));
        pBinder.forField(mediaRating).bind("metadata.mediaRating");
    
        ComboBox aspectRatio = new ComboBox("Aspect Ratio");
        String [] ratio = {"4:3", "16:9"};
        aspectRatio.setItems(Arrays.asList(ratio));
        pBinder.forField(aspectRatio).bind("metadata.aspectRatio");
    
        TextField imageURL = new TextField("Image URL");
        pBinder.forField(imageURL).bind("metadata.imageURL");
    
        metaDataForm.add(season, episode, mediaDuration, genre, contentRating,mediaRating, aspectRatio, imageURL);
        metaDataForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        metaDataForm.setWidth("500px");
        setContents(metaDataForm);
        //audioVideoForm.add(metaDataForm);
        //audioVideoUI.add(audioVideoForm);
    
        setContents(audioVideoForm);
    
        pBinder.readBean(pBean);
    
        // all fields will be locked if viewing or in delete mode
        pBinder.setReadOnly(action == DialogAction.DELETE || action == DialogAction.VIEW);
    
        if (action != DialogAction.VIEW) {
        
        
        }
    }
}
