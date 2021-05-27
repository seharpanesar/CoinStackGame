import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main test = new Main();
        Triplet triplet = new Triplet(3, 3, 3);
        System.out.println();
        do {
            test.userInput(triplet);
            if (triplet.isGameOver()) {
                System.out.println("You win!");
                break;
            }
            test.AITurn(triplet);
            if (triplet.isGameOver()) {
                System.out.println("AI wins!");
            }
        } while (!triplet.isGameOver());
        System.out.println("Game over");
    }

    public void AITurn(Triplet triplet) {
        long start = System.currentTimeMillis();
        AI ai = new AI();
        ArrayList<Move> allMoves = triplet.getAllMoves();
        double minEval = Integer.MAX_VALUE;
        Move bestMove = null;
        for (Move move : allMoves) {
            Triplet resultingTriplet = new Triplet(triplet, move);
            double eval = ai.minimax(resultingTriplet, true);
            if (eval < minEval) {
                minEval = eval;
                bestMove = move;
            }
        }

        assert  bestMove != null;
        triplet.subtract(bestMove.pileNum, bestMove.numCoinSubtracted);

        System.out.printf("AI took away %d coin(s) from pile number %d\n",
                bestMove.numCoinSubtracted, bestMove.pileNum);

        //TODO: start here and play AI best move
        long end = System.currentTimeMillis();

        System.out.printf("done in %d ms\n", end - start);

    }

    public void userInput(Triplet triplet) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Pile 1: %d coins\nPile 2: %d coins\nPile 3: %d coins\n",
                triplet.getFirstVal(), triplet.getSecondVal(), triplet.getThirdVal());
        int pileNum;
        boolean redoFlag;
        do {
            redoFlag = false;
            System.out.println("Which pile would you like to take from?");
            pileNum = Integer.parseInt(scanner.nextLine());
            if (!(pileNum == 1 || pileNum == 2 || pileNum == 3)) {
                System.out.println("Not a valid pile");
                redoFlag = true;
            }
            if (triplet.pileIsEmpty(pileNum)) {
                System.out.printf("Pile %d is empty\n", pileNum);
                redoFlag = true;
            }
        } while(redoFlag);

        int numCoinSubtracted;

        do {
            System.out.println("How many coins would you like to take?");
            numCoinSubtracted = Integer.parseInt(scanner.nextLine());
            if (triplet.getArray()[pileNum - 1] < numCoinSubtracted) {
                System.out.println("That is too many coins");
                redoFlag = true;
            } else {
                triplet.subtract(pileNum, numCoinSubtracted);
                redoFlag = false;
            }
        } while(redoFlag);
        System.out.println();
    }


}
