import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Customer implements User{
    private String name;
    private int password;
    private String id;
    private String mailAddress;
    void Search(Product product){}



    @Override
    public void login() {
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Customer",
                    "root", "22432243Aa");

            Statement statement;
            statement = connection.createStatement();

            String sqll = "insert into Customer (Username, email, password, isAdmin) VALUES ";

            Scanner scanner = new Scanner(System.in);
            System.out.println("-----------LOGIN-----------");
            System.out.println("Please enter your username");
            String username = scanner.nextLine();
            System.out.println("Please enter your password");
            String password = scanner.nextLine();

            //Checking if there is an account like that
            String sql = "SELECT COUNT(*) FROM Customer WHERE username = '" + username + "' AND password = '" + password + "'";
            String isAdminSql = "SELECT isAdmin FROM Customer WHERE username = '" + username + "' AND password = '" + password + "'";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            // Get the count of the matching rows
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 1 ) {
                // Login successful, retrieve isAdmin value
                ResultSet isAdminResultSet = stmt.executeQuery(isAdminSql);
                isAdminResultSet.next();
                boolean isAdmin = isAdminResultSet.getBoolean("isAdmin");
                System.out.println("Login successful!");
                System.out.println("isAdmin: " + isAdmin);
            }
            else {
                System.out.println("Invalid username or password.");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void signUp() {

        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Customer",
                    "root", "22432243Aa");

            Statement statement;
            statement = connection.createStatement();

            String sqll = "insert into Customer (Username, email, password) VALUES ";


            Scanner scanner = new Scanner(System.in);
            System.out.println("-----------SIGN UP-----------");
            System.out.println("Please enter a username");
            String username = scanner.nextLine();

            //Checking if the username already exist
            String checkUserSql = String.format("SELECT * FROM Customer WHERE Username='%s'", username);
            ResultSet resultSet = statement.executeQuery(checkUserSql);

            if (resultSet.next()) {
                // Username already exists
                System.out.println("Username already exists");
            }

            else {



                System.out.println("Please enter your email");
                String mail = scanner.nextLine();


                System.out.println("Please enter a password");
                //scanner.nextLine();
                String password = scanner.nextLine();

                String sqlregister = sqll + String.format("('%s','%s', '%s')", username, mail, password);
                statement.executeUpdate(sqlregister);
                System.out.println("Succesfully created an account");
            }


            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception exception) {

            System.out.println(exception);
        }

    }

    @Override
    public void logOut() {

    }


}