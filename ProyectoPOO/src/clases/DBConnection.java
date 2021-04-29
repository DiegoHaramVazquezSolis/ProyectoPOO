package clases;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	/**
     * Create the database
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
     * 
     * @param containerFolder Ruta de la carpeta que contiene la base de datos
     * @param databaseName Nombre de la base de datos
     * @example connect("C:/db/", "test.db"
     */
	public static void connect(String containerFolder, String databaseName) {
		// Construimos la ruta completa para acceder a la base de datos
		String url = containerFolder + databaseName;
		File file = new File(url);
		
		// Si ya existe un fichero para la base de datos
		if (file.exists()) {
			Connection conn = null;
	        try {
	        	// Inicializamos la conexón
	            conn = DriverManager.getConnection("jdbc:sqlite:" + url);
	            
	            System.out.println("Conexión con la base de datos establecida");
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } finally {
	            try {
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException ex) {
	                System.out.println(ex.getMessage());
	            }
	        }
		} else {
			File folder = new File(containerFolder);
			// Si no existe el folder en donde deberia estar la base de datos lo creamos
			if (!folder.exists()) folder.mkdir();
			
			// Creamos la base de datos
			createAndInitializeDatabase(url);
			
			// Intentamos conectar de nuevo
			connect(containerFolder, databaseName);
		}
    }
}
