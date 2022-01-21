/**
 * Creaci�n de una demo para ver el funcionamiento del programa
 * */

package com.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import com.connection.Conexion;
import com.controller.ControllerProducto;
import com.dao.PrepararStatements;
import com.model.Producto;

public class CRUDDemo {
	
	public static void main(String[] args){
		
		try {
			HashMap<String,Object> connectResult = Conexion.conectar();
			PrepararStatements ps = (PrepararStatements)connectResult.get("statements");
			Connection con = (Connection)connectResult.get("conexion");
		
			Producto producto2 = new Producto("Pollo", "Hacendado", "km 0 (valenciano)");
			Producto producto1 = new Producto("Queso", "Hacendado", "Tarrina grande");
		
			ControllerProducto controller = new ControllerProducto(ps);
			
			//Crear productos a trav�s del controlador.
		
			try {
				int id1 = controller.creacion(producto1);
				producto1.setId(id1);
			} catch (Exception e) {
				System.out.println("Error: Clase CRUDDemo - creacion1");
				e.printStackTrace();
			}
		
			try {
				int id2 = controller.creacion(producto2);
				producto2.setId(id2);
			} catch (Exception e) {
				System.out.println("Error: Clase CRUDDemo - creacion2");
				e.printStackTrace();
			}
			
			//Ver la lista de todos los productos.
			try {
				controller.verListaProductos();
			} catch (Exception e) {
				System.out.println("Error: Clase CRUDDemo - verListaProductos1");
				e.printStackTrace();
			}
			
			//Editar un producto
			try {
				producto1.setNombre("Guacamole");
				controller.actualizar(producto1);
			} catch (Exception e) {
				System.out.println("Error: Clase CRUDDemo - editarProducto");
				e.printStackTrace();
			}
			
			//Ver la lista de todos los productos con el producto modificado.
			try {
				controller.verListaProductos();
			} catch (Exception e) {
				System.out.println("Error: Clase CRUDDemo - verListaProductos2");
				e.printStackTrace();
			}
		
			//Eliminar un producto
			try {
				controller.eliminar(producto1);
			} catch (Exception e) {
				System.out.println("Error: Clase CRUDDemo - eliminar");
				e.printStackTrace();
			}
		
			//Ver la lista de todos los productos con el producto eliminado.
			try {
				controller.verListaProductos();
			} catch (Exception e) {
				System.out.println("Error: Clase CRUDDemo - verListaProductos3");
				e.printStackTrace();
			}
			
			ps.getStmAct().close();
			ps.getStmEli().close();
			ps.getStmVer().close();
			ps.getStmCrear().close();
			con.close();
		 
		} catch (SQLException e) {
			System.out.println("Error: Clase CRUDDemo");
			e.printStackTrace();
		}
	}
}
