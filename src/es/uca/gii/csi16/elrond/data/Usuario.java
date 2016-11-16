package es.uca.gii.csi16.elrond.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;

import es.uca.gii.csi16.elrond.data.Data;

public class Usuario {
	
	private int _iId;
	private String _sNombre, _sEmail, _sPassword;
	private boolean _bIsDeleted;

	//GETS
	public int getId() { return _iId; }
	public String getNombre() { return _sNombre; }
	public String getEmail() { return _sEmail; }
	public String getPassword() { return _sPassword; }
	public boolean getIsDeleted() { return _bIsDeleted; }

	//SETS
	public void setNombre(String sNombre) { _sNombre = sNombre; }
	public void setEmail(String sEmail) { _sEmail = sEmail; }
	public void setPassword(String sPassword) { _sPassword = sPassword; }
	
	/**
	 * @param iId
	 * @throws Exception
	 */
	public Usuario(int iId) throws Exception {
		
		Connection con = null;
        ResultSet rs = null;
		
		try {        	
            con = Data.Connection();
            rs = con.createStatement().executeQuery("SELECT nombre, password, email "
            		+ "FROM usuario where id=" + iId + ";"); 
            
            rs.next();
            _iId = iId;
            _sNombre = rs.getString ( "nombre" );
            _sEmail = rs.getString ( "email" );
            _sPassword = rs.getString ( "password" );
            _bIsDeleted = false;
            
        }
        catch (SQLException ee) { throw ee; }
        finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return(super.toString() + ":" + _iId + ":" + _sNombre + ":" + _sEmail + ":" + _sPassword); 
	}
	
	/**
	 * @param sNombre
	 * @param sEmail
	 * @param sPassword
	 * @return
	 * @throws Exception
	 */
	public static Usuario Create(String sNombre, String sEmail, String sPassword) throws Exception {
		
		Connection con = null;
		
		try {
			con = Data.Connection();
			con.createStatement().executeUpdate("INSERT INTO `usuario` "
					+ "(`nombre`, `email`, `password`) VALUES (" 
					+ Data.String2Sql(sNombre, true, false) + ", " 
					+ Data.String2Sql(sEmail, true, false) + ", " 
					+ Data.String2Sql(sPassword, true, false) + ");"); 
			
			return new Usuario(Data.LastId(con));
			
		}
		catch (Exception ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}	
	}
	
	/**
	 * Borra un registro de la base de datos y pone _bIsDeleted del
	 * objeto a true
	 * @throws Exception
	 */
	public void Delete() throws Exception {
		
		Connection con = null;
		
		try {
			Assert.assertFalse(_bIsDeleted);
			con = Data.Connection();
			con.createStatement().executeUpdate("DELETE FROM `usuario` WHERE `id` =" + _iId);
			_bIsDeleted = true;
		}
		catch (Exception ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}
	}
	
	/**
	 * Actualiza los datos de un objeto en la base de datos
	 * @throws Exception
	 */
	public void Update() throws Exception{
		
		Connection con = null;
		
		try {
			Assert.assertFalse(_bIsDeleted);
			con = Data.Connection();
			con.createStatement().executeUpdate("UPDATE `usuario` SET "
					+ "`nombre` = " + Data.String2Sql(_sNombre, true, false)
					+ ", `email` = " + Data.String2Sql(_sEmail, true, false)
					+ ", `password` = " + Data.String2Sql(_sPassword, true, false)
					+ " WHERE `id` = "+ _iId);
		}
		catch (Exception ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}
	}
	
	/**
	 * Hace un select a la base de datos con datos de búsqueda que se pasan por parametro.
	 * @param sNombre
	 * @param sEmail
	 * @param sPassword
	 * @return Devuelve un ArrayList de objetos
	 * @throws Exception
	 */
	public static ArrayList<Usuario> Select(String sNombre, String sEmail, String sPassword) throws Exception {
		
		ArrayList<Usuario> aResultado = new ArrayList<Usuario>();
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT `id`,`nombre`,`email`,`password` "
					+ "FROM `usuario` "+ Where(sNombre,sEmail,sPassword));
			while (rs.next()) {
				aResultado.add(new Usuario(rs.getInt("id")));
			}
		}
		catch (Exception ee) { throw ee; }
		finally {
			if(con != null) con.close();
		}
		
		return aResultado;
	}
	
	/**
	 * Concatena los parametros de entrada que no sean nulos.
	 * @param sNombre
	 * @param sEmail
	 * @param sPassword
	 * @return Devuelve un String 
	 */
	private static String Where(String sNombre, String sEmail, String sPassword) {
		
		String sCadena = "" ;

		if (sNombre != null)
			sCadena += "`nombre` LIKE " + Data.String2Sql(sNombre,true,false) + " and ";
		if (sEmail != null)
			sCadena += "`email` LIKE " + Data.String2Sql(sEmail, true, false) + " and ";
		if (sPassword != null)
			sCadena += "`password` LIKE " + Data.String2Sql(sPassword, true, false) + " and ";
		
		if (sCadena.length() > 0)
			sCadena = " WHERE " + sCadena.substring(0, sCadena.length() - 5);
		
		return sCadena;
	}
}