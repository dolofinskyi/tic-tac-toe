package main.java.tictactoe;

import java.util.Arrays;

public class Game {

    public final int size = 3;
    public int currMove = 0;

    private final char[][] grid;

    public Game(){
        this.grid = new char[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                grid[i][j] = (char) ('1' + i*size + j);
            }
        }
    }

    public void move(int n){
        int idx = n - 1;
        if (!canMove(idx)) {
            System.out.println("Wrong number");
        } else{
            grid[idx / size][idx % size] = getCurrPlayer();
            if (isAlive()) currMove++;
            display();
        }
    }

    private boolean isWin(){
        char[][] diagonals = new char[2][size];
        char[][] cols = new char[size][size];

        for (int i = 0; i < size; i++){
            diagonals[0][i] = grid[i][i];
            diagonals[1][i] = grid[size - i - 1][i];
            for (int j = 0; j < size; j++){
                cols[i][j] = grid[j][i];
            }
        }

        return validate(diagonals) || validate(cols) || validate(grid);
    }

    private boolean validate(char[][] arrs){
        for (char[] arr: arrs){
            int counter = 0;
            for (char c: arr) if (arr[0] == c && c != ' ') counter++;
            if (counter == size) return true;
        }

        return false;
    }

    private boolean canMove(int idx){
        if (idx < 0 || idx >= size*size) return false;
        char c = grid[idx / size][idx % size];
        return c != 'X' && c != 'O';
    }

    public boolean isAlive(){
        return currMove < (size * size) && !isWin();
    }

    public char getCurrPlayer(){
        return currMove % 2 == 0 ? 'X' : 'O';
    }

    public String getState(){
        return currMove == size*size ? "Draw!" : "Player " + getCurrPlayer() + " win!";
    }

    public void display(){
        StringBuilder sb = new StringBuilder();
        char[] rowSeq = new char[size * 3 + (size - 1)];
        char row = '-';
        char column = '|';

        Arrays.fill(rowSeq, row);
        String stringRow = "\n" + new String(rowSeq) + "\n";

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                sb.append(" ").append(grid[i][j]).append(" ");
                if ((j + 1) != size) sb.append(column);
            }

            if ((i + 1) != size) sb.append(stringRow);
        }

        System.out.println(sb);
    }
}
