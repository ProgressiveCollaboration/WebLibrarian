package com.pc.app.ui.cruds;

import java.util.Arrays;

import com.pc.app.ui.dialog.BasicDialog;
import com.pc.entity.AudioVideo;
import com.pc.enums.AudioVideoAspectRatio;
import com.pc.enums.AudioVideoRating;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class AudioVideoDialog extends BasicDialog {
	private static final long serialVersionUID = 1L;
	String gid;
	Binder<AudioVideo> pBinder = new Binder<>(AudioVideo.class);
//	private AudioVideo pBean;

	public AudioVideoDialog(final AudioVideo pBean, BeanAction action, GenericCRUD.OnAction<AudioVideo> onaction) {
		super(action);

		switch (action) {
		case NEW:
			setTitle("Add Audio/Video");
			break;
		case EDIT:
			setTitle("Edit Audio/Video");
			break;
		case DELETE:
			setTitle("Delete AudioVideo");
			break;
		case VIEW:
			setTitle("View AudioVideo");
			break;
		}

		pBinder.setBean(new AudioVideo());

		TextField title = new TextField("Title");
		pBinder.forField(title).asRequired("Please enter Title").bind("title");

		TextArea description = new TextArea("Description");
		description.getElement().setAttribute("colspan", "2").setAttribute("theme", "small");
		pBinder.forField(description).bind("description");

		DatePicker releaseDate = new DatePicker("Release Date");
		pBinder.forField(description).bind("description");

		FormLayout audioVideoForm = new FormLayout();
		audioVideoForm.add(title, description, releaseDate);
		audioVideoForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
		setContent(audioVideoForm);

		TextField season = new TextField("Season");
		pBinder.forField(season).bind("metaData.season");

		TextField episode = new TextField("Episode");
		pBinder.forField(episode).bind("metaData.episode");

		TextField mediaDuration = new TextField("Media Duration");
//		pBinder.forField(mediaDuration).bind("metaData.mediaDurationSeconds");

		TextField genre = new TextField("Genre");
		pBinder.forField(genre).bind("metaData.genre");

		ComboBox<AudioVideoRating> contentRating = new ComboBox<>("Content Rating");
		contentRating.setItemLabelGenerator(AudioVideoRating::getDisplayText);
		contentRating.setItems(Arrays.asList(AudioVideoRating.values()));
		pBinder.forField(contentRating).bind("metaData.contentRating");

		ComboBox<AudioVideoAspectRatio> aspectRatio = new ComboBox<>("Aspect Ratio");
		aspectRatio.setItemLabelGenerator(AudioVideoAspectRatio::getDisplayText);
		aspectRatio.setItems(Arrays.asList(AudioVideoAspectRatio.values()));
		pBinder.forField(aspectRatio).bind("metaData.aspectRatio");

		FormLayout metaDataForm = new FormLayout();
		metaDataForm.add(season, episode, mediaDuration, genre, contentRating, aspectRatio);
		metaDataForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
		setContent(metaDataForm);

		pBinder.readBean(pBean);

		pBinder.setReadOnly(action == BeanAction.DELETE || action == BeanAction.VIEW);

		if (action != BeanAction.VIEW) {

		}

		setSize(DialogSize.LARGE);
	}
}
