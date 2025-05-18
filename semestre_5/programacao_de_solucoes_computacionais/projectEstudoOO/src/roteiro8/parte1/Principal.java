package roteiro8.parte1;

public class Principal {
    // Veiculo v01 = new Veiculo("11111-BA", 2010); Não é possível instanciar a classe Veiculo, pois ela é abstrata.
    Veiculo v02 = new VeiculoCarga("22222-BA", 2011, 5000);
    Veiculo v03 = new VeiculoPequeno("33333-BA", 2012, "Fusca");
    Veiculo v04 = new VeiculoPasseio("33333-BA", 2012, 5);
}
