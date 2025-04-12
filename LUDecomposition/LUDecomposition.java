package LUDecomposition;
public class LUDecomposition {
    public static void main(String[] args) {
        // Set up the matrix A and vector b
        double[][] A = {
            {1, 1, 1},
            {4, 3, -1},
            {3, 5, 3}
        };
        double[] b = {1, 6, 4};
        int n = 3;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        double[] y = new double[n];
        double[] x = new double[n];

        // Step 1: LU Decomposition
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                U[i][j] = A[i][j];
                for (int k = 0; k < i; k++) {
                    U[i][j] -= L[i][k] * U[k][j];
                }
            }
            for (int j = i; j < n; j++) {
                if (i == j) {
                    L[i][j] = 1;
                } else {
                    L[j][i] = A[j][i];
                    for (int k = 0; k < i; k++) {
                        L[j][i] -= L[j][k] * U[k][i];
                    }
                    L[j][i] /= U[i][i];
                }
            }
        }

        // Step 2: Forward Substitution (Ly = b)
        for (int i = 0; i < n; i++) {
            y[i] = b[i];
            for (int j = 0; j < i; j++) {
                y[i] -= L[i][j] * y[j];
            }
        }

        // Step 3: Backward Substitution (Ux = y)
        for (int i = n-1; i >= 0; i--) {
            x[i] = y[i];
            for (int j = i+1; j < n; j++) {
                x[i] -= U[i][j] * x[j];
            }
            x[i] /= U[i][i];
        }

        // Print results
        System.out.println("L matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(L[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\nU matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(U[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\nAnswer: x = " + x[0] + ", y = " + x[1] + ", z = " + x[2]);
    }
}