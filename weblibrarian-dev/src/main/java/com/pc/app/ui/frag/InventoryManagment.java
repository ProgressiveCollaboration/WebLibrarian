package com.pc.app.ui.frag;

import org.apache.commons.lang3.StringUtils;

import com.pc.app.ui.BaseFragment;
import com.pc.app.ui.HtmlC.SmallButton;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
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
		setTitleText("Inventory");

		SmallButton addpubbtn = new SmallButton("Add Publisher");
		addpubbtn.addClassName("btn-mr");
		SmallButton addauthorbtn = new SmallButton("Add Author");
		addauthorbtn.addClassName("btn-mr");
		SmallButton addpublicationbtn = new SmallButton("Add Publication");
		addpublicationbtn.addClassName("btn-mr");

		addToolbarComponent(addpubbtn, addauthorbtn, addpublicationbtn);

		Tab tab1 = new Tab("Publishers");
		Tab tab2 = new Tab("Authors");
		Tab tab3 = new Tab("Publications");
		tabs.add(tab1, tab2, tab3);

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
		innercontent.add(new Span("index = " + index));
		
		
		
	}
}
