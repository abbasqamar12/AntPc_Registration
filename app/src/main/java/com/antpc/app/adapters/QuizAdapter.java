package com.antpc.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antpc.app.R;
import com.antpc.app.listeners.QuizAnswerListener;
import com.antpc.app.models.QuizModel;

import java.util.List;


public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private Context context;
    private List<QuizModel> quizQueList;
    private QuizAnswerListener quizListener;
    private LayoutInflater layoutInflater;

    public QuizAdapter(Context context, List<QuizModel> quizQueList, QuizAnswerListener quizListener) {
        this.context = context;
        this.quizQueList = quizQueList;
        this.quizListener = quizListener;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_quiz, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        int listPosition =position;

        holder.txtQuestion.setText(quizQueList.get(position).getQuestion());
        holder.radioOption1.setText(quizQueList.get(position).getOption1());
        holder.radioOption2.setText(quizQueList.get(position).getOption2());
        holder.radioOption3.setText(quizQueList.get(position).getOption3());
        holder.radioOption4.setText(quizQueList.get(position).getOption4());
       // holder.setIsRecyclable(false);

/*        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
              *//*  switch (checkedId) {
                    case R.id.radioOption1:
                        quizListener.selectedAnswer("A", listPosition);
                        Toast.makeText(context, "Selected Radio Button is : " + checkedId + "Position " + listPosition, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioOption2:
                        quizListener.selectedAnswer("B", listPosition);
                        Toast.makeText(context, "Selected Radio Button is : " + checkedId + "Position " + listPosition, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioOption3:
                        quizListener.selectedAnswer("C", listPosition);
                        Toast.makeText(context, "Selected Radio Button is : " + checkedId + "Position " + listPosition, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioOption4:
                        quizListener.selectedAnswer("D", listPosition);
                        Toast.makeText(context, "Selected Radio Button is : " + checkedId + "Position " + listPosition, Toast.LENGTH_SHORT).show();
                        break;
                }*//*
                // on below line we are displaying a toast message.
                //Toast.makeText(context, "Selected Radio Button is : " + checkedId + "Position " + listPosition, Toast.LENGTH_SHORT).show();
            }
        });*/

        holder.radioOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizListener.selectedAnswer("A", listPosition);
                setOptions(holder,true,false,false,false);
            }
        });

        holder.radioOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizListener.selectedAnswer("B", listPosition);
                setOptions(holder,false,true,false,false);
            }
        });

        holder.radioOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizListener.selectedAnswer("C", listPosition);
                setOptions(holder,false,false,true,false);
            }
        });
        holder.radioOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizListener.selectedAnswer("D", listPosition);
                setOptions(holder,false,false,false,true);
            }
        });
        setSelectedAnswerOnList(holder,position, quizQueList.get(position).getSelectedOption());

    }

    private void setSelectedAnswerOnList(QuizViewHolder holder, int position, String selectedOption) {
        switch (selectedOption) {
            case "A":
                setOptions(holder,true,false,false,false);
                break;
            case "B":
                setOptions(holder,false,true,false,false);
                break;
            case "C":
                setOptions(holder,false,false,true,false);
                break;
            case "D":
                setOptions(holder,false,false,false,true);
                break;
            default:
                setOptions(holder,false,false,false,false);
        }
    }

    private void setOptions(QuizViewHolder holder, boolean optionA, boolean optionB, boolean optionC, boolean optionD) {
        holder.radioOption1.setSelected(optionA);
        holder.radioOption2.setSelected(optionB);
        holder.radioOption3.setSelected(optionC);
        holder.radioOption4.setSelected(optionD);
    }

  /*  @Override
    public int getItemViewType(int position) {
        return position;
    }
*/
    @Override
    public int getItemCount() {
        return quizQueList.size();
    }


    public class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuestion;
        RadioGroup radioGroup;
        RadioButton radioOption1, radioOption2, radioOption3, radioOption4;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
          //  radioGroup = itemView.findViewById(R.id.radioGroupQuiz);
            radioOption1 = itemView.findViewById(R.id.radioOption1);
            radioOption2 = itemView.findViewById(R.id.radioOption2);
            radioOption3 = itemView.findViewById(R.id.radioOption3);
            radioOption4 = itemView.findViewById(R.id.radioOption4);
        }
    }
}
