package no.nav.atom.fleischer.curlservice.repository.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;

public class CURLDocument  extends Document {

    private String crontab;
    private boolean active;

    public CURLDocument() {
        super();
    }

    public CURLDocument(ObjectId id, String url, String crontab, boolean active) {
        super(id);
        this.setUrl(url);
        this.crontab = crontab;
        this.active = active;
    }

    public String getCrontab() {
        return crontab;
    }

    public void setCrontab(String crontab) {
        this.crontab = crontab;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CURLDocument that = (CURLDocument) o;
        return active == that.active &&
                Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getUrl(), that.getUrl()) &&
                Objects.equals(crontab, that.crontab);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.getId(), this.getUrl(), crontab, active);
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + this.getId() +
                ", url:'" + this.getUrl() + '\'' +
                ", crontab:'" + crontab + '\'' +
                ", active:" + active +
                '}';
    }
}
