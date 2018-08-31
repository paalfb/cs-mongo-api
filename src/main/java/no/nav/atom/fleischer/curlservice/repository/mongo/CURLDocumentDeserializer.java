package no.nav.atom.fleischer.curlservice.repository.mongo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.bson.types.ObjectId;

import java.io.IOException;

public class CURLDocumentDeserializer extends StdDeserializer<CURLDocument> {

    public CURLDocumentDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CURLDocument deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        ObjectId id = new ObjectId(jsonNode.get("id").asText());
        String url = jsonNode.get("url").asText();
        String crontab = jsonNode.get("crontab").asText();
        boolean active = jsonNode.get("active").asBoolean();
        return new CURLDocument(id, url, crontab, active);
    }
}