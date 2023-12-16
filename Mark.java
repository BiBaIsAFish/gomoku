public enum Mark {
   EMPTY,
   CIRCLE,
   CROSS;

   private Mark() {
   }

   public String toString() {
      switch (this) {
         case EMPTY:
            return " ";
         case CIRCLE:
            return "O";
         case CROSS:
            return "X";
         default:
            return " ";
      }
   }

   public Mark getOppositeMark() {
      if (this == EMPTY) {
         return EMPTY;
      } else {
         return this == CIRCLE ? CROSS : CIRCLE;
      }
   }
}
