import java.util.*;

public class Board {
    public enum BoardState {
        END,
        IN_PLAY,
        DRAW;
    }

    public int boardSize = 15;
    public BoardState state;
    Mark winnerMark = null;
    Mark[][] grid;
    Mark currentMark;
    ArrayList<Move> history = new ArrayList<>();
    public int winLength = 5;

    public Board() {
        this.grid = new Mark[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.grid[i][j] = Mark.EMPTY;
            }
        }
        this.state = BoardState.IN_PLAY;
        this.currentMark = Mark.CROSS;
    }

    public Mark getCurrentMark() {
        return this.currentMark;
    }

    private void setCurrentMark(Mark var1) {
        this.currentMark = var1;
    }

    public Mark getMarkAt(int var1, int var2) {
        return this.grid[var1][var2];
    }

    private void setMarkAt(int var1, int var2, Mark var3) {
        this.grid[var1][var2] = var3;
    }

    public Mark getWinnerMark() {
        return this.state == BoardState.END && this.winnerMark != null ? this.winnerMark : null;
    }

    public ArrayList<Move> getAvailableMove() {
        ArrayList<Move> var1 = new ArrayList<>();

        for (int var2 = 0; var2 < this.boardSize; var2++) {
            for (int var3 = 0; var3 < this.boardSize; var3++) {
                if (this.grid[var2][var3] == Mark.EMPTY) {
                    Move var4 = new Move(var2, var3, this.currentMark);
                    var1.add(var4);
                }
            }
        }

        return var1;
    }

    private boolean checkConsecutive(int var1, int var2, int var3, int var4, int var5) {
        boolean var6 = false;
        int var7 = 1;
        Mark var8 = this.grid[var1][var2];

        for (int var9 = 1; var9 < var5 && this.grid[var1 + var3][var2 + var4] == var8; ++var9) {
            ++var7;
            var1 += var3;
            var2 += var4;
        }

        if (var7 >= var5) {
            var6 = true;
            this.winnerMark = var8;
        }

        return var6;
    }

    private boolean checkConsecutive(int var1, int var2, int var3) {
        boolean var4 = false;
        if (var1 + var3 <= this.boardSize && this.checkConsecutive(var1, var2, 1, 0, var3)) {
            return true;
        } else if (var2 + var3 <= this.boardSize && this.checkConsecutive(var1, var2, 0, 1, var3)) {
            return true;
        } else if (var1 + var3 <= this.boardSize && var2 + var3 <= this.boardSize
                && this.checkConsecutive(var1, var2, 1, 1, var3)) {
            return true;
        } else {
            return var1 + var3 <= this.boardSize && var2 >= var3 - 1 && this.checkConsecutive(var1, var2, 1, -1, var3)
                    ? true
                    : var4;
        }
    }

    private void updateBoardStatus() {
        int var1;
        int var2;
        for (var1 = 0; var1 < this.boardSize; ++var1) {
            for (var2 = 0; var2 < this.boardSize; ++var2) {
                if (this.grid[var1][var2] != Mark.EMPTY && this.checkConsecutive(var1, var2, this.winLength)) {
                    this.state = BoardState.END;
                    return;
                }
            }
        }

        for (var1 = 0; var1 < this.boardSize; ++var1) {
            for (var2 = 0; var2 < this.boardSize; ++var2) {
                if (this.grid[var1][var2] == Mark.EMPTY) {
                    return;
                }
            }
        }

        this.state = BoardState.IN_PLAY;
    }

    public boolean isAvailableMove(Move move) {
        if (this.state == BoardState.IN_PLAY && move.getX() != -1 && move.getY() != -1) {
            if (this.grid[move.getX()][move.getY()] == Mark.EMPTY) {
                return true;
            }
        }
        return false;
    }

    public void mark(Move var1) {
        if (isAvailableMove(var1)) {
            this.grid[var1.getX()][var1.getY()] = var1.getMark();
            this.currentMark = this.currentMark.getOppositeMark();
            this.history.add(var1);
            this.updateBoardStatus();
        }
    }

    public Board cloneBoard() {
        Board var1 = new Board();

        for (int var2 = 0; var2 < this.boardSize; ++var2) {
            for (int var3 = 0; var3 < this.boardSize; ++var3) {
                var1.setMarkAt(var2, var3, this.getMarkAt(var2, var3));
            }
        }

        var1.setCurrentMark(this.getCurrentMark());
        var1.updateBoardStatus();
        return var1;
    }

    public boolean isGameOver() {
        switch (this.state) {
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

        for (int var1 = 0; var1 < this.boardSize; ++var1) {
            System.out.print(toChar(var1));
            for (int var2 = 0; var2 < this.boardSize; ++var2) {
                System.out.print("|" + this.grid[var1][var2] + (var2 == this.boardSize - 1 ? "|\n" : ""));
            }
        }

        System.out.println(" =0=1=2=3=4=5=6=7=8=9=A=B=C=D=E=");
    }

    public String toChar(int var) {
        if (var < 10) {
            return Integer.toString(var);
        } else {
            String res = "";
            res += Character.toString(var - 10 + 'A');
            return res;
        }
    }

    public Move getLastMove() {
        return this.history.size() > 0 ? (Move) this.history.get(this.history.size() - 1) : null;
    }
}
