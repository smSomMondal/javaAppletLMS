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
public class dbMember {
    
    Connection con;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;
    String password = "som123";
    
    public Connection connectDB(String db) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_p?useSSL=false", "root", password);
            //System.out.println("connect");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
    public int login(String id,String nm,String pass,String table){
        String sql;
        sql = "select * from "+table+" where id= "+id+"";
        try {
            //System.out.println(con);
            stmt = this.con.createStatement();
            //System.out.println(stmt);
            rs = stmt.executeQuery(sql);
            //System.out.println("hi");
            rs.next();
            //System.out.println(rs.getString("id"));
            if(pass.equals(rs.getString("password")) && nm.equalsIgnoreCase(rs.getString("name"))){
                return 1;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
    
    
    public ResultSet getResultSet(String id,String table){
        String sql = "select * from "+table+" where id= '"+id+"'";
        try {
            connectDB("library_p");
            //System.out.println(con);
            stmt = this.con.createStatement();
            //System.out.println(stmt);
            rs = stmt.executeQuery(sql);
            //System.out.println("hi");
            rs.next();
            //System.out.println(rs.getString("id"));
            return rs;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public ResultSet getResultSetAuthor(String table,String nm){
        String sql ="select * from "+table+" where name like '%"+nm+"%'";
        try {
            connectDB("library_p");
            //System.out.println(con);
            stmt = this.con.createStatement();
            //System.out.println(stmt);
            rs = stmt.executeQuery(sql);
            //System.out.println("hi");
            rs.next();
            //System.out.println(rs.getString("id"));
            return rs;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public ResultSet getResultSetBook(String table,String id){
        System.out.println(id);
        String sql = "select * from book where au_id = '"+id+"'";
        try {
            connectDB("library_p");
            //System.out.println(con);
            stmt = this.con.createStatement();
            System.out.println(stmt);
            rs = stmt.executeQuery(sql);
//            System.out.println("hi");
//            System.out.println(rs);
            rs.next();
            //System.out.println(rs.getString("id"));
            return rs;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    
}
