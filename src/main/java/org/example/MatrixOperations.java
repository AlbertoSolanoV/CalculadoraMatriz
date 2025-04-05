package org.example;

public class MatrixOperations {

    // Suma dos matrices y muestra el resultado
    public static void sumMatrix(int[][] matrizA, int[][] matrizB) {
        int filas = matrizA.length;
        int columnas = matrizA[0].length;
        int[][] matrizSuma = new int[filas][columnas];

        // Se suman elemento a elemento
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizSuma[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }
        System.out.println("\nResultado de la Suma:");
        MatrixUtils.printMatrix(matrizSuma);
    }

    // Resta dos matrices y muestra el resultado
    public static void subMatrix(int[][] matrizA, int[][] matrizB) {
        int filas = matrizA.length;
        int columnas = matrizA[0].length;
        int[][] matrizResta = new int[filas][columnas];

        // Se resta elemento a elemento
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizResta[i][j] = matrizA[i][j] - matrizB[i][j];
            }
        }
        System.out.println("\nResultado de la Resta:");
        MatrixUtils.printMatrix(matrizResta);
    }

    // Multiplica dos matrices y muestra el resultado
    public static void multiplyMatrix(int[][] A, int[][] B) {
        int row1 = A.length, col1 = A[0].length;
        int col2 = B[0].length;


        int[][] C = new int[row1][col2]; // Matriz resultado

        // Se realiza la multiplicación clásica de matrices
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col2; j++) {
                for (int k = 0; k < col1; k++) {
                    C[i][j] += A[i][k] * B[k][j]; // Acumula el producto de elementos
                }
            }
        }
        System.out.println("\nResultado de la Multiplicación:");
        MatrixUtils.printMatrix(C);
    }

    // Multiplica una matriz por un escalar y retorna la nueva matriz resultante
    public static int[][] multiplicarPorEscalar(int[][] matriz, int escalar) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int[][] resultado = new int[filas][columnas];

        // Se multiplica cada elemento de la matriz por el escalar
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado[i][j] = matriz[i][j] * escalar;
            }
        }
        return resultado;
    }

    // Método auxiliar para intercambiar dos filas en una matriz de números reales
    private static void swapRows(double[][] matrix, int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    // Calcula la inversa de una matriz cuadrada usando el método de Gauss-Jordan
    public static double[][] calcularInversa(int[][] matrix) {
        int n = matrix.length;
        double[][] augmented = new double[n][2 * n];

        // Construir la matriz aumentada [A | I]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmented[i][j] = matrix[i][j]; // Se copia la matriz original en la parte izquierda
            }
            augmented[i][n + i] = 1; // Se coloca la identidad en la parte derecha
        }

        // Aplicar el método de Gauss-Jordan para transformar la parte izquierda en la matriz identidad
        for (int i = 0; i < n; i++) {
            // Si el pivote es cero (o casi cero), se busca otra fila para intercambiar
            if (Math.abs(augmented[i][i]) < 1e-10) {
                boolean swapped = false;
                for (int k = i + 1; k < n; k++) {
                    if (Math.abs(augmented[k][i]) > 1e-10) {
                        swapRows(augmented, i, k);
                        swapped = true;
                        break;
                    }
                }
                if (!swapped) { // Si no se encontró pivote, la matriz es singular
                    System.out.println("Error: La matriz no tiene inversa (determinante es 0).");
                    return null;
                }
            }

            double pivot = augmented[i][i];
            // Normaliza la fila dividiendo todos los elementos por el pivote
            for (int j = 0; j < 2 * n; j++) {
                augmented[i][j] /= pivot;
            }

            // Elimina los demás valores en la columna i de todas las otras filas
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = augmented[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        augmented[k][j] -= factor * augmented[i][j];
                    }
                }
            }
        }

        // Extraer la matriz inversa, que se encuentra en la parte derecha de la matriz aumentada
        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = augmented[i][j + n];
            }
        }
        return inverse;
    }

    // Resuelve un sistema de ecuaciones lineales usando la regla de Cramer
    // Se espera que los coeficientes sean una matriz cuadrada y resultados sea un vector
    public static double[] resolverSistemaCramer(int[][] coeficientes, int[] resultados) {
        int n = coeficientes.length;
        double detA = calcularDeterminante(coeficientes);
        // Si el determinante es cero, el sistema no tiene solución única
        if (Math.abs(detA) < 1e-10) {
            System.out.println("Error: El sistema no tiene solución única.");
            return null;
        }
        double[] soluciones = new double[n];
        // Se crea una matriz modificada para cada variable y se calcula su determinante
        for (int i = 0; i < n; i++) {
            int[][] matrizModificada = new int[n][n];
            for (int j = 0; j < n; j++) {
                matrizModificada[j] = coeficientes[j].clone(); // Clona la fila original
                matrizModificada[j][i] = resultados[j];           // Reemplaza la columna i con el vector de resultados
            }
            double detAi = calcularDeterminante(matrizModificada);
            soluciones[i] = detAi / detA;  // Se calcula la solución para la variable i
        }
        return soluciones;
    }

    // Calcula el determinante de una matriz de enteros convirtiéndola a double para mayor precisión
    public static double calcularDeterminante(int[][] matriz) {
        int n = matriz.length;
        double[][] temp = new double[n][n];
        // Se copia la matriz de enteros a una de double
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = matriz[i][j];
            }
        }
        return calcularDeterminante(temp);
    }

    // Calcula el determinante de una matriz de números reales usando eliminación Gaussiana
    public static double calcularDeterminante(double[][] matrix) {
        int n = matrix.length;
        double[][] m = new double[n][n];
        // Se copia la matriz original para no modificarla
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, m[i], 0, n);
        }
        double det = 1;
        // Se procede fila por fila para triangular la matriz
        for (int i = 0; i < n; i++) {
            // Busca el pivote en la columna i
            int pivotRow = i;
            while (pivotRow < n && Math.abs(m[pivotRow][i]) < 1e-10) {
                pivotRow++;
            }
            if (pivotRow == n) {
                return 0; // Si no se encuentra un pivote, el determinante es 0
            }
            // Si se intercambia el pivote, se cambia el signo del determinante
            if (pivotRow != i) {
                swapRows(m, i, pivotRow);
                det *= -1;
            }
            det *= m[i][i];
            // Elimina los elementos debajo del pivote
            for (int j = i + 1; j < n; j++) {
                double factor = m[j][i] / m[i][i];
                for (int k = i; k < n; k++) {
                    m[j][k] -= factor * m[i][k];
                }
            }
        }
        return det;
    }
}
