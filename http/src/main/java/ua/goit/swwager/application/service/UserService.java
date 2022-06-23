package ua.goit.swwager.application.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import ua.goit.swwager.application.MapperUtils;
import ua.goit.swwager.application.model.APIResponse;
import ua.goit.swwager.application.model.User;

public class UserService {

	public APIResponse createWithList(List<User> user) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost request = new HttpPost("https://petstore.swagger.io/v2/user/createWithList");
			String serialize = MapperUtils.serialize(user);
			System.out.println(serialize);
			request.setEntity(new StringEntity(serialize, ContentType.APPLICATION_JSON));
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(APIResponse.class, response.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public User findUserByUserName(String username) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			String uri = getHttps().setPath("v2/user/" + username).build().toASCIIString();
			HttpGet request = new HttpGet(uri);
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(User.class, response.getEntity());
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	private URIBuilder getHttps() {
		return new URIBuilder().setScheme("https").setHost("petstore.swagger.io");
	}

	public APIResponse updateUserByUsername(String username, User user) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			String uri = getHttps().setPath("v2/user/" + username).build().toASCIIString();
			HttpPut request = new HttpPut(uri);
			request.setEntity(
					new StringEntity(MapperUtils.serialize(user), ContentType.APPLICATION_JSON));
			HttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(APIResponse.class, response.getEntity());
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public APIResponse loginUser(String username, String password) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			URI uri = new URIBuilder().setScheme("https")
									  .setHost("petstore.swagger.io/v2")
									  .setPath("/user/login")
									  .setParameter("username", username)
									  .setParameter(password, password)
									  .build();
			HttpGet request = new HttpGet(uri);
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(APIResponse.class, response.getEntity());
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteUserByUsername(String username) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpDelete request = new HttpDelete("https://petstore.swagger.io/v2/user/" + username);
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				System.out.println("User with username " + username + " deleted!");
			} else {
				System.out.println("User not found!");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public APIResponse logout() {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet request = new HttpGet("https://petstore.swagger.io/v2/user/logout");
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(APIResponse.class, response.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public APIResponse createdUser(User user) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost request = new HttpPost("https://petstore.swagger.io/v2/user");
			request.setEntity(new StringEntity(MapperUtils.serialize(user), ContentType.APPLICATION_JSON));
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(APIResponse.class, response.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
