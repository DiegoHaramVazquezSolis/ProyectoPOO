package clases;

import java.util.Date;

public interface Fecha {
	
    //Definimos el atributo
   public Date fecha = new Date();
   
   //Se definen los metodos abstractos
   
   public abstract void setDate(Date fecha);
   
   public abstract String formatearFecha();
   
   public abstract boolean guardarFecha();
}
