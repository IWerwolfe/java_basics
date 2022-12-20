import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "!QAZzaq1";
        String query = "SELECT \n" +
                "    `course_Name`,\n" +
                "    count(distinct month(`subscription_date`)) / (max(month(`subscription_date`)) - min(month(`subscription_date`)) + 1) as count,\n" +
                "    min(`subscription_date`)\n" +
                "FROM skillbox.purchaselist\n" +
                "where year(`subscription_date`) = 2018\n" +
                "group by `course_Name`\n" +
                "order by `course_Name`";

        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
            ResultSet resultset = connection.createStatement().executeQuery(query);

            System.out.println("Среднее количество покупок курсов за месяц");

            while (resultset.next()) {
                System.out.println(resultset.getString("course_Name") +
                        " - " +
                        resultset.getString("count"));
            }

        } catch (Exception e) {
            System.err.println(e.fillInStackTrace());
        }
    }
}
