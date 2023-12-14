import java.util.*;

public class Gomoku {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gomoku gomoku = new Gomoku();
        Board board = new Board();
        testPlayer TestPlayer = new testPlayer();
        testPlayer TestPlayer2 = new testPlayer();
        TestPlayer2.initScanner(scanner);
        TestPlayer.initScanner(scanner);
        TestPlayer.initPlayer(Mark.CROSS, board);
        TestPlayer2.initPlayer(Mark.CIRCLE, board);

        while (!board.isGameOver()) {
            TestPlayer.play();
            TestPlayer2.board.mark(TestPlayer.lastMove);
            board = TestPlayer.board.cloneBoard();
            if (board.isGameOver()) {
                break;
            }
            TestPlayer2.play();
            TestPlayer.board.mark(TestPlayer2.lastMove);
            board = TestPlayer2.board.cloneBoard();
        }
        board.print();
        System.out.println("Winner is " + TestPlayer.markTake);

        // while (true) {
        // playerMove('X', scanner);
        // printBoard();
        // if (isWinner('X')) {
        // System.out.println("Player X wins!");
        // break;
        // }

        // playerMove('O', scanner);
        // printBoard();
        // if (isWinner('O')) {
        // System.out.println("Player O wins!");
        // break;
        // }
        // }
    }

    // public void initializeBoard() {
    // this.board = new Board();
    // }

    // public void printBoard() {
    // System.out.println(" 0 1 2 3 4 5 6 7 8 9 A B C D E");
    // this.board.print();
    // }

    // private static void playerMove(char player, Scanner scanner) {
    // String rowString, colString;
    // int row = -1, col = -1;
    // boolean undo = false;
    // do {
    // System.out.print("Player " + player + ", enter your move (row and column):
    // ");
    // rowString = scanner.next();
    // colString = scanner.next();

    // if (rowString.length() == 1 && colString.length() == 1) {
    // if (rowString.matches("[0-9]")) {
    // row = rowString.charAt(0) - '0';
    // } else if (rowString.matches("[a-e]")) {
    // row = 10 + rowString.charAt(0) - 'a';
    // } else if (rowString.matches("[A-E]")) {
    // row = 10 + rowString.charAt(0) - 'A';
    // }

    // if (colString.matches("[0-9]")) {
    // col = colString.charAt(0) - '0';
    // } else if (colString.matches("[a-e]")) {
    // col = 10 + colString.charAt(0) - 'a';
    // } else if (colString.matches("[A-E]")) {
    // col = 10 + colString.charAt(0) - 'A';
    // }
    // }
    // } while (!isValidMove(row, col));

    // if (!undo) {
    // board[row][col] = player;
    // record[0] = row;
    // record[1] = col;
    // }
    // }

    // private static boolean isValidMove(int row, int col) {
    // return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE &&
    // board[row][col] == '.';
    // }

    // private static boolean isWinner(char player) {
    // // Check rows
    // for (int i = 0; i < BOARD_SIZE; i++) {
    // for (int j = 0; j <= BOARD_SIZE - 5; j++) {
    // boolean win = true;
    // for (int k = 0; k < 5; k++) {
    // if (board[i][j + k] != player) {
    // win = false;
    // break;
    // }
    // }
    // if (win) {
    // return true;
    // }
    // }
    // }

    // // Check columns
    // for (int i = 0; i < BOARD_SIZE; i++) {
    // for (int j = 0; j <= BOARD_SIZE - 5; j++) {
    // boolean win = true;
    // for (int k = 0; k < 5; k++) {
    // if (board[j + k][i] != player) {
    // win = false;
    // break;
    // }
    // }
    // if (win) {
    // return true;
    // }
    // }
    // }

    // // Check diagonals
    // for (int i = 0; i <= BOARD_SIZE - 5; i++) {
    // for (int j = 0; j <= BOARD_SIZE - 5; j++) {
    // boolean winDiagonal1 = true;
    // boolean winDiagonal2 = true;
    // for (int k = 0; k < 5; k++) {
    // if (board[i + k][j + k] != player) {
    // winDiagonal1 = false;
    // }
    // if (board[i + k][j + 4 - k] != player) {
    // winDiagonal2 = false;
    // }
    // }
    // if (winDiagonal1 || winDiagonal2) {
    // return true;
    // }
    // }
    // }

    // return false;
    // }
}
