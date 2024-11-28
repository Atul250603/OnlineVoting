package dao;

import model.Party;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartyDAO {
    private Connection connection;
    
    public PartyDAO() {
        connection = DatabaseConnection.getConnection();
    }
    
    public List<Party> getAllParties() {
        List<Party> parties = new ArrayList<>();
        try {
        	connection = DatabaseConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM parties");
            
            while (rs.next()) {
                Party party = new Party();
                party.setId(rs.getInt("id"));
                party.setName(rs.getString("name"));
                party.setSymbol(rs.getString("symbol"));
                party.setVoteCount(rs.getInt("vote_count"));
                parties.add(party);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parties;
    }
    
    public void incrementVoteCount(int partyId) {
        try {
        	connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                "UPDATE parties SET vote_count = vote_count + 1 WHERE id = ?"
            );
            ps.setInt(1, partyId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}