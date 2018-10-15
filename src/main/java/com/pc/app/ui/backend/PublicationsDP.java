package com.pc.app.ui.backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.mongodb.morphia.aggregation.Projection;
import org.mongodb.morphia.query.CriteriaContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pc.app.ui.backend.PublicationsDP.PublicationsView;
import com.pc.db.MDB;
import com.pc.entity.BaseEntity;
import com.pc.entity.Publication;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;

public class PublicationsDP extends AbstractBackEndDataProvider<PublicationsView, String> {

	private static final long serialVersionUID = -2870619695002272505L;

	private static Logger logger = LoggerFactory.getLogger(PublicationsDP.class);

	public PublicationsDP() {
	}

	@Override
	protected Stream<PublicationsView> fetchFromBackEnd(Query<PublicationsView, String> query) {
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
		return fetch(offset, limit, sortBy, sortAscending, filter).stream();
	}

	@Override
	protected int sizeInBackEnd(Query<PublicationsView, String> query) {
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
		return size(offset, limit, sortBy, sortAscending, filter);
	}

	public List<PublicationsView> fetch(int offset, int limit, String sort, boolean asc, String filter) {
		logger.info(String.format("fetch (%d %d %s %b %s)\n", offset, limit, sort, asc, filter));

		Iterator<PublicationsView> ggr = MDB.getDS()//
				.createAggregation(Publication.class)//
				// .match(query)
				.lookup("publisher", "localField", "foreignField", "publisherlookup")//
				.project(Projection.projection("title"), //
						Projection.projection("uuid"), //
						Projection.projection("description"), //
						Projection.projection("edition"), //
						Projection.projection("genre"), //
						Projection.projection("authorId"), //
						Projection.projection("ISBN_10"), //
						Projection.projection("ISBN_13"), //
						Projection.projection("language"), //
						Projection.projection("publisherId"))//
				.limit(limit)//
				.skip(offset)//
				.aggregate(PublicationsView.class);

		List<PublicationsView> res = new ArrayList<>();
		ggr.forEachRemaining(a -> res.add(a));
		return res;
	}

	public int size(int offset, int limit, String sort, boolean asc, String filter) {
		logger.info(String.format("size (%d %d %s %b %s)\n", offset, limit, sort, asc, filter));

		org.mongodb.morphia.query.Query<Publication> qry = MDB.getDS().find(Publication.class);
		if (filter != null) {
			CriteriaContainer cont = qry.or(//
					qry.criteria("title").containsIgnoreCase(filter), //
					qry.criteria("ISBN_10").containsIgnoreCase(filter) //
			);
		}

		return (int) qry.count();
	}

	public static class PublicationsView extends BaseEntity {
		private static final long serialVersionUID = 1L;

		private String uuid;
		private String description;
		private String edition;
		private String genre; // LOV publication_genres
		private String ISBN_10;
		private String ISBN_13;
		private String publisherId;
		private String title;
		private String language;
		private List<String> authorId = new ArrayList<>(1);

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getEdition() {
			return edition;
		}

		public void setEdition(String edition) {
			this.edition = edition;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		public String getISBN_10() {
			return ISBN_10;
		}

		public void setISBN_10(String iSBN_10) {
			ISBN_10 = iSBN_10;
		}

		public String getISBN_13() {
			return ISBN_13;
		}

		public void setISBN_13(String iSBN_13) {
			ISBN_13 = iSBN_13;
		}

		public String getPublisherId() {
			return publisherId;
		}

		public void setPublisherId(String publisherId) {
			this.publisherId = publisherId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public List<String> getAuthorId() {
			return authorId;
		}

		public void setAuthorId(List<String> authorId) {
			this.authorId = authorId;
		}
	}
}
