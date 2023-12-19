
// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Board {
   public static final int END = 0;
   public static final int IN_PLAY = 1;
   public static final int DRAW = 2;
   public int boardWidth = 15;
   public int boardHeight = 15;
   public int winLength = 5;
   BoardUI bui;
   int gameStatus;
   Mark winningMark = null;
   Mark[][] grid;
   ArrayList<Move> history = new ArrayList<>();
   Mark currentMark;

   public Board() {
      this.grid = new Mark[this.boardHeight][this.boardWidth];

      for (int row = 0; row < this.boardHeight; ++row) {
         for (int col = 0; col < this.boardWidth; ++col) {
            this.grid[row][col] = Mark.EMPTY;
         }
      }

      this.gameStatus = IN_PLAY;
      this.currentMark = Mark.BLACK;
   }

   public int getBoardStatus() {
      return this.gameStatus;
   }

   public Mark getCurrentMark() {
      return this.currentMark;
   }

   private void setCurrentMark(Mark var1) {
      this.currentMark = var1;
   }

   public int getBoardWidth() {
      return this.boardWidth;
   }

   public int getBoardHeight() {
      return this.boardHeight;
   }

   public void setBoardUI(BoardUI var1) {
      this.bui = var1;
   }

   public BoardUI getBoardUI() {
      return this.bui;
   }

   public void addMouseListener(MouseListener var1) {
      if (this.bui != null) {
         this.bui.addMouseListener(var1);
      }
   }

   public Mark getMarkAt(int row, int col) {
      return this.grid[row][col];
   }

   private void setMarkAt(int row, int col, Mark mark) {
      this.grid[row][col] = mark;
   }

   public Mark getWinningMark() {
      return this.gameStatus == 0 && this.winningMark != null ? this.winningMark : null;
   }

   public ArrayList<Move> getAvailableMove() {
      ArrayList<Move> moves = new ArrayList<>();

      for (int row = 0; row < this.boardHeight; ++row) {
         for (int col = 0; col < this.boardWidth; ++col) {
            if (this.grid[row][col] == Mark.EMPTY) {
               Move move = new Move(row, col);
               move.setMark(this.currentMark);
               moves.add(move);
            }
         }
      }
      return moves;
   }

   public void mark(Move move) {
      if (this.gameStatus == IN_PLAY) {
         if (this.grid[move.getX()][move.getY()] == Mark.EMPTY) {
            this.grid[move.getX()][move.getY()] = move.getMark();
            this.currentMark = this.currentMark.getOppositeMark();
            this.history.add(move);
            this.updateBoardStatus();
         }

      }
   }

   public Board cloneBoard() {
      Board newBoard = new Board();

      for (int row = 0; row < this.boardHeight; ++row) {
         for (int col = 0; col < this.boardWidth; ++col) {
            newBoard.setMarkAt(row, col, this.getMarkAt(row, col));
         }
      }

      newBoard.setCurrentMark(this.getCurrentMark());
      newBoard.updateBoardStatus();
      return newBoard;
   }

   private boolean checkConsecutive(int row, int col) {
      boolean var4 = false;
      if (row + this.winLength <= this.boardHeight && this.checkConsecutive(row, col, 1, 0)) {
         return true;
      } else if (col + this.winLength <= this.boardWidth && this.checkConsecutive(row, col, 0, 1)) {
         return true;
      } else if (row + this.winLength <= this.boardHeight && col + this.winLength <= this.boardWidth
            && this.checkConsecutive(row, col, 1, 1)) {
         return true;
      } else {
         return row + this.winLength <= this.boardHeight && col >= this.winLength - 1
               && this.checkConsecutive(row, col, 1, -1) ? true : var4;
      }
   }

   private boolean checkConsecutive(int row, int col, int rowDiff, int colDiff) {   
      boolean isWin = false;
      int consecutiveNum = 0;
      Mark mark = this.grid[row][col];

      while (consecutiveNum < this.winLength){
         if (this.grid[row][col] != mark){
            break;
         }
         consecutiveNum += 1;
         row += rowDiff;
         col += colDiff;
      }

      if (consecutiveNum >= this.winLength) {
         isWin = true;
         this.winningMark = mark;
      }

      return isWin;
   }

   private void updateBoardStatus() {
      int row;
      int col;
      for (row = 0; row < this.boardHeight; ++row) {
         for (col = 0; col < this.boardWidth; ++col) {
            if (this.grid[row][col] != Mark.EMPTY && this.checkConsecutive(row, col)) {
               this.gameStatus = 0;
               return;
            }
         }
      }

      for (row = 0; row < this.boardHeight; ++row) {
         for (col = 0; col < this.boardWidth; ++col) {
            if (this.grid[row][col] == Mark.EMPTY) {
               return;
            }
         }
      }

      this.gameStatus = DRAW;
   }

   public boolean isGameOver() {
      switch (this.gameStatus) {
         case END:
         case DRAW:
            return true;
         case IN_PLAY:
            return false;
         default:
            return false;
      }
   }

   public void print() {
      System.out.println(" =0=1=2=3=4=5=6=7=8=9=a=b=c=d=e=");

      for (int row = 0; row < this.boardHeight; ++row) {
          System.out.print(toChar(row));
          for (int col = 0; col < this.boardWidth; ++col) {
              System.out.print("|" + this.grid[row][col] + (col == this.boardWidth - 1 ? "|\n" : ""));
          }
      }

      System.out.println(" =0=1=2=3=4=5=6=7=8=9=A=B=C=D=E=");
  }

  public String toChar(int num) {
      if (num < 10) {
          return Integer.toString(num);
      } else {
          String res = "";
          res += Character.toString(num - 10 + 'A');
          return res;
      }
  }

   public Move getLastMove() {
      return this.history.size() > 0 ? (Move) this.history.get(this.history.size() - 1) : null;
   }
}
