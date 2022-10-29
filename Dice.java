import java.util.ArrayList;
import java.util.Scanner;

class Dice {
  public static ArrayList<Integer[]> scoreArray = new ArrayList<Integer[]>();
  public static float averageScore;
  public static int roundsPlayed = 0;

  public static int[] getScoreListAsArray() {
    int[] scores = new int[scoreArray.size() * 2];
    int scoreIndex = 0;
    for (int i = 0; i < scoreArray.size(); i++) {
      Integer[] tmp = scoreArray.get(i);
      scores[scoreIndex++] = tmp[0];
      scores[scoreIndex++] = tmp[1];
    }
    return scores;
  }

  public static ArrayList<Integer[]> getScoreList() {
    return scoreArray;
  }

  public static float getAverageScore() {
    return averageScore;
  }

  public static int getRoundsPlayed() {
    return roundsPlayed;
  }

  public static float calculateRoundScore(int score, int bonus) {
    float totalScore = bonus % 2 == 0 ? score * bonus : score / bonus;
    return totalScore;
  }

  public static void calculateAverageScore(int totalScore) {
    averageScore = totalScore / roundsPlayed;
  }

  public static int calculateTotalScore() {
    int totalScore = 0;
    for (int i = 0; i < scoreArray.size(); i++) {
      Integer[] round = scoreArray.get(i);
      totalScore += calculateRoundScore(round[0], round[1]);
    }
    return totalScore;
  }

  public static void incrementRound() {
    roundsPlayed++;
  }

  public static void main(String args[]) {
    System.out.println("Welcome to a game of dice!");
    Scanner s = new Scanner(System.in);
    boolean playing = true;

    while (playing) {
      System.out.print("Enter your score: ");
      int score = s.nextInt();
      System.out.print("Enter your bonus: ");
      int bonus = s.nextInt();

      scoreArray.add(new Integer[] { score, bonus });
      incrementRound();

      System.out.printf("----- End of round %d results -----\n", roundsPlayed);
      System.out.printf("Round score: %.2f\n", calculateRoundScore(score, bonus));
      int totalScore = calculateTotalScore();
      calculateAverageScore(totalScore);
      System.out.printf("Total score: %d\n", totalScore);
      System.out.printf("Final Score after round %d: %.2f\n", roundsPlayed, averageScore);
      System.out.println("----------------------------------");

      System.out.print("Would you like to keep playing? (yes/n)");
      String answer = s.next();

      if (answer.matches("no|n|No|N|NO")) {
        playing = false;
      }

    }

    System.out.printf("Final score: %.2f\n", averageScore);

    System.out.println("Thanks for playing!");

    s.close();
  }
}