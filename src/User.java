package src;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class User {
  private List<Card> userDeck;
  private List<String> backOfDeck;

  public User() {
    userDeck = new ArrayList<>();
  }

  public void addCard(Card card) {
    userDeck.add(card);
  }

  public void removeFromDeck(List<Card> playedHand) {
    userDeck.removeAll(playedHand);
  }

  public void sortDeck() {
    Collections.sort(userDeck);
  }

  public void showDeck() {
    System.out.println(userDeck);
  }

  public void showBackOfDeck() {
    backOfDeck = new ArrayList<>();
    backOfDeck = userDeck.stream().map(card -> "*").collect(Collectors.toList());
    System.out.println(backOfDeck);
  }

  public boolean findD3() {
    for (Card card: userDeck) {
      if (card.getSuit() == Suit.DIAMONDS && card.getRank() == Rank.THREE) {
        return true;
      }
    }
    return false;
  }

}
