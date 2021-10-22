package programa;

import java.util.Scanner;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data() {
        dia = 0;
        mes = 0;
        ano = 0;
    }

    public void novadata() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Introduza a data(dd/mm/aaaa): ");
            String data;
            data = sc.nextLine();
            String div[] = data.split("/");
            if (div.length == 3) {
                dia = Integer.parseInt(div[0]);
                mes = Integer.parseInt(div[1]);
                ano = Integer.parseInt(div[2]);
                if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || ano < 0) {
                    System.out.println("Data Invalida!");
                    continue;
                } else if (dia > 28 && mes == 2) {
                    System.out.println("Data Invalida!");
                    continue;
                } else if (dia > 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) {
                    System.out.println("Data Invalida!");
                    continue;
                } else
                    break;
            } else {
                System.out.println("Data Invalida!");
                continue;
            }
        }
    }

    public void dataDevolucao(Data d) {
        int prazo = 30; // assumindo o prazo de devolucao de 30 dias
        mes = d.mes;
        dia = d.dia;
        ano = d.ano;
        while (prazo > 0) {

            if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10) {
                int a = 31 - dia;
                if (a < prazo) {
                    dia = 1;
                    mes++;
                } else {
                    dia += prazo;
                }
                prazo -= (a + 1);// se trocar o mes ,vai colocar no dia 1 do outro mes, logo sera a + 1
            } else if (mes == 12) {
                int a = 31 - dia;
                if (a < prazo) {
                    dia = 1;
                    mes = 1;
                    ano++;
                } else {
                    dia += prazo;
                }
                prazo -= (a + 1);
            } else if (mes == 2) {
                int a = 28 - dia;
                if (a < prazo) {
                    dia = 1;
                    mes++;
                } else {
                    dia += prazo;
                }
                prazo -= (a + 1);
            } else {
                int a = 30 - dia;
                if (a < prazo) {
                    dia = 1;
                    mes++;
                } else {
                    dia += prazo;
                }
                prazo -= (a + 1);
            }
        }
    }

    public boolean datasIguais(Data d) {
        if (d.dia == dia && d.mes == mes && d.ano == ano)
            return true;
        else
            return false;
    }

    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }
}