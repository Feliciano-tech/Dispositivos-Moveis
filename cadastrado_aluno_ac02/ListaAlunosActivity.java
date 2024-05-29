package com.example.cadastrado_aluno_ac02;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadastrado_aluno_ac02.Aluno;
import com.example.cadastrado_aluno_ac02.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AlunoAdapter adapter;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        recyclerView = findViewById(R.id.recyclerViewAlunos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AlunoAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);
        buscarAlunos();
    }

    private void buscarAlunos() {
        String url = "https://66564ca29f970b3b36c4eebd.mockapi.io/api/v1/Alunos";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject aluno = response.getJSONObject(i);
                                int ra = aluno.getInt("ra");
                                String nome = aluno.getString("nome");
                                String cep = aluno.getString("cep");
                                String logradouro = aluno.getString("logradouro");
                                String complemento = aluno.getString("complemento");
                                String bairro = aluno.getString("bairro");
                                String cidade = aluno.getString("cidade");
                                String uf = aluno.getString("uf");

                                adapter.addAluno(new Aluno(ra, nome, cep, logradouro, complemento, bairro, cidade, uf));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {
                        error.printStackTrace();
                        if (error instanceof TimeoutError) {
                            Toast.makeText(ListaAlunosActivity.this, "Timeout error!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ListaAlunosActivity.this, "Erro ao recuperar alunos!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }
}
