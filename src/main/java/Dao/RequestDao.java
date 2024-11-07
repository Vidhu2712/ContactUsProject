package Dao;

import model.Request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {

    public List<Request> fetchRequests(boolean isArchived) {
        String query = "SELECT * FROM contact_requests WHERE is_archived = ?";
        List<Request> requests = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, isArchived);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Request request = new Request();
                    request.setId(rs.getInt("id"));
                    request.setFullName(rs.getString("full_name"));
                    request.setEmail(rs.getString("email"));
                    request.setMessage(rs.getString("message"));
                    request.setArchived(rs.getBoolean("is_archived"));
                    requests.add(request);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public void saveRequest(Request request) {
        String query = "INSERT INTO contact_requests (full_name, email, message, is_archived) VALUES (?, ?, ?, false)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, request.getFullName());
            stmt.setString(2, request.getEmail());
            stmt.setString(3, request.getMessage());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void archiveRequest(int requestId) {
        String query = "UPDATE contact_requests SET is_archived = TRUE WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, requestId);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void activateRequest(int requestId) {
        String query = "UPDATE contact_requests SET is_archived = FALSE WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, requestId);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

