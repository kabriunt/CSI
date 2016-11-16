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
            Assert.assertEquals(rs.getString("password"), usuario.getPassword());
            
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
            Assert.assertEquals(rs.getString("password"), usuario.getPassword());
        }
        catch (SQLException ee) { throw ee; }
        finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
	}
	
	/**
	 * Comprueba y nos muestras los objetos devueltos por el método Select().
	 * @throws Exception
	 */
	@Test
	public void testSelect() throws Exception {
		
		ArrayList<Usuario> test = new ArrayList<Usuario>();
		test = Usuario.Select("Prueba", null, null);
		
		for(int x=0;x<test.size();x++) {
			System.out.println(test.get(x));
		}
	}
	
	/**
	 * Comprueba que Update() hace las modificaciones en la base de datos.
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {

		Usuario usuario = Usuario.Create("PruebaUpdate", "pruebaupdate@gmail.com", "pruebaupdate");
		Connection con = null;
        ResultSet rs = null;
        
        usuario.setNombre("CambioPruebaUpdate");
		usuario.setEmail("cambiopruebaupdate@gmail.com");
		usuario.setPassword("cambiopruebaupdate");
		usuario.Update();
        
        try {        	
            con = Data.Connection();                     
    		rs = con.createStatement().executeQuery("SELECT id, nombre, email, password "
            		+ "FROM usuario Where email='cambiopruebaupdate@gmail.com';");    
            rs.next();

            Assert.assertEquals(rs.getInt("id"), usuario.getId());
            Assert.assertEquals(rs.getString("nombre"), usuario.getNombre());
            Assert.assertEquals(rs.getString("email"), usuario.getEmail());
            Assert.assertEquals(rs.getString("password"), usuario.getPassword());
        }
        catch (SQLException ee) { throw ee; }
        finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
	}
	
	/**
	 * Comprueba que cambia el estado del booleano a través del método Delete().
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {

		Usuario usuario = Usuario.Create("Jorge", "JorgeEmail", "JorgePass");
		
        try {        	
            Assert.assertFalse(usuario.getIsDeleted());
            usuario.Delete();
            Assert.assertTrue(usuario.getIsDeleted());
        }
        catch (SQLException ee) { throw ee; }
	}
}
