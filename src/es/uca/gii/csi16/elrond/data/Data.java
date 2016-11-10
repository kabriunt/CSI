package es.uca.gii.csi16.elrond.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

import es.uca.gii.csi16.elrond.util.Config;

public class Data {
    public static String getPropertiesUrl() { return "./db.properties"; }
    public static Connection Connection() throws Exception {
        try {
            Properties properties = Config.Properties(getPropertiesUrl());
            return DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password"));
       }
       catch (Exception ee) { throw ee; }
	}
    
    public static void LoadDriver() 
        throws InstantiationException, IllegalAccessException, 
        ClassNotFoundException, IOException {
            Class.forName(Config.Properties(Data.getPropertiesUrl()
            ).getProperty("jdbc.driverClassName")).newInstance();
    }
    
    /**
     * @param s
     * @param bAddQuotes
     * @param bAddWildcards
     * @return
     */
    public static String String2Sql(String s, boolean bAddQuotes, boolean bAddWildcards) {
    	
    	s = s.replace("'", "''"); 
    	
    	if (bAddWildcards)         
    		s = "%" + s + "%";
    	if (bAddQuotes)
    		s = "'" + s + "'";
    	
    	return s;
    }
    
    /**
     * @param b
     * @return
     */
    public static int Boolean2Sql(boolean b) {
    	
    	if (b)
    		return 1;
    	else
    		return 0;
    }
    
    /**
     * @param con
     * @return
     * @throws Exception
     */
    //TODO: La conexión 'con' debe estar activa. 
    public static int LastId(Connection con) throws Exception {
    	
    	Properties properties = Config.Properties(getPropertiesUrl());
    	ResultSet rs = null;
    	
    	try {
    		rs = con.createStatement().executeQuery(properties.getProperty("jdbc.lastIdSentence"));
    		rs.next(); 
    		
        	return (rs.getInt(1));
    	}
    	catch (Exception ee) { throw ee; }
    	finally {
            if (rs != null) rs.close();
        }
    }   
}