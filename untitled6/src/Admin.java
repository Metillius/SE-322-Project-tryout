


import java.sql.*;
import java.util.Scanner;

public class Admin implements User{
    private String name;

    public Admin(String name) {
        this.name = name;
    }

    public void add() {
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Product",
                    "root", "22432243Aa");

            Statement statement;
            statement = connection.createStatement();

            Scanner scanner = new Scanner(System.in);
            System.out.print("How many products do you want to add? ");
            int numProducts = scanner.nextInt();

            String sql = "insert into Product (ProductName, Model, Price) VALUES ";

            // Use a loop to create multiple insert statements based on user input
            for (int i = 0; i < numProducts; i++) {
                System.out.println("Enter details for Product " + (i+1) + ":");
                System.out.print("Product name: ");
                String productName = scanner.next();
                System.out.print("Model: ");
                String model = scanner.next();
                System.out.print("Price: ");
                int price = Integer.parseInt(scanner.next());

                String sqlstatement = sql + String.format("('%s','%s', %s)", productName, model, price);
                statement.executeUpdate(sqlstatement);
                System.out.println("Product " + (i+1) + " added to the database.");
            }

            statement.close();
            connection.close();

        } catch (Exception exception) {

            System.out.println(exception);
        }
    }




    @Override
    public void login() {

    }

    @Override
    public void signUp() {

    }

    @Override
    public void logOut() {

    }



}