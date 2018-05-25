/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Sono D
 */
public class DBOperations {

    String url = "jdbc:mysql://localhost:3306/student";
    String userName = "root";
    String password = "";
    Connection con = null;
    PreparedStatement prt = null;
    ResultSet rs = null;

    boolean addStudent(Student stuDe) {

        try {
            con = (Connection) DriverManager.getConnection(url, userName, password);
            System.out.println("Connection Created");
            String query = "INSERT INTO studentdetails VALUES (?,?,?,?,?,?,?,?,?)";
            prt = (PreparedStatement) con.prepareStatement(query);

            prt.setInt(1, stuDe.getRegID());
            prt.setString(2, stuDe.getFirstName());
            prt.setString(3, stuDe.getLastName());
            prt.setInt(4, stuDe.getAge());
            prt.setString(5, stuDe.getAddress());
            prt.setString(6, stuDe.getGender());
            prt.setString(7, stuDe.getFaculty());
            prt.setString(8, stuDe.getDepartment());
            prt.setInt(9, stuDe.getYearofreg());

            prt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

                if (prt != null) {
                    prt.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    ArrayList<Student> getStudent() {

        try {

            ArrayList<Student> list = new ArrayList<Student>();

            con = (Connection) DriverManager.getConnection(url, userName, password);
            System.out.println("Connection Created");
            String query = "SELECT * FROM studentdetails";
            prt = (PreparedStatement) con.prepareCall(query);

            rs = prt.executeQuery();

            while (rs.next()) {

                Student s = new Student();
                s.setRegID(rs.getInt(1));
                s.setFirstName(rs.getString(2));
                s.setLastName(rs.getString(3));
                s.setAge(rs.getInt(4));
                s.setAddress(rs.getString(5));
                s.setGender(rs.getString(6));
                s.setFaculty(rs.getString(7));
                s.setDepartment(rs.getString(8));
                s.setYearofreg(rs.getInt(9));

                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {

            try {
                if (con != null) {
                    con.close();
                }

                if (prt != null) {
                    prt.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean updateStudent(Student std) {

        try {
            con = (Connection) DriverManager.getConnection(url, userName, password);
            System.out.println("Connection Created");
            String query = "UPDATE studentdetails SET firstname ='" + std.getFirstName() + "',lastname ='" + std.getLastName() + "',address='" + std.getAddress() + "',age=" + std.getAge() + ",faculty='" + std.getFaculty() + "',department='" + std.getDepartment() + "',yearofreg=" + std.getYearofreg() + ",gender='" + std.getGender() + "' WHERE regid=" + std.getRegID();
            prt = (PreparedStatement) con.prepareCall(query);

            prt.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

                if (prt != null) {
                    prt.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

    public boolean deteleStudent(Student s) {
        try {
            con = (Connection) DriverManager.getConnection(url, userName, password);
            System.out.println("Connection Created");
            String query = "DELETE FROM studentdetails WHERE regid ="+s.getRegID();
            prt = (PreparedStatement) con.prepareCall(query);

            prt.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

                if (prt != null) {
                    prt.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }
}
