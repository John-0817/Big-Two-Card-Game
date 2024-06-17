import java.util.List;
import java.util.Collections;

public class HandEvaluator {
  public String evaluateHandType(List<Card> hand) {
    switch (hand.size()) {
      case 1:
        return "Single";
      
      case 2:
        
    
      default:
        break;
    }
    return "Invalid Hand";
  }

  public boolean isPair(List<Card> hand) {
    return hand.size() == 2 && hand.get(0).getRank() == hand.get(1).getRank();  
  }

  public boolean isThreeOfAKind(List<Card> hand) {
    return hand.size() == 3 && hand.get(0).getRank() == hand.get(1).getRank()
                            && hand.get(1).getRank() == hand.get(2).getRank();
  }

  public boolean isStraight(List<Card> hand) {
    
    return hand.size() == 5 && hand.get(0).getRank().getValue() + 4 == hand.get(4).getRank().getValue();
  }

  public boolean is(List<Card> hand) {
    return 
  }

  public boolean is(List<Card> hand) {
    return 
  }

  public boolean is(List<Card> hand) {
    return 
  }

  public boolean is(List<Card> hand) {
    return 
  }

  public boolean is(List<Card> hand) {
    return 
  }

}
