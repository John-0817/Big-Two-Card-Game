package src;
public enum Rank {
  THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), ELEVEN(11), TWELVE(12), THIRTEEN(13), ONE(1), TWO(2);

  private final int value;

  Rank(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static Rank fromValue(int value) {
    for (Rank rank : Rank.values()) {
      if (rank.getValue() == value) {
        return rank;
      }
    }
    throw new IllegalArgumentException("Unknown rank value: " + value);
  }
}
