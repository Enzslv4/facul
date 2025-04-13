package roteiro3.parte2;

import java.util.Scanner;

public class Programa14 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe o valor de x ");
        double x = entrada.nextDouble();
        System.out.println("Informe a operação (+, -, *, /) ");
        String op = entrada.next();
        System.out.println("Informe o valor de y ");
        double y = entrada.nextDouble();

        double resultado;

        switch (op) {
            case "+":
                resultado = soma(x, y);
                System.out.println("Resultado: " + resultado);
                break;
            case "-":
                resultado = subtracao(x, y);
                System.out.println("Resultado: " + resultado);
                break;
            case "*":
                resultado = multiplicacao(x, y);
                System.out.println("Resultado: " + resultado);
                break;
            case "/":
                if (y != 0) {
                    resultado = divisao(x, y);
                    System.out.println("Resultado: " + resultado);
                } else {
                    System.out.println("Erro: divisão por zero.");
                }
                break;
            default:
                System.out.println("Operação inválida");
        }

        entrada.close();
    }

    public static double soma(double x, double y) {
        return x + y;
    }

    public static double subtracao(double x, double y) {
        return x - y;
    }

    public static double multiplicacao(double x, double y) {
        return x * y;
    }

    public static double divisao(double x, double y) {
        return x / y;
    }
}
