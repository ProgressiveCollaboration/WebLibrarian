package com.pc.app.ui.frag;

import java.time.format.DateTimeFormatter;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC;
import com.pc.app.ui.HtmlC.SmallButton;
import com.pc.app.ui.HtmlC.TextFieldClearButton;
import com.pc.app.ui.backend.AuthorsDP;
import com.pc.entity.Author;
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

@PageTitle("Authors")
@Route(value = "authors", layout = BaseFragment.class)
public class AuthorsManager extends Fragment {

	private static final long serialVersionUID = 3837883965132583616L;

	ConfigurableFilterDataProvider<Author, Void, String> filterabledp = new AuthorsDP().withConfigurableFilter();

	public AuthorsManager() {
		log.info("AuthorsManager>>");
		setHeaderText("Authors");

		TextField searchbox = new TextField();
		searchbox.setValueChangeMode(ValueChangeMode.EAGER);
		searchbox.setPrefixComponent(new Icon("lumo", "search"));
		searchbox.setSuffixComponent(new TextFieldClearButton(searchbox));
		searchbox.setPlaceholder("Search By Name");
		searchbox.getElement().setAttribute("theme", "small");
		searchbox.getStyle().set("flexGrow", "1").set("maxWidth", "340px");
		searchbox.addClassName("btn-mr");
		searchbox.addValueChangeListener(vc -> filterabledp.setFilter(vc.getValue()));

		SmallButton addentity = new SmallButton(new Icon("lumo", "plus"));
		addentity.theme("primary");

		FlexLayout headercontrols = new FlexLayout(searchbox, addentity);
		headercontrols.getStyle().set("flexGrow", "1");
		headercontrols.setJustifyContentMode(JustifyContentMode.END);
		addHeaderBComponent(headercontrols);

		Grid<Author> pg = new Grid<>();
		pg.getElement().setAttribute("theme", "no-border");

		pg.addComponentColumn(author -> {
			SmallButton open = new SmallButton(author.getAka());
			open.theme("tertiary-inline");
//			pcode.addClickListener(clk -> openAuthorView(author));
			return open;
		}).setHeader("Name");
//		pg.addColumn(Author::getEmailAddress).setHeader("Email").setFlexGrow(0).setWidth(HtmlC.G_W_EMAIL);
		pg.addColumn(Author::getWebsite).setHeader("Website");
		pg.addColumn(Author::getCreatedBy).setHeader("Added By").setFlexGrow(0).setWidth(HtmlC.G_W_EMAIL);

		pg.addColumn(a -> a.getCreatedDate().format(DateTimeFormatter.ISO_DATE)).setHeader("Added").setFlexGrow(0)
				.setWidth(HtmlC.G_W_DATE);

		pg.addComponentColumn(author -> {
			SmallButton edit = new SmallButton();
			edit.theme("icon").setIcon(new IronIcon("lumo", "edit"));
//			edit.addClickListener(click -> editAuthorView(author, action)); 

			Div fl = new Div(edit);
			fl.getStyle().set("textAlign", "right");
			return fl;
		}).setFlexGrow(0).setWidth("100px");

		pg.setDataProvider(filterabledp);
		addContent(pg);

	}

}
