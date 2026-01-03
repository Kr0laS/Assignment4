
import java.util.Comparator;

// Task 1.4
public class IndexComparator implements Comparator<Integer> {

    double[] array;

    // Assumes: 'm' is not null and that 'row' is a valid row index in 'm'
    public IndexComparator(Matrix m, int row) {
        // ---------------write your code BELOW this line only! ------------------
        if (m == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        if (row < 0 || row >= m.getNumRows()) {
            throw new IllegalArgumentException("Invalid row index");
        }

        this.array = new double[m.getNumCols()];

        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = m.get(row, i);
        }
        // ---------------write your code ABOVE this line only! ------------------
    }


    // Assumes: 'idx1' and 'idx2' are legal indices for the row in Matrix
    public int compare(Integer idx1, Integer idx2) {
        int ans = 0;
        // ---------------write your code BELOW this line only! ------------------
        if (idx1 == null || idx2 == null ||
                idx1 < 0 || idx1 >= array.length ||
                idx2 < 0 || idx2 >= array.length) {
            throw new IllegalArgumentException("Indices must be valid row indices.");
        }

        double left = this.array[idx1];
        double right = this.array[idx2];

        if (left < right) {
            ans = -1;
        } else if (left > right) {
            ans = 1;
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }
}
