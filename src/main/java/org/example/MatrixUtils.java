package org.example;

import java.util.Scanner;

public class MatrixUtils {

    // Imprime una matriz de enteros en formato de tabla.
    public static void printMatrix(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }

    // Imprime una matriz de números decimales convirtiéndolos a fracción (simplificada)
    public static void printMatrixAsFraction(double[][] matrix) {
        System.out.println("Matriz mostrada en fracciones:");
        for (double[] row : matrix) {
            for (double value : row) {
                // Convierte el valor decimal a fracción y lo imprime
                System.out.print(decimalToFraction(value, 0.0001) + "\t");
            }
            System.out.println();
        }
    }

    // Permite ingresar los valores de una matriz
    public static int[][] inputMatrix(int filas, int columnas, Scanner scanner) {
        int[][] matriz = new int[filas][columnas];
        System.out.println("Ingrese los valores de la matriz (" + filas + "x" + columnas + "):");

        // Se recorre la matriz para solicitar el valor al usuario
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("Matriz[" + i + "][" + j + "]: ");
                matriz[i][j] = scanner.nextInt();
            }
        }
        return matriz;
    }

    // Valida que dos matrices sean compatibles para operaciones de suma o resta
    public static boolean validarSumaResta(int[][] matrizA, int[][] matrizB) {
        if (matrizA == null || matrizB == null) {
            System.out.println("Error: Debe ingresar ambas matrices primero.");
            return false;
        }
        // Se valida que ambas matrices tengan las mismas dimensiones
        if (matrizA.length != matrizB.length || matrizA[0].length != matrizB[0].length) {
            System.out.println("Error: Para sumar o restar, ambas matrices deben tener las mismas dimensiones.");
            return false;
        }
        return true;
    }

    // Valida que dos matrices sean compatibles para multiplicación (columnas de A == filas de B)
    public static boolean validarMultiplicacion(int[][] matrizA, int[][] matrizB) {
        if (matrizA == null || matrizB == null) {
            System.out.println("Error: Debe ingresar ambas matrices primero.");
            return false;
        }
        int col1 = matrizA[0].length;
        int row2 = matrizB.length;
        if (col1 != row2) {
            System.out.println("Error: No se puede realizar la multiplicación. El número de columnas de A debe ser igual al número de filas de B.");
            return false;
        }
        return true;
    }

    // Valida que la matriz no sea nula para poder multiplicarla por un escalar
    public static boolean validarMultiplicarPorEscalar(int[][] matriz) {
        if (matriz == null) {
            System.out.println("Error: No hay una matriz cargada.");
            return false;
        }
        return true;
    }

    // Calcula el máximo común divisor
    public static int mcd(int a, int b) {
        return (b == 0) ? a : mcd(b, a % b);
    }

    // Convierte un número decimal a fracción dentro de una tolerancia y la reduce
    public static String decimalToFraction(double value, double tolerance) {
        // Se determina el signo del número
        int sign = (value < 0) ? -1 : 1;
        value = Math.abs(value);

        double bestNumerator = 1;
        double bestDenominator = 1;
        // Calcula el error inicial entre el valor y 1/1
        double bestError = Math.abs(value - (bestNumerator / bestDenominator));

        double numerator, denominator = 1;
        // Se itera aumentando el denominador para encontrar la mejor aproximación
        while (denominator < 10000 && bestError > tolerance) {
            numerator = Math.floor(value * denominator + 0.5); // Se redondea el numerador
            double error = Math.abs(value - (numerator / denominator)); // Calcula el error
            if (error < bestError) {
                bestNumerator = numerator;
                bestDenominator = denominator;
                bestError = error;
            }
            denominator++;
        }

        // Se convierten a enteros y se simplifica la fracción usando el mcd
        int num = (int) bestNumerator;
        int den = (int) bestDenominator;
        int divisor = mcd(num, den);
        num /= divisor;
        den /= divisor;

        return (sign * num) + "/" + den;
    }

    // Convierte una matriz n x 1 en un vector unidimensional
    public static int[] toVector(int[][] matriz) {
        int n = matriz.length;
        int[] vector = new int[n];
        for (int i = 0; i < n; i++) {
            vector[i] = matriz[i][0]; // Se extrae el primer (y único) valor de cada fila
        }
        return vector;
    }
}
