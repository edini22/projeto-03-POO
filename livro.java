package programa;

import java.util.*;

public class livro {
    private String nome;
    private String autor;
    private boolean disponivel;// false se esta requesitado.

    public livro() {
        System.out.print("Nome do livro: ");
        Scanner sc = new Scanner(System.in);
        nome = sc.nextLine();
        System.out.print("Nome do autor do livro: ");
        autor = sc.nextLine();
        disponivel = true;
    }

    public livro(String nome1, String autor1) {
        nome = nome1;
        autor = autor1;
        disponivel = true;
    }

    public boolean disponibilidade() {
        return disponivel;
    }

    public String mostraNome() {
        return nome;
    }

    public void requisita() {
        disponivel = false;
    }

    public void entrega() {
        disponivel = true;
    }

    public String toString() {
        return "Nome do livro:" + nome + "\tNome do Autor:" + autor;
    }
}
