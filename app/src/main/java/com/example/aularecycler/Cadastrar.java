package com.example.aularecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;

public class Cadastrar extends AppCompatActivity {
    static ArrayList<Produto> listaProdutos;
    EditText nomec,catc,valorc;
    RecyclerView recycler;
    Adaptador adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        getSupportActionBar().hide();
        nomec = findViewById(R.id.nomec);
        catc = findViewById(R.id.catc);
        valorc = findViewById(R.id.valorc);

    }
    public void cadastra(View v){
        if(valida()){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            reference.child("Produtos").child(nomec.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //executado quando o aplicativo encontrar alguma coisa no data base
                    if(snapshot.exists()){
                        Toast.makeText(Cadastrar.this, "O produto já existe!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        createP();
                    }
                    adapter = new Adaptador(Cadastrar.this, listaProdutos, new Adaptador.OnItemClickListener() {
                        @Override
                        public void onItemClick(Produto p) {
                            //Aqui é o que vai acontecer quando clicar em um item
                            Toast.makeText(Cadastrar.this, p.getNome(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        else{
            Toast.makeText(this, "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
        }
    }
    public void voltar(View v){
        super.onBackPressed();
    }
    public Boolean valida(){
        if(nomec.getText().toString().isEmpty()||catc.getText().toString().isEmpty()||valorc.getText().toString().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    public void createP() {
        String n = nomec.getText().toString();
        String c = catc.getText().toString();
        Float vc = Float.parseFloat(valorc.getText().toString());
        Produto p1 = new Produto(n, c, vc);
        p1.salvar();
        Toast.makeText(this, "Produto criado", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }
}