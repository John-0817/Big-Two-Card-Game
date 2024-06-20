package src;
import java.util.Objects;

public class Card implements Comparable<Card> {
  private final Suit suit;
  private final Rank rank;

  public Card(Suit suit, Rank rank) {
    this.suit = suit;
    this.rank = rank;
  }

  public Suit getSuit() {
    return suit;
  }

  public Rank getRank() {
    return rank;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    Card card = (Card) obj;
    return suit == card.suit && rank == card.rank;
  }

  @Override
  public int compareTo(Card other) {
    int rankComparison = Integer.compare(this.rank.ordinal(), other.rank.ordinal());
    if (rankComparison != 0) {
      return rankComparison;
    }
    return this.suit.compareTo(other.suit);
  }

  @Override
    public int hashCode() {
      return Objects.hash(suit, rank);
    }

  @Override
  public String toString() {
    return suit.getPrefix() + rank.getValue();
  }

  public static Card fromString(String cardString) {
    if (cardString == null || cardString.length() < 2) {
      throw new IllegalArgumentException("Invalid card string: " + cardString);
    }

    String suitPrefix = cardString.substring(0, 1);
    int rankValue = Integer.parseInt(cardString.substring(1));

    Suit suit = Suit.fromPrefix(suitPrefix);
    Rank rank = Rank.fromValue(rankValue);

    return new Card(suit, rank);
  }
}
