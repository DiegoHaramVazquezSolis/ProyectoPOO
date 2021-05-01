package clases;

public class Proveedor extends Persona{
	
	private String pId, razonSocial;
    //Constructor
    public Proveedor(String rfc, String name, String tel, String dom, 
            String id, String razon) {
        super(rfc, name, tel, dom);
        setPid(id);
        setRazon(razon);
    }
    //setter
    public void setPid(String id) {
        if (id.strip() != "") {
            //Acepta solo si no está vacio
            this.pId = id;
        }
    }
    public void setRazon(String razon) {
        if (razon.strip() != "") {
            //Acepta solo si no está vacio
            this.razonSocial = razon;
        }
    }
    //getter
    public String getPid() {
        return pId;
    }
    
    public String getRazon()
    {
    return razonSocial;
    }
}
