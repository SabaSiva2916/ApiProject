package api.endpoints;

/*
 swagger URL -->https://petstore.swagger.io
 
 create user -->https://petstore.swagger.io/v2/user               post method
 get user    -->https://petstore.swagger.io/v2/user/{username}    get method
 update user -->https://petstore.swagger.io/v2/user/{username}    put method
 Delete user -->https://petstore.swagger.io/v2/user/{username}    delete method

 */

public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";

	public static String post = base_url + "/user";
	public static String get = base_url + "/user/{username}";
	public static String update = base_url + "/user/{username}";
	public static String delete = base_url + "/user/{username}";

}
