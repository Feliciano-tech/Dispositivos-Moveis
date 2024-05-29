package com.example.cadastrado_aluno_ac02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText etRa;
    private EditText etNome;
    private EditText etCep;
    private EditText etLogradouro;
    private EditText etComplemento;
    private EditText etBairro;
    private EditText etCidade;
    private EditText etUf;
    private Button btnBuscarCep;
    private Button btnSalvar;
    private Button btnBuscarAluno;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etRa = findViewById(R.id.etRa);
        etNome = findViewById(R.id.etNome);
        etCep = findViewById(R.id.etCep);
        etLogradouro = findViewById(R.id.etLogradouro);
        etComplemento = findViewById(R.id.etComplemento);
        etBairro = findViewById(R.id.etBairro);
        etCidade = findViewById(R.id.etCidade);
        etUf = findViewById(R.id.etUf);
        btnBuscarCep = findViewById(R.id.btnBuscarCep);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnBuscarAluno = findViewById(R.id.btnBuscarAluno);

        requestQueue = Volley.newRequestQueue(this);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cep = etCep.getText().toString();
                buscarCep(cep);
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = new Aluno(
                        Integer.parseInt(etRa.getText().toString()),
                        etNome.getText().toString(),
                        etCep.getText().toString(),
                        etLogradouro.getText().toString(),
                        etComplemento.getText().toString(),
                        etBairro.getText().toString(),
                        etCidade.getText().toString(),
                        etUf.getText().toString()
                );
                salvarAluno(aluno);
            }
        });

        btnBuscarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaAlunosActivity.class);
                startActivity(intent);
            }
        });
    }

    private void buscarCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            etLogradouro.setText(response.getString("logradouro"));
                            etComplemento.setText(response.getString("complemento"));
                            etBairro.setText(response.getString("bairro"));
                            etCidade.setText(response.getString("localidade"));
                            etUf.setText(response.getString("uf"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(stringRequest);
    }

    private void salvarAluno(Aluno aluno) {
        String url = "https://66564ca29f970b3b36c4eebd.mockapi.io/api/v1/Alunos";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ra", aluno.getRa());
            jsonObject.put("nome", aluno.getNome());
            jsonObject.put("cep", aluno.getCep());
            jsonObject.put("logradouro", aluno.getLogradouro());
            jsonObject.put("complemento", aluno.getComplemento());
            jsonObject.put("bairro", aluno.getBairro());
            jsonObject.put("cidade", aluno.getCidade());
            jsonObject.put("uf", aluno.getUf());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {
                        if (error instanceof NetworkError) {

                        } else if (error instanceof ServerError) {

                        } else if (error instanceof TimeoutError) {

                        } else {

                        }
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
}
