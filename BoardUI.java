// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class BoardUI extends JPanel {
   Board b;
   GameUI gui;
   int boardUIHeight;
   int boardUIWidth;
   int gridSize = 40;
   int startX;
   int startY;

   public BoardUI(Board board, GameUI gameUi) {
      this.gui = gameUi;
      this.b = board;
      board.setBoardUI(this);
      this.boardUIHeight = board.getBoardHeight() * this.gridSize;
      this.boardUIWidth = board.getBoardWidth() * this.gridSize;
      // this.setBackground(new Color(255, 141, 57));
   }

   public int getBoardUIWidth() {
      return this.boardUIWidth;
   }

   public int getBoardUIHeight() {
      return this.boardUIHeight;
   }

   public int getBoardUIX() {
      return this.getLocation().x + (int)(this.getSize().getWidth() - (double)this.boardUIWidth) / 2;
   }

   public int getBoardUIY() {
      return this.getLocation().y + (int)(this.getSize().getHeight() - (double)this.boardUIHeight) / 2;
   }

   public int getGridSize() {
      return this.gridSize;
   }

   public void draw(Graphics graphics) {
      int w = this.b.getBoardWidth();
      int d = this.b.getBoardHeight();

      for(int row = 0; row < w; ++row) {
         for(int col = 0; col < d; ++col) {
            this.drawGrid(graphics, row, col, this.b.getMarkAt(row, col));
         }
      }

   }

   public void drawGrid(Graphics graphics, int row, int col, Mark mark) {
      this.startX = this.getLocation().x + (int)(this.getSize().getWidth() - (double)this.boardUIWidth) / 2;
      this.startY = this.getLocation().y + (int)(this.getSize().getHeight() - (double)this.boardUIHeight) / 2;
      int gridStartX = this.startX + col * this.gridSize;
      int gridStartY = this.startY + row * this.gridSize;
      graphics.setColor(new Color(255, 141, 57));
      graphics.fillRect(gridStartX + 1, gridStartY + 1, this.gridSize - 2, this.gridSize - 2);
      graphics.setColor(Color.BLACK);
      graphics.drawRect(gridStartX, gridStartY, this.gridSize, this.gridSize);
      if (mark != Mark.EMPTY) {
         FontMetrics fontMetrics = graphics.getFontMetrics();
         Font font = fontMetrics.getFont();
         graphics.setFont(font.deriveFont(25.0F));
         graphics.setColor(Color.BLACK);
         String markString = mark.toString();
         fontMetrics = graphics.getFontMetrics();
         int markStringX = gridStartX + (this.gridSize - fontMetrics.stringWidth(markString)) / 2;
         int markStringY = gridStartY + (this.gridSize - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2 + fontMetrics.getAscent();
         graphics.drawString(markString, markStringX, markStringY);
      }
   }

   public void update(Graphics graphics) {
      this.paint(graphics);
   }

   public void paint(Graphics graphics) {
      Image image = this.createImage((int)this.getSize().getWidth(), (int)this.getSize().getHeight());
      Graphics imageGraphics = image.getGraphics();
      this.draw(imageGraphics);
      graphics.drawImage(image, 0, 0, this);
   }
}
