package roteiro6.parte1;

public class Principal {
    public static void main(String[] args) {
        Ligacao lig01 = new Ligacao("121212","565656" , "A", "B", "10:15:02");
        System.out.println(lig01.getHoraInicio());
        lig01.setHoraFim("10:20:35");
        System.out.println(lig01.getHoraFim());
    }
}
