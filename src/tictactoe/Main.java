package tictactoe;
import java.util.Scanner;

public class Main {
    private static Scanner sc;
    private static String cells;
    private static char[][] grid;
    private static char player;
    public static void main(String[] args) {
        init();
        while (!isEnd()) {
            move();
            print();
        }
    }

    private static void init() {
        sc = new Scanner(System.in);
        grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
        player = 'X';
        print();
    }
    
    private static void print() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static boolean isEnd() {
        boolean xwin = isWin('X');
        boolean owin = isWin('O');

        int xs = 0;
        int os = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X') xs++;
                if (grid[i][j] == 'O') os++;
            }
        }
        if (xwin) {
            System.out.println("X wins");
            return true;
        }
        if (owin) {
            System.out.println("O wins");
            return true;
        }
        if (xs + os == 9) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }
    
    private static void move() {
        System.out.println("Enter the coordinates: ");

        int i = 0, j = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                String x = sc.next();
                i = Integer.parseInt(x);
                String y = sc.next();
                j = Integer.parseInt(y);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (i > 3 || i < 1 || j < 1 || j > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (grid[i - 1][j - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            isValid = true;
        }

        grid[i - 1][j - 1] = player;
        if (player == 'X') player = 'O';
        else player = 'X';
    }
    
    private static boolean isWin(char c) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == c && grid[i][1] == c && grid[i][2] == c) return true;
            if (grid[0][i] == c && grid[1][i] == c && grid[2][i] == c) return true;
        }
        if (grid[0][0] == c && grid[1][1] == c && grid[2][2] == c) return true;
        return grid[2][0] == c && grid[1][1] == c && grid[0][2] == c;
    }
}
