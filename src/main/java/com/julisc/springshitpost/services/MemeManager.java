package com.julisc.springshitpost.services;

import com.julisc.springshitpost.models.Meme;
import org.springframework.stereotype.Repository;
import java.net.URISyntaxException;
import java.sql.*;


@Repository
public class MemeManager {

    PreparedStatement ps = null;

    ResultSet rs = null;

    private static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        return DriverManager.getConnection(dbUrl);
    }

    public MemeManager(){
    }

//    public int dumpTheLoad(Meme load) throws SQLException, URISyntaxException {
//        String sql = "INSERT INTO memes" + "(meme)" + "VALUES" + "(?)";
//        try {
//            ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, load.getMeme());
//            ps.executeUpdate();
//            rs = ps.getGeneratedKeys();
//            rs.next();
//            return rs.getInt(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try { rs.close(); } catch (Exception e) { /* ignored */ }
//            try { ps.close(); } catch (Exception e) { /* ignored */ }
//            try { getConnection().close(); } catch (Exception e) { /* ignored */ }
//        }
//
//        return -1;
//    }

    public Memes scavengeForShitposts() {
        String sql = "SELECT * FROM memes";
        Memes memes = new Memes();
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                Meme meme = new Meme(rs.getString(2));
                memes.add(meme);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { ps.close(); } catch (Exception e) { /* ignored */ }
            try { getConnection().close(); } catch (Exception e) { /* ignored */ }
        }

        return memes;
    }

//    public boolean dumpTheLoad(Memes memes) {
//        try {
//            String dropTableIfExists = "DROP TABLE IF EXISTS memes";
//            ps = getConnection().prepareStatement(dropTableIfExists);
//            ps.executeUpdate();
//
//            String createTable = "CREATE TABLE memes (id serial primary key, meme varchar not null)";
//            ps = getConnection().prepareStatement(createTable);
//            ps.executeUpdate();
//
//            System.out.println(memes.getMemes());
//
//            for(Meme meme : memes.getMemes()) {
//                String sql = "INSERT INTO memes" + "(meme)" + "VALUES" + "(?)";
//                ps = getConnection().prepareStatement(sql);
//                ps.setString(1, meme.getMeme());
//                ps.executeUpdate();
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public boolean dumpTheLoad(Memes memes) {
        try {
            String dropTableIfExists = "DROP TABLE IF EXISTS memes";
            ps = getConnection().prepareStatement(dropTableIfExists);
            ps.executeUpdate();

            String createTable = "CREATE TABLE memes (id serial primary key, meme varchar not null)";
            ps = getConnection().prepareStatement(createTable);
            ps.executeUpdate();

            ps = getConnection().prepareStatement("INSERT INTO memes" + "(meme)" + "VALUES" + "(?)");

            int i = 0;
            for (Meme meme : memes.getMemes()) {
                ps.setString(1, meme.getMeme());
                ps.addBatch();
                i++;

                if (i % 1000 == 0 || i == memes.getMemes().size()) {
                    ps.executeBatch(); // Execute every 1000 items.
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
