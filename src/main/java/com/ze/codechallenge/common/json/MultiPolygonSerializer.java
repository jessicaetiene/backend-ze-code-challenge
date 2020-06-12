package com.ze.codechallenge.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import java.io.IOException;

import static com.bedatadriven.jackson.datatype.jts.GeoJson.COORDINATES;
import static com.bedatadriven.jackson.datatype.jts.GeoJson.MULTI_POLYGON;
import static com.bedatadriven.jackson.datatype.jts.GeoJson.TYPE;

public class MultiPolygonSerializer extends JsonSerializer<MultiPolygon> {

	@Override
	public void serialize(MultiPolygon multiPolygon, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField(TYPE, MULTI_POLYGON);
		jsonGenerator.writeArrayFieldStart(COORDINATES);

		for (int i = 0; i != multiPolygon.getNumGeometries(); ++i) {
			writePolygonCoordinates(jsonGenerator, (Polygon) multiPolygon.getGeometryN(i));
		}

		jsonGenerator.writeEndArray();
		jsonGenerator.writeEndObject();
	}

	private void writePolygonCoordinates(JsonGenerator jgen, Polygon value)
			throws IOException {
		jgen.writeStartArray();
		writeLineStringCoords(jgen, value.getExteriorRing());

		for (int i = 0; i < value.getNumInteriorRing(); ++i) {
			writeLineStringCoords(jgen, value.getInteriorRingN(i));
		}
		jgen.writeEndArray();
	}

	private void writeLineStringCoords(JsonGenerator jgen, LineString ring)
			throws IOException {
		jgen.writeStartArray();
		for (int i = 0; i != ring.getNumPoints(); ++i) {
			Point p = ring.getPointN(i);
			writePointCoords(jgen, p);
		}
		jgen.writeEndArray();
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
