
import java.util.Comparator;

// Task 1
public class MatrixOperations {

    // Task 1.1
    // Assumes: 'm' not null and from and to legal indices
    // Assumes: from < to
    // Returns: FlatMatrix starting from row 'from' to row 'to'
    public static Matrix subMatrix(Matrix mat, int from, int to) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        int rows = mat.getNumRows();
        int cols = mat.getNumCols();

        if(mat == null)
            throw new IllegalArgumentException("matrix is null");
        if(from < 0 || from >= to || to > rows)
            throw new IllegalArgumentException("from and to matrix out of bounds");

        SimpleMatrix newMat = new SimpleMatrix(to - from, cols);
        for(int i = from; i <= to; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                double source = mat.get(i + 1,j + 1);
                newMat.set(i - from + 1, j, source);
            }
        }

        ans = newMat;
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.2
    // Assumes: 'm' not null
    // Returns: the normalized matrix
    public static Matrix normalize(Matrix m) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if (m == null)
            throw new IllegalArgumentException("Matrix cannot be null.");
        if (m.getNumRows() == 0)
            throw new IllegalArgumentException("Matrix must contain at least one row.");

        ans = m.copy();

        int rows = m.getNumRows();
        int cols = m.getNumCols();

        for(int j = 0; j < cols; j++)
        {
            double sum = 0;
            for(int i = 0; i < rows; i++) {
                sum = sum + ans.get(i + 1, j + 1);
            }
            double mean = sum / rows;

            double deviationSum = 0;
            for(int i = 0; i < rows; i++) {
                double temp = mean - ans.get(i + 1, j + 1);
                deviationSum = deviationSum + temp * temp;
            }
            double deviation = Math.sqrt(deviationSum / rows);

            if (deviation == 0) {
                for(int i = 0; i < rows; i++) {
                    ans.set(i + 1, j + 1, 0);
                }
            } else {
                for(int i = 0; i < rows; i++) {
                    double cur = ans.get(i + 1, j + 1);
                    ans.set(i, j, (cur - mean) / deviation);
                }
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.3
    // Assumes: 'm' is not null
    // Returns: the square distance matrix of 'mat'
    public static Matrix squareDistance(Matrix m) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if (m == null) {
            throw new IllegalArgumentException("Matrix cannot be null.");
        }

        int n = m.getNumRows();

        Matrix Ft = m.transpose();
        Matrix FFt = multiply(m, Ft);

        Matrix diagnolFFt = diagonal(FFt);

        Matrix oMatrix = CreateOMatrix(n);

        Matrix left = multiply(oMatrix, diagnolFFt);
        Matrix right = multiply(diagnolFFt, oMatrix);

        ans = add(left, right);

        ans = sub(ans, FFt);
        ans = sub(ans, FFt);
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    public static Matrix CreateOMatrix(int n)
    {
        Matrix oMatrix = new SimpleMatrix(n, n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                oMatrix.set(i + 1, j + 1, 1);

        return oMatrix;
    }


    // Assumes: 'mat1' and 'mat2' be able to multiply
    // Returns: the product of 'mat1' and 'mat2'
    public static Matrix multiply(Matrix mat1, Matrix mat2) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if (mat1 == null || mat2 == null || mat1.getNumCols() != mat2.getNumRows())
            throw new IllegalArgumentException();

        ans = new SimpleMatrix(mat1.getNumRows(), mat2.getNumCols());

        for (int i = 0; i < mat1.getNumRows(); i++) {
            for (int j = 0; j < mat2.getNumCols(); j++) {
                double sum = 0;
                for (int k = 0; k < mat1.getNumCols(); k++) {
                    sum = sum + mat1.get(i + 1, k + 1) * mat2.get(k + 1, j + 1);
                }
                ans.set(i + 1, j + 1, sum);
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }


    // Task 1.3A
    // Assumes: 'mat' not null
    // Returns: the diagonal matrix
    public static Matrix diagonal(Matrix m) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if(m == null) throw new IllegalArgumentException("Matrix cannot be null.");
        int rows = m.getNumRows();
        int cols = m.getNumCols();
        if(rows != cols) throw new IllegalArgumentException("Matrix must be square.");

        ans = new SimpleMatrix(rows, cols);
        for(int i = 0; i < rows; i++) {
            ans.set(i + 1,i + 1, m.get(i + 1,i + 1));
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.3B
    // Assumes: 'm1' and 'm2' be able to add
    // Returns: the sum of 'mat1' and 'mat2'
    public static Matrix add(Matrix m1, Matrix m2) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        int rows = m1.getNumRows();
        int cols = m1.getNumCols();

        ans = new SimpleMatrix(rows, cols);

        for(int i = 0; i < rows ; i++) {
            for(int j = 0; j < cols; j++) {
                double aij = m1.get(i + 1, j + 1) + m2.get(i + 1, j + 1);
                ans.set(i + 1, j + 1, aij);
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.3C
    // Assumes: 'm1' and 'm2' be able to subtract
    // Returns: the subtraction of 'mat1' and 'mat2'
    public static Matrix sub(Matrix m1, Matrix m2) {
        Matrix ans = null;
        // ---------------write your code BELOW this line only! ------------------
        if (m1 == null || m2 == null)
            throw new IllegalArgumentException("Matrix cannot be null.");

        int rows = m1.getNumRows();
        int cols = m1.getNumCols();

        if(rows != m2.getNumCols() || cols != m2.getNumCols())
            throw new IllegalArgumentException("Matrices must have same rows and columns.");
        ans = new SimpleMatrix(rows, cols);

        for(int i = 0; i < rows ; i++) {
            for(int j = 0; j < cols; j++) {
                double aij = m1.get(i + 1, j + 1) - m2.get(i + 1, j + 1);
                ans.set(i + 1, j + 1, aij);
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.4
    // Assumes: 'indices' is an index array
    // returns the sorted indexes of indices based on comp
    public static int[] selectionSort(int[] array, Comparator<Integer> comp) {
        int[] ans = null;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.5
    // Assumes: 'trainingFeatures', trainingLabels', 'validationFeatures' npt be null
    // Assumes: compatibility between 'trainingFeatures' and 'trainingLabels'
    // Returns: the validation labels of the 'validationFeatures'
    public static boolean[] testClasses(Matrix trainingFeatures, boolean[] trainingClasses, Matrix testFeatures, int k) {
        boolean[] ans = null;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.6
    // Assumes: 'testLabels' and 'trueLables' are arrays of same length
    // Returns: the accuracy of the test
    public static double measureAccuracy(boolean[] testLabels, boolean[] trueLabels) {
        double ans = 0;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }


}
