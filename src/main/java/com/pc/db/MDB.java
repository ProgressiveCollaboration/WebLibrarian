package com.pc.db;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.pc.entity.AudioVideo;
import com.pc.entity.Author;
import com.pc.entity.LibraryUser;
import com.pc.entity.Publication;
import com.pc.entity.Publisher;

@WebListener
public class MDB implements ServletContextListener {

	private static Logger logger = LoggerFactory.getLogger(MDB.class);

	static final String DBNAME = "weblibrarian";
	public static final String DB_AUDIOVIDEO = "audiovideo";
	public static final String DB_PUBLICATION = "publication";
	public static final String DB_AUTHOR = "author";
	public static final String DB_PUBLISHER = "publisher";

	static String DBSTR = "mongodb+srv://project1:YxhAWI55nUImLa2h@cluster0-fcg0z.mongodb.net/admin";

	static MongoClient mongo = null;
	static Datastore ds = null;

	public static Collection<Publication> getPublications() {
		return getDS().createQuery(Publication.class).asList();
	}

	public static Collection<? extends AudioVideo> getAudioVideo() {
		return getDS().createQuery(AudioVideo.class).asList();
	}

	public static Collection<? extends LibraryUser> getLibraryUsers() {
		return getDS().createQuery(LibraryUser.class).asList();
	}

	public static List<Author> getAuthors() {
		return getDS().createQuery(Author.class).asList();
	}

	public static Author getAuthor(String authorId) {
		return getDS().createQuery(Author.class).filter("Id", authorId).get();
	}

	public static List<Publisher> getPublishers() {
		return getDS().createQuery(Publisher.class).asList();
	}

	public static List<Author> getAuthorsInPublication(Publication pBean) {
		List<String> authorIds = pBean.getAuthorId();
		Query<Author> qry = getDS().createQuery(Author.class).filter("_id", authorIds.get(0));
		for (int n = 1; n < authorIds.size(); n++) {
			qry.or(qry.criteria("_id").equal(authorIds.get(n)));
		}
		return qry.asList();
	}

	@Override
	public void contextInitialized(ServletContextEvent s) {
		startDB();
	}

	@Override
	public void contextDestroyed(ServletContextEvent s) {
		stopDB();
	}

	public static void stopDB() {
		logger.warn("---------------------------- Stopping DB");
		if (mongo != null)
			mongo.close();
		mongo = null;
		ds = null;
	}

	public static void startDB() {
		logger.warn("---------------------------- Starting DB");
		if (mongo == null)
			mongo = new MongoClient(new MongoClientURI(DBSTR));

		if (ds == null) {
			ds = new Morphia().createDatastore(mongo, DBNAME);
			ds.ensureIndexes();
		}

		createAllCollections();
	}

	static void createAllCollections() {
		logger.warn("---------------------------- Creating Collections");
		MongoDatabase db = getDB();
		MongoIterable<String> colls = db.listCollectionNames();

		HashSet<String> cols = new HashSet<>();
		for (String j : colls) {
			cols.add(j);
		}

		if (!cols.contains(DB_AUDIOVIDEO)) {
			db.createCollection(DB_AUDIOVIDEO);
		}

		if (!cols.contains(DB_PUBLICATION)) {
			db.createCollection(DB_PUBLICATION);
		}

		if (!cols.contains(DB_AUTHOR)) {
			db.createCollection(DB_AUTHOR);
		}

		if (!cols.contains(DB_PUBLISHER)) {
			db.createCollection(DB_PUBLISHER);
		}
	}

	public static MongoDatabase getDB() {
		if (mongo == null)
			mongo = new MongoClient(new MongoClientURI(DBSTR));
		return mongo.getDatabase(DBNAME);
	}

	public static Datastore getDS() {
		return ds;
	}

	private static Query<Publication> getPublicationsQuery(String sort, boolean asc) {
		logger.info("getPublicationsQuery>>");
		Optional<Datastore> ds = Optional.ofNullable(getDS());
		Query<Publication> qry = null;
		if (ds.isPresent()) {
			qry = getDS().find(Publication.class).order(asc ? Sort.ascending(sort) : Sort.descending(sort));
			// qry.criteria(Publication._archivedDate).equal(null);
			logger.info("qry>> " + qry.toString());
		} else {
			logger.error("Database is not started.");
		}

		return qry;
	}

