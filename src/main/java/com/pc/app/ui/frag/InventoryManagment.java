package com.pc.app.ui.frag;

import org.apache.commons.lang3.StringUtils;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC.FlexMe;
import com.pc.app.ui.HtmlC.GridHeader;
import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.app.ui.HtmlC.TextFieldClearButton;
import com.pc.app.ui.backend.PublicationsDP;
import com.pc.app.ui.backend.PublicationsDP.PublicationsView;
import com.pc.app.ui.backend.PublishersDP;
import com.pc.app.ui.dialog.BasicDialog.BeanAction;
import com.pc.app.ui.dialog.EditPublisherDialog;
import com.pc.entity.Publisher;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Inventory Managment")
@Route(value = "inventory", layout = BaseFragment.class)
public class InventoryManagment extends Fragment implements HasUrlParameter<String> {

	private static final long serialVersionUID = 3837883965132583616L;

	public static final String _publishers = "publishers";
	public static final String _authors = "authors";
	public static final String _publications = "publications";
	private Tabs tabs = new Tabs();
	private Div innercontent = new Div();

	public InventoryManagment() {
		setHeaderText("Inventory");

		Tab tab1 = new Tab("Publishers");
		Tab tab2 = new Tab("Authors");
		Tab tab3 = new Tab("Publications");
		tabs.add(tab1, tab2, tab3);
		tabs.getElement().setAttribute("theme", "small");

		tabs.addSelectedChangeListener(lstn -> {
			switch (tabs.getSelectedIndex()) {
			case 0:
				tabs.getUI().ifPresent(ui -> ui.navigate(InventoryManagment.class, _publishers));
				break;
			case 1:
				tabs.getUI().ifPresent(ui -> ui.navigate(InventoryManagment.class, _authors));
				break;
			default:
				tabs.getUI().ifPresent(ui -> ui.navigate(InventoryManagment.class, _publications));
				break;
			}

		});

		addContent(tabs, innercontent);
		addFooterComponent(new SmallButton("Footer Button"));
	}

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {

		if (StringUtils.isBlank(parameter))
			parameter = _publications;

		System.out.println("buildPageContent -> " + parameter);

		switch (parameter) {
		case _publishers:
			tabs.setSelectedIndex(0);
			buildPageContent(0);
			break;
		case _authors:
			tabs.setSelectedIndex(1);
			buildPageContent(1);
			break;
		case _publications:
			tabs.setSelectedIndex(2);
			buildPageContent(2);
			break;

		default:
			break;
		}

	}

	void buildPageContent(int index) {
		innercontent.removeAll();

		switch (index) {
		case 0:
			buildforPublishers(innercontent);
			break;
		case 1:
			buildforAuthors(innercontent);
			break;
		case 2:
			buildforPublications(innercontent);
			break;
		}

	}

	private void buildforAuthors(Div parent) {

		TextField searchbox = new TextField();
		searchbox.setValueChangeMode(ValueChangeMode.EAGER);
		searchbox.setPrefixComponent(new Icon("lumo", "search"));
		searchbox.setPlaceholder("Search By Author Name");
		searchbox.getElement().setAttribute("theme", "small");
		searchbox.getStyle().set("flexGrow", "1");
		searchbox.addClassName("btn-mr");
		// searchbox.addValueChangeListener(vc ->
		// filterabledp.setFilter(vc.getValue()));

		SmallButton addpublicationbtn = new SmallButton("Add Author");
		addpublicationbtn.theme("primary");

		Div btngroup = new Div(addpublicationbtn);

		FlexMe sectionblock = new FlexMe(searchbox, btngroup);
		sectionblock.setClassName("sectionblock");
		parent.add(sectionblock);

	}

