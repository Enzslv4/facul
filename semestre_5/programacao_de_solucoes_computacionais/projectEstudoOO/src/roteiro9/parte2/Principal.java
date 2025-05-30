package roteiro9.parte2;

public class Principal {
    public static void main(String[] args){
        Quadrado quadrado01 = new Quadrado(5);
        Retangulo retangulo01 = new Retangulo(2, 4);
        Circulo circulo01 = new Circulo(5);

        System.out.println(quadrado01.getNomeFigura());
        System.out.println(quadrado01.getArea());
        System.out.println(quadrado01.getPerimetro());

        System.out.println(retangulo01.getNomeFigura());
        System.out.println(retangulo01.getArea());
        System.out.println(retangulo01.getPerimetro());

        System.out.println(circulo01.getNomeFigura());
        System.out.println(circulo01.getArea());
        System.out.println(circulo01.getPerimetro());
    }
}