package roteiro1.parte2;

public class Exercicio01 {

    public static void main(String[] args) {

        double salarioBase = 2500;
	    int numHorasExtra = 10;
        int numHorasTrabalhadas = 160;
        double valorHoraNormal = salarioBase/numHorasTrabalhadas;
        double salarioCompleto = numHorasExtra * valorHoraNormal + salarioBase;


	  System.out.println("O seu salaraio com horas extra é de: " + salarioCompleto);
      System.out.println("O número de horas extra trabalhadas é: " + numHorasExtra);
      System.out.println("O seu salario base é de: " + salarioBase);
        
    }
    
}
