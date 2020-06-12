package com.ze.codechallenge.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.Point;

import java.io.IOException;

import static com.bedatadriven.jackson.datatype.jts.GeoJson.COORDINATES;
import static com.bedatadriven.jackson.datatype.jts.GeoJson.POINT;
import static com.bedatadriven.jackson.datatype.jts.GeoJson.TYPE;

public class PointSerializer extends JsonSerializer<Point> {

	@Override
	public void serialize(Point point, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField(TYPE, POINT);
		jsonGenerator.writeFieldName(COORDINATES);
		writePointCoords(jsonGenerator, point);
		jsonGenerator.writeEndObject();
	}

	private void writePointCoords(JsonGenerator jgen, Point p)
			throws IOException {
		jgen.writeStartArray();

		jgen.writeNumber(p.getCoordinate().x);
		jgen.writeNumber(p.getCoordinate().y);

		if(!Double.isNaN(p.getCoordinate().z))
		{
			jgen.writeNumber(p.getCoordinate().z);
		}
		jgen.writeEndArray();
	}
}
