package com.mailandmedia.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mailandmedia.base.BaseClass;
import com.mailandmedia.endpoints.UserEndpoints;
import com.mailandmedia.utilities.Utilities;

import io.restassured.response.Response;

public class UserAPITests extends BaseClass{

	//Stores the api response as a object of single and multiple users
	public Response singleUserResponse= UserEndpoints.readUser();
	public Response multiUserResponse= UserEndpoints.readUsers();
	
	//Verifies the response code is 200 before running tests
	@BeforeTest
	public void setup()
	{
		singleUserResponse.then().log().all().statusCode(200);
		multiUserResponse.then().log().all().statusCode(200);
	}
	
	//Verifies the age returned is greater than 40
	@Test
	public void verifyAgeIsGreaterThanFourty()
	{
		SoftAssert softAssert = new SoftAssert();
		String age= singleUserResponse.jsonPath().get("results[0].dob.age").toString();
		softAssert.assertTrue(Integer.parseInt(age)> Integer.valueOf(prop.getProperty("user_age")), "Age is not greater than 40 years");
		softAssert.assertAll();
	}
	
	//Verifies the country returned is US
	@Test
	public void verifyCountryIsUS()
	{
		SoftAssert softAssert = new SoftAssert();
		String country= singleUserResponse.jsonPath().get("results[0].location.country").toString();
		softAssert.assertEquals(country, prop.getProperty("user_country"), "User is not from United States");
		softAssert.assertAll();
	}
	
	//Verifies the password does not contain special characters
	@Test
	public void verifyPassswordSchema()
	{
		SoftAssert softAssert = new SoftAssert();
		String password= singleUserResponse.jsonPath().get("results[0].login.password").toString();
		password= password.replaceAll("[a-zA-Z0-9]", "");
		softAssert.assertEquals(password.length(), 0, "Password contains special character");
		softAssert.assertAll();
	}
	
	//Verifies all the above three conditions/tests together
	@Test
	public void verifyUserInformation()
	{
		SoftAssert softAssert = new SoftAssert();
		String age= singleUserResponse.jsonPath().get("results[0].dob.age").toString();
		softAssert.assertTrue(Integer.parseInt(age)> Integer.valueOf(prop.getProperty("user_age")), "Age is not greater than 40 years");
		String country= singleUserResponse.jsonPath().get("results[0].location.country").toString();
		softAssert.assertEquals(country, prop.getProperty("user_country"), "User is not from United States");
		String password= singleUserResponse .jsonPath().get("results[0].login.password").toString();
		password= password.replaceAll("[a-zA-Z0-9]", "");
		softAssert.assertEquals(password.length(), 0, "Password contains special character");
		softAssert.assertAll();
	}

	//Sorts the first names in ascending order after filtering with the character passed
	@Test
	public void filterAndSortUsers()
	{
//		SoftAssert softAssert = new SoftAssert();
		Utilities util= new Utilities();
		util.readUsers(prop.getProperty("character_to_filter"), util.getUsersList());
//		softAssert.assertTrue(util.getUsersList().equals(util.getUsersList()));
	}
	
}
