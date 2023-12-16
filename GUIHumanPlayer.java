// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GUIHumanPlayer extends Player implements MouseListener {
   boolean notified = false;
   boolean keepGoingStatus = true;
   Move m = null;

   public GUIHumanPlayer() {
   }

   public void initPlayer(String var1, Mark var2, Board board) {
      super.initPlayer(var1, var2, board);
      board.addMouseListener(this);
   }

   public void makeMove(int var1, int var2) {
      if (this.getCurrentMark() == this.getMarkTaken()) {
         this.m = new Move(var1, var2);
         this.m.setMark(this.getMarkTaken());
         ArrayList var3 = this.getAvailableMoves();
         if (var3.contains(this.m)) {
            this.mark(this.m);
            this.keepGoingStatus = false;
         }

      }
   }

   public void play() {
      this.keepGoingStatus = true;

      while(this.keepGoingStatus) {
         this.waiting();
      }

   }

   private void waiting() {
      try {
         Thread.sleep(200L);
      } catch (InterruptedException var2) {
         System.out.println("Waiting is interrupted");
      }

   }

   public Move getNextMove(Board var1) {
      Move var2 = null;
      ArrayList var3 = var1.getAvailableMove();

      while(!this.notified || this.m == null || !var3.contains(this.m)) {
         try {
            Thread.sleep(300L);
         } catch (InterruptedException var5) {
            System.out.println(var5);
         }
      }

      var2 = this.m;
      this.m = null;
      this.notified = false;
      return var2;
   }

   public void mouseClicked(MouseEvent var1) {
      Point var2 = var1.getPoint();
      if (!var1.isConsumed()) {
         if (this.getCurrentMark() == this.getMarkTaken()) {
            var1.consume();
            BoardUI var3 = this.getBoardUI();
            int var4 = var3.getBoardUIX();
            int var5 = var3.getBoardUIY();
            int var6 = var3.getBoardUIWidth();
            int var7 = var3.getBoardUIHeight();
            if (var2.x <= var4 + var6 && var2.x >= var4) {
               if (var2.y <= var5 + var7 && var2.y >= var5) {
                  int var8 = (var2.y - var5) / var3.getGridSize();
                  int var9 = (var2.x - var4) / var3.getGridSize();
                  this.makeMove(var8, var9);
               }
            }
         }
      }
   }

   public void mouseEntered(MouseEvent var1) {
   }

   public void mouseExited(MouseEvent var1) {
   }

   public void mousePressed(MouseEvent var1) {
   }

   public void mouseReleased(MouseEvent var1) {
   }
}
