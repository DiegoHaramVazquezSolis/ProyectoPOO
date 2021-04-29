package test;

import clases.DBConnection;

public class TestDB {

	public static void main(String[] args) {
		DBConnection.connect("C:/db/", "test.db");
	}

}
