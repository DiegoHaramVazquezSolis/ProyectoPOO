package clases;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Maneja todas las conexiones con la base de datos
 * 
 * Referencias y documentación:
 * http://baro3495.blogspot.com/2012/09/como-instalar-sqlite-en-eclipse-ide.html	Instalación SQLite
 * https://www.sqlitetutorial.net/sqlite-java/										SQLite java
 * https://www.sqlitetutorial.net/sqlite-java/create-database/						Creación de base de datos
 * https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/					Conexión a la base de datos
 * https://www.sqlitetutorial.net/sqlite-java/create-table/							Crear tabla
 * https://www.sqlitetutorial.net/sqlite-java/insert/								Insertar en una tabla
 * https://www.sqlitetutorial.net/sqlite-java/select/								Seleccionar de una tabla
 *
 */
public class DBConnection {
	/**
     * Crea la base de datos
     * 
     * @param url Ruta completa en donde se creara la base de datos
     */
    public static void createAndInitializeDatabase(String url) {

    	// Intenta crear la base de datos
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + url)) {
        	
        	// Si la conexión no es nula podemos asumir que la base de datos se creo correctamente
            if (conn != null) {
                System.out.println("La base de datos ha sido creada correctamente");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Se conecta a la base de datos si existe, si no existe la crea
     * y despues se conecta
     */
	private static Connection connect() {
		String containerFolder = "C:/db/";
		String databaseName = "test.db";

		Connection conn = null;
		// Construimos la ruta completa para acceder a la base de datos
		String url = containerFolder + databaseName;
		File file = new File(url);
		
		// Si ya existe un fichero para la base de datos
		if (file.exists()) {
	        try {
	        	// Inicializamos la conexón
	            conn = DriverManager.getConnection("jdbc:sqlite:" + url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        
	        return conn;
		} else {
			File folder = new File(containerFolder);
			// Si no existe el folder en donde deberia estar la base de datos lo creamos
			if (!folder.exists()) folder.mkdir();
			
			// Creamos la base de datos
			createAndInitializeDatabase(url);
			
			// Intentamos conectar de nuevo
			return connect();
		}
    }
	
	/**
	 * Crea una tabla con el nombre y columnas especificadas en la base de datos
	 * 
	 * @param tableName Nombre de la tabla a crear
	 * @param columns Columnas a crear (ej: id integer primary key, nombre text not null, numero real)
	 */
	public static void createNewTable(String tableName, String columns) {
		String sql = "CREATE TABLE " + tableName + "(\n" + columns + ")";
		try {
			Connection conn = connect();
			Statement stmt = conn.createStatement();
            stmt.execute(sql);
            
            System.out.println("Tabla " + tableName + " creada correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ejecuta la operación de inserción en la tabla especificada 
	 * 
	 * @param tableName Nombre de la tabla
	 * @param columns Columnas especificadas con "," (ej: "id, codigo, nombre")
	 * @param values Arreglo de valores a insertar (arreglo de objects, soporta tipos Integer, Float, Double y String)
	 */
	public static void insertIntoTable(String tableName, String columns, Object ...values) {
		String sql = "INSERT INTO " + tableName + "(" + columns + ") VALUES(";
		try {
			Connection conn = connect();
			for (int i = 0; i < values.length; i++) {
				if (i == values.length - 1) {
					sql += "?)";
				} else {
					sql += "?,";
				}
			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			setValuesOnPreparedStatement(pstmt, values);
			
			pstmt.executeUpdate();
            
            System.out.println("Registro agregado correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ejecuta la operación de actualización de registro en la tabla especificada 
	 * 
	 * @param tableName Nombre de la tabla
	 * @param columns Columnas especificadas con "," (ej: "id, codigo, nombre")
	 * @param values Arreglo de valores a insertar (arreglo de objects, soporta tipos Integer, Float, Double y String)
	 */
	public static void updateTableRecords(String tableName, String columns, String whereCondition, Object ...values) {
		String sql = "UPDATE " + tableName + " SET ";
		String[] cols = columns.split(",");
		
		try {
			Connection conn = connect();
			for (int i = 0; i < cols.length; i++) {
				if (i == values.length - 1) {
					sql += cols[i] + " = ?";
				} else {
					sql += cols[i] + " = ?,";
				}
			}
			
			sql = whereCondition == "" ? sql : (sql + " WHERE " + whereCondition);
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			setValuesOnPreparedStatement(pstmt, values);
			
			int updatedRows = pstmt.executeUpdate();
            
            System.out.println(updatedRows + " Registro(s) actualizado correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Elimina registros en la tabla especificada
	 * @param tableName Nombre de la tabla
	 * @param whereCondition Condición where (opcional) para identificar valores a eliminar. SI SE ENVIAN "" SE ELIMINARAN TODOS LOS REGISTROS DE LA TABLA
	 */
	public static void deleteTableRecords(String tableName, String whereCondition) {
		String sql = "DELETE FROM " + tableName + (whereCondition == "" ? "" : (" WHERE " + whereCondition));
		
		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int updatedRows = pstmt.executeUpdate();
			
			System.out.println(updatedRows + " Registro(s) eliminados correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Asigna de forma dinamica los valores a la instancia de PreparedStatement basado en su tipo de dato
	 * @param pstmt PreparedStatement en donde se requieren setear los valores
	 * @param values Arreglo de valores a setear en el PreparedStatement
	 */
	private static void setValuesOnPreparedStatement(PreparedStatement pstmt, Object ...values) {
		try {
			for (int i = 0; i < values.length; i++) {
				switch(values[i].getClass().getSimpleName()) {
					case "Integer":
						pstmt.setInt(i + 1, (int) values[i]);
						break;
					case "Float":
					case "Double":
						pstmt.setDouble(i + 1, (double) values[i]);
						break;
					case "String":
						pstmt.setString(i + 1, (String) values[i]);
						break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Aplica una query de tipo SELECT sobre la base de datos
	 * @param valuesToSelect Columnas a seleccionar separadas por "," (ej: id, codigo, nombre)
	 * @param tableName Nombre de la tabla de donde se seleccionaran los valores
	 * @param whereCondition Condición opcional WHERE (si no se desea utilice "" comillas vacias)
	 * @return Set de resultados de la consulta
	 */
	public static ResultSet selectQuery(String valuesToSelect, String tableName, String whereCondition) {
		String sql = "SELECT " + valuesToSelect + " FROM " + tableName + (whereCondition == "" ? "" : (" WHERE " + whereCondition));
		ResultSet rs = null;
		
		System.out.println(sql);
		
		Connection conn = connect();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
}
