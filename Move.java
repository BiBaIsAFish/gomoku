// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.Point;

public class Move {
   Point pos;
   Mark m;
   private int score = 0;

   public Move(int var1, int var2) {
      this.pos = new Point(var1, var2);
      this.m = Mark.EMPTY;
   }

   public Mark getMark() {
      return this.m;
   }

   public void setMark(Mark var1) {
      this.m = var1;
   }

   public Point getPosition() {
      return this.pos;
   }

   public int getX() {
      return this.pos.x;
   }

   public int getY() {
      return this.pos.y;
   }

   public void setScore(int var1) {
      this.score = var1;
   }

   public int getScore() {
      return this.score;
   }

   public String toString() {
      return new String("(" + this.pos.x + ", " + this.pos.y + ")");
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof Move)) {
         return false;
      } else {
         Move var2 = (Move)var1;
         return this.pos.equals(var2.pos);
      }
   }

   public int hashCode() {
      boolean var1 = false;
      int var2 = this.pos.x * 10 + this.pos.y;
      return var2;
   }
}
