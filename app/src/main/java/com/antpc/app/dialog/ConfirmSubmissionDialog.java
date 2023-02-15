package com.antpc.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.antpc.app.R;
import com.antpc.app.listeners.QuizAnswerListener;


public class ConfirmSubmissionDialog extends Dialog implements View.OnClickListener {
    Context context;
    LayoutInflater inflater;
    QuizAnswerListener quizAnswerListener;

    TextView submit, cancel;

    public ConfirmSubmissionDialog(@NonNull Context context, QuizAnswerListener listener) {
        super(context);
        this.context = context;
        quizAnswerListener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.dialog_confirm_submission, null, false);
        setContentView(view);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        setCanceledOnTouchOutside(true);

        cancel = findViewById(R.id.txtCancel);
        submit = findViewById(R.id.txtSubmit);
        setOnClickListener();


    }

    private void setOnClickListener() {
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtSubmit:
                quizAnswerListener.submitAnswer(true);
                break;
            case R.id.txtCancel:
                quizAnswerListener.submitAnswer(false);
                break;
        }
    }
}
