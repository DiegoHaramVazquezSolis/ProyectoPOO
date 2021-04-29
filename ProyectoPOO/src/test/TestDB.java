package test;

import clases.DBConnection;

public class TestDB {

	public static void main(String[] args) {
		DBConnection.createNewTable("PRODUCTOS", "id integer primary key, \ncodigo text not null, \nnombre text not null, \ncosto real not null, \nprecio real not null, \nexistencia real not null\n");
	}

}
