package roteiro8.parte3;

public class Principal {

     public static void main(String[] args) {
          // Veiculo v01 = new Veiculo("11111-BA", 2010); Não é possível instanciar a classe Veiculo, pois ela é abstrata.
          VeiculoCarga v02 = new VeiculoCarga("22222-BA", 2011, 5000);
          VeiculoPequeno v03 = new VeiculoPequeno("33333-BA", 2012, "Fusca");
          VeiculoPasseio v04 = new VeiculoPasseio("33333-BA", 2012, 5);
          VeiculoPasseio v05 = new VeiculoPasseio("44444-BA", 2022, 7);

          Cliente c01 = new Cliente("821397652542", "Jose", "Rua", "email", "8173490325");
          c01.setVeiculoCliente(v05);
          System.out.println("Pedagio do carro cliente 1: " + c01.getVeiculoCliente().calcPedagio());

          System.out.println("Pedágio v02 : " + v02.calcPedagio());
          System.out.println("Pedágio v03 : " + v03.calcPedagio());
          System.out.println("Pedágio v04 : " + v04.calcPedagio());
         
     } 
}