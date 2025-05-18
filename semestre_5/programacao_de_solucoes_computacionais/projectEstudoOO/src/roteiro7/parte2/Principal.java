package roteiro7.parte2;
import java.util.Scanner;
import roteiro7.parte2.Aluno;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        //Criando um vetor estático de objetos Aluno
        ArrayList<Aluno> listaAlunos = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);

        for (int i = 0; i < 3;i++){
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
        
            Aluno auxAluno = new Aluno(matricula, nome, curso, anoIngresso, qtdeDisciplina, "Matriculado");
            listaAlunos.add(auxAluno);
        }
        //Instanciando os objetos do tipo Aluno

        
        //Armazenando os objetos no vetor de alunos

        
        System.out.println("Listando os Alunos : ");
        System.out.println("********************************");
        
        for (int i = 0; i < 3; i++) {
            System.out.println("Matricula : " + listaAlunos.get(i).getMatricula());
            System.out.println("Nome : " + listaAlunos.get(i).getNome());
            System.out.println("Curso : " + listaAlunos.get(i).getCurso());
            System.out.println("Ano Ingresso : " + listaAlunos.get(i).getAnoIngresso());
            System.out.println("Status de matricula: " + listaAlunos.get(i).getSituacao());
            System.out.println("********************************");
        }
    }
}
