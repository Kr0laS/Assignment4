public interface Matrix {
    // Assumes that i and j are valid indices of the matrix
    // rows and columns, respectively.
    // Returns the value in cell ('i', 'j')
    public double get(int i, int j);

    // Assumes that i and j are valid indices of the matrix
    // rows and columns, respectively.
    // Changes the value of the ('i', 'j') cell to 'value'
    public void set(int i, int j, double value);

    // Returns the number of rows of the matrix
    public int getNumRows();

    // Returns the number of rows of the matrix
    public int getNumCols();

    // Returns true if the matrix is square
    public Boolean isSquare();

    // Creates a deep copy of the matrix.
    // (a) the new matrix has the same numbers of
    // rows and columns as the creating matrix.
    // (b) The values in the corresponding cells are
    // the same.
    // (c) Changing a value in one matrix does not change the
    // other.
    public Matrix copy();

    // Creates a new matrix, which is the transpose of the
    // creating matrix.
    public Matrix transpose();

}
