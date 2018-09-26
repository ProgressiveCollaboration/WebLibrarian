package com.pc.db;

import java.util.HashSet;
import java.util.List;

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
	}

	public static MongoDatabase getDB() {
		if (mongo == null)
			mongo = new MongoClient(new MongoClientURI(DBSTR));
		return mongo.getDatabase(DBNAME);
	}

	public static Datastore getDS() {
		return ds;
	}

	private static Query<AudioVideo> getordersquery(String sort, boolean asc, String filter) {
		Query<AudioVideo> qry = getDS().find(AudioVideo.class)
				.order(asc ? Sort.ascending(sort) : Sort.descending(sort));
//		if (filter != null) {
//			CriteriaContainer cont = qry.or(qry.criteria(Order._fullName).containsIgnoreCase(filter),
//					qry.criteria(AudioVideo._clientEmail).containsIgnoreCase(filter));
//			if (StringUtils.isNumeric(filter))
//				cont.add(qry.criteria(AudioVideo._orderNumber).equal(Integer.valueOf(filter)));
//		} else {
//			// do not show archived data
		qry.criteria(AudioVideo._archivedDate).equal(null);
//		}
		return qry;
	}

	public static List<AudioVideo> getaudiovideo(int offset, int limit, String sort, boolean asc, String filter) {
		logger.info(String.format("getorders (%d %d %s %b %s)\n", offset, limit, sort, asc, filter));
		FindOptions opt = new FindOptions();
		opt.skip(offset);
		opt.limit(limit);
		return getordersquery(sort, asc, filter).asList(opt);
	}

	public static long countaudiovideo(int offset, int limit, String sort, boolean asc, String filter) {
		logger.info(String.format("countorders (%d %d %s %b %s)\n", offset, limit, sort, asc, filter));
		FindOptions opt = new FindOptions();
		opt.skip(offset);
		opt.limit(limit);
		return getordersquery(sort, asc, filter).count();
	}

}
