package roteiro6.parte3;

public class Ligacao {
    private String numOrigem;
    private String numDestino;
    private String localOrigem;
    private String localDestino;
    private Tempo horaInicio;
    private Tempo horaFim;
    
    public Ligacao (String numOrigem, String numDestino, String localOrigem, String localDestino, Tempo horaInicio){
        this.numOrigem = numOrigem;
        this.numDestino = numDestino;
        this.localOrigem = localOrigem;
        this.localDestino = localDestino;
        this.horaInicio = horaInicio;
        this.horaFim = null;
    }

    public String getNumOrigem() {
        return numOrigem;
    }

    public void setNumOrigem(String numOrigem) {
        this.numOrigem = numOrigem;
    }

    public String getNumDestino() {
        return numDestino;
    }

    public void setNumDestino(String numDestino) {
        this.numDestino = numDestino;
    }

    public String getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(String localOrigem) {
        this.localOrigem = localOrigem;
    }

    public String getLocalDestino() {
        return localDestino;
    }

    public void setLocalDestino(String localDestino) {
        this.localDestino = localDestino;
    }

    public Tempo getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Tempo horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Tempo getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Tempo horaFim) {
        this.horaFim = horaFim;
    }

    public String Pertence(String numE) {
    if (this.getNumOrigem().equals(numE)){
        return "Este é o número de Origem";
    }else if(this.getNumDestino().equals(numE)){
        return "Este é o número de Destino";
        }else{
            return "Não pertence a ligação";
        }
    }
    public double calculaValor() {
        int segundosInicio = this.horaInicio.getHora() * 3600 + this.horaInicio.getMinuto() * 60 + this.horaInicio.getSegundo();
        int segundosFim = this.horaFim.getHora() * 3600 + this.horaFim.getMinuto() * 60 + this.horaFim.getSegundo();
        int duracaoEmSegundos = segundosFim - segundosInicio;
        if (duracaoEmSegundos <= 0){
        return 0.0;
        }
        return (duracaoEmSegundos / 60.0) * 1.0;
    }
    
    public double calculaValorArredondado() {
        int segundosInicio = this.horaInicio.getHora() * 3600 + this.horaInicio.getMinuto() * 60 + this.horaInicio.getSegundo();
        int segundosFim = this.horaFim.getHora() * 3600 + this.horaFim.getMinuto() * 60 + this.horaFim.getSegundo();
        int duracaoEmSegundos = segundosFim - segundosInicio;
        if (duracaoEmSegundos <= 0){ 
        return 0.0;
        }
        int minutos = duracaoEmSegundos / 60;
        if (duracaoEmSegundos % 60 > 0) {
        minutos++;
        }   
        return minutos * 1.0;
    }
}
