package ec.com.comida.serializer;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import ec.com.comida.bean.MenuItem;

public class MenuItemSerializer extends JsonSerializer<List<MenuItem>> {

	@Override
	public void serialize(List<MenuItem> value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeStartArray();
		for (MenuItem item : value) {
			jgen.writeStartObject();
			jgen.writeObjectField("item", item);
			jgen.writeEndObject();
		}
		jgen.writeEndArray();

	}

}
