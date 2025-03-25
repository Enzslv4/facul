import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro numero: ");
        int num1 = scanner.nextInt(); // First number

        System.out.print("Digite o segundo numero: ");
        int num2 = scanner.nextInt(); // Second number

        int sum = num1 + num2; // Adding the two numbers

        System.out.println("A soma de " + num1 + " e " + num2 + " é: " + sum); // Printing the result
    }
}