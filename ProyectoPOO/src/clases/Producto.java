package clases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto {
	//Declaración de las variables
    private String nombre, codigo;
    
    private int categoria;

    private double costo, precio;
    private Number existencia;
    private String[] idsProveedores;
    
    
    

    public Producto(String name, double cost, String cod, double pri, int exis) 
    {    //Constructor
    	
    	
        setNombre(name);
        setCosto(cost);
        setCodigo(cod);
        setPrecio(pri);
        //setCategoria();
        setExistencias(exis);
        
        idsProveedores= null;
        //primera creacion
        
    }
    
    
    public static int addProductToDb(Producto p) {
    	//agregamos el objeto sacando los valores y metiendolos en una query 
    	
    	return DBConnection.insertIntoTable(TablasDB.PRODUCTO,"Codigo, Nombre, Costo, Precio, Existencia",p.getCodigo(),p.getNombre(),p.getCosto(),p.getPrecio(),p.getExistencias());
    	
    }
    public static int  deleteAllProductRecords() {
    	//borra todas las entradas de la tabla producto 
    	
    	int a= DBConnection.deleteTableRecords(TablasDB.PRODUCTO, "");
    	
    	
    	return a;
    }
    public static int updateProductById(Producto updatedProduct) {
    	//mandamos un objeto producto para hacer update estos deben tener el id 
    	int updatedRows = 0;
    	
    		updatedRows = DBConnection.updateTableRecords(TablasDB.PRODUCTO,"Nombre, Costo, Precio, Existencia ","Codigo = '"+updatedProduct.getCodigo()+"'", updatedProduct.getNombre(),updatedProduct.getCosto(),updatedProduct.getPrecio(),updatedProduct.getExistencias());
    
    	
    	return updatedRows;
    }
    public static Producto findProductByCode(String code) {
		/*
		 * Mandamos el codigo del producto y se agrega a una query lo que reusulta en un
		 * RS al que se le dara formato mediante formater para retornar el objeto
		 */
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.PRODUCTO, "Codigo= '" + code + "'");
    	
    	try {
    	rs.next();	
    	}
    	catch(SQLException P){
    		
    		P.printStackTrace();
    	}
    	
    	return formatProduct(rs);
    }
    
    public static Producto findProductByName(String name) {
    	/*
		 * Mandamos el Name del producto y se agrega a una query lo que reusulta en un
		 * RS al que se le dara formato mediante formater para retornar el objeto
		 */
    	
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.PRODUCTO, "Nombre= '" + name + "'");
    	try {
    		rs.next();
    	}catch(SQLException P){
    		P.printStackTrace();
    		}
    	
    	return formatProduct(rs);
    }
    
    
    
    private static Producto formatProduct(ResultSet rs) {
    	
		/*
		 * esta funcion retorna dentro de un objeto listo para utilizar por java el
		 * contenido de la query where implementada anteriormente
		 */    	
    	Producto a=null;
    	try {
    	String cod=rs.getString("Codigo");
    	String name=rs.getString("Nombre");
    	Double cost=rs.getDouble("Costo");
    	Double pre=rs.getDouble("Precio");
    	int exi=rs.getInt("Existencia");
    	//int cat=rs.getInt("IdCategoria");
    	a=new Producto(name,cost,cod,pre,exi);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	
    	
    	return a;
    }
    
    
    
    
    
    
    
    
    public void setNombre(String name) {
        if (name.strip() != "") {
            //Acepta solo si no está vacio
            this.nombre = name;
        }
    }
    public void setCodigo(String code) {
        if (code.strip() != "") {
            //Acepta solo si no está vacio
            this.codigo = code;
        }
    }
    public void setCategoria(int  category) {
        if (category<0) {
            //Acepta solo si no está vacio
            this.categoria = category;
        }
    }
    public void setCosto(double cost) {
        if (cost > 0) {
            //No debe de ser negativo
            this.costo = cost;
        }
    }
    public void setPrecio(double price) {
        if (price > 0) { 	
            //No debe de ser negativo
            this.precio = price;
        }
    }
    public void setExistencias(int exist) {
        if (exist >= 0) {
            //No debe de ser negativo
            this.existencia = exist;
        }
    }
    //Getters 
    public String getNombre() {
        return nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public int getCategoria() {
        return categoria;
    }
    public double getCosto() {
        return costo;
    }
    public double getPrecio() {
        return precio;
    }
    public Number getExistencias() {
        return existencia;
    }
    
    @Override
    public String toString()
    {
    return "Nombre: " + this.getNombre() + " código: " + this.getCodigo() + " Categoria: " + this.getCategoria() + " Costo: $" + this.getCosto()
        + " Precio: $" + getPrecio() + " Existencia: " + getExistencias();
    
    }
    
    @Override
    public boolean equals(Object p)
    {
    if (!(p instanceof Producto)) {
            return false;
        }
        
    else{
        Producto producto = (Producto) p;
        boolean flag1,flag2,flag3;
        flag1= this.getNombre() == producto.getNombre();
        flag2= this.getCodigo()== producto.getCodigo();
        flag3= this.getCategoria()== producto.getCategoria();
        
           if (flag1 && flag2 && flag3) {
            return true;
        }
    }
    return false;
    }
}
