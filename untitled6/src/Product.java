
import java.sql.*;
import java.util.ArrayList;

public class Product {
    private double price;
    private String ProductName;
    private String Model;
    private Connection connection;


    public void getProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Product",
                    "root", "22432243Aa");


            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from Product");


            while (resultSet.next()) {

                Product pr1 = new Product();
                pr1.setProductName(resultSet.getString(1));
                pr1.setModel(resultSet.getString(2));
                pr1.setPrice(resultSet.getInt(3));
                products.add(pr1);


            }


            resultSet.close();
            statement.close();
            connection.close();


        } catch (Exception exception) {
            System.out.println(exception);
        }
        int count = 0;
        for (Product product : products) {
            System.out.println("ProductName: " + product.getProductName());
            System.out.println("Model: " + product.getModel());
            System.out.println("Price: " + product.getPrice());
            System.out.println("------------------");
            count++;

        }
        System.out.println("Total products printed: " + count);
    }

    public ArrayList<Product> searchProducts(String keyword) {
        ArrayList<Product> productList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Product",
                    "root", "22432243Aa");


            String keywordWithWildcards = "%" + keyword + "%";
            String sql = "SELECT * FROM product WHERE ProductName LIKE ? OR Model LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, keywordWithWildcards);
            statement.setString(2, keywordWithWildcards);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String ProductName = resultSet.getString("ProductName");
                String Model = resultSet.getString("Model");
                int price = resultSet.getInt("price");

                Product product = new Product();
                product.setProductName(ProductName);
                product.setModel(Model);
                product.setPrice(price);
                productList.add(product);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Product product : productList) {
            System.out.println("ProductName: " + product.getProductName());
            System.out.println("Model: " + product.getModel());
            System.out.println("Price: " + product.getPrice());
            System.out.println("------------------");
        }
        return productList;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }
}