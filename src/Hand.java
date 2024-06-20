package src;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class Hand {
  private List<Card> hand;
  private Combination combination;
  private Card topCard;

  private Rank topRank = null;

  public Hand(List<Card> hand) {
    this.hand = hand;
    this.combination = Combination.evaluateHandType(hand);
    this.topCard = getTopCard();
  }
  
  public Card returnTopCard() {
    return this.topCard;
  }

  public Combination getCombination() {
    return this.combination;
  }

  public Card getTopCard() {
    Collections.sort(hand, Comparator.comparing(Card::getRank));
    switch (combination) {
      case Single:
        return hand.get(0);
      case Pair:
      case ThreeOfAKind:
      case Straight:
      case Flush:
      case StraightFlush:
        return hand.getLast();
      case FullHouse:
        return getTopCardOfFullHouse();
      case FourOfAKind:
        return getTopCardOfFourOfAKind();
      default:
        break;
    }
    return null;
  }

  public Card getTopCardOfFullHouse() {
    Map<Rank, Integer> rankCount = new HashMap<>();
    for (Card card : hand) {
      rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(),0) + 1);
    }

    for (Map.Entry<Rank, Integer> entry : rankCount.entrySet()) {
      if (entry.getValue() == 3) {
        topRank = entry.getKey();
      } 
    }

    Map<Boolean, List<Card>> partitioned = hand.stream().collect(Collectors.partitioningBy(card -> card.getRank() == topRank));
    List<Card> threeOfAKind = partitioned.get(true);

    return threeOfAKind.getLast();
  }

  public Card getTopCardOfFourOfAKind() {
    Map<Rank, Integer> rankCount = new HashMap<>();
    for (Card card : hand) {
      rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(),0) + 1);
    }

    for (Map.Entry<Rank, Integer> entry : rankCount.entrySet()) {
      if (entry.getValue() == 4) {
        topRank = entry.getKey();
      } 
    }

    Map<Boolean, List<Card>> partitioned = hand.stream().collect(Collectors.partitioningBy(card -> card.getRank() == topRank));
    List<Card> threeOfAKind = partitioned.get(true);

    return threeOfAKind.getLast();
  }

  public boolean compareHand(Hand topHand) {
    int combinationComparison = this.combination.compareTo(topHand.combination);

    if (topHand.combination.ordinal() < 4) {
      if (combinationComparison != 0) {
        return false;
      } else {
        int topCardComparison = this.topCard.compareTo(topHand.topCard);
        return topCardComparison > 0;
      }
    } else {
      if (combinationComparison > 0) {
        return true;
      } else if (combinationComparison < 0) {
        return false;
      } else {
        int topCardComparison = this.topCard.compareTo(topHand.topCard);
        return topCardComparison > 0;
      }
    }
  }

}
