package arc.main.java.model.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import arc.main.java.model.EventManagement.Event;
import arc.main.java.model.EventManagement.EventDirectory;
import arc.main.java.model.ItemManagement.Item;
import arc.main.java.model.ItemManagement.ItemDirectory;

public class loadData {

    public static void loadItems(ItemDirectory itemDirectory) {
        String filePath = "arc/main/resources/data/item.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                //System.out.println("Line: " + line); 
                String[] data = line.split(",");
                if (data.length == 3) {
                    int type = Integer.parseInt(data[0]);
                    String name = data[1];
                    int price = Integer.parseInt(data[2]);

                    itemDirectory.newItem(type, name, price);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    public static void loadEvents(EventDirectory eventDirectory, ItemDirectory itemDirectory) {
        String filePath = "arc/main/resources/data/event.csv"; 
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                //System.out.println("Line: " + line); 
                String[] data = line.split(",");
                if (data.length == 4) {
                    String itemName = data[0];
                    String information = data[1];
                    Item item = itemDirectory.searchItem(itemName);
                    if (item != null) {
                        eventDirectory.newEvent(item, information);
                    } else {
                        System.out.println("Item not found: " + itemName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    


}
