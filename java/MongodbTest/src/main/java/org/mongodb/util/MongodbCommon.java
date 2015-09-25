package org.mongodb.util;

import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public abstract class MongodbCommon {
	private static final String DEFAULT_HOST = "192.168.199.142";
	private static final int DEFAULT_PORT = 27017;

	public MongoClient getDefauClient() {
		return this.getClient(DEFAULT_HOST, DEFAULT_PORT);
	}

	public MongoClient getClient(String host, int port) {
		return new MongoClient(host, port);
	}

	public MongoCollection<Document> getCollection(MongoClient client) {
		MongoDatabase database = client.getDatabase("testdb");
		MongoCollection<Document> cll = database.getCollection("testColl");
		return cll;
	}

	public void insertDocument(Document doc,MongoClient client) {
		MongoCollection<Document> cll = this.getCollection(client);
		cll.insertOne(doc);
		
	}
	public void insertMuiltDocument(List<Document> docList,MongoClient client) {
		MongoCollection<Document> cll = this.getCollection(client);
		cll.insertMany(docList);
	}
	public void updateDocument(Document doc,MongoClient client) {
		MongoCollection<Document> cll = this.getCollection(client);
//		cll.updateOne(arg0, arg1);
	}
}
