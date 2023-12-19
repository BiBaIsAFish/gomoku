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
            return "white";
         case BLACK:
            return "black";
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
