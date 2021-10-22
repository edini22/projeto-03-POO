package programa;

import java.util.Scanner;

public class leitor {
    private int numero;
    private String nome;

    public leitor() {
        System.out.print("Nome: ");
        Scanner sc = new Scanner(System.in);
        nome = sc.nextLine();
        System.out.print("Numero de utente: ");
        numero = sc.nextInt();
    }

    public leitor(int numero1, String nome1) {
        nome = nome1;
        numero = numero1;
    }

    public String toString() {
        return "Nome:" + nome + "\tNumero do utente:" + numero;
    }

}
