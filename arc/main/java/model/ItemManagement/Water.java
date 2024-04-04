package arc.main.java.model.ItemManagement;

public class Water {
    int price;
    int quantity;

    public Water() {
        this.price = 10;
        this.quantity = 0;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

}
