package clases;

public class Producto {
	//Declaración de las variables
    private String nombre, codigo, categoria;
    private double costo, precio;
    private Number existencia;
    private String[] idsProveedores;
    //Constructor
    public Producto(String name, double cost, String cod, double pri,
            String cat, int exis) 
    {
        setNombre(name);
        setCosto(cost);
        setCodigo(cod);
        setPrecio(pri);
        setCategoria(cat);
        setExistencias(exis);
        
        idsProveedores= null;
    }
    //Setters 
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
    public void setCategoria(String category) {
        if (category.strip() != "") {
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
    public String getCategoria() {
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
}
