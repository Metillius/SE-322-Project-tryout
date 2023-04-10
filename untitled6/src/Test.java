
import java.util.Scanner;


public class Test {
    public static void main(String[] args)  {
        //Scanner scanner= new Scanner(System.in);
        Product product= new Product();


       /* System.out.println("Current product in the Store ");
        //product.getProduct();
        //System.out.println("Enter a keyword to search a product: ");
        //String keyw=scanner.next();

        product.searchProducts(keyw);*/


        Admin admin= new Admin("Öykü");
        //admin.add();

        Customer customer = new Customer();
        //customer.signUp();

        customer.login();


    }
}