import java.awt.Point;

public class Move {
    Point position;
    Mark mark;

    public Move(int x, int y, Mark m) {
        this.position = new Point(x, y);
        this.mark = m;
    }

    public int getX() {
        return this.position.x;
    }

    public int getY() {
        return this.position.y;
    }

    public Mark getMark() {
        return this.mark;
    }

    public void setMark(Mark m) {
        this.mark = m;
    }
}
