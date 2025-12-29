
public class SimpleMatrix implements Matrix {

    private int rows;
    private int cols;
    private double[][] mat;
	
	// Task 3.1
    // 'rows' > 0, 'cols' > 0
    // Constructs a SimpleMatrix with the given rows and cols, with zeros in all cells
    public SimpleMatrix(int rows, int cols) {
        // ---------------write your code BELOW this line only! ------------------
        if(rows<=0 || cols<=0)
            throw new IllegalArgumentException("rows and/or cols must be positive");
        mat = new  double[rows][cols];
        this.rows = rows;
        this.cols = cols;
        // ---------------write your code ABOVE this line only! ------------------
    }
	
	// Task 3.2
    // 'arr' is not null or empty
    // Constructs a SimpleMatrix with the values of 'arr'
    public SimpleMatrix(double[][] arr) {
        // ---------------write your code BELOW this line only! ------------------
        rows = arr.length;
        cols = arr[0].length;
        mat = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = arr[i][j];
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
    }

	// Task 3.3
    // 'mat' is not null
    // Constructs a SimpleMatrix with the values of 'mat'
    public SimpleMatrix(Matrix mat) {
        // ---------------write your code BELOW this line only! ------------------
        this.rows = mat.getNumRows();
        this.cols = mat.getNumCols();
        this.mat = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.mat[i][j] = mat.get(i+1,j+1);
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
    }

    // Task 3.4
    // 'row' and 'col' are legal indices in the matrix
    // Returns the value in the corresponding row and col in the matrix
    public double get(int row, int col) {
		double ans = 0;
		// ---------------write your code BELOW this line only! ------------------
        ans = mat[row - 1][col - 1];
        // ---------------write your code ABOVE this line only! ------------------
		return ans;
    }

    // Task 3.5
    // 'row' and 'col' are legal indices in the matrix
    // Updates the value in the corresponding row and col in the matrix to be 'value'
    public void set(int row, int col, double value) {
        // ---------------write your code BELOW this line only! ------------------
        mat[row - 1][col - 1] = value;
        // ---------------write your code ABOVE this line only! ------------------
    }
	
	// Task 3.6
    // Returns the transposed matrix
    public Matrix transpose() {
        SimpleMatrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        double[][] newMat = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newMat[j][i] = mat[i][j];
            }
        }

        ans = new SimpleMatrix(newMat);
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }
	
    public int getNumRows() {
        return rows;
    }

    public int getNumCols() {
        return cols;
    }

    public Boolean isSquare() {
        return rows == cols;
    }

    public Matrix copy() {
        return new SimpleMatrix(this);
    }

	//Task 3.7
    public String toString() {
        String s = "";
        // ---------------write your code BELOW this line only! ------------------
        s = "SimpleMatrix " + rows + " X " + cols;
        // ---------------write your code ABOVE this line only! ------------------
        return s;
    }
	
	//Task 3.8	
	public boolean equals(Object other) {
        boolean ans = false;
        // ---------------write your code BELOW this line only! ------------------
        if(other instanceof Matrix mtx)
        {
            ans = true;

            if(rows != mtx.getNumRows())
                ans = false;

            if(cols != mtx.getNumCols())
                ans = false;

            for (int i = 0; i < rows && ans ; i++) {
                for (int j = 0; j < cols && ans ; j++) {
                    if(mtx.get(i + 1,j + 1) != this.get(i + 1,j + 1))
                        ans = false;
                }
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }
	
}
