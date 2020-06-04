package model;

public class Product {
    static int count =100;
    private String id;
    private String name;
    private double price;
    private  String description;
    private String maker;

    public Product(String name, double price, String description, String maker) {
        this.id = "PRO"+ count++;
        this.name = name;
        this.price = price;
        this.description = description;
        this.maker = maker;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

}
