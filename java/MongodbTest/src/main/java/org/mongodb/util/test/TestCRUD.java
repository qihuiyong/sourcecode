package org.mongodb.util.test;

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

//    @Test
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

	/**
	 * 多条件 或(or) 查询
	 *  //比较符    
225.        //"$gt"： 大于    
226.        //"$gte"：大于等于    
227.        //"$lt"： 小于    
       //"$lte"：小于等于    
      //"$in"： 包含  

	 */
	@Test
	public void testMuiltOrQuery() {
		MongoClient client = this.getDefauClient();
		BasicDBObject dbObj = new BasicDBObject();
		dbObj.put("size", new BasicDBObject("$gt", 8));
		dbObj.put("size", new BasicDBObject("$lt", 10));
		MongoCollection<Document> coll = this.getCollection(client);
		FindIterable<Document> itrDoc = coll.find(dbObj);
		MongoCursor<Document> mongoCursor = itrDoc.iterator();
		int count = 0;
		while (mongoCursor.hasNext()) {
			Document doc = mongoCursor.next();
			System.out.println(doc);
			count++;
		}
		System.out.println("OR匹配一共查到："+count+"个文档");
		client.close();
	}
	
	/**
	 * 多条件 且(and ) 查询
	 */
	@Test
	public void testMuiltAndQuery() {
		MongoClient client = this.getDefauClient();
		BasicDBObject dbObj = new BasicDBObject();
		BasicDBObject andQuery = new BasicDBObject("$gt", 8).append("$lt", 10);//条件
		dbObj.put("size", andQuery);
		MongoCollection<Document> coll = this.getCollection(client);
		FindIterable<Document> itrDoc = coll.find(dbObj);
		MongoCursor<Document> mongoCursor = itrDoc.iterator();
		int count = 0;
		while (mongoCursor.hasNext()) {
			Document doc = mongoCursor.next();
			System.out.println(doc);
			count++;
		}
		System.out.println("AND匹配一共查到："+count+"个文档");
		client.close();
	}
	
	/**
	 * 字段=匹配
	 */
//	@Test
	public void testFieldQuery() {
		MongoClient client = this.getDefauClient();
		BsonDocument filterDoc = new BsonDocument();
		filterDoc.append("name", new BsonString("MYSQL3"));
		MongoCollection<Document> coll = this.getCollection(client);
		FindIterable<Document> itrDoc = coll.find(filterDoc);
		MongoCursor<Document> mongoCursor = itrDoc.iterator();
		int count = 0;
		while (mongoCursor.hasNext()) {
			Document doc = mongoCursor.next();
			System.out.println(doc);
//			System.out.println(doc.getObjectId("_id"));
			count++;
		}
		System.out.println("一共查到："+count+"个文档");
		client.close();
	}

	@Before
	public void beforeM() {
		start_ss = System.currentTimeMillis();
	}

	@After
	public void afterM() {
		long end_ss = System.currentTimeMillis();
		System.out.println("耗时>>>>:" + (end_ss - start_ss));
	}

}
