package roteiro2.parte3;

import java.util.Scanner;

public class Programa06 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Informe a primeira nota");
        int nota = entrada.nextInt();

        while (nota != -1) {
            
            if (nota >= 7) {
                System.out.println("Aprovado.");
            }
            else {
                System.out.println("Reprovado.");
            }
            
            System.out.println("Informe a proxima nota: ");
            nota  = entrada.nextInt();
        }

        entrada.close();
    }
}