	private void buildforPublishers(Div parent) {

		ConfigurableFilterDataProvider<Publisher, Void, String> fdp = new PublishersDP().withConfigurableFilter();

		TextField searchbox = new TextField();
		searchbox.setValueChangeMode(ValueChangeMode.EAGER);
		searchbox.setPrefixComponent(new Icon("lumo", "search"));
		searchbox.setSuffixComponent(new TextFieldClearButton(searchbox));
		searchbox.setPlaceholder("Search By Publisher Name");
		searchbox.getElement().setAttribute("theme", "small");
		searchbox.getStyle().set("flexGrow", "1");
		searchbox.addClassName("btn-mr");
		searchbox.addValueChangeListener(vc -> fdp.setFilter(vc.getValue()));

		SmallButton addpublisher = new SmallButton("Add Publisher");
		addpublisher.theme("primary");

		addpublisher.onclick(() -> new EditPublisherDialog(new Publisher(), BeanAction.NEW, (bean) -> {
			bean.save();
			fdp.setFilter(null);
			// fdp.refreshAll();
			return true;
		}).open());
		Div btngroup = new Div(addpublisher);

		FlexMe sectionblock = new FlexMe(searchbox, btngroup);
		sectionblock.setClassName("sectionblock");
		parent.add(sectionblock);

		Grid<Publisher> pg = new Grid<>();
		pg.addClassName("sectionblock");
		pg.getElement().setAttribute("theme", "no-border");

		pg.addComponentColumn(pub -> {
			SmallButton open = new SmallButton(pub.getPublisherName());
			open.theme("tertiary-inline contrast");
			open.addClickListener(c -> open.getUI().get().navigate(PublisherView.class, pub.getId()));
			return open;
		}).setHeader(new GridHeader("Publisher Name")).setSortProperty(Publisher._publisherName);

		pg.addColumn(Publisher::getPublisherWebsite).setHeader(new GridHeader("Website"));

		pg.addComponentColumn(fex -> {

			SmallButton edit = new SmallButton();
			edit.theme("contrast").setIcon(new IronIcon("lumo", "edit"));
			edit.addClassName("btn-mr");
			edit.addClickListener(click -> new EditPublisherDialog(fex, BeanAction.EDIT, (bean) -> {
				bean.save();
				fdp.refreshItem(fex);
				return true;
			}).open());

			SmallButton delete = new SmallButton();
			delete.theme("icon error").setIcon(new IronIcon("lumo", "cross"));
			delete.addClickListener(click -> new EditPublisherDialog(fex, BeanAction.DELETE, (bean) -> {
				bean.delete();
				fdp.setFilter(null);
				return true;
			}).open());

			Div fl = new Div(edit, delete);
			fl.getStyle().set("textAlign", "right");
			return fl;
		}).setFlexGrow(0).setWidth("160px");

		pg.setDataProvider(fdp);
		sectionblock.add(pg);
		parent.add(pg);

	}

	private void buildforPublications(Div parent) {

		ConfigurableFilterDataProvider<PublicationsView, Void, String> filterabledp = new PublicationsDP()
				.withConfigurableFilter();

		TextField searchbox = new TextField();
		searchbox.setValueChangeMode(ValueChangeMode.EAGER);
		searchbox.setPrefixComponent(new Icon("lumo", "search"));
		searchbox.setPlaceholder("Search By Title, ISBN");
		searchbox.getElement().setAttribute("theme", "small");
		searchbox.getStyle().set("flexGrow", "1");
		searchbox.addClassName("btn-mr");
		searchbox.addValueChangeListener(vc -> filterabledp.setFilter(vc.getValue()));

		SmallButton importbtn = new SmallButton("Import Data");
		importbtn.addClassName("btn-mr");
		SmallButton exportbtn = new SmallButton("Export Data");
		exportbtn.addClassName("btn-mr");
		SmallButton addpublicationbtn = new SmallButton("Add Publication");
		addpublicationbtn.theme("primary");

		Div btngroup = new Div(importbtn, exportbtn, addpublicationbtn);

		FlexMe sectionblock = new FlexMe(searchbox, btngroup);
		sectionblock.setClassName("sectionblock");
		parent.add(sectionblock);

		Grid<PublicationsView> pg = new Grid<>();
		pg.addClassName("sectionblock");
		pg.getElement().setAttribute("theme", "no-border");
//		pg.addColumn(Publication::getId).setHeader(new GridHeader("ID"));

		pg.addComponentColumn(pub -> {
			SmallButton open = new SmallButton(pub.getISBN_10());
			open.theme("tertiary-inline contrast");
			open.addClickListener(c -> open.getUI().get().navigate(PublicationView.class, pub.getId()));
			return open;
		}).setHeader(new GridHeader("ISBN-10"))//
				.setSortProperty("ISBN_10") //
				.setFlexGrow(0)//
				.setWidth("120px");

		pg.addColumn(PublicationsView::getTitle).setHeader(new GridHeader("Title")).setSortProperty("Title");
		pg.addColumn(PublicationsView::getDescription).setHeader(new GridHeader("Description"));
		pg.addColumn(PublicationsView::getGenre).setHeader(new GridHeader("Genre")).setSortProperty("Genre")
				.setFlexGrow(0).setWidth("120px");
		pg.addColumn(PublicationsView::getPublisherId).setHeader(new GridHeader("Publisher"))
				.setSortProperty("Publisher");
		pg.addColumn(PublicationsView::getAuthorId).setHeader(new GridHeader("Author")).setSortProperty("Author");
//		pg.addColumn(Publication::getFirstPublishedDate);

		pg.setDataProvider(filterabledp);

		sectionblock.add(pg);

		parent.add(pg);
	}
}
