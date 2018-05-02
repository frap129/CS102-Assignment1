package maples.joe;

// Interface (package-private) providing the specifications for the TennisMatch class.
// See: https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html
interface TennisMatchInterface extends Comparable<TennisMatch> {
   
   // Accessors (getters).
   public String getPlayer1Id();
   public String getPlayer2Id();
   public int getDateYear();
   public int getDateMonth();
   public int getDateDay();
   public String getTournament();
   public String getScore();
   public String getWinner(); // Changed to type String so that I can return player ID or an error message
   
   // Desc.: Prints this tennis match on the console.
   public void print();
   
}


