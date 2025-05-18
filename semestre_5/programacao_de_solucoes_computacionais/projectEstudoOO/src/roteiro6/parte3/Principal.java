package roteiro6.parte3;

public class Principal {
    public static void main(String[] args) {

        System.out.println("Exemplo 1:");
        Tempo horaInicio1 = new Tempo(10, 0, 0);
        Tempo horaFim1 = new Tempo(10, 0, 1);
        Ligacao lig01 = new Ligacao("94616385", "98234761", "São Paulo", "Campinas", horaInicio1);
        String pertencente01 = lig01.Pertence("98234761");
        System.out.println("Verificação do número: " + pertencente01);
        lig01.setHoraFim(horaFim1);
        System.out.println("Valor exato: R$" + lig01.calculaValor());
        System.out.println("Valor arredondado: R$" + lig01.calculaValorArredondado());
        System.out.println("");

        System.out.println("Exemplo 2:");
        Tempo horaInicio2 = new Tempo(10, 0, 0);
        Tempo horaFim2 = new Tempo(10, 5, 1);
        Ligacao lig02 = new Ligacao("97324567", "98564720", "Belo Horizonte", "Uberlândia", horaInicio2);
        String pertencente02 = lig02.Pertence("98234761");
        System.out.println("Verificação do número: " + pertencente02);
        lig02.setHoraFim(horaFim2);
        System.out.println("Valor exato R$" + lig02.calculaValor());
        System.out.println("Valor arredondado: R$" + lig02.calculaValorArredondado());
        System.out.println("");

        System.out.println("Exemplo 3:");
        Tempo horaInicio3 = new Tempo(10, 0, 0);
        Tempo horaFim3 = new Tempo(10, 5, 35);
        Ligacao lig03 = new Ligacao("99781342", "98452361", "Curitiba", "Florianópolis", horaInicio3);
        String pertencente03 = lig03.Pertence("99781342");
        System.out.println("Verificação do número: " + pertencente03);
        lig03.setHoraFim(horaFim3);
        System.out.println("Valor exato: R$" + lig03.calculaValor());
        System.out.println("Valor arredondado: R$" + lig03.calculaValorArredondado());
        System.out.println("");

        System.out.println("Exemplo 4:");
        Tempo horaInicio4 = new Tempo(9, 55, 50);
        Tempo horaFim4 = new Tempo(10, 3, 53);
        Ligacao lig04 = new Ligacao("96874215", "97643109", "Salvador", "Elísio Medrado", horaInicio4);
        String pertencente04 = lig04.Pertence("97643109");
        System.out.println("Verificação do número: " + pertencente04);
        lig04.setHoraFim(horaFim4);
        System.out.println("Valor exato: R$" + lig04.calculaValor());
        System.out.println("Valor arredondado: R$" + lig04.calculaValorArredondado());
        System.out.println("");
    }
}
