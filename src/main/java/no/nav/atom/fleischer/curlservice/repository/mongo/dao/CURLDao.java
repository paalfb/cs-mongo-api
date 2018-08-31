package no.nav.atom.fleischer.curlservice.repository.mongo.dao;

import com.mongodb.client.result.UpdateResult;
import no.nav.atom.fleischer.curlservice.repository.mongo.CURLDocument;
import org.bson.types.ObjectId;

import java.util.List;

public interface CURLDao {
    void create(CURLDocument curlDocument);

    List<CURLDocument> findAll();

    List<CURLDocument> findActive();

    CURLDocument findById(ObjectId objectId);

    List<CURLDocument> findDeactivated();

    UpdateResult updateUrl(ObjectId objectId, String url);

    UpdateResult updateCrontab(ObjectId objectId, String crontab);

    UpdateResult setActive(ObjectId objectId, boolean active);

    CURLDocument delete(ObjectId objectId);

    void deleteAll();

    boolean equals(Object o);

}
