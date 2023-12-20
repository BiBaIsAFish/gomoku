// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameUI extends JFrame implements MouseListener {
   BoardUI bui;
   GameControl gc;
   Board b;
   JTextArea messageJTA;
   JScrollPane messageJSP;
   JTextField[] playerClass;
   JTextField[] player;
   String[] defaultPlayerClassName;
   UpdateThread updateThread;
   ArrayList<Player> uiObserver;

   public GameUI(GameControl gc, Board board) {
      this.gc = gc;
      this.b = board;
      this.bui = new BoardUI(board, this);
      this.uiObserver = new ArrayList<>();
      this.defaultPlayerClassName = new String[2];
      this.defaultPlayerClassName[0] = "GUIHumanPlayer";
      this.defaultPlayerClassName[1] = "GUIHumanPlayer";
      this.initializeUI();
      this.setPlayerClassName(this.defaultPlayerClassName);
      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      this.setVisible(true);
      this.updateThread = new UpdateThread(this);
      this.updateThread.start();

      
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public void setBoard(Board var1) {
      this.b = var1;
   }

   public String[] getPlayerClassName() {
      String[] var1 = new String[2];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = this.playerClass[var2].getText();
      }

      return var1;
   }

   public void setPlayerClassName(String[] var1) {
      for(int var2 = 0; var2 < this.playerClass.length; ++var2) {
         this.playerClass[var2].setText(var1[var2]);
      }

   }

   private void initializeUI() {
      this.setLayout(new BorderLayout());
      JButton startButton = new JButton("Start");
      startButton.addActionListener(this.gc);
      this.messageJTA = new JTextArea(4, 50);
      this.messageJTA.setAlignmentX(0.5F);
      this.messageJTA.setEditable(false);
      this.messageJSP = new JScrollPane(this.messageJTA);
      this.playerClass = new JTextField[2];
      this.playerClass[0] = new JTextField();
      this.playerClass[0].setHorizontalAlignment(0);
      this.playerClass[1] = new JTextField();
      this.playerClass[1].setHorizontalAlignment(0);
      JLabel playerClassNameLabel = new JLabel("<---------- \u73a9\u5bb6\u985e\u5225\u540d\u7a31 ---------->"); // player class name 
      playerClassNameLabel.setHorizontalAlignment(0);
      this.player = new JTextField[2];
      this.player[0] = new JTextField("\u73a9\u5bb61");  // player1
      this.player[0].setHorizontalAlignment(0);
      this.player[1] = new JTextField("\u73a9\u5bb62");  // player2
      this.player[1].setHorizontalAlignment(0);
      JPanel gameStatusPanel = new JPanel(new GridLayout(2, 1));
      gameStatusPanel.add(this.messageJSP);
      JPanel gameInfoPanel = new JPanel(new GridLayout(2, 1));
      JPanel playerNamePanel = new JPanel(new GridLayout(1, 3));
      playerNamePanel.add(this.playerClass[0]);
      playerNamePanel.add(playerClassNameLabel);
      playerNamePanel.add(this.playerClass[1]);
      gameInfoPanel.add(playerNamePanel);
      JPanel startPanel = new JPanel(new GridLayout(1, 3));
      startPanel.add(this.player[0]);
      startPanel.add(startButton);
      startPanel.add(this.player[1]);
      gameInfoPanel.add(startPanel);
      gameStatusPanel.add(gameInfoPanel);
      this.getContentPane().add(gameStatusPanel, "South");
      this.getContentPane().add(this.bui, "Center");
   }

   public String getPlayerClassName(int index) {
      return index != 0 && index != 1 ? null : this.playerClass[index].getText();
   }

   public String getPlayerName(int index) {
      return this.player[index].getText();
   }

   public void setMessage(String var1) {
      this.messageJTA.append(var1 + "\n");
      this.messageJTA.setCaretPosition(this.messageJTA.getDocument().getLength());
   }

   public void mouseClicked(MouseEvent var1) {
      Point var2 = var1.getPoint();
      int var3 = this.bui.getBoardUIX();
      int var4 = this.bui.getBoardUIY();
      int var5 = this.bui.getBoardUIWidth();
      int var6 = this.bui.getBoardUIHeight();
      if (var2.x <= var3 + var5 && var2.x >= var3) {
         if (var2.y <= var4 + var6 && var2.y >= var4) {
            int var7 = (var2.y - var4) / this.bui.getGridSize();
            int var8 = (var2.x - var3) / this.bui.getGridSize();
            System.out.println("Grid: (" + var7 + ", " + var8 + ")");
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
