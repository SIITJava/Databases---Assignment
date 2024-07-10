import java.sql.*;

public class BookingApp {
    private static String DB_URL = "jdbc:postgresql://localhost:2222/booking_app";
    private static String DB_USER = "postgres";
    private static String DB_PASSWORD = "0000";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            insertAccommodation(conn, "Hotel", "Queen", 2, "A cozy room with a queen bed");
            insertRoomFair(conn, 150.0, "Summer");
            insertAccommodationRoomFairRelation(conn, 1, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertAccommodation(Connection conn, String type, String bedType, int maxGuests, String description) throws SQLException {
        String sql = "INSERT INTO accommodation (type, bed_type, max_guests, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, type);
            pstmt.setString(2, bedType);
            pstmt.setInt(3, maxGuests);
            pstmt.setString(4, description);
            pstmt.executeUpdate();
        }
    }

    private static void insertRoomFair(Connection conn, double value, String season) throws SQLException {
        String sql = "INSERT INTO room_fair (value, season) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, value);
            pstmt.setString(2, season);
            pstmt.executeUpdate();
        }
    }

    private static void insertAccommodationRoomFairRelation(Connection conn, int accommodationId, int roomFairId) throws SQLException {
        String sql = "INSERT INTO accommodation_room_fair_relation (accommodation_id, room_fair_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accommodationId);
            pstmt.setInt(2, roomFairId);
            pstmt.executeUpdate();
        }
    }
}
