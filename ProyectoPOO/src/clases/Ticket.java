package clases;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.ResultSet;
public class Ticket implements Fecha {
	

	
private Producto [] productos;
	private LocalDate fechaDeCreacion;
    
    private Cliente cliente;
    
    private double subtotal, impuesto, total;
    
    private String folio;
    
    private Usuario usuario;
    
    
    
    public Ticket(String f,Producto []pd, double iva,Cliente c,Usuario u) {
    	
    	//constructor
    	this.setFolio(f);
    	this.setProductos(pd);
    	this.setImpuesto(iva);
    	this.setCliente(c);
    	this.setUsuario(u);
    	this.setTotal(this.calculateTotal());
    	this.setSubtotal(this.calculateSubTotal());
    	
    }
    
public Ticket(String f,Producto []pd, double iva,Cliente c,Usuario u,LocalDate F) {
    	
    	//constructor para las querys

		this.setFolio(f);
		this.setProductos(pd);
		this.setImpuesto(iva);
		this.setCliente(c);
		this.setUsuario(u);
		this.setTotal(this.calculateTotal());
		this.setSubtotal(this.calculateSubTotal());
	
    	
    	this.setDate(F);
    	
    }
    public void setProductos(Producto []pd) {
    	
    	this.productos=pd;
    	
    }
    
    //setters
    
    public void setDate(LocalDate L) {this.fechaDeCreacion=L;}
    public void setFolio(String f) {this.folio=f;}
    public void setTotal(double t) {this.total=t;}
    public void setImpuesto(double iva) {this.impuesto=iva;}
    public void setSubtotal(double st) {this.subtotal=st;}
    public void setCliente(Cliente cl) {this.cliente=cl; }
    public void setUsuario(Usuario user) {this.usuario=user;}

	/*
	 * los genters necesarios ya que la mayoria de veces se imprime el ticket y la
	 * info necesaria
	 */
    public double getTotal() {return this.total;}
    public double getSubTotal(){return this.subtotal;}
    public Cliente getCliente() {return this.cliente;}
    public Usuario getUsuario() {return this.usuario;}
    
    public double getInpuesto() {return this.impuesto;}
    
  //funciones calculadoras 
    public double calculateTotal() {
    	
    	
    	double t=0.0;
    	for(Producto a: this.productos) {
    		t+=a.getPrecio();
    	}
    	double iva= t*.16;
    	
    	return t+iva;
    }
    
    public double calculateSubTotal() {
    	double t=0.0;
    	for(Producto a: this.productos) {
    		t+=a.getPrecio();
    	}

    	
    	return t;
    }
    
    public int saveTicketDB(Ticket a) {
    	
    	
    	
    	
    	long createdAt = System.currentTimeMillis();
    	return DBConnection.insertIntoTable(TablasDB.TICKET, "SubTotal, Impuesto, fecha",a.getSubTotal(),a.getInpuesto(),createdAt);
    }
    
    
    
    

    @Override 
    public String toString() {
    	String rtn="------Tiendita Generica---------\n";
    	rtn=rtn+"\n\n";
    	rtn=rtn+"Cliente: "+this.getCliente().getNombrePer()+" \n";
    	rtn=rtn+"Vendedor: "+this.getUsuario().getNombre()+" \n";
    	rtn=rtn+"Productos:\n";
    	for(Producto a: this.productos) {
    		
    		rtn=rtn+a.getNombre()+"  precio: "+a.getPrecio()+"  \n";
    		
    	}
    	rtn=rtn+"-------------------------------------\n";
    	rtn=rtn+"Subtotal   "+this.getSubTotal()+"\n";
    	rtn=rtn+"total   "+this.getTotal();
    	
    	return rtn;
    }
    
	/**
	 * Retorna un string con la fecha formateada
	 */
	@Override
	public String formatearFecha(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}
}