	private static Query<Author> getAuthorsQuery(String sort, boolean asc) {
		logger.info("getAuthorsQuery>>");
		Optional<Datastore> ds = Optional.ofNullable(getDS());
		Query<Author> qry = null;
		if (ds.isPresent()) {
			qry = getDS().find(Author.class).order(asc ? Sort.ascending(sort) : Sort.descending(sort));
			// qry.criteria(Publication._archivedDate).equal(null);
			logger.info("qry>> " + qry.toString());
		} else {
			logger.error("Database is not started.");
		}

		return qry;
	}

	public static List<Author> getAuthors(int offset, int limit, String sort, boolean asc) {
		logger.info(String.format("getAuthors (%d %d %s %b)\n", offset, limit, sort, asc));
		FindOptions opt = new FindOptions();
		opt.skip(offset);
		opt.limit(limit);
		return getAuthorsQuery(sort, asc).asList(opt);
	}

	public static List<Publication> getPublications(int offset, int limit, String sort, boolean asc) {
		logger.info(String.format("getPublications (%d %d %s %b)\n", offset, limit, sort, asc));
		FindOptions opt = new FindOptions();
		opt.skip(offset);
		opt.limit(limit);
		return getPublicationsQuery(sort, asc).asList(opt);
	}

	public static long countPublications(int offset, int limit, String sort, boolean asc) {
		logger.info(String.format("count Publications (%d %d %s %b)\n", offset, limit, sort, asc));
		FindOptions opt = new FindOptions();
		opt.skip(offset);
		opt.limit(limit);
		return getPublicationsQuery(sort, asc).asList(opt).size();
	}

	private static Query<Publisher> getPublishersQuery(String sort, boolean asc) {
		logger.info("getPublishersQuery>>");
		Optional<Datastore> ds = Optional.ofNullable(getDS());
		Query<Publisher> qry = null;
		if (ds.isPresent()) {
			qry = getDS().find(Publisher.class).order(asc ? Sort.ascending(sort) : Sort.descending(sort));
			// qry.criteria(Publication._archivedDate).equal(null);
			logger.info("qry>> " + qry.toString());
		} else {
			logger.error("Database is not started.");
		}

		return qry;
	}

	public static List<Publisher> getPublishers(int offset, int limit, String sort, boolean asc) {
		logger.info(String.format("getPublisher (%d %d %s %b)\n", offset, limit, sort, asc));
		FindOptions opt = new FindOptions();
		opt.skip(offset);
		opt.limit(limit);
		return getPublishersQuery(sort, asc).asList(opt);
	}

	private static Query<LibraryUser> getLibraryUsersQuery(String sort, boolean asc) {
		logger.info("getLibraryUsersQuery>>");
		Optional<Datastore> ds = Optional.ofNullable(getDS());
		Query<LibraryUser> qry = null;
		if (ds.isPresent()) {
			qry = getDS().find(LibraryUser.class).order(asc ? Sort.ascending(sort) : Sort.descending(sort));
			// qry.criteria(Publication._archivedDate).equal(null);
			logger.info("qry>> " + qry.toString());
		} else {
			logger.error("Database is not started.");
		}

		return qry;
	}

	public static List<LibraryUser> getLibraryUsers(int offset, int limit, String sort, boolean asc) {
		logger.info(String.format("getLibraryUser (%d %d %s %b)\n", offset, limit, sort, asc));
		FindOptions opt = new FindOptions();
		opt.skip(offset);
		opt.limit(limit);
		return getLibraryUsersQuery(sort, asc).asList(opt);
	}

	/*
	 * public static List<AudioVideo> getaudiovideo(int offset, int limit, String
	 * sort, boolean asc, String filter) {
	 * logger.info(String.format("getorders (%d %d %s %b %s)\n", offset, limit,
	 * sort, asc, filter)); FindOptions opt = new FindOptions(); opt.skip(offset);
	 * opt.limit(limit); return getordersquery(sort, asc, filter).asList(opt); }
	 */

}
