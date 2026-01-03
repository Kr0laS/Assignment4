
// Task 2
public class FlatMatrix implements Matrix {

    private double[] mat;
    private int rows;
    private int cols;


    // Assumes: 'rows' > 0, 'cols' > 0
    // Constructs a FlatMatrix with the given rows and cols, with zeros in all cells
    public FlatMatrix(int rows, int cols) {
        // ---------------write your code BELOW this line only! ------------------
        if(rows < 1 || cols < 1)
            throw new IllegalArgumentException("Rows/Cols value is not valid");

        this.rows = rows;
        this.cols = cols;

        mat = new double[rows*cols];
        // ---------------write your code ABOVE this line only! ------------------
    }


    // Assumes: 'mat' be a matrix of nxm
    // Constructs a FlatMatrix with the same values as 'mat'
    public FlatMatrix(Matrix mat) {
        // ---------------write your code BELOW this line only! ------------------
        if (mat == null)
            throw new IllegalArgumentException("null matrix");

        this.rows = mat.getNumRows();
        this.cols = mat.getNumCols();

        if(rows < 1 || cols < 1)
            throw new IllegalArgumentException("Rows/Cols value is not valid");

        this.mat = new double[rows*cols];

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols ;j++) {
                this.mat[index] = mat.get(i + 1, j + 1);
                index = index + 1;
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
    }


    // Assumes: 'mat' is not null
    // Constructs a FlatMatrix with the same values as 'mat'
    public FlatMatrix(double[][] mat) {
        // ---------------write your code BELOW this line only! ------------------
        if (mat == null)
            throw new IllegalArgumentException("null argument");

        if (mat.length == 0 || mat[0].length == 0)
            throw new IllegalArgumentException("invalid matrix");

        this.rows = mat.length;
        this.cols = mat[0].length;

        for (int i = 0; i < rows - 1; i++)
            if(mat[i] != mat[i + 1]) throw new IllegalArgumentException("Matrix must be rectangular");

        this.mat = new double[rows*cols];

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.mat[index++] = mat[i][j];
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
    }

    // Assumes that i and j are valid indices of the matrix
    // rows and columns, respectively.
    // Returns the value in cell ('i', 'j')
    public double get(int i, int j) {
        double ans = 0;
        // ---------------write your code BELOW this line only! ------------------
        if(i < 1 || j < 1 || j > cols || i > rows)
            throw new IllegalArgumentException("i/j must be more then 0 and less then rows/cols number.");

        ans = mat[cols * (i - 1) + (j - 1)];
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Assumes that i and j are valid indices of the matrix
    // rows and columns, respectively.
    // Changes the value of the ('i', 'j') cell to 'value'
    public void set(int i, int j, double value) {
        // ---------------write your code BELOW this line only! ------------------
        if(i < 1 || j < 1 || j > cols || i > rows)
            throw new IllegalArgumentException("i/j must be more then 0 and less then rows/cols number.");

        this.mat[cols * (i - 1) + (j - 1)] = value;

        // ---------------write your code ABOVE this line only! ------------------
    }

    // Returns the number of rows of the matrix
    public int getNumRows() {
        int ans = 0;
        // ---------------write your code BELOW this line only! ------------------
        ans = rows;
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Returns the number of columns of the matrix
    public int getNumCols() {
        int ans = 0;
        // ---------------write your code BELOW this line only! ------------------
        ans = cols;
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Returns true if the matrix is square
    public Boolean isSquare() {
        boolean ans = false;
        // ---------------write your code BELOW this line only! ------------------
        ans = rows == cols;
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
        ans = new FlatMatrix(this);
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Creates a new matrix, which is the transpose of the
    // creating matrix.
    public Matrix transpose() {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double aij = this.get(i + 1, j + 1);
                double aji = this.get(j + 1, i + 1);
                this.set(j+1,i+1,aij);
                this.set(i+1,j+1,aji);
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Returns if 'other' equals the FlatMatrix
    public boolean equals(Object other) {
        boolean ans = false;
        // ---------------write your code BELOW this line only! ------------------
        if(other instanceof Matrix otherMat)
        {
            ans = true;
            if(otherMat.getNumRows() != this.rows && otherMat.getNumCols() != this.cols) {
                ans = false;
            } else {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if(this.get(i + 1, j  + 1) != otherMat.get(i + 1, j + 1))
                            ans = false;
                    }
                }
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // returns the string representation of the matrix
    public String toString() {
        String ans = "";
        // ---------------write your code BELOW this line only! ------------------
        ans = "Flat Matrix " + rows + " X " + cols;
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }
}
