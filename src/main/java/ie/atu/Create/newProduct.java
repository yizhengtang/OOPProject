package ie.atu.Create;

import ie.atu.Interfaces.DisplayMsg;

public class newProduct implements DisplayMsg {
    private String name;
    private String brand;
    private int price;
    private String description;

    public newProduct(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return "\nNew Product Added!";
    }
}
