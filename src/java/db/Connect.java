
package db;

import java.sql.*;
/**
 *
 * @author kimin
 */
public class Connect {
    private Connection conn;
    
    public Connect() throws ClassNotFoundException{
        
        Class.forName("com.mysql.jdbc.Driver");      
        try{
            conn = DriverManager.getConnection("jdbc:mysql://" + DBCredential.serverName + "/" + DBCredential.dbName, DBCredential.username, DBCredential.password);
        } catch(SQLException e) {
            System.out.println("Failed to connect to database");
        }
    }
    
    public ResultSet executeQuery(String query){
        ResultSet result = null;
        try {
            Statement statement = conn.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Cannot execute given query");
        }
        return result;
    }
    
    public ResultSet executeQueryWithParam(String query, Object param){
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, (Integer) param);
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Cannot execute given query with parameter");
        }
        return result;
    }
    
    public boolean updateQuery(String query){
        try {
            Statement statement = conn.createStatement(); 
            return statement.executeUpdate(query) > 0;
        } catch (SQLException e) {
            System.out.println("Cannot update given query");
        }
        return false;
    }    
    
    public void closeConn() throws SQLException {
        conn.close();
    }
   
}
