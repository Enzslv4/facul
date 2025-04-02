package parte3;

import java.util.Scanner;

public class Programa07 {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("Informe a primeira nota");
        int nota = entrada.nextInt();
        int qNotas = 0;
        int media = 0;
        int notaTotal = 0;

        while (nota != -1) {
            
            if (nota >= 7) {
                System.out.println("Aprovado.");
            }
            else {
                System.out.println("Reprovado.");
            }

           
            
            notaTotal = notaTotal + nota;
            qNotas++;

            System.out.println("Informe a proxima nota: ");
            nota  = entrada.nextInt();
        }

        if (qNotas > 0)
        {
           media = notaTotal / qNotas;
           System.out.println("Média: " + media);
        }
        else
            System.out.println("Nao existem notas");
        entrada.close();
    }
}
