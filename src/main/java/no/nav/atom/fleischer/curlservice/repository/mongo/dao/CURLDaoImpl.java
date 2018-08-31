package no.nav.atom.fleischer.curlservice.repository.mongo.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import no.nav.atom.fleischer.curlservice.repository.mongo.CURLDocument;
import no.nav.atom.fleischer.curlservice.repository.mongo.ConnectionFactory;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CURLDaoImpl implements CURLDao {
    private static MongoClient mongoClient;
    private static Logger logger = LoggerFactory.getLogger(CURLDaoImpl.class);
    private MongoCollection<CURLDocument> collection;

    public CURLDaoImpl(String serverAddress, int port, String dbName, String collectionName) {
        if (mongoClient == null) mongoClient = ConnectionFactory.getMongoClient(serverAddress, port);
        if (collection == null) collection = ConnectionFactory.getCURLDBCollection(mongoClient, dbName, collectionName);
    }

    public void create(CURLDocument curlDocument) {
        logger.debug("Create({})", curlDocument.toString());
        collection.insertOne(curlDocument);
    }

    public List<CURLDocument> findAll() {
        logger.debug("findAll()");
        FindIterable<CURLDocument> res = collection.find();
        List<CURLDocument> results = new ArrayList<>();
        for (CURLDocument doc : res) {
            results.add(doc);
        }
        return results;
    }

    public CURLDocument findById(ObjectId objectId) {
        logger.debug("findById({})",objectId);
        List<CURLDocument> results = new ArrayList<>();
        Bson filter = eq("_id",objectId);
        FindIterable<CURLDocument> curlDocuments = collection.find(filter);
        for (CURLDocument doc : curlDocuments) {
            results.add(doc);

        }
        logger.debug("Found {} jobs i db", results.size());
        return results.get(0);

    }

    public List<CURLDocument> findActive() {
        logger.debug("findActive()");
        List<CURLDocument> results = new ArrayList<>();
        Bson filter = eq("active", true);
        FindIterable<CURLDocument> curlDocuments = collection.find(filter);
        for (CURLDocument doc : curlDocuments) {
            results.add(doc);
        }
        logger.info("Found {} active jobs i db", results.size());
        return results;
    }

    public List<CURLDocument> findDeactivated() {
        logger.debug("findDeactivated()");
        List<CURLDocument> results = new ArrayList<>();
        Bson filter = eq("active", false);
        FindIterable<CURLDocument> curlDocuments = collection.find(filter);
        for (CURLDocument doc : curlDocuments) {
            results.add(doc);
        }
        logger.info("Found {} active jobs i db", results.size());
        return results;
    }

    public UpdateResult updateUrl(ObjectId objectId, String url) {
        logger.debug("updateUrl({},{})", objectId.toString(), url);
        Bson filter = eq("_id", objectId);
        return collection.updateOne(filter, new Document("$set", new Document("url", url)));
    }

    public UpdateResult updateCrontab(ObjectId objectId, String crontab) {
        logger.debug("updateCrontab({},{})", objectId.toString(), crontab);
        Bson filter = eq("_id", objectId);
        return collection.updateOne(filter, new Document("$set", new Document("crontab", crontab)));
    }

    public UpdateResult setActive(ObjectId objectId, boolean active) {
        logger.debug("setActive({},{})",objectId.toString(),active);
        Bson filter = eq("_id", objectId);
        return collection.updateOne(filter, new Document("$set", new Document("active", active)));
    }

    public CURLDocument delete(ObjectId objectId) {
        logger.debug("delete({})", objectId.toString());
        Bson filter = eq("_id", objectId);
        return collection.findOneAndDelete(filter);
    }

    public void deleteAll() {
        logger.debug("deleteAll()");
        BasicDBObject dbObject = new BasicDBObject();
        collection.deleteMany(dbObject);
    }
}
