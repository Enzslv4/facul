package roteiro7.parte3;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        ArrayList<Aluno> listaAlunos = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);

        for (int i = 0; i < 1; i++) {
            System.out.println("Insira a sua matricula: ");
            float matricula = entrada.nextFloat();
            entrada.nextLine();
            System.out.println("Insira o seu nome: ");
            String nome = entrada.nextLine();
            System.out.println("Insira o seu curso: ");
            String curso = entrada.nextLine();
            System.out.println("Insira o seu ano de ingresso: ");
            int anoIngresso = entrada.nextInt();
            entrada.nextLine();
            System.out.println("Insira a quantidade de disciplinas: ");
            int qtdeDisciplina = entrada.nextInt();
            entrada.nextLine();

            Aluno auxAluno = new Aluno(matricula, nome, curso, anoIngresso, qtdeDisciplina);

            for (int j = 0; j < qtdeDisciplina; j++) {
                System.out.println("Insira o nome da disciplina " + (j + 1) + ":");
                String auxDisciplina = entrada.nextLine();
                auxAluno.adicionarDisciplina(auxDisciplina);
            }

            listaAlunos.add(auxAluno);
        }

        System.out.println("Listando os Alunos : ");
        System.out.println("********************************");

        for (Aluno aluno : listaAlunos) {
            System.out.println("Matricula : " + aluno.getMatricula());
            System.out.println("Nome : " + aluno.getNome());
            System.out.println("Curso : " + aluno.getCurso());
            System.out.println("Ano Ingresso : " + aluno.getAnoIngresso());
            System.out.println("Status de matricula: " + aluno.getSituacao());
            System.out.println("Qtd de disciplinas: " + aluno.getQtdeDisciplina());
            System.out.println("Disciplinas: " + aluno.listarDisciplinas());
            System.out.println("********************************");
        }

        entrada.close();
    }
}
