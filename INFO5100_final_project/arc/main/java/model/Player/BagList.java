package arc.main.java.model.Player;

import java.util.ArrayList;

public class BagList {
    ArrayList<BagItems> bagList = new ArrayList<BagItems>();
    int quantity = 0;
    
    public BagList(){
        this.bagList = new ArrayList<BagItems>();
        this.quantity = 0;
    }
}
