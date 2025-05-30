package roteiro9.parte1;

public class Principal {
    public static void main(String[] args){
        Quadrado quadrado01 = new Quadrado(5);
        Retangulo retangulo01 = new Retangulo(2, 4);

        System.out.println(quadrado01.getNomeFigura());
        System.out.println(quadrado01.getArea());
        System.out.println(quadrado01.getPerimetro());

        System.out.println(retangulo01.getNomeFigura());
        System.out.println(retangulo01.getArea());
        System.out.println(retangulo01.getPerimetro());
    }

}
/* 6 - Sim, pois o polimorfísmo conceitua-se em classes diferentes que possuiem os mesmos métodos. */