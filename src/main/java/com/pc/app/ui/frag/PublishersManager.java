package com.pc.app.ui.frag;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC.GridHeader;
import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.app.ui.HtmlC.TextFieldClearButton;
import com.pc.app.ui.backend.PublishersDP;
import com.pc.app.ui.dialog.BasicDialog.BeanAction;
import com.pc.app.ui.dialog.EditPublisherDialog;
import com.pc.entity.Publisher;
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

@PageTitle("Publishers")
@Route(value = "publisher", layout = BaseFragment.class)
public class PublishersManager extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	ConfigurableFilterDataProvider<Publisher, Void, String> fdp = new PublishersDP().withConfigurableFilter();

	public PublishersManager() {
		log.info("PublishersManager>>");
		setHeaderText("Publishers");

		TextField searchbox = new TextField();
		searchbox.setValueChangeMode(ValueChangeMode.EAGER);
		searchbox.setPrefixComponent(new Icon("lumo", "search"));
		searchbox.setSuffixComponent(new TextFieldClearButton(searchbox));
		searchbox.setPlaceholder("Search By Publisher Name");
		searchbox.getElement().setAttribute("theme", "small");
		searchbox.getStyle().set("flexGrow", "1").set("maxWidth", "500px");
		searchbox.addClassName("btn-mr");
		searchbox.addValueChangeListener(vc -> fdp.setFilter(vc.getValue()));

		SmallButton addpublisher = new SmallButton("Add Publisher");
		addpublisher.theme("primary");

		addpublisher.onclick(() -> new EditPublisherDialog(new Publisher(), BeanAction.NEW, (bean) -> {
			bean.save();
			fdp.setFilter(null);
			return true;
		}).open());

		FlexLayout headercontrols = new FlexLayout(searchbox, addpublisher);
		headercontrols.getStyle().set("flexGrow", "1"); 
		headercontrols.setJustifyContentMode(JustifyContentMode.END);
		addHeaderBComponent(headercontrols);

		Grid<Publisher> pg = new Grid<>();
		pg.getElement().setAttribute("theme", "no-border");

		pg.addComponentColumn(pub -> {
			SmallButton open = new SmallButton(pub.getPublisherName());
			open.theme("tertiary-inline");
			open.addClickListener(c -> open.getUI().get().navigate(PublisherView.class, pub.getId()));
			return open;
		}).setHeader(new GridHeader("Publisher Name")).setSortProperty(Publisher._publisherName);

		pg.addColumn(Publisher::getPublisherWebsite).setHeader(new GridHeader("Website"));

		pg.addComponentColumn(fex -> {
			SmallButton edit = new SmallButton();
			edit.theme("icon").setIcon(new IronIcon("lumo", "edit"));
			edit.addClickListener(click -> new EditPublisherDialog(fex, BeanAction.EDIT, (bean) -> {
				fdp.refreshAll();
				return true;
			}).open());

//			SmallButton delete = new SmallButton();
//			delete.theme("icon error").setIcon(new IronIcon("lumo", "cross"));
//			delete.addClickListener(click -> new EditPublisherDialog(fex, BeanAction.DELETE, (bean) -> {
//				bean.delete();
//				fdp.setFilter(null);
//				return true;
//			}).open());

			Div fl = new Div(edit);
			fl.getStyle().set("textAlign", "right");
			return fl;
		}).setFlexGrow(0).setWidth("80px");

		pg.setDataProvider(fdp);
		addContent(pg);
	}

}
