import java.util.Scanner;

public class testPlayer extends Player {
    Scanner scanner = null;
    // public void play() {
    // Random rand = new Random();
    // ArrayList<Move> moves; // data structure used for storing available moves
    // moves = getAvailableMoves(); // obtain available moves using API
    // System.out.println("possible moves: " + moves); // you can use this to output
    // all possible moves in dos box
    // Move myMove = moves.get(rand.nextInt(moves.size())); // the type of available
    // moves in the ArrayList is Move.
    // mark(myMove); // make actual move by calling mark()
    // }

    public void play() {
        playerMove();
    }

    public void initScanner(Scanner s) {
        this.scanner = s;
    }

    public void playerMove() {
        String rowString, colString;
        int row = -1, col = -1;
        Move move;
        this.board.print();
        do {
            System.out.print("Player " + this.markTake + ", enter your move (row and column): ");
            rowString = scanner.next();
            colString = scanner.next();

            if (rowString.length() == 1 && colString.length() == 1) {
                if (rowString.matches("[0-9]")) {
                    row = rowString.charAt(0) - '0';
                } else if (rowString.matches("[a-e]")) {
                    row = 10 + rowString.charAt(0) - 'a';
                } else if (rowString.matches("[A-E]")) {
                    row = 10 + rowString.charAt(0) - 'A';
                }

                if (colString.matches("[0-9]")) {
                    col = colString.charAt(0) - '0';
                } else if (colString.matches("[a-e]")) {
                    col = 10 + colString.charAt(0) - 'a';
                } else if (colString.matches("[A-E]")) {
                    col = 10 + colString.charAt(0) - 'A';
                }
            }
            move = new Move(row, col, this.markTake);
        } while (!isValidMove(move));

        mark(move);
    }

    public boolean isValidMove(Move move) {
        return board.isAvailableMove(move);
    }
}