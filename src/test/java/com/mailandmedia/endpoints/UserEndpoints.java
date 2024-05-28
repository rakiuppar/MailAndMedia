package com.mailandmedia.endpoints;

import com.mailandmedia.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class UserEndpoints extends BaseClass{

	// We can have single method which stores one or more users info. 
	//Reads and stores the single user data as a object
	public static Response readUser() {
		RestAssured.baseURI= prop.getProperty("base_uri");
		Response response = RestAssured
			.given()
				.contentType(prop.getProperty("content_type"))
			.when()
				.get(prop.getProperty("get_user_uri"));
		return response;
	}
	
	//Reads and stores the multiple users data as a object
	public static Response readUsers() {
		RestAssured.baseURI= prop.getProperty("base_uri");
		Response response = RestAssured
			.given()
				.contentType(prop.getProperty("content_type"))
			.when()
				.queryParam("results",prop.getProperty("no_of_users"))
				.get(prop.getProperty("get_user_uri"));
		return response;
	}
}
