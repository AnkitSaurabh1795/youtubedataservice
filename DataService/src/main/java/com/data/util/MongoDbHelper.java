package com.data.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.data.util.constants.constant.MONGO_URI;
import static com.mongodb.client.model.Filters.eq;

@Component
@RequiredArgsConstructor
public class MongoDbHelper {
    String COLLECTION_NAME = "video";
    MongoClient mongoClient = MongoClients.create(MONGO_URI);
    MongoDatabase database = mongoClient.getDatabase("youtubedata");

    public List<String> getData(int pageSize, int pageNo){
        List<String> result = new ArrayList<>();
        try{
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
//            List<Document> query = collection.find().sort(sort).skip(pageNo).limit(pageSize).into(new ArrayList<>());
            List<Document> query = collection.aggregate(Arrays.asList(
                    new Document("$group", new Document("_id", "$id").append("doc", new Document("$first", "$$ROOT"))),
                    new Document("$sort", new Document("doc.publishedAt", -1)),
                    new Document("$skip", pageNo),
                    new Document("$limit", pageSize)
            )).into(new ArrayList<>());

            for (Document doc : query) {
                System.out.println("inside get data" + doc.toJson());
                result.add(doc.toJson());
            }
            return result;
        }catch (Exception e){
            throw e;
        }
    }
    public List<String> searchData(String query){
        List<String> result = new ArrayList<>();
        try{
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            List<Document> docs = collection.find(
                            new Document("$text", new Document("$search", query))
                    )
                    .projection(new Document("score", new Document("$meta", "textScore")))
                    .sort(new Document("score", new Document("$meta", "textScore")))
                    .skip(0)
                    .limit(5)
                    .into(new ArrayList<>());

            List<String> objIds = docs.stream()
                    .map(doc -> doc.getString("id"))
                    .collect(Collectors.toList());

            for(String id:objIds) {
                //ObjectId id = (ObjectId) doc.get("_id");
                Document document = collection.find(eq("id", id)).first();
                //Do something with the document
                System.out.println("inside get data" + document.toJson());
                result.add(document.toJson());
            }
            return result;
        }catch(Exception e){
            throw e;
        }

    }
}
