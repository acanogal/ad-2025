package com.digitechfp.curso_ad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Migration {

    private final DbConnection db;
    private final PreparedStatement checkBook;
    private final PreparedStatement insertAuthor;
    private final PreparedStatement updateBook;

    public Migration() throws SQLException {
        this.db = DbConnection.instance();
        this.checkBook = db.createStatement("SELECT ID FROM AUTHORS WHERE NAME = ?");
        this.insertAuthor = db.createStatementWithReturnID("INSERT INTO AUTHORS (NAME) VALUES (?)");
        this.updateBook = db.createStatement("UPDATE BOOKS SET AUTHOR_ID = ? WHERE ID = ?");
    }

    public void execute () throws SQLException {
        System.out.println("Connected to database: " + db.getConnection().getCatalog());
        try (ResultSet rs = db.select("SELECT * FROM BOOKS WHERE AUTHOR_ID IS NULL")) {
            while (rs.next()) {
                System.out.println("Migration of "+rs.getString("TITLE"));
                migrateBook(rs);

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // Perform migration tasks here
        // For example, create tables, insert data, etc.
        // ...
        System.out.println("Migration completed successfully.");
    }
    private void migrateBook(ResultSet rs) throws SQLException {
        checkBook.setString(1, rs.getString("AUTHOR"));
        Integer id = db.exist(checkBook, "id");
        List<Integer> ids;
        if (id == null) {
            insertAuthor.setString(1, rs.getString("AUTHOR"));
            ids = db.executeStatement(insertAuthor);
            System.out.println("Inserted author: " + rs.getString("AUTHOR")+ ", ID: " + ids.get(0));
            id = ids.get(0);
        }
        updateBook.setInt(1, id);
        updateBook.setInt(2, rs.getInt("ID"));
        db.executeStatement(updateBook);
        System.out.println("Book update: " + rs.getString("TITLE") + ", AUTHOR_ID: "+ id);
    }
}
