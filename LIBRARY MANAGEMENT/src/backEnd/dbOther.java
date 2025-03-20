/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

import java.sql.*;

/**
 *
 * @author sommo
 */
public class dbOther {
    Connection con;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;
    
    String password = "som123";
    
    public Connection connectDB(String db) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db + "?useSSL=false", "root", password);
            //System.out.println("connect");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    public int login(String id,String nm,String pass,String table){
        String sql;
        sql = "select * from "+table+" where id = '"+id+"'" ;/*where id= "+id+"";*/
        try {
            //System.out.println(con);
            stmt = this.con.createStatement();
            //System.out.println(stmt);
            rs = stmt.executeQuery(sql);
            //System.out.println("hi");
            rs.next();
            System.out.println(rs.getString("name"));
            if(pass.equals(rs.getString("password")) && nm.equalsIgnoreCase(rs.getString("name"))){
                return 1;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public ResultSet getResultSet(){
        return rs;
    } 
    
    public String genarateId(String table){
        
        try{
            connectDB("library_p");
            String id,sql="SELECT id FROM "+table+" ORDER BY id DESC";
            stmt=this.con.createStatement();
            rs=stmt.executeQuery(sql);
            rs.next();
            //System.out.println(rs.getString("id"));
            id=rs.getString("id");
            sql=id.substring(1);
            //System.out.println(sql);
            //int x=Integer.parseInt(sql)+1;
            //System.out.println(x);
            sql=Integer.toString(Integer.parseInt(sql)+1);
            sql=id.charAt(0)+sql;
            //.out.println(sql);
            return sql;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
}
