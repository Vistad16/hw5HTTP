package ua.goit.swwager.application.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import ua.goit.swwager.application.MapperUtils;
import ua.goit.swwager.application.model.APIResponse;
import ua.goit.swwager.application.model.Pet;
import ua.goit.swwager.application.model.Status;

public class PetService {

	public Pet createPet(Pet pet) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost request = new HttpPost("https://petstore.swagger.io/v2/pet");
			request.setEntity(
					new StringEntity(MapperUtils.serialize(pet), ContentType.APPLICATION_JSON));
			HttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(Pet.class, response.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public APIResponse uploadImage(int id, File file) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost request = new HttpPost("https://petstore.swagger.io/v2/pet/" + id + "/uploadImage");
			HttpEntity entity = MultipartEntityBuilder.create()
													  .addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, "filename")
													  .build();
			request.setEntity(entity);
			CloseableHttpResponse execute = client.execute(request);
			System.out.println(execute.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(APIResponse.class, execute.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Pet updateExistingPet(Pet pet) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPut request = new HttpPut("https://petstore.swagger.io/v2/pet");
			request.setEntity(
					new StringEntity(MapperUtils.serialize(pet), ContentType.APPLICATION_JSON));
			HttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(Pet.class, response.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Pet> findByStatus(Status status) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			URI uri = new URIBuilder().setScheme("https")
									  .setHost("petstore.swagger.io/v2")
									  .setPath("/pet/findByStatus")
									  .setParameter("status", status.getValue())
									  .build();
			HttpGet request = new HttpGet(uri);
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserializeList(Pet.class, response.getEntity());

		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public Pet findPetByID(int id) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet request = new HttpGet("https://petstore.swagger.io/v2/pet/" + id);
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(Pet.class, response.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Pet updateByID(Pet pet) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost request = new HttpPost("https://petstore.swagger.io/v2/pet");
			request.setEntity(
					new StringEntity(MapperUtils.serialize(pet), ContentType.APPLICATION_JSON));
			HttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return MapperUtils.deserialize(Pet.class, response.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteByID(int id) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpDelete request = new HttpDelete("https://petstore.swagger.io/v2/pet/" + id);
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				System.out.println("Pet with id " + id + " deleted!");
			} else {
				System.out.println("Pet not found!");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
