package cs200.team16.chocan;

/**
 * Stores information for a ChocAn manager.
 *
 * @author Kaitlyn NeSmith
 */
public class ManagerRecord extends PersonRecord {

  /**
   * This manager's unique log-in pin.
   */
  private final int pin;
  
  /**
   * Constructs a fully-populated ManagerRecord.
   */
  public ManagerRecord(String name, int number, Address address, int pin) {
    super(name, number, address);
    this.pin = pin;
  }
  
  /**
   * Verifies the current pin of this ManagerRecord.
   *
   * @return A boolean representing whether the given pin is correct.
   */
  public Boolean isPin(int pin) {
    return this.pin == pin;
  }
  
}
