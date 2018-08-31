package no.nav.atom.fleischer.curlservice.repository.mongo.dao;

import no.nav.atom.fleischer.curlservice.repository.mongo.ResponseDocument;

import java.time.LocalDateTime;
import java.util.List;

public interface ResponseDao {
    List<ResponseDocument> findByField(String key, String value);

    List<ResponseDocument> findByDate(LocalDateTime localDateTime);

    List<ResponseDocument> findByDate(String key, LocalDateTime localDateTime);

    List<ResponseDocument> findBetweenDates(LocalDateTime from, LocalDateTime to);

    List<ResponseDocument> findBetweenDates(String key, LocalDateTime from, LocalDateTime to);

    ResponseDocument findFirst(String url);

    ResponseDocument findLast(String url);

    void create(ResponseDocument responseDocument);

    void deleteAll();

}
