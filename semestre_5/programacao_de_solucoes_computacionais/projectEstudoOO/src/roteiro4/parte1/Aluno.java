package roteiro4.parte1;

public class Aluno {
    private float matricula;
    private String nome;
    private String curso;
    private int anoIngresso;
    private int qtdeDisciplina;
    private String situacao;

    Aluno(float pMatricula, String pNome, String pCurso, int pAnoIngresso, int pQtdeDisciplina, String pSituacao){
        matricula = pMatricula;
        nome = pNome;
        curso = pCurso;
        anoIngresso = pAnoIngresso;
        qtdeDisciplina = pQtdeDisciplina;
        situacao = pSituacao;
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
}
