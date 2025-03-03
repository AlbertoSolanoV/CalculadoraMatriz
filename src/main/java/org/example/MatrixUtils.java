package org.example;

import java.util.Scanner;

public class MatrixUtils {

    public static void printMatrix(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }

    public static void printMatrixAsFraction(double[][] matrix) {
        System.out.println("Matriz Inversa en fracciones:");
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(MatrixUtils.decimalToFraction(value, 0.0001) + "\t");
            }
            System.out.println();
        }
    }

    public static int getRow(int[][] matriz) {
        return matriz.length;
    }

    public static int getColumn(int[][] matriz) {
        return matriz[0].length;
    }

    public static int[][] inputMatrix(int filas, int columnas, Scanner scanner) {
        int[][] matriz = new int[filas][columnas];
        System.out.println("Ingrese los valores de la matriz (" + filas + "x" + columnas + "):");

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("Matriz[" + i + "][" + j + "]: ");
                matriz[i][j] = scanner.nextInt();
            }
        }
        return matriz;
    }

    public static boolean validarSumaResta(int[][] matrizA, int[][] matrizB) {
        if (matrizA == null || matrizB == null) {
            System.out.println("Error: Debe ingresar ambas matrices primero.");
            return false;
        }

        if (matrizA.length != matrizB.length || matrizA[0].length != matrizB[0].length) {
            System.out.println("Error: Para sumar o restar, ambas matrices deben tener las mismas dimensiones.");
            return false;
        }

        return true;
    }

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

    public static boolean validarMultiplicarPorEscalar(int[][] matriz) {
        if (matriz == null) {
            System.out.println("Error: No hay una matriz cargada.");
            return false;
        }


        return true;
    }

    public static String decimalToFraction(double value, double tolerance) {
        int sign = (value < 0) ? -1 : 1;
        value = Math.abs(value);

        double numerator = 1;
        double denominator = 1;
        double bestNumerator = 1;
        double bestDenominator = 1;
        double bestError = Math.abs(value - (bestNumerator / bestDenominator));

        while (bestError > tolerance) {
            numerator = Math.floor(value * denominator + 0.5);
            double error = Math.abs(value - (numerator / denominator));

            if (error < bestError) {
                bestNumerator = numerator;
                bestDenominator = denominator;
                bestError = error;
            }
            denominator++;
        }

        return (sign * (int) bestNumerator) + "/" + (int) bestDenominator;
    }


}
