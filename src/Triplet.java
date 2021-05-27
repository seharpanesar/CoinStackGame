import java.util.ArrayList;

public class Triplet {
    private int firstVal;
    private int secondVal;
    private int thirdVal;

    public Triplet(int firstVal, int secondVal, int thirdVal) {
        this.firstVal = firstVal;
        this.secondVal = secondVal;
        this.thirdVal = thirdVal;
    }

    public Triplet(Triplet triplet, Move proposedMove) {
        this.firstVal = triplet.firstVal;
        this.secondVal = triplet.secondVal;
        this.thirdVal = triplet.thirdVal;
        subtract(proposedMove.pileNum, proposedMove.numCoinSubtracted);
    }

    public void subtract(int pileNum, int valueSubtracted) {
        switch (pileNum) {
            case 1 -> firstVal -= valueSubtracted;
            case 2 -> secondVal -= valueSubtracted;
            case 3 -> thirdVal -= valueSubtracted;
        }
    }

    public int[] getArray() {
        return new int[]{firstVal, secondVal, thirdVal};
    }

    public int getFirstVal() {
        return firstVal;
    }

    public int getSecondVal() {
        return secondVal;
    }

    public int getThirdVal() {
        return thirdVal;
    }

    public boolean isGameOver() {
        return firstVal == 0 && secondVal == 0 && thirdVal == 0;
    }

    public ArrayList<Move> getAllMoves() {
        ArrayList<Move> allMoves = new ArrayList<>();
        ArrayList<Triplet> possibleTriplets = new ArrayList<>();
        for (int i = 1; i <= firstVal; i++) {
            Move proposedMove = new Move(1, i);
            Triplet resultingTriplet = new Triplet(this, proposedMove);
            resultingTriplet.sort(); // sort the triplet so it is easy to compare when searching for duplicates
            possibleTriplets.add(resultingTriplet); //building a collection of possible triplets so that we can identify duplicates
            allMoves.add(proposedMove);
        }
        for (int i = 1; i <= secondVal; i++) {
            Move proposedMove = new Move(2, i);
            Triplet resultingTriplet = new Triplet(this, proposedMove);
            resultingTriplet.sort();
            if (!isDuplicate(resultingTriplet, possibleTriplets)) {
                possibleTriplets.add(resultingTriplet);
                allMoves.add(proposedMove);
            }

        }
        for (int i = 1; i <= thirdVal; i++) {
            Move proposedMove = new Move(3, i);
            Triplet resultingTriplet = new Triplet(this, proposedMove);
            resultingTriplet.sort();
            if (!isDuplicate(resultingTriplet, possibleTriplets)) {
                possibleTriplets.add(resultingTriplet);
                allMoves.add(proposedMove);
            }
        }
        return allMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Triplet triplet = (Triplet) o;
        return firstVal == triplet.firstVal &&
                secondVal == triplet.secondVal &&
                thirdVal == triplet.thirdVal;
    }

    private boolean isDuplicate(Triplet resultingTriplet, ArrayList<Triplet> possibleTriplets) {
        for (Triplet triplet : possibleTriplets) {
            if (resultingTriplet.equals(triplet)) {
                return true;
            }
        }
        return false;
    }

    /* Sorts the triplets in increasing order
     */

    private void sort() {
        int[] array = getArray();
        // basic bubble sort on Array
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        firstVal = array[0];
        secondVal = array[1];
        thirdVal = array[2];
    }

    public boolean pileIsEmpty(int pileNum) {
        return switch (pileNum) {
            case 1 -> firstVal == 0;
            case 2 -> secondVal == 0;
            case 3 -> thirdVal == 0;
            default -> false;
        };
    }
}
