package com.pc.app.ui.backend;

import java.util.List;
import java.util.stream.Stream;

import com.pc.db.MDB;
import com.pc.entity.Publication;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;

public class PublicationsDataProvider extends AbstractBackEndDataProvider<Publication, String> {

	private static final long serialVersionUID = -2870619695002272505L;

	public PublicationsDataProvider() {
	}

	@Override
	protected Stream<Publication> fetchFromBackEnd(Query<Publication, String> query) {
		String filter = query.getFilter().orElse(null);
		int limit = query.getLimit();
		int offset = query.getOffset();
		List<QuerySortOrder> sortorder = query.getSortOrders();
		String sortBy = Publication._createdDate;
		boolean sortAscending = false;
		if (sortorder.size() > 0) {
			QuerySortOrder so = sortorder.get(0);
			sortBy = so.getSorted();
			sortAscending = so.getDirection() == SortDirection.ASCENDING;
		}
		return MDB.getpublications(offset, limit, sortBy, sortAscending, filter).stream();
	}

	@Override
	protected int sizeInBackEnd(Query<Publication, String> query) {
		String filter = query.getFilter().orElse(null);
		int limit = query.getLimit();
		int offset = query.getOffset();
		List<QuerySortOrder> sortorder = query.getSortOrders();
		String sortBy = Publication._createdDate;
		boolean sortAscending = false;
		if (sortorder.size() > 0) {
			QuerySortOrder so = sortorder.get(0);
			sortBy = so.getSorted();
			sortAscending = so.getDirection() == SortDirection.ASCENDING;
		}
		return (int) MDB.countpublications(offset, limit, sortBy, sortAscending, filter);
	}

}
