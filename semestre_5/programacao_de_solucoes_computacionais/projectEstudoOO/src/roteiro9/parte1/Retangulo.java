package roteiro9.parte1;

public class Retangulo implements FiguraGeometrica{
    private double altura;
    private double largura;
    public Retangulo(double altura, double largura) {
        this.altura = altura;
        this.largura = largura;
    }
    public double getAltura() {
        return altura;
    }
    public void setAltura(double altura) {
        this.altura = altura;
    }
    public double getLargura() {
        return largura;
    }
    public void setLargura(double largura) {
        this.largura = largura;
    }
    public String getNomeFigura() {
        return "Retangulo";
    }
    public double getArea() {
        return this.altura * this.largura;
    }
    public double getPerimetro() {
        return this.altura * 2 + this.largura * 2;
    }
    
}