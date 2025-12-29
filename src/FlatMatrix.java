
// Task 2
public class FlatMatrix implements Matrix {

    private double[] mat;
    private int rows;
    private int cols;


    // Assumes: 'rows' > 0, 'cols' > 0
    // Constructs a FlatMatrix with the given rows and cols, with zeros in all cells
    public FlatMatrix(int rows, int cols) {
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
    }


    // Assumes: 'mat' be a matrix of nxm
    // Constructs a FlatMatrix with the same values as 'mat'
    public FlatMatrix(Matrix mat) {
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
    }


    // Assumes: 'mat' is not null
    // Constructs a FlatMatrix with the same values as 'mat'
    public FlatMatrix(double[][] mat) {
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
    }

    // Assumes that i and j are valid indices of the matrix
    // rows and columns, respectively.
    // Returns the value in cell ('i', 'j')
    public double get(int i, int j) {
        double ans = 0;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Assumes that i and j are valid indices of the matrix
    // rows and columns, respectively.
    // Changes the value of the ('i', 'j') cell to 'value'
    public void set(int i, int j, double value) {
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
    }

    // Returns the number of rows of the matrix
    public int getNumRows() {
        int ans = 0;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Returns the number of columns of the matrix
    public int getNumCols() {
        int ans = 0;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Returns true if the matrix is square
    public Boolean isSquare() {
        boolean ans = false;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Creates a deep copy of the matrix.
    // (a) the new matrix has the same numbers of
    // rows and columns as the creating matrix.
    // (b) The values in the corresponding cells are
    // the same.
    // (c) Changing a value in one matrix does not change the
    // other.
    public Matrix copy() {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Creates a new matrix, which is the transpose of the
    // creating matrix.
    public Matrix transpose() {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Returns if 'other' equals the FlatMatrix
    public boolean equals(Object other) {
        boolean ans = false;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // returns the string representation of the matrix
    public String toString() {
        String ans = "";
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }
}
