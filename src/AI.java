import java.util.ArrayList;

public class AI {
    public AI() {
    }

    public double minimax(Triplet triplet, boolean isPlayersTurn) {
        // base case
        if (triplet.isGameOver()) {
            if (!isPlayersTurn) { // last person to take coin was player -> player wins
                return 1;
            }
            else { // last person to take coin was AI -> AI wins
                return 0;
            }
        }

        ArrayList<Move> allMoves = triplet.getAllMoves();

        // recursion
        if (isPlayersTurn) {
            double runningTotal = 0;
            for (Move move : allMoves) {
                Triplet modifiedTriplet = new Triplet(triplet, move);
                runningTotal += minimax(modifiedTriplet, false);
            }
            return runningTotal/allMoves.size();
        }
        else {
            double minEval = Integer.MAX_VALUE;
            for (Move move : allMoves) {
                Triplet modifiedTriplet = new Triplet(triplet, move);
                double eval = minimax(modifiedTriplet, true);
                minEval = Math.min(eval, minEval);
            }
            return minEval;
        }
    }
}



