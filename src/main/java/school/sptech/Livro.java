package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private List<Avaliacao> avaliacoes;

    public Livro(String titulo, LocalDate dataPublicacao, String autor) {
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
        this.autor = autor;
        this.avaliacoes = new ArrayList<Avaliacao>();
    }

    public Livro() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", avaliacoes=" + avaliacoes +
                '}';
    }

    public void adicionarAvaliacao(String descricao, Double qtdEstrelas) throws ArgumentoInvalidoException{

        if (descricao == null || qtdEstrelas == null || descricao.isBlank()) {
            throw new ArgumentoInvalidoException("A descrição não pode ser nula, vazia ou conter apenas espaços em branco.");
        }

        if (qtdEstrelas < 0 || qtdEstrelas > 5) {
            throw new ArgumentoInvalidoException("A quantidade de estrelas deve estar entre 0 e 5.");
        }


        Avaliacao adicionar = new Avaliacao(descricao,qtdEstrelas);
        avaliacoes.add(adicionar);

    }

    public Double calcularMediaAvaliacoes(){
        Double media = 0.0;

        if(avaliacoes.isEmpty()){
            return media;
        }


        for(int i = 0; i < avaliacoes.size(); i++){

            Avaliacao avaliacaoDaVez = avaliacoes.get(i);

            media += avaliacaoDaVez.getQtdEstrelas();
        }
        return media/avaliacoes.size();
    }
}
