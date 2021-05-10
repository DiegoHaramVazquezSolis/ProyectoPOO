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
    
    
    public String toString()
    {
  return "Nombre: " + this.getNombrePer() + " RFC: " + this.getRfc() + " Telefono: " + this.getTelefono() + " Domicilio: " + this.getDomicilio()
        + " Razon Social: " + this.getRazon() + (Integer.valueOf(this.getPid())> 0 ? (" Id: " + this.getPid()) : "");
    
    }
    
    public boolean equals(Proveedor pr)
    {
    if (!(pr instanceof Proveedor)) {
            return false;
        }
        
    else{
        Proveedor proveedor = (Proveedor) pr;
        boolean flag1,flag2,flag3,flag4,flag5,flag6;
        flag1= this.getNombrePer()== pr.getNombrePer();
        flag2= this.getRfc()== pr.getRfc();
        flag3= this.getTelefono()== pr.getTelefono();
        flag4= this.getDomicilio()==pr.getDomicilio();
        flag5= this.getPid()==pr.getPid();
        flag6= this.getRazon()== pr.getRazon();
        
           if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6) {
            return true;
        }
    }
    return false;
    }
}
