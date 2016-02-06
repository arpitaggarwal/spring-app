package com.test.mongo;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * References : http://www.mkyong.com/mongodb/java-mongodb-hello-world-example/
 *
 */
public class MongoQueries {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = getMongoClient("localhost", 27017);
		DB db = getDatabase(mongoClient, "db");
		DBCollection dbCollection = getCollection(db, "testcollection");

		BasicDBObject document = new BasicDBObject();
		document.put("name", "mkyong");
		document.put("age", 30);
		document.put("createdDate", new Date());

		dbCollection.insert(document);

		DBObject dbObject = new BasicDBObject();
		dbObject.put("name", "mkyong");

		DBCursor cursor = dbCollection.find(dbObject);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * Get mongo client for a mongo server.
	 * 
	 * @param host
	 * @param port
	 * @return {@link MongoClient}
	 * @throws UnknownHostException
	 */
	private static MongoClient getMongoClient(String host, int port)
			throws UnknownHostException {
		return new MongoClient(host, port);
	}

	/**
	 * Get all database names.
	 * 
	 * @param mongoClient
	 * @return {@link List<String>}
	 */
	public static List<String> getAllDatabaseNames(MongoClient mongoClient) {
		return mongoClient.getDatabaseNames();
	}

	/**
	 * Get all collections of a database.
	 * 
	 * @param db
	 * @return {@link Set<String>}
	 */
	public static Set<String> getAllCollection(DB db) {
		return db.getCollectionNames();
	}

	/**
	 * Get collection of a specific database.
	 * 
	 * @param db
	 * @param collectioName
	 * @return {@link DBCollection}
	 */
	public static DBCollection getCollection(DB db, String collectioName) {
		return db.getCollection(collectioName);
	}

	/**
	 * Get database.
	 * 
	 * @param mongoClient
	 * @param databaseName
	 * @return {@link DB}
	 */
	private static DB getDatabase(MongoClient mongoClient, String databaseName) {
		return mongoClient.getDB(databaseName);
	}

	/**
	 * Get database.
	 * 
	 * @param mongoClient
	 * @param databaseName
	 * @param username
	 * @param password
	 * @return {@link DB}
	 */
	public static DB getDatabase(MongoClient mongoClient, String databaseName,
			String username, String password) {
		DB db = mongoClient.getDB(databaseName);
		boolean auth = db.authenticate(username, password.toCharArray());
		return auth == true ? db : null;
	}
}