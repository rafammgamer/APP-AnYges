package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class TelaLogin extends AppCompatActivity {

    EditText usuario, senha;
    Dados dd = new Dados();
    Context a = this;
    Class<?> b = TelaHome.class;
    Class<?> f = TelaOpcao.class;
    Intent c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        usuario = findViewById(R.id.txLogin);
        senha = findViewById(R.id.txSenha);
    }
    Conexao bd = new Conexao();

    public void Acesso(View v){
        String texto1 = usuario.getText().toString();
        String texto2 = senha.getText().toString();
        dd.Verificar(texto1,texto2);
        bd.entBanco(this);

        try{
            bd.RS = bd.stmt.executeQuery("SELECT email_usuario, senha_usuario FROM tblUsuario WHERE email_usuario ='" +texto1+ "' and senha_usuario ='" +texto2+ "';");
            if (bd.RS.next()) {
                String emailUsuario = bd.RS.getString("email_usuario");
                dd.pegaDados(emailUsuario);
                dd.recebeAcesso(a,b);
                c=dd.enviaAcesso();
                c.putExtra("email_usuario", emailUsuario);
                startActivity(c);
                finish();
            } else if (dd.Verificar(texto1,texto2)){
                dd.recebeAcesso(a,b);
                c=dd.enviaAcesso();
                startActivity(c);
                finish();
            }
            else {
                Toast.makeText(this, "Usuário e/ou senha incorretos. Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException ex) {
            Toast.makeText(this, "erro no acesso", Toast.LENGTH_SHORT).show();
        }

        /*if(dd.Verificar(texto1,texto2)){
            dd.recebeAcesso(a,b);
            c=dd.enviaAcesso();
            startActivity(c);
            finish();
        }else{
            Toast.makeText(this, "Usuário e/ou senha incorretos. Tente novamente.", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void Voltar(View v){
        dd.recebeAcesso(a,f);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }
}