package com.example.tsi.firebase;

public class Contato {
    private String nome;
    private String email;

    public Contato(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // Construtor vazio, exigência do Firebase:
    public Contato() { }

    public String getNome() {  return nome;  }

    public void setNome(String nome) {  this.nome = nome;  }

    public String getEmail() {  return email;  }

    public void setEmail(String email) {  this.email = email;  }
}


