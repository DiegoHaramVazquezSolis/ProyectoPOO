package clases;

public class Usuario {
	//Se declaran los atributos
    private String uid, nombreUsuario;
    
    private boolean admin;
    
    private String contraseña;
    
    //Constructor
    public Usuario(String id,String name,String pass,boolean ad) 
    {
        setUid(id);
        setNombreUs(name);
        setPassword(pass);
        setAdmin(admin);
    }
    //Setters 
    public void setUid(String id) {
        if (id.strip() != "") {
            //Acepta solo si no está vacio
            this.uid = id;
        }
    }
    public void setNombreUs(String name) {
        if (name.strip() != "") {
            //Acepta solo si no está vacio
            this.nombreUsuario = name;
        }
    }
    public void setPassword(String password) {
        if (password.strip() != "") {
            //Acepta solo si no está vacio
            this.contraseña = password;
        }
    }
    public void setAdmin(boolean admin) {
            this.admin = admin;
        }
    
    //Getters 
    public String getUid() {
        return uid;
    }
    public String getNombre() {
        return nombreUsuario;
    }
    public String getPassword() {
        return contraseña;
    }
    public boolean getAdmin() {
        return admin;
    }
}
