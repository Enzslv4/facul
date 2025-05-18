package roteiro8.parte1;

public class VeiculoCarga extends Veiculo {
    private int pesoMaximo;
    public VeiculoCarga(String placa, int anoFabricacao, int pesoMaximo) {
        super(placa, anoFabricacao);
        this.pesoMaximo = pesoMaximo;
    }
    
    public int getPesoMaximo() {
        return pesoMaximo;
    }
    public void setPesoMaximo(int pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }
}
