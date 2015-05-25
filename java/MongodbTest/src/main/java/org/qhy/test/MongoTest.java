
package org.qhy.test;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;



public class MongoTest {
    private  MongoClient mongoClient = null;
    public static void main(String[] args) {
        MongoTest mt =new MongoTest();
        Document doc = new Document("name", "MYSQL")
        .append("type", "database")
        .append("count", 3)
        .append("info", new Document("user", "qihy").append("password", "pwdqhy"));
//        mt.findAllDatabases();
//        mt.mainTest();
        mt.addDocument(doc);
    }
    private void mainTest() {
        this.openConnect();
        MongoDatabase mdb = mongoClient.getDatabase("testdb");
        MongoIterable<String> mcllItr = mdb.listCollectionNames();
        mdb.getCollection("");
        this.close();
    }
    private void findAllDatabases() {
        this.openConnect();
        List<String> list = mongoClient.getDatabaseNames();
        System.out.println(list);
        this.close();
    }
    private void openConnect(){
        mongoClient = new MongoClient("192.168.199.134",27017);
    }
    private void close(){
        mongoClient.close();
    }
    private void addDocument(Document doc) {
        this.openConnect();
        MongoDatabase database = mongoClient.getDatabase("testdb");
        MongoCollection<Document>  cll =database.getCollection("testColl");
        
        Document doc1 = new Document("name", "MYSQL").append("type", "database").append("size", 3)
            .append("info", new Document("user", "test").append("password", "testpwd"));
        
        Document doc2 = new Document("name", "MongoDB").append("type", "database").append("size", 5)
                .append("info", new Document("user", "qihy").append("password", "pwdqhy"));
        
        Document doc3 = new Document("name", "Oracle").append("type", "database").append("size", 1)
                .append("info", new Document("user", "qihuiyong").append("password", "huiyongq"));
        
        Document doc4 = new Document("name", "MYSQL3").append("type", "database").append("size", 6)
                .append("info", new Document("user", "qihy7").append("password", "pwdqhy7"));
        
        Document doc5 = new Document("name", "AcMQ").append("type", "message").append("size", 9)
                .append("info", new Document("user", "qihy9").append("password", "pwdqhy9"));
        List<Document> list = new ArrayList<Document>();
        list.add(doc1);
        list.add(doc2);
        list.add(doc3);
        list.add(doc4);
        list.add(doc5);
        cll.insertMany(list);
        this.close();
    }
    private Long getCount() {
        Long count = null;
        this.openConnect();
        List<String> list = mongoClient.getDatabaseNames();
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document>  cll =database.getCollection("testcll");
        count = cll.count();
        this.close();
        return count;
        
    }
    private void findFrist() {
        this.openConnect();
        List<String> list = mongoClient.getDatabaseNames();
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document>  cll =database.getCollection("testcll");
       Document doc = cll.find().first();
       System.out.println(doc);
        this.close();
    }
    private void findAll() {
        this.openConnect();
        List<String> list = mongoClient.getDatabaseNames();
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document>  cll =database.getCollection("testcll");
        MongoCursor<Document> mongoCursor = cll.find().iterator();
        while(mongoCursor.hasNext()){
            Document doc = mongoCursor.next();
            System.out.println(doc);
            System.out.println(doc.getObjectId("_id"));
        }
        this.close();
    }
    
}
