package com.pc.app.ui.backend;

import java.util.List;
import java.util.stream.Stream;

import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pc.db.MDB;
import com.pc.entity.AudioVideo;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;

public class AudioVideoDP extends AbstractBackEndDataProvider<AudioVideo, String> {

	private static final long serialVersionUID = -2870619695002272505L;

	private static Logger logger = LoggerFactory.getLogger(AudioVideoDP.class);

	public AudioVideoDP() {
	}

	@Override
	protected Stream<AudioVideo> fetchFromBackEnd(Query<AudioVideo, String> query) {
		String filter = query.getFilter().orElse(null);
		int limit = query.getLimit();
		int offset = query.getOffset();
		List<QuerySortOrder> sortorder = query.getSortOrders();
		String sortBy = AudioVideo._createdDate;
		boolean sortAscending = false;
		if (sortorder.size() > 0) {
			QuerySortOrder so = sortorder.get(0);
			sortBy = so.getSorted();
			sortAscending = so.getDirection() == SortDirection.ASCENDING;
		}
		return fetch(offset, limit, sortBy, sortAscending, filter).stream();
	}

	@Override
	protected int sizeInBackEnd(Query<AudioVideo, String> query) {
		String filter = query.getFilter().orElse(null);
		int limit = query.getLimit();
		int offset = query.getOffset();
		List<QuerySortOrder> sortorder = query.getSortOrders();
		String sortBy = AudioVideo._createdDate;
		boolean sortAscending = false;
		if (sortorder.size() > 0) {
			QuerySortOrder so = sortorder.get(0);
			sortBy = so.getSorted();
			sortAscending = so.getDirection() == SortDirection.ASCENDING;
		}
		return size(offset, limit, sortBy, sortAscending, filter);
	}

	private org.mongodb.morphia.query.Query<AudioVideo> query(String sort, boolean asc, String filter) {
		org.mongodb.morphia.query.Query<AudioVideo> qry = MDB.getDS().find(AudioVideo.class)
				.order(asc ? Sort.ascending(sort) : Sort.descending(sort));
		if (filter != null) {
			qry.or(qry.criteria(AudioVideo._title).containsIgnoreCase(filter));
		}
		return qry;
	}

	public List<AudioVideo> fetch(int offset, int limit, String sort, boolean asc, String filter) {
		logger.info(String.format("fetch (%d %d %s %b %s)\n", offset, limit, sort, asc, filter));
		FindOptions opt = new FindOptions();
		opt.skip(offset);
		opt.limit(limit);
		return query(sort, asc, filter).asList(opt);
	}

	public int size(int offset, int limit, String sort, boolean asc, String filter) {
		logger.info(String.format("size (%d %d %s %b %s)\n", offset, limit, sort, asc, filter));
		FindOptions opt = new FindOptions();
		opt.skip(offset);
		opt.limit(limit);
		return (int) query(sort, asc, filter).count();
	}

}
