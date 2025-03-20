/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

/**
 *
 * @author sommo
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class dbAdmin {

    Connection con;
    PreparedStatement pst;
    Statement stmt;
    ResultSet rs;

    String password = "som123";

    public Connection connectDB(String db) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db + "?useSSL=false", "root", password);
           // System.out.println("connect");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public ResultSet getResultSet(String sqlCode, String table) {
        String sql = sqlCode;
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String genarateId(String table) {

        try {
            connectDB("library_p");
            String id, sql = "SELECT id FROM " + table + " ORDER BY id DESC";
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            System.out.println(rs.getString("id"));
            id = rs.getString("id");
            sql = id.substring(1);
            //System.out.println(sql);
            //int x=Integer.parseInt(sql)+1;
            //System.out.println(x);
            sql = Integer.toString(Integer.parseInt(sql) + 1);
            sql = id.charAt(0) + sql;
            //System.out.println(sql);
            return sql;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int updateData(String sql) {
        try {

            /*Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/btech_1st_2022-23?useSSL=false","root","pranab1234");
            int roll=Integer.parseInt(t1.getText());
            String sql;
            String nm=t2.getText();
            String add=t3.getText();
            String pwd=t4.getText();
            sql="update student set S_Name='"+nm+"',Address='"+add+"',Password='"+pwd+"' where Roll_No='"+roll+"'";*/
            connectDB("library_p");
            pst = this.con.prepareStatement(sql);
            int x = pst.executeUpdate();
            if (x == 1)
                System.out.println("done");/*JOptionPane.showMessageDialog(rootPane, "data Updated Successfully !!!");*/
            else
                System.out.println("not done");/*JOptionPane.showMessageDialog(rootPane, "data NOT Updated Successfully !!!");*/                 
            
            return x;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

}
