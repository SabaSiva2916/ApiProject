package api.endpoints;

import api.payload.Users;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(Users users) {

		Response post = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(users).when()
				.post(Routes.post);

		return post;
	}

	public static Response readUser(String username) {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", username).when().get(Routes.get);

		return response;
	}

	public static Response updateUser(String username, Users payload) {

		Response post = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.pathParam("username", username).when().put(Routes.update);

		return post;
	}

	public static Response DeleteUser(String username) {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", username).when().delete(Routes.delete);

		return response;
	}
}
