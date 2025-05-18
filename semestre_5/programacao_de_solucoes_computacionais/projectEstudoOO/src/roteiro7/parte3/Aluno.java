package roteiro7.parte3;
import java.util.ArrayList;

public class Aluno {
    private float matricula;
    private String nome;
    private String curso;
    private int anoIngresso;
    private int qtdeDisciplina;
    private String situacao;
    private ArrayList<String> listaDisciplinas;
    Aluno(float pMatricula, String pNome, String pCurso, int pAnoIngresso, int pQtdeDisciplina){
    matricula = pMatricula;
    nome = pNome;
    curso = pCurso;
    anoIngresso = pAnoIngresso;
    qtdeDisciplina = pQtdeDisciplina;
    this.listaDisciplinas = new ArrayList<>();
    }
    public float getMatricula() {
    return matricula;
    }
    public void setMatricula(float matricula) {
    this.matricula = matricula;
    }
    public String getNome() {
    return nome;
    }
    public void setNome(String nome) {
    this.nome = nome;
    }

    public String getCurso() {
    return curso;
    }
    public void setCurso(String curso) {
    this.curso = curso;
    }

    public int getAnoIngresso() {
    return anoIngresso;
    }
    public void setAnoIngresso(int anoIngresso) {
    this.anoIngresso = anoIngresso;
    }

    public int getQtdeDisciplina() {
    return qtdeDisciplina;
    }
    public void setQtdeDisciplina(int qtdeDisciplina) {
    this.qtdeDisciplina = qtdeDisciplina;
    }
    
    public String getSituacao() {
        if (qtdeDisciplina > 0){
            situacao = "Matriculado";
        }
        else{
            situacao = "Não Matriculado";
        }
    return situacao;
    }
    public ArrayList<String> getListaDisciplinas() {
        return listaDisciplinas;
    }

    public void setListaDisciplinas(ArrayList<String> listaDisciplinas) {
        this.listaDisciplinas = listaDisciplinas;
    }

    
    public void adicionarDisciplina(String disciplina) {
        this.listaDisciplinas.add(disciplina);
    }

    public String listarDisciplinas() {
        if (listaDisciplinas.isEmpty()) {
            return "Nenhuma disciplina cadastrada.";
        }
        return String.join(", ", listaDisciplinas);
    }
}
