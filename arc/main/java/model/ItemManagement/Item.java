package arc.main.java.model.ItemManagement;

public class Item {
    int type; //0 for animal, 1 for plant, 1 for tool
    String name;
    int price;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item(int type, String name, int price){
        this.type = type;
        this.name = name;
        this.price = price;
    }
}
