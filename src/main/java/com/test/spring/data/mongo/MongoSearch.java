package com.test.spring.data.mongo;

import java.net.UnknownHostException;
import java.util.List;
import java.util.function.BiFunction;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;
import com.test.functions.TriFunction;
import com.test.model.Person;

/**
 * References : //
 * http://www.mkyong.com/mongodb/spring-data-mongodb-query-document/
 *
 */
public class MongoSearch {

	public static void main(String[] args) throws UnknownHostException {

		MongoTemplate template = getMongoTemplate("localhost", 27017, "db");
		if (template.collectionExists("testcollection")) {
			Query query = new Query();
			query.addCriteria(Criteria.where("address").elemMatch(
					Criteria.where("country").is("IN")
							.andOperator(Criteria.where("street").is("hello"))));

			List<Person> personList = template.find(query, Person.class);
			System.out.println(personList);

			query.addCriteria(Criteria.where("address").elemMatch(
					Criteria.where("country").in("IN", "USA")));
			List<Person> anotherPersonList = template.find(query, Person.class);
			System.out.println(anotherPersonList);
		}
	}

	private static MongoTemplate getMongoTemplate(String host, int port,
			String databaseName) throws UnknownHostException {
		Mongo mongod = new Mongo(host, port);
		return new MongoTemplate(mongod, databaseName);
	}

	public BiFunction<String, List<String>, Query> documentQuery = (criteria,
			tagName) -> {
		Query query = new Query();
		return query.addCriteria(Criteria.where(criteria).in(tagName));
	};

	public BiFunction<String, String, Query> query = (criteria, tagName) -> {
		Query query = new Query();
		return query.addCriteria(Criteria.where(criteria).in(tagName));
	};

	public TriFunction<String, String, List<String>, Query> subDocumentQuery = (
			criteria, field, tagName) -> {
		Query query = new Query();
		return query.addCriteria(Criteria.where(criteria).elemMatch(
				Criteria.where(field).in(tagName)));
	};

}
