package com.example.cadastrado_aluno_ac02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cadastrado_aluno_ac02.Aluno;
import com.example.cadastrado_aluno_ac02.R;
import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {
    private List<Aluno> alunos;

    public AlunoAdapter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_aluno, parent, false);
        return new AlunoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlunoViewHolder holder, int position) {
        Aluno currentAluno = alunos.get(position);
        holder.textViewRa.setText(String.valueOf(currentAluno.getRa()));
        holder.textViewNome.setText(currentAluno.getNome());
        holder.textViewCep.setText(currentAluno.getCep());
        holder.textViewLogradouro.setText(currentAluno.getLogradouro());
        holder.textViewComplemento.setText(currentAluno.getComplemento());
        holder.textViewBairro.setText(currentAluno.getBairro());
        holder.textViewCidade.setText(currentAluno.getCidade());
        holder.textViewUf.setText(currentAluno.getUf());
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
        notifyItemInserted(alunos.size() - 1);
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewRa;
        public TextView textViewNome;
        public TextView textViewCep;
        public TextView textViewLogradouro;
        public TextView textViewComplemento;
        public TextView textViewBairro;
        public TextView textViewCidade;
        public TextView textViewUf;

        public AlunoViewHolder(View itemView) {
            super(itemView);
            textViewRa = itemView.findViewById(R.id.textViewRa);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewCep = itemView.findViewById(R.id.textViewCep);
            textViewLogradouro = itemView.findViewById(R.id.textViewLogradouro);
            textViewComplemento = itemView.findViewById(R.id.textViewComplemento);
            textViewBairro = itemView.findViewById(R.id.textViewBairro);
            textViewCidade = itemView.findViewById(R.id.textViewCidade);
            textViewUf = itemView.findViewById(R.id.textViewUf);
        }
    }
}
