package src;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public enum Combination {
  Invalid(0), Single(1), Pair(2), ThreeOfAKind(3), Straight(4), Flush(5), FullHouse(6), FourOfAKind(7), StraightFlush(8);

  private final int rank;

  Combination(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return rank;
  }

  public static Combination evaluateHandType(List<Card> hand) {
    switch (hand.size()) {
      case 1:
        return Single;

      case 2:
        if (isPair(hand)) {
          return Pair;
        }
        break;
      
      case 3:
        if (isThreeOfAKind(hand)) {
          return ThreeOfAKind;
        }
        break;

      case 5:
        if (isStraight(hand)) {
          return Straight;
        } else if (isFlush(hand)) {
          return Flush;
        } else if (isFullHouse(hand)) {
          return FullHouse;
        } else if (isFourOfAKind(hand)) {
          return FourOfAKind;
        } else if (isStraightFlush(hand)) {
          return StraightFlush;
        }
        break;
    
      default:
        break;
    }
    return Invalid;
  }

  public static boolean isPair(List<Card> hand) {
    return hand.size() == 2 && hand.get(0).getRank() == hand.get(1).getRank();  
  }

  public static boolean isThreeOfAKind(List<Card> hand) {
    return hand.size() == 3 && hand.get(0).getRank() == hand.get(1).getRank()
                            && hand.get(1).getRank() == hand.get(2).getRank();
  }

  public static boolean isStraight(List<Card> hand) {
    if (hand.size() != 5) {
      return false;
    }

    Collections.sort(hand, Comparator.comparing(Card::getRank));
    for (int i = 0; i < 3; i++) {
      if (hand.get(i).getRank().ordinal() + 1 != hand.get(i + 1).getRank().ordinal()) {
        return false;
      }
    }

    return true;
  }

  public static boolean isFlush(List<Card> hand) {
    if (hand.size() != 5) {
      return false;
    }
    
    Suit suit = hand.get(0).getSuit();

    for (Card card : hand) {
      if (card.getSuit() != suit) {
        return false;
      }
    }
    
    return true;
  }

  public static boolean isFullHouse(List<Card> hand) {
    if (hand.size() != 5) {
      return false;
    }
    
    Map<Rank, Integer> rankCount = new HashMap<>();
    for (Card card : hand) {
      rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(),0) + 1);
    }
    
    return rankCount.size() == 2 && (rankCount.containsValue(3) && rankCount.containsValue(2));
  }

  public static boolean isFourOfAKind(List<Card> hand) {
    if (hand.size() != 5) {
      return false;
    }
    
    Map<Rank, Integer> rankCount = new HashMap<>();
    for (Card card: hand) {
      rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(),0) + 1);
    }
    
    return rankCount.containsValue(4);
  }

  public static boolean isStraightFlush(List<Card> hand) {
    return isStraight(hand) && isFlush(hand);
  }

}
