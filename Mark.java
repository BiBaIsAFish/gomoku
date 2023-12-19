public enum Mark {
   EMPTY,
   WHITE,
   BLACK;

   private Mark() {
   }

   public String toString() {
      switch (this) {
         case EMPTY:
            return " ";
         case WHITE:
            return "O";
         case BLACK:
            return "X";
         default:
            return " ";
      }
   }

   public Mark getOppositeMark() {
      if (this == EMPTY) {
         return EMPTY;
      } else {
         return this == WHITE ? BLACK : WHITE;
      }
   }
}
