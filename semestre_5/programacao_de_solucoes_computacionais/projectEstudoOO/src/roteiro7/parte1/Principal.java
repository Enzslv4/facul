package roteiro7.parte1;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        //Criando um vetor estático de objetos Aluno
        Scanner entrada = new Scanner(System.in);

        Aluno[] listaAlunos = new Aluno[3];

        for (int i = 0; i<1;i++){
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
        
            Aluno auxAluno = new Aluno(matricula, nome, curso, anoIngresso, qtdeDisciplina, curso);

            listaAlunos[i] = auxAluno;
        }
        //Instanciando os objetos do tipo Aluno

        
        //Armazenando os objetos no vetor de alunos

        
        System.out.println("Listando os Alunos : ");
        System.out.println("********************************");
        
        for (int i = 0; i < 1; i++) {
            System.out.println("Matricula : " + listaAlunos[i].getMatricula());
            System.out.println("Nome : " + listaAlunos[i].getNome());
            System.out.println("Curso : " + listaAlunos[i].getCurso());
            System.out.println("Ano Ingresso : " + listaAlunos[i].getAnoIngresso());
            System.out.println("Status de matricula: " + listaAlunos[i].getSituacao());
            System.out.println("********************************");
        }

        entrada.close();
    }
}
