package ua.goit.swwager.application;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.http.HttpEntity;

public class MapperUtils {
	public static String serialize(Object o) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T deserialize(Class<T> clazz, HttpEntity entity) {
		ObjectMapper objectMapper = JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES).build();
		try {
			byte[] content = entity.getContent().readAllBytes();
			System.out.println("Original response is \n" + new String(content) + "\n");
			return objectMapper.readValue(content, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> List<T> deserializeList(Class<T> clazz, HttpEntity entity) {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.readValue(
					entity.getContent(),
					om.getTypeFactory().constructCollectionType(List.class, clazz)
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
