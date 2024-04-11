package arc.main.java.model.ItemManagement;

public class Item {
    int type; // 0 for animal, 1 for plant
    String name;
    int price;

    public Item(int type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

}
