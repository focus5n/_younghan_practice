package younghan.core.singleton.service;

public class StatefulService {

    private String name;
    private int price = 0;

    public void order(String name, int price) {
        System.out.println("name = " + name);
        System.out.println("price = " + price + "\n");

        this.name = name;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
