import java.sql.*;

public class BookingAppTest {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/booking_app";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            queryRoomPrices(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void queryRoomPrices(Connection conn) throws SQLException {
        String sql = "SELECT a.type, a.bed_type, a.max_guests, rf.value, rf.season " +
                "FROM accommodation a " +
                "JOIN accommodation_room_fair_relation arfr ON a.id = arfr.accommodation_id " +
                "JOIN room_fair rf ON arfr.room_fair_id = rf.id";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String type = rs.getString("type");
                String bedType = rs.getString("bed_type");
                int maxGuests = rs.getInt("max_guests");
                double value = rs.getDouble("value");
                String season = rs.getString("season");
                System.out.println("Accommodation Type: " + type + ", Bed Type: " + bedType +
                        ", Max Guests: " + maxGuests + ", Price: $" + value + ", Season: " + season);
            }
        }
    }
}
