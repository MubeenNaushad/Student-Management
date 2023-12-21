/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package students;

import db.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Score {
    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    
    public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from Score");
            while (rs.next()) {

                id = rs.getInt(1);
            }
        } catch (SQLException ex) {

            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);

        }
        return id + 1;

    }
      public boolean getDetails(int id,int semesterNo){
            try {
                ps = con.prepareStatement("select * from course where student_id = ? and semester = ?");
                ps.setInt(1, id);
                ps.setInt(2, semesterNo);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                        Home.jTextField41.setText(String.valueOf(rs.getInt(2)));
                        Home.jTextField10.setText(String.valueOf(rs.getInt(3)));
                        Home.jTextField11.setText(rs.getString(4));
                        Home.jTextField12.setText(rs.getString(5));
                        Home.jTextField13.setText(rs.getString(6));
                        Home.jTextField14.setText(rs.getString(7));
                        Home.jTextField15.setText(rs.getString(8));
                        
                        
                    return true;
                }else {
                    JOptionPane.showMessageDialog(null, "Student id or Semester ID doesn't exist");
                } 
            } catch (SQLException ex) {
                Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
      
       public boolean isIdExist(int id) {
        try {
            ps = con.prepareStatement("select * from Score where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
       
       public boolean isSIdExist(int sid, int semesterNo) {
        try {
            ps = con.prepareStatement("select * from Score where student_id = ? and semester =?");
            ps.setInt(1, sid);
            ps.setInt(2, semesterNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
       
       
       public void getScoreValue(JTable table, String searchValue) {
        String sql = "select * from score where concat(id, student_id, semester) like ? order by id desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[14];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getString(6);
                row[6] = rs.getDouble(7);
                row[7] = rs.getString(8);
                row[8] = rs.getDouble(9);
                row[9] = rs.getString(10);
                row[10] = rs.getDouble(11);
                row[11] = rs.getString(12);
                row[12] = rs.getDouble(13);
                
                row[13] = rs.getDouble(14);
                
               
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       
       
        public void insert(int id, int sid, int semester, String course1, String course2, String course3, String course4, String course5
        ,double score1, double score2, double score3, double score4, double score5, double average) {
        String sql = "insert into score Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, sid);
            ps.setInt(3, semester);
            ps.setString(4, course1);
            ps.setDouble(5, score1);
            ps.setString(6, course2);
            ps.setDouble(7, score2);
            ps.setString(8, course3);
            ps.setDouble(9, score3);
            ps.setString(10, course4);
            ps.setDouble(11, score4);
            ps.setString(12, course5);
            ps.setDouble(13, score5);
            ps.setDouble(14, average);
            if (ps.executeUpdate() > 0) {

                JOptionPane.showMessageDialog(null, "Scores Added Successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

        
        public void update(int id, double score1, double score2, double score3, double score4, double score5, double average) {
        String sql = "update score set score1 = ?, score2 = ?, score3 = ?, score4 = ?, score5 = ?, average = ? where id = ?";
        try {
            ps = con.prepareStatement(sql);
        
            ps.setDouble(1, score1);
            ps.setDouble(2, score2);
            ps.setDouble(3, score3);
            ps.setDouble(4, score4);
            ps.setDouble(5, score5);
            ps.setDouble(6, average);
            ps.setInt(7, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Score Updated Successfully.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
        
        public void delete(int id, int semNo) {
        int yesOrNo = JOptionPane.showConfirmDialog(null, "Score records will be deleted", "Score Delete", JOptionPane.OK_CANCEL_OPTION, 0);
        if (yesOrNo == JOptionPane.OK_OPTION) {
            
            try {
                ps = con.prepareStatement("delete from score where student_id = ? and semester = ?");
                ps.setInt(1, id);
                ps.setInt(2, semNo);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Student deleted successfully.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
        
}


