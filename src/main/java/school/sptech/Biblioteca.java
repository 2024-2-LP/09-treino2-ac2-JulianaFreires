package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome, List<Livro> livros) {
        this.nome = nome;
        this.livros = new ArrayList<Livro>();
    }

    public Biblioteca() {
        this.livros = new ArrayList<Livro>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarLivro(Livro livro) throws Exception{

        if(livro == null) {
            throw new ArgumentoInvalidoException();
        }
        if(livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new ArgumentoInvalidoException();
        }
        if(livro.getAutor() == null) {
            throw new ArgumentoInvalidoException();
        }
        if(livro.getAutor().isBlank()){
            throw new ArgumentoInvalidoException();
        }
        if(livro.getDataPublicacao() == null) {
            throw new ArgumentoInvalidoException();
        }

            livros.add(livro);



    }

    public void removerLivroPorTitulo(String titulo) throws Exception{

        Boolean encontrado = false;

        if(titulo == null || titulo.isEmpty() || titulo.isBlank()){
            throw new ArgumentoInvalidoException("O título não pode ser nulo, vazio ou conter apenas espaços.");

        }else{
            for( int i = 0 ; i < livros.size() ; i++){
                Livro livroDaVez = livros.get(i);

                if(livroDaVez.getTitulo().equalsIgnoreCase(titulo)){
                    livros.remove(livroDaVez);
                    encontrado = true;
                }
            }
        }
        if(encontrado == false){
            throw new LivroNaoEncontradoException("Livro não encontrado");
        }
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        Boolean encontrado = false;

        Livro livroEncontrado = new Livro();

        if(titulo == null || titulo.isEmpty() || titulo.isBlank()){
            throw new ArgumentoInvalidoException("O título não pode ser nulo, vazio ou conter apenas espaços.");

        }else{
            for( int i = 0 ; i < livros.size() ; i++){
                Livro livroDaVez = livros.get(i);

                if(livroDaVez.getTitulo().equalsIgnoreCase(titulo)){
                    encontrado = true;
                    livroEncontrado = livroDaVez;
                }
            }
        }

        if(encontrado == false){
            throw new LivroNaoEncontradoException("Livro não encontrado");
        }
        return livroEncontrado;

    }

    public Integer contarLivros(){

        Integer totalLivros = 0;

        for( int i = 0 ; i < livros.size() ; i++) {
            Livro livroDaVez = livros.get(i);
            totalLivros++;
        }
        return totalLivros;

    }

    public List<Livro> obterLivrosAteAno(Integer ano){
        List<Livro> livrosAno = new ArrayList<Livro>();

        for( int i = 0 ; i < livros.size() ; i++) {
            Livro livroDaVez = livros.get(i);
            if(livroDaVez.getDataPublicacao().getYear() <= ano){
                livrosAno.add(livroDaVez);
            }
        }
        return livrosAno;

    }

    public List<Livro> retornarTopCincoLivros(){
        List<Livro> topLivros = new ArrayList<Livro>();

        if (livros.isEmpty()) {
            return topLivros;
        }

        Livro livro = livros.get(0);
        for( int i = 0 ; i < livros.size() ; i++) {
            Livro livroDaVez = livros.get(i);
            topLivros.add(livroDaVez);

            topLivros.sort((livro1, livro2) -> Double.compare(livro2.calcularMediaAvaliacoes(), livro1.calcularMediaAvaliacoes()));


        }

        if (topLivros.size() > 5) {
            topLivros.subList(5, topLivros.size()).clear(); // Remove todos os elementos além do índice 4
        }

        return topLivros;
    }

}
