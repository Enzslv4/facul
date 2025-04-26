package roteiro4.parte4;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Digite o número da matrícula do aluno:");
        Scanner strMatri = new Scanner(System.in);
        float matricula = strMatri.nextFloat();

        System.out.println("Digite o nome do aluno:");
        Scanner scaNome = new Scanner(System.in);
        String nome = scaNome.nextLine();

        System.out.println("Digite o curso do aluno:");
        Scanner scaCurso = new Scanner(System.in);
        String curso = scaNome.nextLine();
    
        System.out.println("Digite o ano de ingresso do aluno:");
        Scanner strAnoIngresso = new Scanner(System.in);
        int anoIngresso = strAnoIngresso.nextInt();

        System.out.println("Digite a quantidade de disciplinas do aluno:");
        Scanner strDisciplina = new Scanner(System.in);
        int disciplina = strDisciplina.nextInt();

        strMatri.close();
        scaNome.close();
        scaCurso.close();
        strAnoIngresso.close();
        strDisciplina.close();

        Aluno aluno01 = new Aluno(matricula, nome, curso, anoIngresso, disciplina, "");
        System.out.println("Matricula : " + aluno01.getMatricula());
        System.out.println("Nome : " + aluno01.getNome());
        System.out.println("Curso : " + aluno01.getCurso());
        System.out.println("Ano Ingresso : " + aluno01.getAnoIngresso());
        System.out.println("Quantidade de disciplina : " + aluno01.getQtdeDisciplina());
        System.out.println("Situação do aluno : " + aluno01.getSituacao());
    }
}
