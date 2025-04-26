package roteiro2.parte3;

import java.util.Scanner;

public class Programa05 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int cont = 0;
        entrada.close();
        for (cont = 0; cont < 5; cont++) {
            System.out.println("Informe uma nota: ");
            int nota = entrada.nextInt();
            
            if (nota >= 7) {
                System.out.println("Aprovado.");
            } else {
                System.out.println("Reprovado.");
            }
        }
        
        
    }
}
