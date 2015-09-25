package org.mongodb.util.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.util.MongodbCommon;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.QueryBuilder;

public class TestCRUD extends MongodbCommon {
	private static long start_ss;

	// @Test
	public void testInsert() {
		MongoClient client = this.getDefauClient();
		TestCRUD q = new TestCRUD();
		Document doc = new Document("name", "MYSQL3")
				.append("type", "database")
				.append("size", 6)
				.append("info",
						new Document("user", "qihy7").append("password",
								"pwdqhy7"));
		q.insertDocument(doc, client);
		client.close();
	}

//	@Test
	public void testMuiltInsert() {
		MongoClient client = this.getDefauClient();
		Document doc1 = new Document("name", "QHY")
				.append("type", "database")
				.append("size", 13)
				.append("info",
						new Document("user", "test").append("password",
								"testpwd"));

		Document doc2 = new Document("name", "QHY22")
				.append("type", "database")
				.append("size", 22)
				.append("info",
						new Document("user", "qihy").append("password",
								"pwdqhy"));

		Document doc3 = new Document("name", "22HY")
				.append("type", "database")
				.append("size", 122)
				.append("info",
						new Document("user", "qihuiyong").append("password",
								"huiyongq"));

		Document doc4 = new Document("name", "23QHY3434")
				.append("type", "database")
				.append("size", 56)
				.append("info",
						new Document("user", "qihy7").append("password",
								"pwdqhy7"));

		Document doc5 = new Document("name", "QH2223Y")
				.append("type", "message")
				.append("size", 12)
				.append("info",
						new Document("user", "qih2y9").append("password",
								"pwdqhy9"));
		List<Document> list = new ArrayList<Document>();
		list.add(doc1);
		list.add(doc2);
		list.add(doc3);
		list.add(doc4);
		list.add(doc5);
		this.insertMuiltDocument(list, client);
		client.close();
	}

	@Test
	public void testQueryAll() {
		MongoClient client = this.getDefauClient();
		MongoCollection<Document> coll = this.getCollection(client);
		MongoCursor<Document> docItr = coll.find().iterator();
		long count = coll.count();
		System.out.println("总数:" + count);
		while (docItr.hasNext()) {
			Document d = docItr.next();
			System.out.println("doc====>" + d);
		}
	}

	@Before
	public void beforeM() {
		start_ss = System.currentTimeMillis();
	}

	@After
	public void afterM() {
		long end_ss = System.currentTimeMillis();
		System.out.println("耗时>>>>: " + (end_ss - start_ss) + " MS");
	}

}
