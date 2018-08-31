package no.nav.atom.fleischer.curlservice.repository.mongo;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

public class CURLImpCodec implements Codec<CURLDocument> {
    private CodecRegistry codecRegistry;

    CURLImpCodec(CodecRegistry codecRegistry) {
        this.codecRegistry = codecRegistry;
    }

    @Override
    public CURLDocument decode(BsonReader bsonReader, DecoderContext decoderContext) {
        bsonReader.readStartDocument();
        ObjectId id = bsonReader.readObjectId();
        String url = bsonReader.readString("url");
        String crontab = bsonReader.readString("crontab");
        boolean active = bsonReader.readBoolean("active");
        String aclass = bsonReader.readString("_class");

        bsonReader.readEndDocument();

        CURLDocument curlDocument = new CURLDocument();
        curlDocument.setId(id);
        curlDocument.setUrl(url);
        curlDocument.setCrontab(crontab);
        curlDocument.setActive(active);
        return curlDocument;
    }

    @Override
    public void encode(BsonWriter bsonWriter, CURLDocument curlDocument, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();
        bsonWriter.writeName("_id");
        bsonWriter.writeObjectId(curlDocument.getId());
        bsonWriter.writeName("url");
        bsonWriter.writeString(curlDocument.getUrl());
        bsonWriter.writeName("crontab");
        bsonWriter.writeString(curlDocument.getCrontab());
        bsonWriter.writeName("active");
        bsonWriter.writeBoolean(curlDocument.isActive());
        bsonWriter.writeName("_class");
        bsonWriter.writeString(String.valueOf(curlDocument.getClass()));
        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<CURLDocument> getEncoderClass() {
        return CURLDocument.class;
    }
}
