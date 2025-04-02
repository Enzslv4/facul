package roteiro2.parte3;

import java.util.Scanner;

public class Programa05 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Quantas notas deseja informar? ");
        int totalNotas = 0;

        // Validate user input for the number of iterations
        while (true) {
            try {
                totalNotas = Integer.parseInt(entrada.nextLine());
                if (totalNotas > 0) {
                    break;
                } else {
                    System.out.print("Por favor, insira um número positivo: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, insira um número inteiro: ");
            }
        }

        for (int cont = 0; cont < totalNotas; cont++) {
            System.out.print("Informe uma nota: ");
            try {
                int nota = Integer.parseInt(entrada.nextLine());
                if (nota >= 7) {
                    System.out.println("Aprovado.");
                } else {
                    System.out.println("Reprovado.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                cont--; // Retry the current iteration
            }
        }

        entrada.close();
    }
}