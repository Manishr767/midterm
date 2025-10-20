package com.example._5.Repository;



import com.example._5.DTO.MemberDTO;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemberDAO {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/webserver";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Insert new member
    public void insertMember(MemberDTO memberDTO) {
        try {
            Class.forName(JDBC_DRIVER);

            try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(
                         "INSERT INTO member (pwd,email,age,role) VALUES (?, ?, ?,?)")) {

                pstmt.setString(1, memberDTO.getPwd());
                pstmt.setString(2, memberDTO.getEmail());
                pstmt.setInt(3, memberDTO.getAge());
                pstmt.setString(4, memberDTO.getRole());

                int rowsInserted;
                rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("✅ A new member was inserted successfully!");
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find user by email and password
    public MemberDTO finduser(MemberDTO memberDTO) {
        try {
            Class.forName(JDBC_DRIVER);

            try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(
                         "SELECT * FROM member WHERE email = ? AND pwd = ?")) {

                pstmt.setString(1, memberDTO.getEmail());
                pstmt.setString(2, memberDTO.getPwd());

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    MemberDTO member = new MemberDTO();
                    member.setId(rs.getInt("id"));
                    member.setAge(rs.getInt("age"));
                    member.setEmail(rs.getString("email"));
                    member.setPwd(rs.getString("pwd"));
                    member.setPwd(rs.getString("role"));

                    System.out.println("✅ This is a member: " + member.getEmail());
                    return member;
                } else {
                    System.out.println("❌ This is not a member");
                    return null;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Get all members
    public List<MemberDTO> getAllMembers() {
        List<MemberDTO> members = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);

            try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM member");
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    MemberDTO member = new MemberDTO();
                    member.setId(rs.getInt("id"));
                    member.setEmail(rs.getString("email"));
                    member.setPwd(rs.getString("pwd"));
                    member.setAge(rs.getInt("age"));
                    member.setRole(rs.getString("role"));
                    members.add(member);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return members;
    }

    // Find member by ID

    public void deleteById(int id) {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM member WHERE id = ?")) {
                pstmt.setInt(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                if(rowsDeleted > 0){
                    System.out.println("✅ Member deleted successfully. ID: " + id);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}

