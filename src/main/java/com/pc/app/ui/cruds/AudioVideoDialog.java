package com.pc.app.ui.cruds;

import java.util.Arrays;

import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.app.ui.HtmlC.ThemeButton;
import com.pc.app.ui.NumberTextField;
import com.pc.app.ui.dialog.BasicDialog;
import com.pc.entity.AudioVideo;
import com.pc.enums.AudioVideoAspectRatio;
import com.pc.enums.AudioVideoRating;
import com.pc.enums.AudioVideoType;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.vaadin.flow.data.binder.Binder;

public class AudioVideoDialog extends BasicDialog {
	private static final long serialVersionUID = 1L;
	String gid;

	public AudioVideoDialog(final AudioVideo bean, BeanAction action, GenericCRUD.OnAction<AudioVideo> onaction) {
		super(action);

		Binder<AudioVideo> binder = new Binder<>(AudioVideo.class);

		switch (action) {
		case NEW:
			setTitle("Add Audio/Video");
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

		binder.setBean(new AudioVideo());

		TextField title = new TextField("Media Title");
		title.getElement().setAttribute("colspan", "2");
		binder.forField(title).asRequired("Please enter Title").bind("title");

		ComboBox<AudioVideoType> mediaType = new ComboBox<>("Media Type");
		mediaType.setItemLabelGenerator(AudioVideoType::getDisplayText);
		mediaType.setItems(Arrays.asList(AudioVideoType.values()));
		binder.forField(mediaType).asRequired("Please choose a Media Type").bind("audioVideoType");

		ComboBox<AudioVideoRating> rating = new ComboBox<>("Content Rating");
		rating.setItemLabelGenerator(AudioVideoRating::getDisplayText);
		rating.setItems(Arrays.asList(AudioVideoRating.values()));
		binder.forField(rating).asRequired("Please set the Content Rating").bind("metaData.contentRating");

		TextArea description = new TextArea("Media Description");
		description.getStyle().set("minHeight", "16rem").set("maxHeight", "16rem");
		binder.forField(description).bind("description");

		TextField season = new TextField("Season");
		binder.forField(season).bind("metaData.season");

		TextField episode = new TextField("Episode");
		binder.forField(episode).bind("metaData.episode");

//		NumberTextField mediaDuration = new NumberTextField("Media Duration");
//		mediaDuration.centerAlign();
//		binder.forField(mediaDuration).bind("metaData.mediaDurationSeconds");
//
//		if (action != BeanAction.DELETE && action != BeanAction.VIEW) {
//			ThemeButton pluss = new ThemeButton("tertiary-inline", new Icon(VaadinIcon.PLUS));
//			pluss.addClickListener(c -> mediaDuration.setValue(mediaDuration.getValue() + 1));
//			pluss.setTabIndex(8);
//			mediaDuration.setSuffixComponent(pluss);
//
//			ThemeButton minuss = new ThemeButton("tertiary-inline", new Icon(VaadinIcon.MINUS));
//			minuss.addClickListener(c -> {
//				if (mediaDuration.getValue() > 0) {
//					mediaDuration.setValue(mediaDuration.getValue() - 1);
//				}
//			});
//			mediaDuration.setPrefixComponent(minuss);
//		}

		TextField genre = new TextField("Media Genre");
		binder.forField(genre).bind("metaData.genre");

		DatePicker releaseDate = new DatePicker("Release Date");
		binder.forField(releaseDate).bind("releaseDate");

		ComboBox<AudioVideoAspectRatio> aspectRatio = new ComboBox<>("Media Aspect Ratio");
		aspectRatio.setItemLabelGenerator(AudioVideoAspectRatio::getDisplayText);
		aspectRatio.setItems(Arrays.asList(AudioVideoAspectRatio.values()));
		binder.forField(aspectRatio).bind("metaData.aspectRatio");

		MultiFileBuffer multiFileBuffer = new MultiFileBuffer();

		Upload upload = new Upload(multiFileBuffer);
		upload.setAcceptedFileTypes("image/*");
		upload.setMaxFiles(5);

		upload.addSucceededListener(upl -> {
			upl.getFileName();
		});

		FormLayout partA = new FormLayout(title, description, new Div(upload));
		partA.getElement().setAttribute("colspan", "2");

		FormLayout partB = new FormLayout(mediaType, rating, season, episode, releaseDate, genre, aspectRatio);

		FormLayout mixedform = new FormLayout(partA, partB);
		mixedform.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));

		setContent(mixedform);

		binder.readBean(bean);
		binder.setReadOnly(action == BeanAction.DELETE || action == BeanAction.VIEW);

		if (action != BeanAction.VIEW) {

			SmallButton savebutton = new SmallButton(action != BeanAction.DELETE ? "Save" : "Delete");
			savebutton.theme("primary");
			savebutton.addClickListener(e -> {
				if (binder.writeBeanIfValid(bean)) {
					onaction.action(bean);
					close();
				}
			});
			addTerminalComponent(savebutton);
		}

		setSize(DialogSize.LARGE);
	}
}
