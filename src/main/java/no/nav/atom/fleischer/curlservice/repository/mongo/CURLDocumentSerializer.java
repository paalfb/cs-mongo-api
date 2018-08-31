package no.nav.atom.fleischer.curlservice.repository.mongo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CURLDocumentSerializer extends StdSerializer<CURLDocument> {
    public CURLDocumentSerializer() {
        this(null);
    }

    public CURLDocumentSerializer(Class<CURLDocument> t) {
        super(t);
    }

    @Override
    public void serialize(CURLDocument curlDocument, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", curlDocument.getId().toString());
        jsonGenerator.writeStringField("url", curlDocument.getUrl());
        jsonGenerator.writeStringField("crontab", curlDocument.getCrontab());
        jsonGenerator.writeStringField("active", "" + curlDocument.isActive());
        jsonGenerator.writeEndObject();
    }
}
