package com.digitechfp.curso_ad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
    private final String URL = "jdbc:postgresql://localhost:15432/postgres";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";
    private Connection conn;
    private static DbConnection _instance;

    private DbConnection() {
        // Private constructor to prevent instantiation
    }

    public static DbConnection instance() {
        if (_instance == null) {
            _instance = new DbConnection();
        }
        return _instance;
    }

    public Connection getConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            return conn;
        }
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }
    public Connection getConnection(String url, String user, String password) throws SQLException {
        if (url == null || user == null || password == null) {
            throw new IllegalArgumentException("URL, user and password cannot be null");
        }
        if (conn != null && !conn.isClosed()) {
            return conn;
        }
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public ResultSet select(String sql) throws SQLException {
        Statement st = getConnection().createStatement();
        return st.executeQuery(sql);
    }
    public PreparedStatement createStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    public PreparedStatement createStatementWithReturnID(String sql) throws SQLException {
        return getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }


    public List<Integer> executeStatement(PreparedStatement st) throws SQLException {
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        List<Integer> ids = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            ids.add(id);
            System.out.println("ID: " + id);
        }
        return ids;
    }

    public Integer exist (PreparedStatement st, String field) throws SQLException {
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getInt(field);
        }
        return null;
    }
    // INSERT INTO AUTHOR (NAME) VALUES SELECT DISTINCT AUTHOR FROM BOOKS WHERE AUTHOR IS NOT NULL;
}