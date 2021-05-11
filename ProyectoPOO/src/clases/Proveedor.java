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
    
    @Override
    public String toString()
    {
  return "Nombre: " + this.getNombrePer() + " RFC: " + this.getRfc() + " Telefono: " + this.getTelefono() + " Domicilio: " + this.getDomicilio()
        + " Razon Social: " + this.getRazon() + (Integer.valueOf(this.getPid())> 0 ? (" Id: " + this.getPid()) : "");
    
    }
    
    @Override
    public boolean equals(Object pr)
    {
    if (!(pr instanceof Proveedor)) {
            return false;
        }
        
    else{
        Proveedor proveedor = (Proveedor) pr;
        boolean flag1,flag2,flag3,flag4,flag5,flag6;
        flag1= this.getNombrePer()== proveedor.getNombrePer();
        flag2= this.getRfc()== proveedor.getRfc();
        flag3= this.getTelefono()== proveedor.getTelefono();
        flag4= this.getDomicilio()==proveedor.getDomicilio();
        flag5= this.getPid()==proveedor.getPid();
        flag6= this.getRazon()== proveedor.getRazon();
        
           if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6) {
            return true;
        }
    }
    return false;
    }
}
