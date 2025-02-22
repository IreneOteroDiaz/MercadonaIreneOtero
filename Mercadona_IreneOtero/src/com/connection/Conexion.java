/**
 * Clase que implementa la conexi�n a la base de datos "prod".
 * Para esto es necesario el driver de conexi�n denominado "mysql-connector-java-8.0.28.jar"
 * ecnontrado en "Referenced Libraries" del proyecto.
 *
 * @author Irene Otero
 * @version 1.0
 * @since   2022-01-22
 * */

package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import com.dao.*;
 
public class Conexion {
	/**
	* Este m�todo es el encargado de realizar toda la conexi�n.
	* @param nada
	* @return HashMap con un objeto Connection "con" y con otro objeto PrepararStatements "ps" .
	*/
	public static HashMap<String,Object> conectar() {
		Connection con = null;
		
		String password = "root"; 
		String usuario = "root"; 
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/prod?useServerPrepStmts=true",
					usuario, password); //Los mismos datos utilizados para la conexi�n en MySQL Workbench
			if (con != null) {
				System.out.println("Conectado");
			}
		} catch (SQLException e) {
			System.out.println("No se pudo conectar a la base de datos");
			e.printStackTrace();
		}
		PrepararStatements ps = new PrepararStatements();
		ps.prepararStatements(con);
		
		HashMap<String,Object> res = new HashMap<String,Object>();
		res.put("conexion", con);
		res.put("statements", ps);
		
		return res;
	}
}
