package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.Users;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	Users users;
	public Logger logger;

	@BeforeClass
	private void setupData() {
		faker = new Faker();
		users = new Users();

		users.setId(faker.idNumber().hashCode());
		users.setUsername(faker.name().username());
		users.setFirst_name(faker.name().firstName());
		users.setLast_name(faker.name().lastName());
		users.setEmail(faker.internet().safeEmailAddress());
		users.setPassword(faker.internet().password(5, 10));
		users.setPassword(faker.phoneNumber().cellPhone());

		logger =  LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostUser() {
		logger.info("********** Creating User *************");
		Response response = UserEndPoints.createUser(users);
		
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("********** user is created **********");

	}

	@Test(priority = 2)
	public void testGetUserByName() {

		logger.info("********** Reading User Info *************");

		Response response = UserEndPoints.readUser(this.users.getUsername());
		

		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("**********  User Info Displayed *************");
	}

	@Test(priority = 3)
	public void testUpdateByName() {
		logger.info("********** Updating User Info *************");

		users.setFirst_name(faker.name().firstName());
		users.setLast_name(faker.name().lastName());
		users.setEmail(faker.internet().emailAddress());

		Response response = UserEndPoints.updateUser(this.users.getUsername(), users);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** Updating User Info *************");

	}

	@Test(priority = 4)
	public void testDeleteData() {

		logger.info("********** Deleting User Info *************");
		Response response = UserEndPoints.DeleteUser(this.users.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("********** Deleting User Info *************");
	}

}
