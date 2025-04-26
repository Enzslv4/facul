package roteiro4.parte1;

public class Principal {
    public static void main(String[] args) {
        Aluno aluno01 = new Aluno();
        // Aluno aluno01 = new Aluno(111, "Jose", "Sistema de Informação", 2019, 4, "Aprovado");
        aluno01.matricula = 111;
        aluno01.nome = "Jose";
        aluno01.curso = "Sistema de Informação";
        aluno01.anoIngresso = 2019;
        System.out.println("Matricula : " + aluno01.matricula);
        System.out.println("Nome : " + aluno01.nome);
        System.out.println("Curso : " + aluno01.curso);
        System.out.println("Ano Ingresso : " + aluno01.anoIngresso);

        Aluno aluno02 = new Aluno();
        aluno02.matricula = 115;
        aluno02.nome = "Maria";
        aluno02.curso = "ADS";
        aluno02.anoIngresso = 2022;
        System.out.println("Matricula : " + aluno02.matricula);
        System.out.println("Nome : " + aluno02.nome);
        System.out.println("Curso : " + aluno02.curso);
        System.out.println("Ano Ingresso : " + aluno02.anoIngresso);
    }
}
