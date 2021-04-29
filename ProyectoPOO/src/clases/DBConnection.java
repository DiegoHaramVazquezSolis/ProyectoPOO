package clases;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Maneja todas las conexiones con la base de datos
 * 
 * Referencias y documentación:
 * http://baro3495.blogspot.com/2012/09/como-instalar-sqlite-en-eclipse-ide.html	Instalación SQLite
 * https://www.sqlitetutorial.net/sqlite-java/create-database/						Creación de base de datos
 * https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/					Conexión a la base de datos
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
	            
	            System.out.println("Conexión con la base de datos establecida");
	            
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
			System.out.println(sql);
			Statement stmt = conn.createStatement();
            // Create a new table
            stmt.execute(sql);
            
            System.out.println("Tabla " + tableName + " creada correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
