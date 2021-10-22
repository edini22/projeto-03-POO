package programa;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class biblioteca {
    public static void main(String[] args) {
        biblioteca b = new biblioteca();
        b.adicionaLivro("Programação Orientada a Objetos com Java", "Michael Kolling e David J. Barnes");
        b.adicionaLivro("Segurança em Redes Informáticas", "André Zúquete");
        b.adicionaleitor(2020213717, "Eduardo Figueredo");
        b.adicionaleitor(2020212586, "Maria Rego");
        int escolha;
        Scanner stdin = new Scanner(System.in);
        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Lista de livros disponiveis");
            System.out.println("2 - Requisitar um livro");
            System.out.println("3 - Lista de livros requisitados numa data");
            System.out.println("4 - Lista de livros");
            System.out.println("5 - Entregar livro");
            System.out.println("6 - Adiciona livro");
            System.out.println("7 - Adiciona leitor");
            System.out.println("0 - Sair");
            escolha = stdin.nextInt();
            switch (escolha) {
            case 1:
                b.listaLivrosDisponíveis();
                break;
            case 2:
                Data dtReq = new Data();
                dtReq.novadata();
                Data dtDev = new Data();
                dtDev.dataDevolucao(dtReq);
                if (b.leitores.size() == 0) {
                    System.out.println("Nao existem leitores registados!!");
                    break;
                }
                System.out.println("Lista dos leitores:");
                for (int i = 0; i < b.leitores.size(); i++) {
                    leitor l = b.leitores.get(i);
                    System.out.printf("%d-> ", i);
                    System.out.println(l.toString());
                }
                while (true) {
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Introduza o indice do leitor");
                    int indice = sc3.nextInt();
                    if (indice > b.leitores.size() - 1 || indice < 0)
                        continue;
                    else {
                        leitor l = b.leitores.get(indice);
                        Requisicao req;
                        req = b.requisitaLivro(l, dtReq, dtDev);
                        b.requisicoes.add(req);
                        break;
                    }
                }
                break;
            case 3:
                Data d = new Data();
                d.novadata();
                b.listaRequisitados(d);
                break;

            case 4:
                b.imprimeBiblioteca();
                break;
            case 5:
                if (b.requisicoes.size() == 0) {
                    System.out.println("Nao existem livros requisitados!");
                } else {
                    int c = 0;
                    boolean ciclo = true;
                    for (int i = 0; i < b.livros.size(); i++) {
                        livro li = b.livros.get(i);
                        if (!li.disponibilidade()) {
                            if (c == 0)
                                System.out.println("Lista dos livros para entrega:\n");
                            c++;
                            System.out.printf("%d-> ", i);
                            System.out.println(li.toString());
                        }
                    }
            
                    if (c == 0) {
                        System.out.println("Nao existem livros para entregar!");
                        ciclo = false;
                    }
            
                    while (ciclo == true) {
                        System.out.println("Introduza um livro para entregar(referencia do livro)");
                        int indice = stdin.nextInt();
                        if (indice > b.livros.size() - 1 || indice < 0)
                            continue;
                        livro livro = b.livros.get(indice);
                        if (livro.disponibilidade() == true)
                            continue;
                        else {
                            b.entregaLivro(livro);
                            ciclo = false;
                        }
                    }
                }
                break;
            case 6:
                b.adicionaLivro();
                break;
            case 7:
                b.adicionaleitor();
                break;
            case 0:
                System.exit(0);
            }
        } while (escolha != 0);

    }

    private List<livro> livros;
    private List<leitor> leitores;
    private List<Requisicao> requisicoes;

    public biblioteca() {
        livros = new ArrayList<livro>();
        leitores = new ArrayList<leitor>();
        requisicoes = new ArrayList<Requisicao>();
    }

    public void adicionaLivro() {
        livros.add(new livro());
    }

    public void adicionaleitor() {
        leitores.add(new leitor());
    }

    public void adicionaLivro(String nome1, String autor1) {
        livros.add(new livro(nome1, autor1));
    }

    public void adicionaleitor(int num, String nome) {
        leitores.add(new leitor(num, nome));
    }

    // Para imprimir na consola com cores a mensagem
    private final String RESET = "\033[0m";
    private final String RED = "\033[0;31m";
    private final String GREEN = "\033[0;32m";

    public void imprimeBiblioteca() {
        System.out.println("A Lista de livros é:");
        for (int i = 0; i < livros.size(); i++) {
            livro li = livros.get(i);
            System.out.print(li.toString());
            if (li.disponibilidade()) {
                System.out.println(" ->" + GREEN + "Disponivel" + RESET);
            }

            else
                System.out.println(" ->" + RED + "Requisitado" + RESET);
        }
    }

    public void listaLivrosDisponíveis() {
        int c = 0;
        for (int i = 0; i < livros.size(); i++) {
            livro li = livros.get(i);
            if (li.disponibilidade()) {
                if (c == 0)
                    System.out.println("Livros disponiveis: ");
                c++;
                System.out.println(li.toString());
            }
        }

    }

    public void listaRequisitados(Data d) {
        int c = 0;
        if (requisicoes.size() == 0)
            System.out.println("Nao existem livros que foram requisitados nesta data!");
        else {
            for (Requisicao r : requisicoes) {
                Data date = new Data();
                date = r.dataRequisicao();
                if (date.datasIguais(d)) {
                    if (c == 0) {
                        System.out.println("A Lista de livros requesitados na data " + d.toString() + " e:");
                    }
                    c++;
                    System.out.println(r.toString());
                }
            }
            if (c == 0)
                System.out.println("Nao existem livros que foram requisitados nesta data!");
        }
    }

    public Requisicao requisitaLivro(leitor l, Data dtReq, Data dtDev) {
        Requisicao r = new Requisicao(l, dtReq, dtDev);
        Scanner sc2 = new Scanner(System.in);
        boolean ciclo = true;
        int c = 0;

        for (int i = 0; i < livros.size(); i++) {
            livro li = livros.get(i);
            if (li.disponibilidade()) {
                if (c == 0)
                    System.out.println("Lista dos livros disponiveis para requisitar:\n");
                c++;
                System.out.printf("%d-> ", i);
                System.out.println(li.toString());
            }
        }

        if (c == 0) {
            System.out.println("Nao existem livros disponiveis para requisitar!");
            ciclo = false;
        }

        while (ciclo == true) {
            System.out.println("Introduza um livro para requisitar(referencia do livro)");
            int indice = sc2.nextInt();
            if (indice > livros.size() - 1 || indice < 0)
                continue;
            livro livro = livros.get(indice);
            if (livro.disponibilidade() == false)
                continue;
            else {
                r.adicionaLivro(livro);
                System.out.println(GREEN + "Livro Requisitado" + RESET);
                ciclo = false;
            }
        }
        return r;
    }

    public void entregaLivro(livro l) {
        l.entrega();
        System.out.printf(" O livro com o nome \"%s\" foi entregue", l.mostraNome());
    }

}
