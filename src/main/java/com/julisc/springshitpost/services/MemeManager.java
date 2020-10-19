package com.julisc.springshitpost.services;

import com.julisc.springshitpost.models.Meme;
import org.springframework.stereotype.Repository;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemeManager {

    private static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        return DriverManager.getConnection(dbUrl);
    }

    public MemeManager(){
    }

    public int dumpTheLoad(Meme load) throws SQLException, URISyntaxException {
        String sql = "INSERT INTO memes" + "(meme)" + "VALUES" + "(?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, load.getMeme());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getConnection().close();
        return -1;
    }

    public List<Meme> scavengeForShitposts() throws SQLException, URISyntaxException {
        String sql = "SELECT * FROM memes";
        List<Meme> memes = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Meme meme = new Meme(rs.getString(2));
                memes.add(meme);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        getConnection().close();
        return memes;
    }
}
