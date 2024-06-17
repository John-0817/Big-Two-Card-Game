public enum Suit {
  DIAMOND("D"), HEART("H"), CLUB("C"), SPADE("S");

  private final String prefix;

  Suit(String prefix) {
    this.prefix = prefix;
  }

  public String getPrefix() {
    return prefix;
  }
}
