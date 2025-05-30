package roteiro8.parte2;

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
    @Override
    public double calcPedagio() {
        super.taxaPedagio = 2.0;
        return super.taxaPedagio * this.pesoMaximo;
    }
}