package roteiro6.parte2;

public class Principal {
    public static void main(String[] args) {
        Tempo horaInicioCompleta = new Tempo(10, 20, 30);
        Ligacao lig01 = new Ligacao("121212","565656" , "A", "B", horaInicioCompleta);
        System.out.println(lig01.getHoraInicio().getHora() + ":" +  lig01.getHoraInicio().getMinuto() + ":" + lig01.getHoraInicio().getSegundo() );
        }
}
