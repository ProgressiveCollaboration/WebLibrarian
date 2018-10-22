package com.pc.app.ui.frag;

import java.util.stream.Collectors;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC.GridHeader;
import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.app.ui.backend.PublicationsDP;
import com.pc.app.ui.backend.PublicationsDP.PublicationsView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Publications")
@Route(value = "publications", layout = BaseFragment.class)
public class PublicationsManager extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	ConfigurableFilterDataProvider<PublicationsView, Void, String> filterabledp = new PublicationsDP()
			.withConfigurableFilter();

	public PublicationsManager() {
		log.info("Publications>>");
		setHeaderText("Publications");

		TextField searchbox = new TextField();
		searchbox.setValueChangeMode(ValueChangeMode.EAGER);
		searchbox.setPrefixComponent(new Icon("lumo", "search"));
		searchbox.setPlaceholder("Search By Title, ISBN");
		searchbox.getElement().setAttribute("theme", "small");
		searchbox.getStyle().set("flexGrow", "1").set("maxWidth", "500px");
		searchbox.addClassName("btn-mr");
		searchbox.addValueChangeListener(vc -> filterabledp.setFilter(vc.getValue()));

//		SmallButton importbtn = new SmallButton("Import Data");
//		importbtn.addClassName("btn-mr");
//		SmallButton exportbtn = new SmallButton("Export Data");
//		exportbtn.addClassName("btn-mr");
		SmallButton addpublicationbtn = new SmallButton("Add Publication");
		addpublicationbtn.theme("primary");

		FlexLayout headercontrols = new FlexLayout(searchbox, addpublicationbtn);
		headercontrols.getStyle().set("flexGrow", "1"); 
		headercontrols.setJustifyContentMode(JustifyContentMode.END);
		addHeaderBComponent(headercontrols);

		Grid<PublicationsView> pg = new Grid<>();
		pg.getElement().setAttribute("theme", "no-border");

		pg.addComponentColumn(pub -> {
			SmallButton open = new SmallButton(pub.getISBN_10());
			open.theme("tertiary-inline");
			open.addClickListener(c -> open.getUI().get().navigate(PublicationView.class, pub.getId()));
			return open;
		}).setHeader(new GridHeader("ISBN-10"))//
				.setSortProperty("ISBN_10") //
				.setFlexGrow(0)//
				.setWidth("120px");

		pg.addColumn(PublicationsView::getTitle).setHeader(new GridHeader("Title")).setSortProperty("Title"); 
		pg.addColumn(a -> a.getAuthors().stream().map(t -> t.getAka()).collect(Collectors.joining(", "))).setHeader(new GridHeader("Author")).setSortProperty("Author");
		pg.addColumn(PublicationsView::getEdition).setHeader(new GridHeader("Edition")).setSortProperty("Edition"); 
		pg.addColumn(PublicationsView::getGenre).setHeader(new GridHeader("Genre")).setSortProperty("Genre").setFlexGrow(0).setWidth("120px");
		pg.addColumn(a -> a.getPublisher().getPublisherName()).setHeader(new GridHeader("Publisher")).setSortProperty("Publisher");
		pg.setDataProvider(filterabledp);
		addContent(pg);
	}

}
