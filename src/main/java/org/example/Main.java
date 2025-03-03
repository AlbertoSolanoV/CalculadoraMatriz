package org.example;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrizA = null;
        int[][] matrizB = null;

        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Ingresar matrices manualmente");
            System.out.println("2. Sumar matrices");
            System.out.println("3. Restar matrices");
            System.out.println("4. Multiplicar matrices");
            System.out.println("5. Multiplicar por escalar");
            System.out.println("6. Calcular la inversa de una matriz (Gauss-Jordan)");
            System.out.println("7. Calcular determinante de una matriz");
            System.out.println("8. Resolver sistema de ecuaciones (Regla de Cramer)");
            System.out.println("9. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\nIngrese las dimensiones de la matriz A:");
                    System.out.print("Filas: ");
                    int filasA = scanner.nextInt();
                    System.out.print("Columnas: ");
                    int columnasA = scanner.nextInt();
                    matrizA = MatrixUtils.inputMatrix(filasA, columnasA, scanner);

                    System.out.println("\nIngrese las dimensiones de la matriz B:");
                    System.out.print("Filas: ");
                    int filasB = scanner.nextInt();
                    System.out.print("Columnas: ");
                    int columnasB = scanner.nextInt();
                    matrizB = MatrixUtils.inputMatrix(filasB, columnasB, scanner);

                    System.out.println("\nMatriz A:");
                    MatrixUtils.printMatrix(matrizA);

                    System.out.println("\nMatriz B:");
                    MatrixUtils.printMatrix(matrizB);
                    break;

                case 2:
                    if (MatrixUtils.validarSumaResta(matrizA, matrizB)) {
                        MatrixOperations.sumMatrix(matrizA, matrizB);
                    }
                    break;

                case 3:
                    if (MatrixUtils.validarSumaResta(matrizA, matrizB)) {
                        MatrixOperations.subMatrix(matrizA, matrizB);
                    }
                    break;

                case 4:
                    if (MatrixUtils.validarMultiplicacion(matrizA, matrizB)) {
                        MatrixOperations.multiplyMatrix(matrizA, matrizB);
                    }
                    break;
                case 5:
                    System.out.println("\nSeleccione la matriz a multiplicar (A o B): ");
                    char matrizSeleccionada = scanner.next().toUpperCase().charAt(0);

                    System.out.print("Ingrese el número por el cual multiplicar la matriz: ");
                    int escalar = scanner.nextInt();

                    if (matrizSeleccionada == 'A') {
                        if(MatrixUtils.validarMultiplicarPorEscalar(matrizA)){
                            matrizA = MatrixOperations.multiplicarPorEscalar(matrizA, escalar);
                            System.out.println("\nResultado de A * " + escalar + ":");
                            MatrixUtils.printMatrix(matrizA);
                        }
                    } else if (matrizSeleccionada == 'B') {
                        if(MatrixUtils.validarMultiplicarPorEscalar(matrizB)) {
                            matrizB = MatrixOperations.multiplicarPorEscalar(matrizB, escalar);
                            System.out.println("\nResultado de B * " + escalar + ":");
                            MatrixUtils.printMatrix(matrizB);
                        }
                    } else {
                        System.out.println("Selección inválida.");
                    }
                    break;
                case 6:
                    System.out.println("\nSeleccione la matriz a calcular la inversa (A o B): ");
                    matrizSeleccionada = scanner.next().toUpperCase().charAt(0);
                    double[][] resultado;
                    if (matrizSeleccionada == 'A') {
                        resultado = MatrixOperations.calcularInversa(matrizA);
                    } else if (matrizSeleccionada == 'B') {
                        resultado = MatrixOperations.calcularInversa(matrizB);
                    } else {
                        System.out.println("Selección inválida.");
                        break;
                    }

                    if (resultado != null) {
                        System.out.println("\nMatriz Inversa:");
                        MatrixUtils.printMatrixAsFraction(resultado);
                    } else {
                        System.out.println("No se pudo calcular la inversa.");
                    }
                    break;
                case 7:
                    System.out.println("\nSeleccione la matriz a calcular el determinante (A o B): ");
                    matrizSeleccionada = scanner.next().toUpperCase().charAt(0);
                    double resultadoDet;
                    if (matrizSeleccionada == 'A') {
                        resultadoDet = MatrixOperations.calcularDeterminante(matrizA);
                    } else if (matrizSeleccionada == 'B') {
                        resultadoDet = MatrixOperations.calcularDeterminante(matrizB);
                    } else {
                        System.out.println("Selección inválida.");
                        break;
                    }

                    if (Double.isNaN(resultadoDet)) {
                        System.out.println("No se pudo calcular el determinante.");
                    } else {
                        System.out.print("\nDeterminante:");
                        System.out.println(resultadoDet);
                    }
                    break;
                case 8:
                    System.out.println("\nIngrese el número de ecuaciones (y variables): ");
                    int n = scanner.nextInt();

                    System.out.println("Ingrese la matriz de coeficientes:");
                    int[][] coeficientes = MatrixUtils.inputMatrix(n, n, scanner);

                    System.out.println("Ingrese el vector de términos independientes:");
                    int[][] resultados = MatrixUtils.inputMatrix(n, 1, scanner);

                    double[] solucion = MatrixOperations.resolverSistemaCramer(coeficientes, resultados);

                    if (solucion != null) {
                        System.out.println("\nSolución del sistema:");
                        for (int i = 0; i < solucion.length; i++) {
                            System.out.println("x" + (i + 1) + " = " + solucion[i]);
                        }
                    } else {
                        System.out.println("No se pudo resolver el sistema.");
                    }
                    break;

                case 9:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida, intenta nuevamente.");
            }
        }
    }
}