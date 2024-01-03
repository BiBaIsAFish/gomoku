import java.util.Scanner;

public class StdinPlayer extends Player {
    Scanner stdin = new Scanner(System.in);

    public void play() {
        String input = stdin.nextLine();
        String[] pos = input.split(" ");
        int row = 14 - (Integer.parseInt(pos[0]) - 1);
        int col = pos[1].charAt(0) - 'A';
        mark(new Move(row, col));
    };
}
