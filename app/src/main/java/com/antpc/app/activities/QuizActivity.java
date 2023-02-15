package com.antpc.app.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.antpc.app.R;
import com.antpc.app.adapters.QuizAdapter;
import com.antpc.app.api.APIClient;
import com.antpc.app.api.RetrofitAPI;
import com.antpc.app.dialog.ConfirmSubmissionDialog;
import com.antpc.app.listeners.QuizAnswerListener;
import com.antpc.app.models.QuizModel;
import com.antpc.app.models.QuizResultRequest;
import com.antpc.app.models.QuizResultResponse;

import com.antpc.app.utils.Const;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizActivity extends AppCompatActivity implements QuizAnswerListener {
    List<QuizModel> quizList = new ArrayList<>();
    QuizAdapter quizAdapter;
    RecyclerView recyclerView;
    TextView txtSubmit;
    ConfirmSubmissionDialog confirmSubmissionDialog;
    int userId;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        recyclerView = findViewById(R.id.rvQuiz);
        txtSubmit = findViewById(R.id.txtSubmit);
        loadingPB = (ProgressBar) findViewById(R.id.progressBar);

        if (getIntent().hasExtra("USER_ID")) {
            userId = getIntent().getIntExtra("USER_ID", 0);
        }
        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmSubmissionDialog = new ConfirmSubmissionDialog(QuizActivity.this, QuizActivity.this);
                confirmSubmissionDialog.show();
            }
        });
        createMcqQuiz();


    }


    private void createMcqQuiz() {
        quizList.add(new QuizModel("Q 1. Who is the father of Computers?", "James Gosling", "Charles Babbage", "Dennis Ritchie", "Bjarne Stroustrup", "B"));
        quizList.add(new QuizModel("Q 2. Which of the following is the correct abbreviation of COMPUTER?", "Commonly Occupied Machines Used in Technical and Educational Research", "Commonly Operated Machines Used in Technical and Environmental Research", "Commonly Oriented Machines Used in Technical and Educational Research", "Commonly Operated Machines Used in Technical and Educational Research", "D"));
        quizList.add(new QuizModel("Q 3. Which of the following is the correct definition of Computer?", "Computer is a machine or device that can be programmed to perform arithmetical or logic operation sequences automatically", "Computer understands only binary language which is written in the form of 0s & 1s", "Computer is a programmable electronic device that stores, retrieves, and processes the data", "All of the mentioned", "D"));
        quizList.add(new QuizModel("Q 4. What is the full form of CPU?", "Computer Processing Unit", "Computer Principle Unit", "Central Processing Unit", "Control Processing Unit", "C"));
        quizList.add(new QuizModel("Q 5. Which of the following language does the computer understand?", "Computer understands only C Language", "Computer understands only Assembly Language", "Computer understands only Binary Language", "Computer understands only BASIC", "C"));
        quizList.add(new QuizModel("Q 6. Which of the following computer language is written in binary codes only?", "pascal", "machine language", "C", "C#", "B"));
        quizList.add(new QuizModel("Q 7. Which of the following is the brain of the computer?", "Central Processing Unit", "Memory", "Arithmetic and Logic unit", "Control unit", "A"));
        quizList.add(new QuizModel("Q 8. Which of the following is not a characteristic of a computer?", "Versatility", "Accuracy", "Diligence", "I.Q.", "D"));
        quizList.add(new QuizModel("Q 9. Which of the following is the smallest unit of data in a computer?", "Bit", "KB", "Nibble", "Byte", "A"));
        quizList.add(new QuizModel("Q 10. Which of the following unit is responsible for converting the data received from the user into a computer understandable format?", "Output Unit", "Input Unit", "Memory Unit", "Arithmetic & Logic Unit", "B"));
        quizList.add(new QuizModel("Q 11. Which of the following monitor looks like a television and are normally used with non-portable computer systems?", "LED", "LCD", "CRT", "Flat Panel Monitors", "C"));
        quizList.add(new QuizModel("Q 12. Which of the following is not a type of computer code?", "EDIC", "ASCII", "BCD", "EBCDIC", "A"));
        quizList.add(new QuizModel("Q 13. Which of the following part of a processor contains the hardware necessary to perform all the operations required by a computer?", "Controller", "Registers", "Cache", "Data path", "D"));
        quizList.add(new QuizModel("Q 14. Which of the following is designed to control the operations of a computer?", "User", "Application Software", "System Software", "Utility Software", "C"));
        quizList.add(new QuizModel("Q 15. Which of the following device use positional notation to represent a decimal number?", "Pascaline", "Abacus", "Computer", "Calculator", "B"));
        quizList.add(new QuizModel("Q 16. Which of the following is used in EBCDIC?", "Super Computers", "Mainframes", "Machine Codes", "Programming", "B"));
        quizList.add(new QuizModel("Q 17. Which of the following are physical devices of a computer?", "Hardware", "Software", "System Software", "Package", "A"));
        quizList.add(new QuizModel("Q 18. Which of the following defines the assigned ordering among the characters used by the computer?", "Accumulation", "Sorting", "Collating Sequence", "Unicode", "C"));
        quizList.add(new QuizModel("Q 19. Which of the following storage is a system where a robotic arm will connect or disconnect off-line mass storage media according to the computer operating system demands?", "Magnetic", "Secondary", "Virtual", "Tertiary", "D"));
        quizList.add(new QuizModel("Q 20. Which of the following is known as the interval between the instant a computer makes a request for the transfer of data from a disk system to the primary storage and the instance the operation is completed?", "Disk utilization time", "Drive utilization time", "Disk access time", "Disk arrival time", "C"));
        quizList.add(new QuizModel("Q 21. Which of the following devices provides the communication between a computer and the outer world?", "Compact", "I/O", "Drivers", "Storage", "B"));
        quizList.add(new QuizModel("Q 22. Which of the following are the input devices that enable direct data entry into a computer system from source documents?", "System Access devices", "Data acquiring devices", "Data retrieving devices", "Data Scanning devices", "D"));
        quizList.add(new QuizModel("Q 23. Which of the following is the device used for converting maps, pictures, and drawings into digital form for storage in computers?", "Image Scanner", "Digitizer", "MICR", "Scanner", "B"));
        quizList.add(new QuizModel("Q 24. Which of the following can access the server?", "Web Client", "User", "Web Browser", "Web Server", "A"));
        quizList.add(new QuizModel("Q 25. Which of the following is known as the language made up of binary-coded instructions?", "High level", "BASIC", "C", "Machine", "D"));
        quizList.add(new QuizModel("Q 26. Which of the following package allows individuals to use personal computers for storing and retrieving their personal information?", "Personal assistance package", "Graphics package", "Spreadsheet package", "Animation package", "A"));
        quizList.add(new QuizModel("Q 27. Which of the following is created when a user opens an account in the computer system?", "SFD", "MFD", "Subdirectory", "RFD", "C"));
        quizList.add(new QuizModel("Q 28. Which of the following is a technique that marked the beginning of computer communications?", "User Environment", "Batch Environment", "Time Sharing", "Message passing", "C"));
        quizList.add(new QuizModel("Q 29. Which of the following is a type of technique in which dumb terminals are connected to a central computer system?", "Time Sharing", "Message passing", "Batch environment", "User environment", "A"));
        quizList.add(new QuizModel("Q 30. Which of the following service allows a user to log in to another computer somewhere on the Internet?", "e-mail", "UseNet", "Telnet", "FTP", "C"));

        setQuizAdapter();
    }

    private void setQuizAdapter() {
        quizAdapter = new QuizAdapter(QuizActivity.this, quizList, QuizActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(QuizActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(quizAdapter);
    }

    @Override
    public void answerUpdated(boolean isAnswerCorrect) {

    }

    @Override
    public void selectedAnswer(String optionSelected, int position) {
//Here assign the value to the Element Obj
        if (quizList.get(position).getCorrectOption().equals(optionSelected)) {
            quizList.get(position).setAnswerCorrect(true);
        } else {
            quizList.get(position).setAnswerCorrect(false);
        }
        quizList.get(position).setSelectedOption(optionSelected);
        //quizAdapter.notifyDataSetChanged();
        // quizAdapter.notifyItemChanged(position);
        quizAdapter.notifyDataSetChanged();
    }

    @Override
    public void submitAnswer(boolean isReady) {
        if (isReady) {
            confirmSubmissionDialog.dismiss();
            callUpdateQuizAPI();
            //Toast.makeText(QuizActivity.this, "Your Score is " + collectQuizData(), Toast.LENGTH_LONG).show();
        } else {
            confirmSubmissionDialog.dismiss();
        }
    }

    private int collectQuizData() {
        int score = 0;
        for (QuizModel quizModel : quizList) {
            if (quizModel.isAnswerCorrect()) {
                score++;
            }
        }
        return score;
    }

    private void callUpdateQuizAPI() {
        loadingPB.setVisibility(View.VISIBLE);
        RetrofitAPI retrofitAPI = APIClient.getRetrofitInstance(Const.DEV_URL_ANT).create(RetrofitAPI.class);
        //SignupRequest signUpRequestModel = new SignupRequest(Const.API_KEY, firstName, lastName, userEmail, userMobile, userAge, state, referenceText, demoRequired, ratingCount, usersSuggestion);
        QuizResultRequest quizResultRequest = new QuizResultRequest(String.valueOf(userId), String.valueOf(collectQuizData()));
        Call<QuizResultResponse> call = retrofitAPI.quizAPIRequest(quizResultRequest);
        call.enqueue(new Callback<QuizResultResponse>() {
            @Override
            public void onResponse(Call<QuizResultResponse> call, Response<QuizResultResponse> response) {
                assert response.body() != null;
                if (response.body().getStatus()) {
                    loadingPB.setVisibility(View.GONE);
                    Log.d(TAG, "" + response.body().getMessage());
                    // Toast.makeText(MainActivity.this, "Congratulations! you are successfully registered with Bingo", Toast.LENGTH_LONG).show();
                    //SharedPreferenceUtils.saveString(LoginActivity.this, Const.ACCESS_TOKEN, response.body().getToken());

                    Intent i = new Intent(QuizActivity.this, ThanksActivity.class);
                    startActivity(i);
                    finish();


                } else if (response.code() == 200) {
                    loadingPB.setVisibility(View.GONE);
                    Log.e(TAG, "Else condition");
                    Toast.makeText(QuizActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                } else {
                    loadingPB.setVisibility(View.GONE);
                    // AppUtils.showToast(Const.no_records, LoginActivity.this);

                }
            }

            @Override
            public void onFailure(Call<QuizResultResponse> call, Throwable t) {
                Log.e(TAG, "" + t);
                loadingPB.setVisibility(View.GONE);
                //AppUtils.showToast(Const.something_went_wrong, LoginActivity.this);
            }
        });

    }

}