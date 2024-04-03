package arc.main.java.model.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import arc.main.java.model.ItemManagement.Item;
import arc.main.java.model.ItemManagement.ItemDirectory;

public class loadData {

    public static void loadItems(ItemDirectory itemDirectory) {
        String filePath = "arc/main/resources/data/item.csv"; // 文件路径硬编码
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 跳过第一行（标题行）
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    int type = Integer.parseInt(data[0]);
                    String name = data[1];
                    int price = Integer.parseInt(data[2]);

                    Item item = new Item(type, name, price);
                    itemDirectory.addItem(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printItemDirectory(ItemDirectory itemDirectory) {
        System.out.println("Items in ItemDirectory:");
        for (Item item : itemDirectory.getItems()) {
            System.out.println("Type: " + item.getType() + ", Name: " + item.getName() + ", Price: " + item.getPrice());
        }
    }
    
}
