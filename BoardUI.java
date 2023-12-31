
// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class BoardUI extends JPanel {

   private static final int UP = 0;
   private static final int DOWN = 1;
   private static final int LEFT = 2;
   private static final int RIGHT = 3;

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
      return this.getLocation().x + (int) (this.getSize().getWidth() - (double) this.boardUIWidth) / 2;
   }

   public int getBoardUIY() {
      return this.getLocation().y + (int) (this.getSize().getHeight() - (double) this.boardUIHeight) / 2;
   }

   public int getGridSize() {
      return this.gridSize;
   }

   public void draw(Graphics graphics) {
      int w = this.b.getBoardWidth();
      int d = this.b.getBoardHeight();

      for (int row = 0; row < w; ++row) {
         for (int col = 0; col < d; ++col) {
            this.drawGrid(graphics, row, col, this.b.getMarkAt(row, col));
         }
      }
      if (this.b.winningMark != null) {
         drawWinningLine(graphics, this.b.winningPoint, this.b.winningDiff, this.b.winningLength);
      }

      if (this.b.history.size() != 0) {
         drawLastPiece(graphics, this.b.history.get(this.b.history.size() - 1));
      }

      for (int order = 0; order < this.b.history.size(); order++) {
         this.drawOrder(graphics, this.b.history.get(order), order + 1);
      }
   }

   private void drawLastPiece(Graphics graphics, Move lastMove) {
      Graphics2D g2 = (Graphics2D) graphics;

      g2.setStroke(new BasicStroke(5));
      int row = lastMove.getPosition().x;
      int col = lastMove.getPosition().y;
      this.startX = this.getLocation().x + (int) (this.getSize().getWidth() - (double) this.boardUIWidth) / 2;
      this.startY = this.getLocation().y + (int) (this.getSize().getHeight() - (double) this.boardUIHeight) / 2;
      int x = this.startX + col * this.gridSize;
      int y = this.startY + row * this.gridSize;
      int ovalSize = (int) (this.gridSize * 0.8);
      int ovalX = x + (this.gridSize - ovalSize) / 2;
      int ovalY = y + (this.gridSize - ovalSize) / 2;

      graphics.setColor(Color.RED);
      graphics.drawOval(ovalX, ovalY, ovalSize, ovalSize);
   }

   private void drawWinningLine(Graphics graphics, Point point, Point diff, int length) {
      int row = point.x;
      int col = point.y;

      this.startX = this.getLocation().x + (int) (this.getSize().getWidth() - (double) this.boardUIWidth) / 2;
      this.startY = this.getLocation().y + (int) (this.getSize().getHeight() - (double) this.boardUIHeight) / 2;
      int sX = this.startX + col * this.gridSize + this.gridSize / 2;
      int sY = this.startY + row * this.gridSize + this.gridSize / 2;

      int eX = sX + diff.x * (length - 1) * this.gridSize;
      int eY = sY + diff.y * (length - 1) * this.gridSize;

      graphics.setColor(new Color(0, 255, 0, 144));

      Graphics2D g2 = (Graphics2D) graphics;
      g2.setStroke(new BasicStroke(10));
      g2.draw(new Line2D.Float(sX, sY, eX, eY));

   }

   private void drawOrder(Graphics graphics, Move move, int order) {

      if (move.getMark() != Mark.BLACK) {
         graphics.setColor(new Color(15, 15, 15));
      } else {
         graphics.setColor(new Color(240, 240, 240));
      }

      int row = move.getPosition().x;
      int col = move.getPosition().y;

      this.startX = this.getLocation().x + (int) (this.getSize().getWidth() - (double) this.boardUIWidth) / 2;
      this.startY = this.getLocation().y + (int) (this.getSize().getHeight() - (double) this.boardUIHeight) / 2;
      int x = this.startX + col * this.gridSize;
      int y = this.startY + row * this.gridSize;

      FontMetrics fontMetrics = graphics.getFontMetrics();
      Font font = fontMetrics.getFont().deriveFont(Font.BOLD);
      graphics.setFont(font.deriveFont(15.0F));
      String positionString = "";

      positionString = String.valueOf(order);
      fontMetrics = graphics.getFontMetrics();
      int stringX = x + (this.gridSize - fontMetrics.stringWidth(positionString)) / 2;
      int stringY = y + (this.gridSize - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2
            + fontMetrics.getAscent();
      graphics.drawString(positionString, stringX, stringY);
   }

   private void drawLineOnDirections(Graphics graphics, int x, int y, int length, int direction) {

      int endX = x, endY = y;
      switch (direction) {
         case UP:
            endY -= length;
            break;
         case DOWN:
            endY += length;
            break;
         case LEFT:
            endX -= length;
            break;
         case RIGHT:
            endX += length;
            break;
         default:
            break;
      }
      graphics.drawLine(x, y, endX, endY);
   }

   private void drawRectOnGraphics(Graphics graphics, int startX, int startY, int size, boolean atTop, boolean atBottom,
         boolean atLeft, boolean atRight) {

      int lineLen = size / 2;
      int middleX = startX + lineLen;
      int middleY = startY + lineLen;

      if (!atTop) {
         drawLineOnDirections(graphics, middleX, middleY, lineLen, UP);
      }
      if (!atBottom) {
         drawLineOnDirections(graphics, middleX, middleY, lineLen, DOWN);
      }
      if (!atLeft) {
         drawLineOnDirections(graphics, middleX, middleY, lineLen, LEFT);
      }
      if (!atRight) {
         drawLineOnDirections(graphics, middleX, middleY, lineLen, RIGHT);
      }
   }

   private void drawPointOnGraphics(Graphics graphics, int x, int y, int gridSize, int row, int col) {
      if (((row == 3 || row == 11) && (col == 3 || col == 11)) || (row == 7 && col == 7)) {
         graphics.fillOval(x + gridSize / 2 - 3, y + gridSize / 2 - 3, 6, 6);
      }
   }

   private void drawOvalOnGraphics(Graphics graphics, int x, int y, int size, boolean isBlack) {

      if (isBlack) {
         graphics.setColor(new Color(15, 15, 15));
      } else {
         graphics.setColor(new Color(240, 240, 240));
      }
      int ovalSize = (int) (size * 0.8);
      int ovalX = x + (size - ovalSize) / 2;
      int ovalY = y + (size - ovalSize) / 2;

      graphics.fillOval(ovalX, ovalY, ovalSize, ovalSize);
   }

   private void drawLocationStringOnGraphics(Graphics graphics, int x, int y, int size, int row, int col) {
      FontMetrics fontMetrics = graphics.getFontMetrics();
      Font font = fontMetrics.getFont();
      graphics.setFont(font.deriveFont(25.0F));
      graphics.setColor(Color.BLACK);
      String positionString = "";

      if (row == this.b.boardHeight - 1) {
         positionString = String.valueOf((char) ('A' + col));
         fontMetrics = graphics.getFontMetrics();
         int stringX = x + (this.gridSize - fontMetrics.stringWidth(positionString)) / 2;
         int stringY = y + (this.gridSize - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2
               + fontMetrics.getAscent();
         graphics.drawString(positionString, stringX, stringY + size);
      }
      if (col == 0) {
         positionString = String.valueOf(this.b.boardHeight - row);
         fontMetrics = graphics.getFontMetrics();
         int stringX = x + (this.gridSize - fontMetrics.stringWidth(positionString)) / 2;
         int stringY = y + (this.gridSize - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2
               + fontMetrics.getAscent();
         graphics.drawString(positionString, stringX - size, stringY);
      }
   }

   public void drawGrid(Graphics graphics, int row, int col, Mark mark) {
      this.startX = this.getLocation().x + (int) (this.getSize().getWidth() - (double) this.boardUIWidth) / 2;
      this.startY = this.getLocation().y + (int) (this.getSize().getHeight() - (double) this.boardUIHeight) / 2;
      int gridStartX = this.startX + col * this.gridSize;
      int gridStartY = this.startY + row * this.gridSize;

      drawLocationStringOnGraphics(graphics, gridStartX, gridStartY, gridSize, row, col);

      graphics.setColor(new Color(255, 141, 57));
      graphics.fillRect(gridStartX, gridStartY, this.gridSize, this.gridSize);
      graphics.setColor(Color.BLACK);

      boolean atTop = row == 0;
      boolean atBottom = row == this.b.getBoardHeight() - 1;
      boolean atLeft = col == 0;
      boolean atRight = col == this.b.getBoardWidth() - 1;

      drawRectOnGraphics(graphics, gridStartX, gridStartY, this.gridSize, atTop, atBottom, atLeft, atRight);

      drawPointOnGraphics(graphics, gridStartX, gridStartY, this.gridSize, row, col);

      if (mark != Mark.EMPTY) {
         drawOvalOnGraphics(graphics, gridStartX, gridStartY, gridSize, mark == Mark.BLACK);
      }
   }

   public void update(Graphics graphics) {
      this.paint(graphics);
   }

   public void paint(Graphics graphics) {
      Image image = this.createImage((int) this.getSize().getWidth(), (int) this.getSize().getHeight());
      Graphics imageGraphics = image.getGraphics();
      this.draw(imageGraphics);
      graphics.drawImage(image, 0, 0, this);
   }
}
