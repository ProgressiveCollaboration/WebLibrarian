package com.pc.app.ui.frag;

import java.time.format.DateTimeFormatter;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC;
import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.app.ui.HtmlC.TextFieldClearButton;
import com.pc.app.ui.backend.AudioVideoDP;
import com.pc.app.ui.cruds.AudioVideoDialog;
import com.pc.app.ui.dialog.BasicDialog.BeanAction;
import com.pc.entity.AudioVideo;
import com.pc.enums.AudioVideoType;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("AudioVideo")
@Route(value = "audiovideo", layout = BaseFragment.class)
public class AudioVideoManager extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	ConfigurableFilterDataProvider<AudioVideo, Void, String> filterabledp = new AudioVideoDP().withConfigurableFilter();

	public AudioVideoManager() {
		log.info("AudioVideoManager>>");

		setHeaderText("Audio / Video");
		TextField searchbox = new TextField();
		searchbox.setValueChangeMode(ValueChangeMode.EAGER);
		searchbox.setPrefixComponent(new Icon("lumo", "search"));
		searchbox.setSuffixComponent(new TextFieldClearButton(searchbox));
		searchbox.setPlaceholder("Search By Title");
		searchbox.getElement().setAttribute("theme", "small");
		searchbox.getStyle().set("flexGrow", "1").set("maxWidth", "340px");
		searchbox.addClassName("btn-mr");
		searchbox.addValueChangeListener(vc -> filterabledp.setFilter(vc.getValue()));

		SmallButton addentity = new SmallButton(new Icon("lumo", "plus"));
		addentity.addClickListener(clk -> new AudioVideoDialog(new AudioVideo(), BeanAction.NEW, (bean) -> {
			bean.save();
			filterabledp.refreshAll();
		}).open());
		addentity.theme("primary");

		FlexLayout headercontrols = new FlexLayout(searchbox, addentity);
		headercontrols.getStyle().set("flexGrow", "1");
		headercontrols.setJustifyContentMode(JustifyContentMode.END);
		addHeaderBComponent(headercontrols);

		Grid<AudioVideo> pg = new Grid<>();
		pg.getElement().setAttribute("theme", "no-border");
		pg.addComponentColumn(author -> {
			SmallButton open = new SmallButton(author.getTitle());
			open.theme("tertiary-inline");
			return open;
		}).setHeader("Name");
		pg.addColumn(a -> AudioVideoType.getDisplayText(a.getAudioVideoType())).setHeader("Format").setFlexGrow(0)
				.setWidth(HtmlC.G_W_EMAIL);
		pg.addColumn(a -> a.getMetaData().getMediaDurationSeconds()).setHeader("Duration");
		pg.addColumn(a -> a.getMetaData().getContentRating()).setHeader("Rating").setFlexGrow(0)
				.setWidth(HtmlC.G_W_EMAIL);
		pg.addColumn(a -> a.getMetaData().getSeason()).setHeader("Season").setFlexGrow(0).setWidth(HtmlC.G_W_EMAIL);

		pg.addColumn(a -> a.getCreatedDate().format(DateTimeFormatter.ISO_DATE)).setHeader("Added").setFlexGrow(0)
				.setWidth(HtmlC.G_W_DATE);
		pg.addComponentColumn(av -> {
			SmallButton edit = new SmallButton();
			edit.theme("icon").setIcon(new IronIcon("lumo", "edit"));
			edit.addClickListener(clk -> new AudioVideoDialog(av, BeanAction.NEW, (bean) -> {
				bean.save();
				filterabledp.refreshItem(av);
			}).open());
			Div fl = new Div(edit);
			fl.getStyle().set("textAlign", "right");
			return fl;
		}).setFlexGrow(0).setWidth("80px");

		pg.setDataProvider(filterabledp);
		addContent(pg);

	}

}
