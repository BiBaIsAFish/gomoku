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
      Server.start();
      this.players = new Player[2];
      String className;
      if (this.gui.getPlayerClassName(0) == null) {
         className = "GUIHumanPlayer";
      } else {
         className = this.gui.getPlayerClassName(0);
      }

      this.players[0] = this.instantiatePlayer(className, this.gui.getPlayerName(0), Mark.BLACK, this.b);
      if (this.gui.getPlayerClassName(1) == null) {
         className = "GUIHumanPlayer";
      } else {
         className = this.gui.getPlayerClassName(1);
      }

      this.players[1] = this.instantiatePlayer(className, this.gui.getPlayerName(1), Mark.WHITE, this.b);
      int currentPlayerIndex = 0;

      do {
         this.gui.setMessage("Now is player: " + this.players[currentPlayerIndex].getPlayerName() + "'s turn, who takes " + this.players[currentPlayerIndex].getMarkTaken());
         this.players[currentPlayerIndex].play();
         this.gui.setMessage("Player: " + this.players[currentPlayerIndex].getPlayerName() + " \u4f7f\u7528 (" + this.players[currentPlayerIndex].getMarkTaken() + ") \u4e0b\u5728\u4f4d\u7f6e " + this.players[currentPlayerIndex].getLastMove() + "\u8655");
         currentPlayerIndex = (currentPlayerIndex + 1) % 2;
      } while(!this.b.isGameOver());

      switch (this.b.getBoardStatus()) {
         case 0:
            this.gui.setMessage("Winner is: " + this.players[(currentPlayerIndex + 1) % 2].getPlayerName() + " who takes " + this.players[(currentPlayerIndex + 1) % 2].getMarkTaken());
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
