package capstone.mauriziocrispino;

import capstone.mauriziocrispino.MaurizioCrispino.DTO.FeedbackDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class MauriziocrispinoApplicationTests {

	private String bearerToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzA2NjE4ODc0LCJleHAiOjE3MDcyMjM2NzR9.MFpCpjjxb814BLP0cTQ6ey7FBuR2etwGiEkMX3GAKHQ";


	private ObjectMapper objectMapper = new ObjectMapper();
	@Test
	void loginOK() throws Exception {
		String requestBody = "{\"email\": \"marchese@hotmail.com\",\"password\":\"1234\"}";

		given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("http://localhost:3001/auth/login")
				.then()
				.statusCode(200)
				.body(matchesRegex("\\{\"token\":\".{1,}\"\\}"));
	}
	@Test
	void loginNo() throws Exception {
		String requestBody = "{\"email\": \"maurizio@hotmail.com\",\"password\":\"1\"}";

		given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("http://localhost:3001/auth/login")
				.then()
				.statusCode(401);
		//.body(matchesRegex("\\{\"token\":\".{1,}\"\\}"));
	}

	@Test
	void creaFeedback() throws JsonProcessingException {
		String requestBody = objectMapper.writeValueAsString(
				new FeedbackDTO("gioco originale",
						8));

		Response response = given()
				.header("Authorization", "Bearer " + bearerToken) // Aggiungi il token di autenticazione Bearer
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("http://localhost:3010/feedbacks");
		response.then().assertThat().statusCode(anyOf(equalTo(401), equalTo(201)));
		System.out.println("Status Code: "+response.statusCode());
	}
	@Test
	void creareGetAndDeleteFeednack() throws JsonProcessingException {
		//creo un nuovo feedback
		String requestBody = objectMapper.writeValueAsString(
				new FeedbackDTO("gioco originale",
						8));

		Response response = given()
				.header("Authorization", "Bearer " + bearerToken) // Aggiungi il token di autenticazione Bearer
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("http://localhost:3001/feedbacks");
		response.then().assertThat().statusCode(anyOf(equalTo(401), equalTo(201)));
		//prendo l'indirizzo appena creato
		if(response.statusCode() == 201) {
			Long idFeedback = objectMapper.readTree(response.body().asString()).get("id").asLong();
			System.out.println("feedback appena creato: " + idFeedback);
			Response response2 = given()
					.header("Authorization", "Bearer " + bearerToken) // Aggiungi il token di autenticazione Bearer
					.contentType("application/json")
					.when()
					.get("http://localhost:3001/feedbacks/" + idFeedback);
			response2.then().assertThat().statusCode(200);

			//cancello l'indirizzo appena creato
			Response response3 = given()
					.header("Authorization", "Bearer " + bearerToken) // Aggiungi il token di autenticazione Bearer
					.contentType("application/json")
					.when()
					.delete("http://localhost:3001/indirizzi/"+idFeedback);
			response3.then().assertThat().statusCode(204);
		}
		if(response.statusCode() == 401){
			//prendo tutti i feedback
			Response response3 = given()
					.header("Authorization", "Bearer " + bearerToken) // Aggiungi il token di autenticazione Bearer
					.contentType("application/json")
					.when()
					.get("http://localhost:3001/feedbacks" );
			response3.then().assertThat().statusCode(200);
			JsonNode jsonNode = objectMapper.readTree(response3.body().asString()).get("content");
			//elimino il primo feedback
			Response response4 = given()
					.header("Authorization", "Bearer " + bearerToken) // Aggiungi il token di autenticazione Bearer
					.contentType("application/json")
					.when()
					.delete("http://localhost:3001/feedbacks/"+jsonNode.get(0).get("id").asLong());
			response4.then().assertThat().statusCode(204);
		}

	}

}
