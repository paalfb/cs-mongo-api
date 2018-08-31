package no.nav.atom.fleischer.curlservice.repository.mongo;

import org.bson.types.ObjectId;
 public abstract class Document {
    private ObjectId id;
    private String url;

    public Document() {
        this.id = new ObjectId();
    }

    public Document(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
