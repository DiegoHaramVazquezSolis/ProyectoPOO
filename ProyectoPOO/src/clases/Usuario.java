package clases;

public class Usuario {
	//Se declaran los atributos
    private String uid, nombreUsuario;
    
    private boolean admin;
    
    private String contrase�a;
    
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
            //Acepta solo si no est� vacio
            this.uid = id;
        }
    }
    public void setNombreUs(String name) {
        if (name.strip() != "") {
            //Acepta solo si no est� vacio
            this.nombreUsuario = name;
        }
    }
    public void setPassword(String password) {
        if (password.strip() != "") {
            //Acepta solo si no est� vacio
            this.contrase�a = password;
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
        return contrase�a;
    }
    public boolean getAdmin() {
        return admin;
    }
}
