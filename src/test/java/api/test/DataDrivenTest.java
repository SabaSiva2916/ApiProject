package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.Users;
import api.utilities.Dataprovider;
import io.restassured.response.Response;

public class DataDrivenTest {

	@Test(priority = 1, dataProvider = "getAllData", dataProviderClass = Dataprovider.class)
	public void testPostUser(String userId, String username, String firstName, String lastName, String email,
			String password, String phone) {

		Users userPayLoad = new Users();
		userPayLoad.setId(Integer.parseInt(userId));
		userPayLoad.setUsername(username);
		userPayLoad.setFirst_name(firstName);
		userPayLoad.setLast_name(lastName);
		userPayLoad.setEmail(email);
		userPayLoad.setPassword(password);
		userPayLoad.setPhone(phone);

		Response response = UserEndPoints.createUser(userPayLoad);
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(response.body().asPrettyString());

	}

	@Test(priority = 2, dataProvider = "getUserName", dataProviderClass = Dataprovider.class)
	public void testDeleteUser(String username) {
		Response response = UserEndPoints.DeleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
