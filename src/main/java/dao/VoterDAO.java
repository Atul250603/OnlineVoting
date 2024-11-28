package dao;

import model.Voter;
import java.sql.*;

public class VoterDAO {
    private Connection connection;
    
    public VoterDAO() {
        connection = DatabaseConnection.getConnection();
    }
    
    public Voter validateVoter(String voterId, String password) {
        try {
        	connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM voters WHERE voter_id = ? AND password = ?"
            );
            ps.setString(1, voterId);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Voter voter = new Voter();
                voter.setId(rs.getInt("id"));
                voter.setVoterId(rs.getString("voter_id"));
                voter.setName(rs.getString("name"));
                voter.setHasVoted(rs.getBoolean("has_voted"));
                return voter;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void updateVoteStatus(int voterId) {
        try {
        	connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                "UPDATE voters SET has_voted = true WHERE id = ?"
            );
            ps.setInt(1, voterId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean voterExists(String voterId) throws SQLException {
    	connection = DatabaseConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM voters WHERE voter_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, voterId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean registerVoter(Voter voter) throws SQLException {
    	connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO voters (voter_id, name, password, has_voted) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, voter.getVoterId());
            stmt.setString(2, voter.getName());
            stmt.setString(3, voter.getPassword());
            stmt.setBoolean(4, voter.isHasVoted());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}