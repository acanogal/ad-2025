package com.digitechfp.curso_ad;

import java.sql.*;

public class App {
    private static final String URL = "jdbc:postgresql://localhost:15432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL_FULL = "jdbc:postgresql://localhost:15432/postgres?user=postgres&password=postgres";
    public static void main(String[] args) throws SQLException {
        DbConnection.instance().getConnection(URL, USER, PASSWORD).setAutoCommit(false);
        Migration migration = new Migration();
        try {
            migration.execute();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println(conn.isValid(0));
        Connection conn2 = DriverManager.getConnection(URL_FULL);
        System.out.println(conn2.isValid(0));
        selectBooks(conn2, "Select Books conn2");
        conn.setAutoCommit(false);
        try (PreparedStatement insert = insertBookStatement(conn)) {
            insertBook (insert, "El Señor de los Anillos", "J.R.R. Tolkien");
            insertBook (insert, "El Hobbit", "J.R.R. Tolkien");
            insertBook (insert, "El Silmarillion", "J.R.R. Tolkien");
            selectBooks (conn, "Select Books conn after insert");
            selectBooks (conn2, "Select Books conn2 after insert");
            insertBook (insert, null,null);
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            conn.rollback();
/*        } finally {
            insert.close();*/
        }
        selectBooks(conn, "Select Books conn after rollback");
        conn.close();
    }

    public static void selectBooks (Connection conn, String msg) throws SQLException {
        final String SELECT = "SELECT * FROM books";
        PreparedStatement st = conn.prepareStatement(SELECT);
        ResultSet rs = st.executeQuery();
        System.out.println("SelectBooks: " + msg);
        while (rs.next()) {
            System.out.println(rs.getString("title"));
        }
    }

    public static PreparedStatement insertBookStatement (Connection conn) throws SQLException {
        final String INSERT = "INSERT INTO books (title, author) VALUES (?, ?)";
        return conn.prepareStatement(INSERT);
    }
    public static void insertBook (PreparedStatement st, String title, String author) throws SQLException {
        st.setString(1, title);
        st.setString(2, author);
        st.executeUpdate();
    }
}