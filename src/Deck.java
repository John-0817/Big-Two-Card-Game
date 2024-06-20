package src;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
  private List<Card> deckList;

  public Deck() {
    deckList = new ArrayList<>();

    for (Suit suit : Suit.values()) {
      for (Rank rank: Rank.values()) {
        deckList.add(new Card(suit, rank));
      }
    }

    Collections.shuffle(deckList);
  }

  public List<Card> getShuffledDeck() {
    return deckList;
  }
}
