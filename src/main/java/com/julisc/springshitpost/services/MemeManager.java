package com.julisc.springshitpost.services;

import com.julisc.springshitpost.models.Meme;
import org.springframework.stereotype.Repository;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public int dumpTheLoad(Meme load) throws SQLException, URISyntaxException {
        String sql = "INSERT INTO memes" + "(meme)" + "VALUES" + "(?)";
        try {
            ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, load.getMeme());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { ps.close(); } catch (Exception e) { /* ignored */ }
            try { getConnection().close(); } catch (Exception e) { /* ignored */ }
        }

        return -1;
    }

    public List<Meme> scavengeForShitposts() {
        String sql = "SELECT * FROM memes";
        List<Meme> memes = new ArrayList<>();
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
}
