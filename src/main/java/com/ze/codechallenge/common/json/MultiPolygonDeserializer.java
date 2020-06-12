package com.ze.codechallenge.common.json;

import com.bedatadriven.jackson.datatype.jts.parsers.MultiPolygonParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import java.io.IOException;

public class MultiPolygonDeserializer extends JsonDeserializer<MultiPolygon> {

    @Override
    public MultiPolygon deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        MultiPolygonParser pointParser = new MultiPolygonParser(new GeometryFactory());
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode root = oc.readTree(jsonParser);
        return pointParser.multiPolygonFromJson(root);
    }
}
