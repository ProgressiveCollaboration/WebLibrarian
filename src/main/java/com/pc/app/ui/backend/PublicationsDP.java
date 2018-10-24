package com.pc.app.ui.backend;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.mongodb.morphia.aggregation.Projection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pc.app.ui.backend.PublicationsDP.PublicationsView;
import com.pc.db.MDB;
import com.pc.entity.Author;
import com.pc.entity.BaseEntity;
import com.pc.entity.Publication;
import com.pc.entity.Publisher;
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
				.lookup("publisher", "publisherId", "_id", "publisher")//
				.lookup("author", "authorId", "_id", "authors")//
				.unwind("publisher").project(Projection.projection("title"), //
						Projection.projection("publisher"), //
						Projection.projection("edition"), //
						Projection.projection("genre"), //
						Projection.projection("authors"), //
						Projection.projection("ISBN_10"), //
						Projection.projection("createdDate"), //
						Projection.projection("language")) //
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
			qry.or(//
					qry.criteria("title").containsIgnoreCase(filter), //
					qry.criteria("ISBN_10").containsIgnoreCase(filter) //
			);
		}

		return (int) qry.count();
	}

	public static class PublicationsView extends BaseEntity {
		private static final long serialVersionUID = 1L;

		private LocalDateTime createdDate;
		private String uuid;
		private String edition;
		private String genre;
		private String ISBN_10;
		private String title;
		private String language;
		private List<Author> authors = new ArrayList<>(1);
		private Publisher publisher;

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
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

		public Publisher getPublisher() {
			return publisher;
		}

		public void setPublisher(Publisher publisher) {
			this.publisher = publisher;
		}

		public List<Author> getAuthors() {
			return authors;
		}

		public void setAuthors(List<Author> authors) {
			this.authors = authors;
		}

		public LocalDateTime getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}
	}
}
