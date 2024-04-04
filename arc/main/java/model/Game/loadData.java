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
        String filePath = "arc/main/resources/data/item.csv"; // 文件路径硬编码
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 跳过第一行（标题行）
            br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println("Line: " + line); // 打印每一行数据
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

    

    public static void loadEvents(EventDirectory eventDirectory, ItemDirectory itemDirectory) {
        String filePath = "arc/main/resources/data/event.csv"; // 文件路径硬编码
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 跳过第一行（标题行）
            br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println("Line: " + line); // 打印每一行数据
                String[] data = line.split(",");
                if (data.length == 4) {
                    String itemName = data[0];
                    String information = data[3];

                    // 通过物品名称在物品目录中查找对应的物品对象
                    Item item = findItemByName(itemName, itemDirectory);
                    if (item != null) {
                        Event event = new Event(item, information);
                        eventDirectory.addEvent(event);
                    } else {
                        System.out.println("Item not found: " + itemName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Item findItemByName(String itemName, ItemDirectory itemDirectory) {
        for (Item item : itemDirectory.getItems()) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null; // 没有找到对应名称的物品
    }


}
