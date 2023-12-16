// Source code is decompiled from a .class file using FernFlower decompiler.
public class UpdateThread extends Thread {
   GameUI gui;

   public UpdateThread(GameUI var1) {
      this.gui = var1;
   }

   public void run() {
      while(true) {
         this.gui.repaint();

         try {
            Thread.sleep(200L);
         } catch (InterruptedException var2) {
            System.out.println(var2);
         }
      }
   }
}
