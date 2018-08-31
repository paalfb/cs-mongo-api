package no.nav.atom.fleischer.curlservice.repository.mongo;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class ResponseCodecProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> aClass, CodecRegistry codecRegistry) {
        if (aClass == ResponseDocument.class) {
            return (Codec<T>) new ResponseImpCodec(codecRegistry);
        } else if (aClass == CURLDocument.class) {
            return (Codec<T>) new CURLImpCodec(codecRegistry);
        }
        return null;
    }
}
