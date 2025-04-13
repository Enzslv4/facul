package roteiro3.parte1;
import java.util.Scanner;

public class Programa10 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        double sb, grat, imp, sr;
        System.out.println("Informe o Salário Base :");
        sb = entrada.nextDouble();
        grat = sb * 5/100;
        imp = sb * 7/100;
        sr = sb + grat - imp;
        System.out.println("Salário a receber : " + sr);
        entrada.close();
    }

}
