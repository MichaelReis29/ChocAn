package cs200.team16.chocan;

/**
 * Stores information for a ChocAn operator.
 *
 * @author Kaitlyn NeSmith
 */
public class OperatorRecord extends PersonRecord {

  /**
   * This operator's unique log-in pin.
   */
  private final int pin;
  
  /**
   * Constructs a fully-populated OperatorRecord.
   */
  public OperatorRecord(String name, int number, Address address, int pin) {
    super(name, number, address);
    this.pin = pin;
  }
  
  /**
   * Verifies the current pin of this OperatorRecord.
   *
   * @return A boolean representing whether the given pin is correct.
   */
  public Boolean isPin(int pin) {
    return this.pin == pin;
  }
  
}
