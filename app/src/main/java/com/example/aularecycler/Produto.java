package com.example.aularecycler;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Produto {
    String nome, categoria;
    float preco;

    public Produto(String nome, String categoria, float preco) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }
    public Produto() {}
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    public void salvar() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Produtos").child(nome).setValue(this);
    }
}
