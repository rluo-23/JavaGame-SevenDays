package arc.main.java.ui;

import arc.main.java.model.Player.Player;
import arc.main.java.model.Player.BagItems;
import arc.main.java.model.Player.BagList;
import arc.main.java.model.Player.Skill;
import arc.main.java.model.Activity.Activity;
import arc.main.java.model.Game.loadData;
import arc.main.java.model.ItemManagement.ItemDirectory;

public class Game {
    public static void main(String[] args) {
        ItemDirectory itemDirectory = new ItemDirectory();
        
        // 加载项目数据
        loadData.loadItems(itemDirectory);

        // 打印 ItemDirectory 中的项目信息
        loadData.printItemDirectory(itemDirectory);
        
    }
}
