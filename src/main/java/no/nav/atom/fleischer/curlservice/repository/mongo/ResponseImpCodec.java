package no.nav.atom.fleischer.curlservice.repository.mongo;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class ResponseImpCodec implements Codec<ResponseDocument> {
    private CodecRegistry codecRegistry;

    ResponseImpCodec(CodecRegistry codecRegistry) {
        this.codecRegistry = codecRegistry;
    }

    @Override
    public ResponseDocument decode(BsonReader bsonReader, DecoderContext decoderContext) {
        bsonReader.readStartDocument();
        ObjectId id = bsonReader.readObjectId();
        String url = bsonReader.readString("url");
        long lDateTime = bsonReader.readDateTime("dateTime");
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(lDateTime), ZoneId.systemDefault());
        ZoneId zoneId = ZoneId.of(bsonReader.readString("zoneId"));
        String avgTimeNameLookup = bsonReader.readString("avgTimeNameLookup");
        String avgTimeConnect = bsonReader.readString("avgTimeConnect");
        String avgTimeAppConnect = bsonReader.readString("avgTimeAppConnect");
        String avgTimePreTransfer = bsonReader.readString("avgTimePreTransfer");
        String avgTimeRedirect = bsonReader.readString("avgTimeRedirect");
        String avgTimeStartTransfer = bsonReader.readString("avgTimeStartTransfer");
        String avgTimeTotal = bsonReader.readString("avgTimeTotal");
        String aclass = bsonReader.readString("_class");

        bsonReader.readEndDocument();

        ResponseDocument responseDocument = new ResponseDocument();
        responseDocument.setId(id);
        responseDocument.setUrl(url);
        responseDocument.setLocalDateTime(ldt);
        responseDocument.setZoneId(zoneId);
        responseDocument.setAvgTimeNameLookup(avgTimeNameLookup);
        responseDocument.setAvgTimeConnect(avgTimeConnect);
        responseDocument.setAvgTimeAppConnect(avgTimeAppConnect);
        responseDocument.setAvgTimePreTransfer(avgTimePreTransfer);
        responseDocument.setAvgTimeRedirect(avgTimeRedirect);
        responseDocument.setAvgTimeStartTransfer(avgTimeStartTransfer);
        responseDocument.setAvgTimeTotal(avgTimeTotal);

        return responseDocument;
    }

    @Override
    public void encode(BsonWriter bsonWriter, ResponseDocument responseDocument, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();
        bsonWriter.writeName("_id");
        bsonWriter.writeObjectId(responseDocument.getId());
        bsonWriter.writeName("url");
        bsonWriter.writeString(responseDocument.getUrl());
        bsonWriter.writeName("dateTime");
        bsonWriter.writeDateTime((Util.fromLdt(responseDocument.getLocalDateTime())).getTime());
        bsonWriter.writeName("zoneId");
        bsonWriter.writeString(responseDocument.getZoneId().toString());
        bsonWriter.writeName("avgTimeNameLookup");
        bsonWriter.writeString(responseDocument.getAvgTimeNameLookup());
        bsonWriter.writeName("avgTimeConnect");
        bsonWriter.writeString(responseDocument.getAvgTimeConnect());
        bsonWriter.writeName("avgTimeAppConnect");
        bsonWriter.writeString(responseDocument.getAvgTimeAppConnect());
        bsonWriter.writeName("avgTimePreTransfer");
        bsonWriter.writeString(responseDocument.getAvgTimePreTransfer());
        bsonWriter.writeName("avgTimeRedirect");
        bsonWriter.writeString(responseDocument.getAvgTimeRedirect());
        bsonWriter.writeName("avgTimeStartTransfer");
        bsonWriter.writeString(responseDocument.getAvgTimeStartTransfer());
        bsonWriter.writeName("avgTimeTotal");
        bsonWriter.writeString(responseDocument.getAvgTimeTotal());
        bsonWriter.writeName("_class");
        bsonWriter.writeString(String.valueOf(responseDocument.getClass()));
        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<ResponseDocument> getEncoderClass() {
        return ResponseDocument.class;
    }
}
