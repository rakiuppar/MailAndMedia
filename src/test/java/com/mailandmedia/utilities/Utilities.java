package com.mailandmedia.utilities;

import java.util.ArrayList;
import java.util.Comparator;

import com.mailandmedia.base.BaseClass;
import com.mailandmedia.tests.UserAPITests;

public class Utilities extends BaseClass {

	UserAPITests userTests = new UserAPITests();

	public void readUsers(String filterValue, ArrayList<String> list) {
		list.sort(Comparator.naturalOrder());

		System.out.println("-------------------------------------------");
		System.out.println("Complete Sorted List: ");
		for (String firstName : list)
			System.out.println(firstName);
		System.out.println("Filtered Sorted List: ");
		for (String firstName : list) {
			if (firstName.length() > 0) {
				if (Character.toLowerCase(firstName.charAt(0)) == Character.toLowerCase(filterValue.charAt(0)))
					System.out.println(firstName);
			}

		}
	}

	public ArrayList getUsersList() {
		ArrayList<String> usersList = new ArrayList<String>();
		for (int i = 0; i < Integer.valueOf(prop.getProperty("no_of_users")); i++) {
			usersList.add(userTests.multiUserResponse.jsonPath().get("results[" + i + "].name.first").toString());
		}
		return usersList;
	}

}
