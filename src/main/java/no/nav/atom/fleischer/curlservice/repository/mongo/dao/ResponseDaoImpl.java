package no.nav.atom.fleischer.curlservice.repository.mongo.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import no.nav.atom.fleischer.curlservice.repository.mongo.ConnectionFactory;
import no.nav.atom.fleischer.curlservice.repository.mongo.ResponseDocument;
import no.nav.atom.fleischer.curlservice.repository.mongo.Util;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;



public class ResponseDaoImpl implements ResponseDao {
    private static MongoClient mongoClient;
    private static Logger logger = LoggerFactory.getLogger(ResponseDaoImpl.class);
    private MongoCollection<ResponseDocument> collection;

    public ResponseDaoImpl(String serverAddress, int port, String dbName, String collectionName) {
        if (mongoClient == null) mongoClient = ConnectionFactory.getMongoClient(serverAddress, port);
        if (collection == null)
            collection = ConnectionFactory.getResponseDBCollection(mongoClient, dbName, collectionName);
    }

    public List<ResponseDocument> findByField(String key, String value) {
        logger.debug("findByField({},{})", key, value);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put(key, value);
        FindIterable<ResponseDocument> docs = collection.find(searchQuery);
        List<ResponseDocument> responses = new ArrayList<>();
        for (ResponseDocument doc : docs) {
            responses.add(doc);
        }
        return responses;
    }

    public List<ResponseDocument> findByDate(LocalDateTime localDateTime) {
        logger.debug("findByDate({})", localDateTime.toString());
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("dateTime", Util.fromLdt(localDateTime));
        FindIterable<ResponseDocument> docs = collection.find(searchQuery);
        List<ResponseDocument> responses = new ArrayList<>();
        for (ResponseDocument doc : docs) {
            responses.add(doc);
        }
        return responses;
    }

    public List<ResponseDocument> findByDate(String url, LocalDateTime localDateTime) {
        logger.debug("findByDate({},{})", url, localDateTime.toString());
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("url", url);
        searchQuery.put("dateTime", Util.fromLdt(localDateTime));
        FindIterable<ResponseDocument> docs = collection.find(searchQuery);
        List<ResponseDocument> responses = new ArrayList<>();
        for (ResponseDocument doc : docs) {
            responses.add(doc);
        }
        return responses;
    }

    public List<ResponseDocument> findBetweenDates(LocalDateTime start, LocalDateTime end) {
        logger.debug("findBetweenDates({},{})", start.toString(), end.toString());
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("dateTime", BasicDBObjectBuilder.start("$gte", Util.fromLdt(start)).add("$lte", Util.fromLdt(end)).get());
        FindIterable<ResponseDocument> docs = collection.find(searchQuery);
        List<ResponseDocument> responses = new ArrayList<>();
        for (ResponseDocument doc : docs) {
            responses.add(doc);
        }
        return responses;
    }

    public List<ResponseDocument> findBetweenDates(String key, LocalDateTime start, LocalDateTime end) {
        logger.debug("findBetweenDates({},{},{})", key, start.toString(), end.toString());
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("dateTime", BasicDBObjectBuilder.start("$gte", Util.fromLdt(start)).add("$lte", Util.fromLdt(end)).get());
        searchQuery.put("url", key);
        FindIterable<ResponseDocument> docs = collection.find(searchQuery);
        List<ResponseDocument> responses = new ArrayList<>();
        for (ResponseDocument doc : docs) {
            responses.add(doc);
        }
        return responses;
    }

    public ResponseDocument findFirst(String url) {
        logger.debug("findFirst({})", url);
        Bson filter = eq("url", url);
        FindIterable<ResponseDocument> docs = collection.find(filter, ResponseDocument.class).limit(10).sort(new BasicDBObject("dateTime", 1));
        return docs.first();
    }

    public ResponseDocument findLast(String url) {
        logger.debug("findLast({})", url);
        Bson filter = eq("url", url);
        FindIterable<ResponseDocument> docs = collection.find(filter, ResponseDocument.class).limit(10).sort(new BasicDBObject("dateTime", -1));
        return docs.first();
    }

    public void create(ResponseDocument responseDocument) {
        logger.debug("Create({})", responseDocument.toString());
        collection.insertOne(responseDocument);
    }

    public void deleteAll() {
        logger.debug("deleteAll()");
        BasicDBObject dbObject = new BasicDBObject();
        collection.deleteMany(dbObject);
    }

    public void drop() {
        logger.debug("drop collection");
        collection.drop();
    }

}

