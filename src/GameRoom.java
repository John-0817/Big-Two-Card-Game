package src;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class GameRoom {
  private boolean gameStart = false;
  private Scanner scanner;
  private int gameRound;
  private int passCount;
  private int activeUser;
  private int topUser;
  private Hand topHand;

  public static void main(String[] args) {
    GameRoom gameRoom = new GameRoom();
    
    gameRoom.play();
  }

  public void startGame() {
    gameStart = true;
  }

  public void endGame() {
    gameStart = false;
  }

  public void distributeCards(User[] users) {
    Deck deck = new Deck();
    List<Card> shuffledDeck = deck.getShuffledDeck();

    int userIndex = 0;
    for (Card card: shuffledDeck) {
      users[userIndex].addCard(card);
      userIndex = (userIndex + 1) % 4;
    }
  }

  public int findGameStarter(User[] users) {
    int userIndex = 0;
    for (int i = 0; i < 4; i++) {
      if (users[i].findD3()) {
        userIndex = i;
      }
    }

    return userIndex;
  }

  public void displayGameRoom(int activeUser, int gameRound, User[] users) {
    System.out.println("Round " + gameRound);
    for (int i = 0; i < 4; i++) {
      users[i].sortDeck();
      System.out.print("User " + (i + 1) + ":");
      if (i == activeUser) {
        users[i].showDeck();
      } else {
        users[i].showBackOfDeck();
      }
    }
  }

  public void play() {
    User[] users = new User[4];
    for (int i = 0; i < 4; i++){
      users[i] = new User();
    }
    
    startGame();
    distributeCards(users);
    
    scanner = new Scanner(System.in);
    
    activeUser = findGameStarter(users);
    gameRound = 1;
    passCount = 0;
    topHand = null;
    topUser = activeUser;
    while(gameStart) {
      User user = users[activeUser];
      boolean loop = true;

      while (loop) {
        displayGameRoom(activeUser, gameRound, users);
        
        // User Input (Hand)
        System.out.print("User " + (activeUser + 1) + ":");
        String input = scanner.nextLine();
        
        if (input.equalsIgnoreCase("pass")) {
          passCount++;
          loop = false;
          continue;
        }

        List<Card> playedHand = new ArrayList<>();
        String[] inputArray = input.split(" ");
        for (String cardString : inputArray) {
          playedHand.add(Card.fromString(cardString));
        }

        Hand hand = new Hand(playedHand);
        Combination combination = hand.getCombination();

        // Round 1. Initiate game with D3.
        if (topHand == null && gameRound == 1) {
          if (combination == Combination.Invalid) {
            System.out.println("Your hand is invalid. Try again.");
          } else if (!playedHand.contains(new Card(Suit.DIAMONDS, Rank.THREE))) {
            System.out.println("Your hand must include D3. Try again."); 
          } else {
            user.removeFromDeck(playedHand);
            topHand = hand;
            topUser = activeUser;
            passCount = 0;
            loop = false;
            System.out.println("Top Hand: " + topHand.returnTopCard() + " " + topHand.getCombination());
          }
        } 
        
        // Round n. Initiate freely.
        else if (topHand == null && gameRound != 1) {
          if (combination == Combination.Invalid) {
            System.out.println("Your hand is invalid. Try again.");
          } else {
            user.removeFromDeck(playedHand);
            topHand = hand;
            topUser = activeUser;
            passCount = 0;
            loop = false;
            System.out.println("Top Hand: " + topHand.returnTopCard() + " " + topHand.getCombination());
          }
        }

        // Regular rounds & Subsequent rounds.
        else {
          if (combination == Combination.Invalid) {
            System.out.println("Your hand is invalid. Try again.");
          } else {
            boolean beaten = hand.compareHand(topHand);
            if (beaten) {
              user.removeFromDeck(playedHand);
              topHand = hand;
              topUser = activeUser;
               passCount = 0;
              loop = false;
              System.out.println("Top Hand: " + topHand.returnTopCard() + " " + topHand.getCombination());
            } else {
              System.out.println("Your hand cannot beat the current top hand. Try again with different combination.");
              System.out.println("Top Hand: " + topHand.returnTopCard() + " " + topHand.getCombination());
            }
          }
        }
      }

      if (passCount == 3) {
        topHand = null;
        passCount = 0;
        activeUser = topUser;
        gameRound++;
      } else {
        activeUser = (activeUser + 1) % 4;
      }

    }

    scanner.close();
  }

}
