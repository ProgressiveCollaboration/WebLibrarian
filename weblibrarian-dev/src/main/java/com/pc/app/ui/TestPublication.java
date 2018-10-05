package com.pc.app.ui;

import com.pc.app.ui.backend.PublicationsDataProvider;
import com.pc.db.MDB;
import com.pc.entity.AudioVideo;
import com.pc.entity.Publication;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("TestPublication")
@Route(value = "", layout = BaseFragment.class)
public class TestPublication extends Div {

	private static final long serialVersionUID = 3837883965132583616L;

	public TestPublication() {
		long t = MDB.countpublications(0, 100, AudioVideo._createdBy, false, null);

		add(new Span("Test Data " + t));

		Grid<Publication> pubgrid = new Grid<>();
		pubgrid.setDataProvider(new PublicationsDataProvider());
		pubgrid.addColumn(Publication::getTitle).setHeader("Title");
		pubgrid.addColumn(Publication::getEdition).setHeader("Edition");
		pubgrid.addColumn(Publication::getGenre).setHeader("Genre");
		pubgrid.addColumn(Publication::getLanguage).setHeader("Language");
		pubgrid.addColumn(Publication::getNumberOfPages).setHeader("Pages");
		pubgrid.addColumn(Publication::getBinding).setHeader("Binding");

		add(pubgrid);

	}

}
