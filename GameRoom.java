import java.util.List;

public class GameRoom {
  private boolean gameStart = true;

  public static void main(String[] args) {
    GameRoom gameRoom = new GameRoom();
    
    gameRoom.play();
    while (gameRoom.gameStart) {
    }
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

  public void displayGameRoom(int activeUser, User[] users) {
    for (int i = 0; i < 4; i++) {
      users[i].sortHand();
      System.out.print("User " + i + ":");
      if (i == activeUser) {
        users[i].showHand();
      } else {
        users[i].showHandHidden();
      }
    }
  }

  public void play() {
    User[] users = new User[4];
    for (int i = 0; i < 4; i++){
      users[i] = new User();
    }

    distributeCards(users);

    int activeUser = findGameStarter(users);
    displayGameRoom(activeUser, users);

  }

}
