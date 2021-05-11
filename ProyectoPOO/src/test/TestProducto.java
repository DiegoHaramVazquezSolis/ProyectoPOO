package test;

import clases.Producto;

public class TestProducto {
	
	
	public static void main(String[] args) {
	
	

	
	Producto.deleteAllProductRecords();
	Producto test = new Producto("pantene",22.2,"ahsdasda",12.1,100);
	Producto.addProductToDb(test);
	Producto test2 = new Producto("PlatanoChiapas",2,"ahsdasda",5.90,100);
	Producto.addProductToDb(test2);
		
	Producto searched=	Producto.findProductByCode("ahsdasda");
	//buscamos el producto en la base de datos
	Producto searched2=Producto.findProductByName("PlatanoChiapas");
	//lo modificamos como id es final no se puede modificar y se hace el update mediante este identificador

	searched2.setNombre("PlatanoChiapasMamadisimo");
	
	Producto.updateProductById(searched2);
	
	
	System.out.println(searched.getNombre());
	System.out.println(searched2.getNombre());
	
	
}
}