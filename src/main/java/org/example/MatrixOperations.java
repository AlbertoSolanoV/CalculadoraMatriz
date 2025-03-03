package org.example;

public class MatrixOperations {


    public static void sumMatrix(int[][] matrizA, int[][] matrizB) {
        int filas = matrizA.length;
        int columnas = matrizA[0].length;
        int[][] matrizSuma = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizSuma[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }

        System.out.println("\nResultado de la Suma:");
        MatrixUtils.printMatrix(matrizSuma);
    }

    public static void subMatrix(int[][] matrizA, int[][] matrizB) {
        int filas = matrizA.length;
        int columnas = matrizA[0].length;
        int[][] matrizResta = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizResta[i][j] = matrizA[i][j] - matrizB[i][j];
            }
        }

        System.out.println("\nResultado de la Resta:");
        MatrixUtils.printMatrix(matrizResta);
    }

    public static void multiplyMatrix(int[][] A, int[][] B) {
        int row1 = A.length, col1 = A[0].length;
        int row2 = B.length, col2 = B[0].length;

        if (col1 != row2) {
            System.out.println("\nNo se puede realizar la multiplicación. El número de columnas de A debe ser igual al número de filas de B.");
            return;
        }

        int[][] C = new int[row1][col2];

        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col2; j++) {
                for (int k = 0; k < col1; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        System.out.println("\nResultado de la Multiplicación:");
        MatrixUtils.printMatrix(C);
    }

    public static int[][] multiplicarPorEscalar(int[][] matriz, int escalar) {
        if (matriz == null) {
            System.out.println("Error: No hay una matriz cargada.");
            return null;
        }

        int filas = matriz.length;
        int columnas = matriz[0].length;
        int[][] resultado = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado[i][j] = matriz[i][j] * escalar;
            }
        }

        return resultado;
    }

    public static double[][] calcularInversa(int[][] matrix) {
        int n = matrix.length;
        double[][] augmented = new double[n][2 * n];

        // Construir la matriz aumentada [A | I]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmented[i][j] = matrix[i][j];
            }
            augmented[i][n + i] = 1; // Agregar la identidad
        }

        // Aplicar el método de Gauss-Jordan
        for (int i = 0; i < n; i++) {
            // Hacer que augmented[i][i] sea 1 dividiendo la fila por el pivote
            double pivot = augmented[i][i];
            if (pivot == 0) {
                System.out.println("Error: La matriz no tiene inversa (determinante es 0).");
                return null;
            }
            for (int j = 0; j < 2 * n; j++) {
                augmented[i][j] /= pivot;
            }

            // Hacer ceros en la columna i de las demás filas
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = augmented[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        augmented[k][j] -= factor * augmented[i][j];
                    }
                }
            }
        }

        // Extraer la matriz inversa desde la matriz aumentada
        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = augmented[i][j + n];
            }
        }

        return inverse;
    }


    public static double[] resolverSistemaCramer(int[][] coeficientes, int[][] resultados) {
        int n = coeficientes.length;
        double detA = calcularDeterminante(coeficientes);

        if (detA == 0) {
            System.out.println("Error: El sistema no tiene solución única.");
            return null;
        }

        double[] soluciones = new double[n];

        for (int i = 0; i < n; i++) {
            int[][] matrizModificada = new int[n][n];
            for (int j = 0; j < n; j++) {
                matrizModificada[j] = coeficientes[j].clone();
                matrizModificada[j][i] = resultados[j][0];
            }
            double detAi = calcularDeterminante(matrizModificada);
            soluciones[i] = detAi / detA;
        }

        return soluciones;
    }

    public static double calcularDeterminante(int[][] matriz) {
        int n = matriz.length;
        if (n == 1) return matriz[0][0];

        double det = 0;
        for (int i = 0; i < n; i++) {
            int[][] menor = new int[n - 1][n - 1];

            for (int j = 1; j < n; j++) {
                int col = 0;
                for (int k = 0; k < n; k++) {
                    if (k == i) continue;
                    menor[j - 1][col++] = matriz[j][k];
                }
            }

            det += Math.pow(-1, i) * matriz[0][i] * calcularDeterminante(menor);
        }
        return det;
    }

    public static double calcularDeterminante(double[][] matrix) {
        int n = matrix.length;
        double[][] augmentedMatrix = new double[n][n];

        // Crear una copia de la matriz para no modificar la original
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
            }
        }

        // Aplicar la eliminación de Gauss
        for (int i = 0; i < n; i++) {
            // Si el pivote es 0, buscamos una fila para intercambiar
            if (augmentedMatrix[i][i] == 0) {
                boolean swapped = false;
                for (int j = i + 1; j < n; j++) {
                    if (augmentedMatrix[j][i] != 0) {
                        // Intercambiar filas
                        double[] temp = augmentedMatrix[i];
                        augmentedMatrix[i] = augmentedMatrix[j];
                        augmentedMatrix[j] = temp;
                        swapped = true;
                        break;
                    }
                }
                if (!swapped) {
                    System.out.println("El determinante es 0 (la matriz es singular).");
                    return 0; // La matriz es singular, determinante = 0
                }
            }

            // Eliminar las filas debajo del pivote
            for (int j = i + 1; j < n; j++) {
                double factor = augmentedMatrix[j][i] / augmentedMatrix[i][i];
                for (int k = i; k < n; k++) {
                    augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                }
            }
        }

        // El determinante es el producto de los elementos diagonales
        double determinant = 1;
        for (int i = 0; i < n; i++) {
            determinant *= augmentedMatrix[i][i];
        }

        return determinant;
    }

}
