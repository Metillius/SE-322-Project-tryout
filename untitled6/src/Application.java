

import java.util.ArrayList;

public class Application {
    private ArrayList<Product> products;
    private String name;
    boolean isAdmin;

    public Application( String name) {
        products= new ArrayList<>();
        this.name = name;
    }
    public void menuScreen() {
        if (isAdmin){
            return;
        }
        else {
        }
    }

}