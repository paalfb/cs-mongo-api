package no.nav.atom.fleischer.curlservice.repository.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public final class Util {
    public static Date fromLdt(LocalDateTime localDateTime) {
        LocalDateTime l = localDateTime.withNano(0);
        Instant instant = (l.atZone(ZoneId.systemDefault())).toInstant();
        return Date.from(instant);
    }
    public static String serialize(List<CURLDocument> documents) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(CURLDocument.class, new CURLDocumentSerializer());
        module.addDeserializer(CURLDocument.class, new CURLDocumentDeserializer(CURLDocument.class));
        mapper.registerModule(module);
        return mapper.writeValueAsString(documents);
    }
    public static String serialize(CURLDocument document) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(CURLDocument.class, new CURLDocumentSerializer());
        module.addDeserializer(CURLDocument.class, new CURLDocumentDeserializer(CURLDocument.class));
        mapper.registerModule(module);
        return mapper.writeValueAsString(document);
    }
}
