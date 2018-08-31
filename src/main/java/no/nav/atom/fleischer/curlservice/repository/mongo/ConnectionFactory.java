package no.nav.atom.fleischer.curlservice.repository.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.List;

public class ConnectionFactory {

    /**
     * Connect to database
     */
    public static MongoClient getMongoClient(String serverAddress, int port) {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(new ResponseCodecProvider()),
                com.mongodb.MongoClient.getDefaultCodecRegistry());

        ConnectionString cn = new ConnectionString("mongodb://" + serverAddress + ":" + port + "/");
        return MongoClients.create(
                MongoClientSettings.builder()
                        .codecRegistry(codecRegistry)
                        .applyConnectionString(cn)
                        .build());
    }

    public static MongoClient getMongoClient(List<ServerAddress> hosts) {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(new ResponseCodecProvider()),
                com.mongodb.MongoClient.getDefaultCodecRegistry());
        return MongoClients.create(
                MongoClientSettings.builder()
                        .codecRegistry(codecRegistry)
                        .applyToClusterSettings(builder ->
                                builder.hosts(hosts))
                        .build());
    }

    public static MongoCollection<ResponseDocument> getResponseDBCollection(MongoClient mongoClient,
                                                                            String dbname,
                                                                            String collectionName) {
        return mongoClient
                .getDatabase(dbname)
                .getCollection(
                        collectionName,
                        ResponseDocument.class);
    }

    public static MongoCollection<CURLDocument> getCURLDBCollection(MongoClient mongoClient,
                                                                    String dbname,
                                                                    String collectionName) {
        return mongoClient
                .getDatabase(dbname)
                .getCollection(
                        collectionName,
                        CURLDocument.class);
    }
}
