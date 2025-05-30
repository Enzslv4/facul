package roteiro9.parte2;

public class Circulo implements FiguraGeometrica {
    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }
    public String getNomeFigura() {
        return "Circulo";
    }
    public double getArea() {
        return 3.14159265358979323846 * (this.raio * this.raio);
    }
    public double getPerimetro() {
        return 2 * 3.14159265358979323846 * this.raio;
    }
}