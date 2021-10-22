package programa;

public class Requisicao {//Class da requisiçoes feitas na Biblioteca
    private livro livro1;
    private leitor leitor1;
    private Data dtReq;
    private Data dtDev;

    public Requisicao(leitor l, Data dataReq, Data dataDev) {
        leitor1 = l;
        dtReq = dataReq;
        dtDev = dataDev;
    }

    public void adicionaLivro(livro l) {
        livro1 = l;
        l.requisita();
    }

    public Data dataRequisicao() {
        return dtReq;
    }

    public Data dataDevolucao() {
        return dtDev;
    }

    public leitor printLeitor() {
        return leitor1;
    }

    public livro acedeLivro() {
        return livro1;
    }

    public String toString() {
        return "  " + livro1.toString() + "\n  " + leitor1.toString() + "\n  Data de Requisição: " + dtReq.toString()
                + "\n  Data de Devolução: " + dtDev.toString() + "\n----------------------------------------";
    }
}
