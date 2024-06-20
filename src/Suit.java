package src;
public enum Suit {
  DIAMONDS("D"), CLUBS("C"), HEARTS("H"), SPADES("S");

  private final String prefix;

  Suit(String prefix) {
    this.prefix = prefix;
  }

  public String getPrefix() {
    return prefix;
  }

  public static Suit fromPrefix(String prefix) {
    for (Suit suit : Suit.values()) {
      if (suit.getPrefix().equals(prefix)) {
        return suit;
      }
    }
    throw new IllegalArgumentException("Unknown suit prefix: " + prefix);
  }
}
