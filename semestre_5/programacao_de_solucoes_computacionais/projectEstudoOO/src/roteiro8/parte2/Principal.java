package roteiro8.parte2;

public class Principal {

     public static void main(String[] args) {
     // Veiculo v01 = new Veiculo("11111-BA", 2010); Não é possível instanciar a classe Veiculo, pois ela é abstrata.
     VeiculoCarga v02 = new VeiculoCarga("22222-BA", 2011, 5000);
     VeiculoPequeno v03 = new VeiculoPequeno("33333-BA", 2012, "Fusca");
     VeiculoPasseio v04 = new VeiculoPasseio("33333-BA", 2012, 5);

     System.out.println("Pedágio v02 : " + v02.calcPedagio());
     System.out.println("Pedágio v03 : " + v03.calcPedagio());
     System.out.println("Pedágio v04 : " + v04.calcPedagio());
         
     } 
}