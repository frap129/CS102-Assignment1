package maples.joe;

// Custom (checked) exception for the TennisDatabase package, representing critical runtime errors (that must be handled).
public class TennisDatabaseException extends java.lang.Exception {
   
   // Desc.: Constructor.
   // Input: Description of the runtime error.
   public TennisDatabaseException( String s ) { super(s); }
   
}


