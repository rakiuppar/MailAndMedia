package com.mailandmedia.tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mailandmedia.utilities.Utilities;

class ReadUsersTest2 {
	Utilities util = new Utilities();
	private ArrayList<String> list;

	@BeforeMethod
	void setUp() {
		list = new ArrayList<>(Arrays.asList("apple", "cat", "Ball", "Ant", "Cut", "bat"));
	}

	@Test
	void verifyMatchFound() {
		// General case with filter 'A' or 'a'
		readUsers("A", list);
		// You should observe console output for "Ant" and "apple" in sorted order
	}

	@Test
	void verifyZeroMatch() {
		// Filter 'X' or 'x' which matches none of the names
		readUsers("X", list);
	}

	@Test
	void verifyEmptyList() {
		list.clear();
		readUsers("A", list);
	}

	@Test
	void verifyEmptyFilterValue() {
		readUsers("", list);
	}

	@Test
	void verifyUpperCase() {
		readUsers("C", list);
	}

	@Test
	void verifyLowerCase() {
		readUsers("C", list);
	}

	@Test
	void verifySpecialChar() {
		list.add("éclair");
		readUsers("é", list);
	}

	// Method to invoke the readUsers method and capture output
	private void readUsers(String filterValue, ArrayList<String> list) {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));
		util.readUsers(filterValue, list);
		System.setOut(originalOut);
		System.out.print(outContent.toString());
	}
}
