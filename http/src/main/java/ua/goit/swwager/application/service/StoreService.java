package ua.goit.swwager.application.service;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import ua.goit.swwager.application.MapperUtils;
import ua.goit.swwager.application.model.Store;

public class StoreService {
	public Store placeOrder(Store store) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost request = new HttpPost("https://petstore.swagger.io/v2/store/order");
			request.setEntity(new StringEntity(MapperUtils.serialize(store), ContentType.APPLICATION_JSON));
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(Store.class, response.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Store findOrderById(int id) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet request = new HttpGet("https://petstore.swagger.io/v2/store/order/" + id);
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(Store.class, response.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteOrderByID(int id) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpDelete request = new HttpDelete("https://petstore.swagger.io/v2/store/order/" + id);
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				System.out.println("Order with id " + id + " deleted!");
			} else {
				System.out.println("Order not found!");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Map<String, Integer> inventory() {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet request = new HttpGet("https://petstore.swagger.io/v2/store/inventory");
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return new ObjectMapper().readValue(response.getEntity().getContent(), new TypeReference<>() {
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
