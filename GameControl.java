// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;

public class GameControl implements ActionListener, Runnable {
   Board b;
   Player[] players;
   GameUI gui;
   Thread t;
   Player currentPlayer;

   public GameControl() {
      this.setupNewGame();
   }

   public void run() {
      this.play();
   }

   public void setupNewGame() {
      if (this.gui != null) {
         this.gui.dispose();
         this.gui = null;
      }

      this.b = new Board();
      this.gui = new GameUI(this, this.b);
   }

   private Player instantiatePlayer(String className, String name, Mark mark, Board board) {
      Player player = null;

      try {
         Class<?> var6 = Class.forName(className);
         player = (Player)var6.getDeclaredConstructor().newInstance();
         player.initPlayer(name, mark, board);
      } catch (ClassNotFoundException exception) {
         System.err.println("Can't find class: " + player);
      } catch (InstantiationException exception) {
         System.err.println("Can't instantiate the class");
      } catch (IllegalAccessException exception) {
         System.err.println("IllegalAccessException!");
      } catch (NoSuchMethodException exception) {
         System.err.println("NoSuchMethodException!");
      } catch (SecurityException exception) {
         System.err.println("SecurityExceptio!");
      } catch (InvocationTargetException exception) {
         System.err.println("InvocationTargetException!");
      }
      return player;
   }

   public void play() {
      this.players = new Player[2];
      String var1;
      if (this.gui.getPlayerClassName(0) == null) {
         var1 = "GUIHumanPlayer";
      } else {
         var1 = this.gui.getPlayerClassName(0);
      }

      this.players[0] = this.instantiatePlayer(var1, this.gui.getPlayerName(0), Mark.CROSS, this.b);
      if (this.gui.getPlayerClassName(1) == null) {
         var1 = "GUIHumanPlayer";
      } else {
         var1 = this.gui.getPlayerClassName(1);
      }

      this.players[1] = this.instantiatePlayer(var1, this.gui.getPlayerName(1), Mark.CIRCLE, this.b);
      int var2 = 0;

      do {
         this.gui.setMessage("Now is player: " + this.players[var2].getPlayerName() + "'s turn, who takes " + this.players[var2].getMarkTaken());
         this.players[var2].play();
         this.gui.setMessage("Player: " + this.players[var2].getPlayerName() + " \u4f7f\u7528 (" + this.players[var2].getMarkTaken() + ") \u4e0b\u5728\u4f4d\u7f6e " + this.players[var2].getLastMove() + "\u8655");
         var2 = (var2 + 1) % 2;
      } while(!this.b.isGameOver());

      switch (this.b.getBoardStatus()) {
         case 0:
            this.gui.setMessage("Winner is: " + this.players[(var2 + 1) % 2].getPlayerName() + " who takes " + this.players[(var2 + 1) % 2].getMarkTaken());
            break;
         case 2:
            this.gui.setMessage("\u96d9\u65b9\u5e73\u624b");
      }

   }

   public void actionPerformed(ActionEvent var1) {
      String var2 = var1.getActionCommand();
      if (var2.equals("Start") && var1.getSource() instanceof JButton) {
         JButton var3 = (JButton)var1.getSource();
         var3.setText("Reset");
         this.t = new Thread(this);
         this.t.start();
      }

      if (var2.equals("Reset")) {
         String[] var4 = this.gui.getPlayerClassName();
         this.gui.dispose();
         this.gui = null;
         this.setupNewGame();
         this.gui.setPlayerClassName(var4);
      }

   }
}
