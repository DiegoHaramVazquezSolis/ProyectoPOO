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
    
    public boolean equals(Cliente c)
    {
    if (!(c instanceof Cliente)) {
            return false;
        }
        
    else{
        Cliente client = (Cliente) c;
        boolean flag1,flag2,flag3,flag4,flag5;
        flag1= this.getNombrePer()== c.getNombrePer();
        flag2= this.getRfc()== c.getRfc();
        flag3= this.getTelefono()== c.getTelefono();
        flag4= this.getDomicilio()==c.getDomicilio();
        flag5= this.getCid()==c.getCid();
        
           if (flag1 && flag2 && flag3 && flag4 && flag5) {
            return true;
        }
    }
    return false;
    }
}
