package es.uca.gii.csi16.elrond.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uca.gii.csi16.elrond.data.Data;
import es.uca.gii.csi16.elrond.data.Usuario;

public class UsuarioTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Data.LoadDriver();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testConstructor() throws Exception {
		
		int iId = 1;
		Usuario usuario = new Usuario(iId);
		Connection con = null;
        ResultSet rs = null;
        
        try {        	
            con = Data.Connection();            
            rs = con.createStatement().executeQuery("SELECT id, nombre, email, password "
            		+ "FROM usuario Where id=" + iId + ";");      
            rs.next();
                        
            Assert.assertEquals(rs.getInt("id"), usuario.getId());
            Assert.assertEquals(rs.getString("nombre"), usuario.getNombre());
            Assert.assertEquals(rs.getString("email"), usuario.getEmail());
            Assert.assertEquals(rs.getString("password"), usuario.getContrasena());
            
            System.out.println(usuario.toString());
        }
        catch (SQLException ee) { throw ee; }
        finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testCreate() throws Exception{ 

		Usuario usuario = Usuario.Create("Prueba", "prueba6@gmail.com", "prueba");
		Connection con = null;
        ResultSet rs = null;

        try {        	
            con = Data.Connection();                     
            rs = con.createStatement().executeQuery("SELECT id, nombre, email, password "
            		+ "FROM usuario Where email='prueba6@gmail.com';");    
            rs.next();

            Assert.assertEquals(rs.getInt("id"), usuario.getId());
            Assert.assertEquals(rs.getString("nombre"), usuario.getNombre());
            Assert.assertEquals(rs.getString("email"), usuario.getEmail());
            Assert.assertEquals(rs.getString("password"), usuario.getContrasena());
            
        }
        catch (SQLException ee) { throw ee; }
        finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
	}
	
	@Test
	public void testSelect() throws Exception {
		
		ArrayList<Usuario> test = new ArrayList<Usuario>();
		test = Usuario.Select("Prueba", null, null);
		
		for(int x=0;x<test.size();x++) {
			System.out.println(test.get(x));
		}
	}
	
	public void testUpdate() throws Exception {//hacerlo con conexion y resultSet
		Usuario usuario = Usuario.Create("PruebaUpdate", "pruebaupdate@gmail.com", "pruebaupdate");
		usuario.setNombre("CambioPruebaUpdate");
		usuario.setEmail("cambiopruebaupdate@gmail.com");
		usuario.setContrasena("cambiopruebaupdate");
		usuario.Update();
	}
	
	public void testDelete() {
		
	}

}
