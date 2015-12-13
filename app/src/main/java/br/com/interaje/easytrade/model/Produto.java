package br.com.interaje.easytrade.model;

/**
 * Created by charles on 21/11/15.
 */
public class Produto {

    private Long id;
    private String nome, descricao;
    private Double valor;
    private int quantidade;
    private byte[] foto;

    public Produto(){

    }

    public Produto(String nome, Double valor, int quantidade, String descricao){
        setNome(nome);
        setValor(valor);
        setQuantidade(quantidade);
        setDescricao(descricao);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
