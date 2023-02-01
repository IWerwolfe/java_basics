import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "!QAZzaq1";
    public static long MAX_BUFFER = 10_000L;
    private static final StringBuilder insertQuery = new StringBuilder();
    private static final StringBuilder query = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id), " +
                        "UNIQUE KEY name_date(name(50), birthDate))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void multiInsert() throws SQLException {
        query.append("INSERT INTO voter_count(name, birthDate, `count`) VALUES")
                .append(insertQuery)
                .append("ON DUPLICATE KEY UPDATE `count` = `count` + 1");
        DBConnection.getConnection().createStatement().execute(query.toString());
        query.delete(0, query.length());
    }

    public static void countVoter(String name, String birthDay) throws SQLException {

        birthDay = birthDay.replace('.', '-');
        int length = insertQuery.length();

        if (length > MAX_BUFFER) {
            multiInsert();
            insertQuery.delete(0, length);
            length = 0;
        }

        insertQuery.append(length == 0 ? "" : ", ")
                .append("('")
                .append(name)
                .append("', '")
                .append(birthDay)
                .append("', 1)");
    }

    public static void printVoterCounts() throws SQLException {

        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        StringBuilder out = new StringBuilder();

        while (rs.next()) {

            if (out.length() > MAX_BUFFER) {
                System.out.println(out);
                insertQuery.delete(0, out.length());
            }

            out.append("\t")
                    .append(rs.getString("name"))
                    .append(" (")
                    .append(rs.getString("birthDate"))
                    .append(") - ")
                    .append(rs.getInt("count"))
                    .append(System.lineSeparator());
        }
        System.out.println(out);
    }
}
