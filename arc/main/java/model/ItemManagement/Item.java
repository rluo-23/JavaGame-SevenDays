package arc.main.java.model.ItemManagement;

public class Item {
    int type; //0 for animal, 1 for plant, 1 for tool
    String name;
    int price;

    public Item(int type, String name, int price){
        this.type = type;
        this.name = name;
        this.price = price;
    }
}
