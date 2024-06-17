import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class User {
  private List<Card> hand;
  private List<String> hiddenHand;

  public User() {
    hand = new ArrayList<>();
  }

  public void addCard(Card card) {
    hand.add(card);
  }

  public void removeCard(Card[] card) {
    
  }

  public int cardComparator(Card card1, Card card2) {
    int suitComparator = card1.getSuit().ordinal() - card2.getSuit().ordinal();
    if (suitComparator != 0) {
      return suitComparator;
    }
    return card1.getRank().ordinal() - card2.getRank().ordinal();
  }

  public Comparator<Card> getCardComparator() {
    return this::cardComparator;
  }

  public void sortHand() {
    hand.sort(getCardComparator());
  }

  public void showHand() {
    System.out.println(hand);
    System.out.println(hand.get(12));
    System.out.println(hand.get(12).getRank().ordinal());
  }

  public void showHandHidden() {
    hiddenHand = new ArrayList<>();
    hiddenHand = hand.stream().map(card -> "*").collect(Collectors.toList());
    System.out.println(hiddenHand);
  }

  public boolean findD3() {
    for (Card card: hand) {
      if (card.getSuit() == Suit.DIAMOND && card.getRank() == Rank.THREE) {
        return true;
      }
    }
    return false;
  }

}
