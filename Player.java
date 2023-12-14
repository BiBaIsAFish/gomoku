import java.util.*;

public abstract class Player {
    public Mark markTake;
    public Board board;
    public Move lastMove;

    public Player() {
        this.markTake = null;
        this.lastMove = null;
        this.board = null;
    }

    public void initPlayer(Mark m, Board b) {
        this.markTake = m;
        this.board = b;
    }

    public Mark getMarkTaken() {
        return this.markTake;
    }

    public void setMarkTaken(Mark var1) {
        this.markTake = var1;
    }

    public Mark getCurrentMark() {
        return this.board.getCurrentMark();
    }

    public void setBoard(Board var1) {
        this.board = var1;
    }

    public Move getLastMove() {
        return this.lastMove;
    }

    public boolean isGameOver() {
        return this.board.isGameOver();
    }

    public boolean isGameOver(Board var1) {
        return var1.isGameOver();
    }

    public Board getCurrentState() {
        return this.board.cloneBoard();
    }

    public Mark getOppositeMark() {
        return this.markTake.getOppositeMark();
    }

    public ArrayList<Move> getAvailableMoves() {
        return this.getAvailableMoves(this.board);
    }

    public ArrayList<Move> getAvailableMoves(Board var1) {
        return var1.getAvailableMove();
    }

    public Mark getWinningMark(Board var1) {
        return var1.getWinnerMark();
    }

    public void mark(Move var1) {
        var1.setMark(this.markTake);
        this.board.mark(var1);
        this.lastMove = var1;
    }

    public boolean isMyTurn() {
        return this.board.getCurrentMark() == this.getMarkTaken();
    }

    public Board getNextState(Board var1, Move var2) {
        Board var3 = var1.cloneBoard();
        var2.setMark(var1.getCurrentMark());
        var3.mark(var2);
        return var3;
    }

    public Board getNextState(Board var1, Move var2, Mark var3) {
        Board var4 = var1.cloneBoard();
        var2.setMark(var3);
        var4.mark(var2);
        return var4;
    }

    public abstract void play();
}
