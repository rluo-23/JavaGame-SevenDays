package arc.main.java.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import arc.main.java.model.Player.Player;
import arc.main.java.model.Player.BagItems;
import arc.main.java.model.Player.BagList;
import arc.main.java.model.Player.Skill;
import arc.main.java.model.Activity.Activity;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game!");
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        Player player = new Player(name);

        

    }
}
