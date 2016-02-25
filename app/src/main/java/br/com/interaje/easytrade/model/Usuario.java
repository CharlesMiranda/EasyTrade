package br.com.interaje.easytrade.model;

/**
 * Created by charles on 21/11/15.
 */
public class Usuario {

    private Long id;
    private String nome, email, senha;

    // Serve para receber mensagens do servidor
    private String idGcmUser;

    public Usuario() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIdGcmUser() {
        return idGcmUser;
    }

    public void setIdGcmUser(String idGcmUser) {
        this.idGcmUser = idGcmUser;
    }
}
