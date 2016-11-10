package es.uca.gii.csi16.elrond.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import es.uca.gii.csi16.elrond.data.Data;

public class DataTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Data.LoadDriver();
	}

	/**
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testTableAccess() throws Exception {
		
		Connection con = null;
        ResultSet rs = null;
        
        try {        	
            con = Data.Connection();                  
            rs = con.createStatement().executeQuery("SELECT * FROM usuario;");
            System.out.println("Numero de campos: " + rs.getMetaData().getColumnCount());
        	int i = 0;
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("nombre") + " | " + rs.getString("email"));
                i++; 
            }
            
            rs.close();
            rs = con.createStatement().executeQuery("SELECT COUNT(*) FROM usuario;");
            rs.next();
            System.out.println("Numero de registros: " + rs.getInt(1)); //Espaciacion
            
            Assert.assertEquals(i, rs.getInt(1));
        }
        catch (SQLException ee) { throw ee; }
        finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
		
	}
	
	@Test
	public void TestString2Sql() {
		
		System.out.println("\nPRUEBAS String2Sql:");
		System.out.println(Data.String2Sql("hola", false, false));
		System.out.println(Data.String2Sql("hola", true, false));
		System.out.println(Data.String2Sql("hola", false, true));
		System.out.println(Data.String2Sql("hola", true, true));
		System.out.println(Data.String2Sql("O'Connell", false, false));
		System.out.println(Data.String2Sql("O'Connell", true, false));
		System.out.println(Data.String2Sql("'Smith '", false, true));
		System.out.println(Data.String2Sql("'Smith '", true, false));
		System.out.println(Data.String2Sql("'Smith '", true, true));
	}
	
	@Test
	public void TestBoolean2Sql() {
		
		System.out.println("\nPRUEBAS Boolean2Sql:");
		System.out.println("Verdadero: " + Data.Boolean2Sql(true));	//Espaciacion
		System.out.println("Falso: " + Data.Boolean2Sql(false)); //Espaciacion
	}

}
