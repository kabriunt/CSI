package es.uca.gii.csi16.elrond.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;

import es.uca.gii.csi16.elrond.data.Data;

public class Usuario {
	
	private int _iId;
	private String _sNombre, _sEmail, _sContrasena;
	private boolean _bIsDeleted;

	//GETS
	public int getId() { return _iId; }
	public String getNombre() { return _sNombre; }
	public String getEmail() { return _sEmail; }
	public String getContrasena() { return _sContrasena; }
	public boolean getIsDeleted() { return _bIsDeleted; }

	//SETS
	public void setNombre(String sNombre) { _sNombre = sNombre; }
	public void setEmail(String sEmail) { _sEmail = sEmail; }
	public void setContrasena(String sContrasena) { _sContrasena = sContrasena; }
	
	/**
	 * @param iId
	 * @throws Exception
	 */
	public Usuario(int iId) throws Exception{
		
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
            _sContrasena = rs.getString ( "password" );
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
	/*public String toString() {
		return(super.toString() + ":" + _iId + ":" + _sNombre + ":" + _sEmail + ":" + _sContrasena); 
	}*/
	public String toString() {
		return("ID: " + _iId + "\n"
				+ "Nombre: " + _sNombre + "\n"
				+ "Email: " + _sEmail + "\n"
				+ "Contraseña: " + _sContrasena) + "\n";
	}
	
	/**
	 * @param sNombre
	 * @param sEmail
	 * @param sContrasena
	 * @return
	 * @throws Exception
	 */
	public static Usuario Create(String sNombre, String sEmail, String sContrasena) throws Exception{
		
		Connection con = null;
		
		try {
			con = Data.Connection();
			con.createStatement().executeUpdate("INSERT INTO `usuario` "
					+ "(`nombre`, `email`, `password`) VALUES (" 
					+ Data.String2Sql(sNombre, true, false) + ", " 
					+ Data.String2Sql(sEmail, true, false) + ", " 
					+ Data.String2Sql(sContrasena, true, false) + ");"); 
			
			return new Usuario(Data.LastId(con));
			
		}
		catch (Exception ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}	
	}
	
	public void Delete() throws Exception {
		
		Connection con = null;
		
		try {
			con = Data.Connection();
			con.createStatement().executeUpdate("DELETE FROM `usuario` WHERE `id` =" + _iId);
			Assert.assertTrue(_bIsDeleted);
			_bIsDeleted = true;
		}
		catch (Exception ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}
	}
	
	public void Update() throws Exception{
		
		Connection con = null;
		
		try {
			Assert.assertTrue(_bIsDeleted);
			con = Data.Connection();
			con.createStatement().executeUpdate("UPDATE `usuario` SET "
					+ "`nombre` = " + Data.String2Sql(_sNombre, true, false)
					+ ", `email` = " + Data.String2Sql(_sEmail, true, false)
					+ ", `password` = " + Data.String2Sql(_sContrasena, true, false)
					+ " WHERE `id` = "+ _iId);
		}
		catch (Exception ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}
	}
	
	public static ArrayList<Usuario> Select(String sNombre, String sEmail, String sContrasena) throws Exception {
		
		ArrayList<Usuario> aResultado = new ArrayList<Usuario>();
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT `id`,`nombre`,`email`,`password` "
					+ "FROM `usuario` "+ Where(sNombre,sEmail,sContrasena));
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
	
	private static String Where(String sNombre, String sEmail, String sContrasena) {
		
		String sCad = "" ;

		if (sNombre != null)
			sCad += "`nombre` = " + Data.String2Sql(sNombre,true,false) + " and ";
		if (sEmail != null)
			sCad += "`email` = " + Data.String2Sql(sEmail, true, false) + " and ";
		if (sContrasena != null)
			sCad += "`password` = " + Data.String2Sql(sContrasena, true, false) + " and ";
		
		if (sCad.length() > 0)
			sCad = " WHERE " + sCad.substring(0, sCad.length() - 5);
		
		return sCad;
	}
}