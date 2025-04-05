package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;

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

            int opcion;
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intente nuevamente");
                scanner.nextLine(); // Limpiar el buffer
                continue;
            }

            switch (opcion) {
                case 1:
                    // Ingresar matrices manualmente
                    try {
                        // Solicita las dimensiones de la matriz A
                        System.out.println("\nIngrese las dimensiones de la matriz A:");
                        System.out.print("Filas: ");
                        int filasA = scanner.nextInt();
                        System.out.print("Columnas: ");
                        int columnasA = scanner.nextInt();
                        matrizA = MatrixUtils.inputMatrix(filasA, columnasA, scanner);

                        // Solicita las dimensiones de la matriz B
                        System.out.println("\nIngrese las dimensiones de la matriz B:");
                        System.out.print("Filas: ");
                        int filasB = scanner.nextInt();
                        System.out.print("Columnas: ");
                        int columnasB = scanner.nextInt();
                        matrizB = MatrixUtils.inputMatrix(filasB, columnasB, scanner);

                        // Muestra las matrices ingresadas
                        System.out.println("\nMatriz A:");
                        MatrixUtils.printMatrix(matrizA);

                        System.out.println("\nMatriz B:");
                        MatrixUtils.printMatrix(matrizB);
                    } catch (InputMismatchException e) {
                        System.out.println("Error en la entrada de datos. Asegúrese de ingresar números enteros");
                        scanner.nextLine();
                    }
                    break;

                case 2:
                    // Sumar matrices
                    if (MatrixUtils.validarSumaResta(matrizA, matrizB)) {
                        MatrixOperations.sumMatrix(matrizA, matrizB);
                    }
                    break;

                case 3:
                    // CRestar matrices
                    if (MatrixUtils.validarSumaResta(matrizA, matrizB)) {
                        MatrixOperations.subMatrix(matrizA, matrizB);
                    }
                    break;

                case 4:
                    // Multiplicar matrices
                    if (MatrixUtils.validarMultiplicacion(matrizA, matrizB)) {
                        MatrixOperations.multiplyMatrix(matrizA, matrizB);
                    }
                    break;

                case 5:
                    // Multiplicar una matriz por un escalar
                    System.out.println("\nSeleccione la matriz a multiplicar (A o B): ");
                    char matrizSeleccionada = scanner.next().toUpperCase().charAt(0);
                    System.out.print("Ingrese el número por el cual multiplicar la matriz: ");
                    int escalar = scanner.nextInt();

                    if (matrizSeleccionada == 'A') {
                        if (MatrixUtils.validarMultiplicarPorEscalar(matrizA)) {
                            matrizA = MatrixOperations.multiplicarPorEscalar(matrizA, escalar);
                            System.out.println("\nResultado de A * " + escalar + ":");
                            MatrixUtils.printMatrix(matrizA);
                        }
                    } else if (matrizSeleccionada == 'B') {
                        if (MatrixUtils.validarMultiplicarPorEscalar(matrizB)) {
                            matrizB = MatrixOperations.multiplicarPorEscalar(matrizB, escalar);
                            System.out.println("\nResultado de B * " + escalar + ":");
                            MatrixUtils.printMatrix(matrizB);
                        }
                    } else {
                        System.out.println("Selección inválida.");
                    }
                    break;

                case 6:
                    // Calcular la inversa de una matriz
                    System.out.println("\nSeleccione la matriz a calcular la inversa (A o B): ");
                    matrizSeleccionada = scanner.next().toUpperCase().charAt(0);
                    double[][] resultadoInversa;
                    if (matrizSeleccionada == 'A') {
                        resultadoInversa = MatrixOperations.calcularInversa(matrizA);
                    } else if (matrizSeleccionada == 'B') {
                        resultadoInversa = MatrixOperations.calcularInversa(matrizB);
                    } else {
                        System.out.println("Selección inválida.");
                        break;
                    }
                    // Si se pudo calcular la inversa, se muestra en formato de fraccion
                    if (resultadoInversa != null) {
                        System.out.println("\nMatriz Inversa:");
                        MatrixUtils.printMatrixAsFraction(resultadoInversa);
                    } else {
                        System.out.println("No se pudo calcular la inversa.");
                    }
                    break;

                case 7:
                    // Calcular el determinante
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
                    System.out.println("\nDeterminante: " + resultadoDet);
                    break;

                case 8:
                    // Resolver un sistema de ecuaciones por la regla de Cramer
                    try {
                        System.out.println("\nIngrese el número de ecuaciones (y variables): ");
                        int n = scanner.nextInt();

                        // Se ingresa la matriz
                        System.out.println("Ingrese la matriz de coeficientes:");
                        int[][] coeficientes = MatrixUtils.inputMatrix(n, n, scanner);

                        // Se ingresa el vector de términos independientes
                        System.out.println("Ingrese el vector de términos independientes:");
                        int[][] resultadosMatriz = MatrixUtils.inputMatrix(n, 1, scanner);
                        // Se convierte la matriz de resultados a un vector unidimensional
                        int[] resultados = MatrixUtils.toVector(resultadosMatriz);

                        // Se resuelve el sistema y se obtienen las soluciones
                        double[] solucion = MatrixOperations.resolverSistemaCramer(coeficientes, resultados);
                        if (solucion != null) {
                            System.out.println("\nSolución del sistema:");
                            for (int i = 0; i < solucion.length; i++) {
                                System.out.println("x" + (i + 1) + " = " + solucion[i]);
                            }
                        } else {
                            System.out.println("No se pudo resolver el sistema.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error en la entrada de datos para el sistema de ecuaciones.");
                        scanner.nextLine();
                    }
                    break;

                case 9:
                    //  Salir del programa
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida, intenta nuevamente.");
            }
        }
    }
}
