package main.java.tictactoe;

import java.util.Scanner;

public class Launcher {

    static Game game = new Game();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        game.display();

        while (game.isAlive()){
            System.out.print("Choose number (1 - 9): ");
            int choice;

            if (input.hasNextInt()){
                choice = input.nextInt();
                game.move(choice);
            } else{
                System.out.println("Wrong");
                input.next();
            }
        }

        System.out.println(game.getState());
    }
}
