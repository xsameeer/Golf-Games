package Projects.Project5;
/*************
 *Program name: Golf Games
 *Program Description: A program to help Golfing Emporium analyze their clients’ putt-putt golf games and display the winner, average score for a hole
 * and the names of the players that are below the average for the hole.
 --------------------------------------------------------------------------------------------
 *Name: Sameer Ali
 *Version date: 04/06/2023
 *CMSC 255 901
 ***************/

public class GolfGames {
    public static String[] getNames(String inputNameString) {
        return inputNameString.split(",");
    }

    public static int[][] getScores(String inputScoresString) {
        String []golfHoles = inputScoresString.split("<>");
        int holeCount = golfHoles.length;
        int playerCount = golfHoles[0].split(",").length;
        int scores[][] = new int[holeCount][playerCount];

        for(int i = 0; i < holeCount; i++)
        {
            String[] currentHole = golfHoles[i].split(",");
            for(int j = 0; j < currentHole.length; j++)
            {
                scores[i][j] = Integer.parseInt(currentHole[j]);
            }
        }
        return scores;
    }


    public static String findWinner(String[] names, int [][]scores) {

        String winner = "";
        int minScore=0;
        for(int j = 0; j < names.length; j++)
        {
            int currentScore = 0;
            for(int i = 0; i < scores.length; i++)
            {
                currentScore += scores[i][j];
            }

            if(j == 0)
            {
                minScore = currentScore;
                winner = names[j];
            }
            else
            {
                if(currentScore < minScore)
                {
                    minScore = currentScore;
                    winner = names[j];
                }
            }
        }
        return winner;
    }

    public static double findAvgForHole(int [][]scores, int holeNum)
    {
        double holeScoreSum = 0;
        int totalPlayers = scores[holeNum-1].length;
        for(int i = 0; i < totalPlayers; i++)
        {
            holeScoreSum += scores[holeNum-1][i];
        }
        return holeScoreSum/totalPlayers;
    }

    public static String[] searchPlayersBelowAvg(String[] names, int [][]scores, int holeNum,double avgForHole) {
        String[] tempRes = new String[names.length];
        int playerIdx = 0;
        for(int i = 0; i < names.length; i++)
        {
            if(scores[holeNum-1][i] < avgForHole)
            {
                tempRes[playerIdx++] = names[i];
            }
        }
        String[] result = new String[playerIdx];
        for(int i = 0; i < playerIdx; i++)
        {
            result[i] = tempRes[i];
        }
        return result;
    }
    public static void main(String[] args) {

        String[] names = getNames(args[0]);
        int[][] scores = getScores(args[1]);
        int holeNum = 6;

        String winner = findWinner(names, scores);
        double holeAvg = findAvgForHole(scores, holeNum);
        String []playersBelowAvg = searchPlayersBelowAvg(names,scores,holeNum,holeAvg);

        System.out.println("The winner is: " + winner);
        System.out.println("The average for Hole " + holeNum + " is: " + holeAvg);
        System.out.print("The players below the average for Hole " + holeNum + " are: ");
        for(int i = 0; i < playersBelowAvg.length; i++)
        {
            System.out.print(playersBelowAvg[i]);
            if(i == playersBelowAvg.length - 1)
            {
                System.out.println();
            }
            else
            {
                System.out.print(" ");
            }
        }
    }
}
