
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

        FlatMatrix newMat = new FlatMatrix(to - from, cols);
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
        int cols = m.getNumCols();

        for (int j = 0; j < cols; j++) {
            double mean = calculateMean(ans, j);
            double std  = calculateStd(ans, j, mean);
            normalizeColumn(ans, j, mean, std);
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    private static double calculateMean(Matrix m, int col) {
        int rows = m.getNumRows();
        double sum = 0;

        for (int i = 0; i < rows; i++) {
            sum += m.get(i + 1, col + 1);
        }

        return sum / rows;
    }

    private static double calculateStd(Matrix m, int col, double mean) {
        int rows = m.getNumRows();
        double deviationSum = 0;

        for (int i = 0; i < rows; i++) {
            double diff = mean - m.get(i + 1, col + 1);
            deviationSum += diff * diff;
        }

        return Math.sqrt(deviationSum / rows);
    }

    private static void normalizeColumn(Matrix matrix, int col, double mean, double std) {
        int rows = matrix.getNumRows();

        if (std == 0) {
            for (int i = 0; i < rows; i++) {
                matrix.set(i + 1, col + 1, 0);
            }
        } else {
            for (int i = 0; i < rows; i++) {
                double cur = matrix.get(i + 1, col + 1);
                matrix.set(i + 1, col + 1, (cur - mean) / std);
            }
        }
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
        Matrix oMatrix = new FlatMatrix(n, n);

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

        ans = new FlatMatrix(mat1.getNumRows(), mat2.getNumCols());

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

        ans = new FlatMatrix(rows, cols);
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

        ans = new FlatMatrix(rows, cols);

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
        ans = new FlatMatrix(rows, cols);

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
        if (array == null || comp == null) {
            throw new IllegalArgumentException();
        }
        ans = array.clone();

        for (int i = 0; i < ans.length - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < ans.length; j++) {
                if (comp.compare(ans[j], ans[minIdx]) < 0) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                int temp = ans[minIdx];
                ans[minIdx] = ans[i];
                ans[i] = temp;
            }
        }
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


        if (trainingFeatures == null || trainingClasses == null || testFeatures == null) {
            throw new IllegalArgumentException("Null Arguments");
        }
        int trainingRows = trainingFeatures.getNumRows();
        int testRows = testFeatures.getNumRows();
        if (trainingFeatures.getNumCols() != testFeatures.getNumCols()) {
            throw new IllegalArgumentException("Both Matrices should have the same amount of Columns");
        }
        if (trainingFeatures.getNumRows() != trainingClasses.length){
            throw new IllegalArgumentException("trainingFeatures rows != trainingClasses.length");
        }
        if (k < 1 || k > trainingFeatures.getNumRows()) {
            throw new IllegalArgumentException("k value cannot be " + k);
        }

        Matrix normalizedTrainingFeatures = normalize(trainingFeatures);
        Matrix normalizedTestFeatures = normalizeUsingTraining(trainingFeatures, testFeatures);

        Matrix concatinatedMatrix = concatMatrices(normalizedTrainingFeatures, normalizedTestFeatures);

        Matrix distMatrix = squareDistance(concatinatedMatrix);

        ans = new boolean[testRows];

        for (int i = 0; i < testFeatures.getNumRows(); i++) {
            int testIndex = trainingRows + i;

            int[] indices = new int[trainingRows];
            for (int x = 0; x < trainingRows; x++)
                indices[x] = x;

            Comparator<Integer> comp = new IndexComparator(distMatrix, testIndex);

            int[] sorted = selectionSort(indices, comp);

            int countTrue = 0;
            for (int x = 0; x < k; x++) {
                if (trainingClasses[sorted[x]])
                    countTrue++;
            }

            ans[i] = (countTrue > k / 2);
        }

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    private static Matrix normalizeUsingTraining(Matrix train, Matrix destMat)
    {
        if (train == null || destMat == null)
            throw new IllegalArgumentException();

        if (train.getNumCols() != destMat.getNumCols())
            throw new IllegalArgumentException();

        if (train.getNumRows() == 0)
            throw new IllegalArgumentException();

        Matrix ans = destMat.copy();
        int cols = train.getNumCols();

        for (int j = 0; j < cols; j++) {
            double mean = calculateMean(train, j);
            double std  = calculateStd(train, j, mean);
            normalizeColumn(ans, j, mean, std);
        }

        return ans;
    }

    private static Matrix concatMatrices(Matrix mat1, Matrix mat2)
    {
        int totalRows = mat1.getNumRows() + mat2.getNumRows();
        int cols = mat1.getNumCols();

        Matrix concatinatedMatrix = new FlatMatrix(totalRows, mat2.getNumCols());

        for (int i = 0; i < mat1.getNumRows(); i++) {
            for (int j = 0; j < cols; j++) {
                double val = mat1.get(i + 1, j + 1);
                concatinatedMatrix.set(i + 1, j + 1, val);
            }
        }

        for (int i = 0; i < mat2.getNumRows(); i++) {
            for (int j = 0; j < cols; j++) {
                double val = mat2.get(i + 1, j + 1);

                concatinatedMatrix.set(mat1.getNumRows() + i + 1, j + 1, val);
            }
        }

        return concatinatedMatrix;
    }

    // Task 1.6
    // Assumes: 'testLabels' and 'trueLables' are arrays of same length
    // Returns: the accuracy of the test
    public static double measureAccuracy(boolean[] testLabels, boolean[] trueLabels) {
        double ans = 0;
        // ---------------write your code BELOW this line only! ------------------
        double trueCounter = 0;
        double total = testLabels.length;

        for (int i = 0; i < testLabels.length; i++) {
            if(testLabels[i] == trueLabels[i])
                trueCounter = trueCounter + 1;
        }

        ans = trueCounter / total;
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }
}
