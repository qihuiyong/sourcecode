package org.mongodb.util.test;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.util.MongodbCommon;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

public class TestIndex extends MongodbCommon {
	private static long start_ss;

	@Test
	public void testGetIndex() {
		MongoClient client = this.getDefauClient();
		MongoCollection<Document> coll = this.getCollection(client);
//		coll.createIndex(arg0);
		client.close();
	}
	

	@Before
	public void beforeM() {
		start_ss = System.currentTimeMillis();
	}

	@After
	public void afterM() {
		long end_ss = System.currentTimeMillis();
		System.out.println("耗时>>>>: " + (end_ss - start_ss)+" MS");
	}

}
