package roteiro9.parte2;

public class Quadrado implements FiguraGeometrica{
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }
    public double getLado() {
        return lado;
    }
    public void setLado(double lado) {
        this.lado = lado;
    }
    public String getNomeFigura() {
        return "Quadrado";
    }
    public double getArea() {
        return this.lado * this.lado;
    }
    public double getPerimetro() {
        return this.lado * 4;
    }
}