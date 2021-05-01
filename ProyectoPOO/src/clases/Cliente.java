package clases;

public class Cliente extends Persona{
	
	private String id;
    //Constructor
    public Cliente(String rfc, String name, String tel, String dom,String id)
    {
    super(rfc, name, tel, dom);
        setCid(id);
    }
    
    //setter
    public void setCid(String id) {
        if (id.strip() != "") {
            //Acepta solo si no está vacio
            this.id = id;
        }
    }
    //getter
    public String getCid() {
        return id;
    }
}
