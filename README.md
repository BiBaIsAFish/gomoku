要記得按最下面的 start 開始遊戲

要改背景顏色在 
- BoardUI.java
    - public void drawGrid(Graphics graphics, int row, int col, Mark mark) {
      - graphics.setColor(new Color(255, 141, 57));

改格子大小
- BoardUI.java
  - int gridSize = 40;

改 O 跟 X 的大小
- BoardUI.java
  - graphics.setFont(font.deriveFont(30.0F));

## 亂鬥模式

- 用神奇的方式把 C++ 和 Java 串起來了
- 需要 Express 才能啟動，要先下載 module
  ```
  npm install
  ```
- 用 `run.sh` 啟動，會同時啟動 API Server、C++ 演算法和 Java 演算法
  - 注: 原本的 run.sh 改成 run-ui.sh 了
- C++ 會使用 StdinPlayer
  - 若 C++ 是黑子，請把 server.js 第 9 行改成 `W`，白子則改 `B`
- C++ 編譯好的執行檔要放在此資料夾中
