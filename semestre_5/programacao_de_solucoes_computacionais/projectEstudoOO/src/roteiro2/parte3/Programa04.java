package roteiro2.parte3;

import javax.swing.JOptionPane;

public class Programa04 {
    public static void main(String[] args) {
        System.out.println("Seu nome: ");
        String nome = JOptionPane.showInputDialog("Informe seu nome: ");
        JOptionPane.showMessageDialog(null,"Nome informado: " + nome);
        String stridade = JOptionPane.showInputDialog("Informe sua idade: ");
        int idade = Integer.parseInt(stridade);
        JOptionPane.showMessageDialog(null,"Idade informada: " + idade);
    }
}
