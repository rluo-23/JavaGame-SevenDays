package arc.main.java.ui;

import arc.main.java.model.Player.Player;
import arc.main.java.model.Player.BagItems;
import arc.main.java.model.Player.BagList;
import arc.main.java.model.Player.Skill;
import arc.main.java.model.Activity.Activity;
import arc.main.java.model.EventManagement.EventDirectory;
import arc.main.java.model.Game.loadData;
import arc.main.java.model.ItemManagement.ItemDirectory;

public class Game {
    public static void main(String[] args) {
        ItemDirectory itemDirectory = new ItemDirectory();
        EventDirectory eventDirectory = new EventDirectory();
        
        // 加载项目数据
        loadData.loadItems(itemDirectory);

        // 打印 ItemDirectory 中的项目信息
        loadData.printItemDirectory(itemDirectory);

        // 加载事件数据
        loadData.loadEvents(eventDirectory, itemDirectory);
        // 打印 EventDirectory 中的事件信息
        loadData.printEventDirectory(eventDirectory);

    }
}
