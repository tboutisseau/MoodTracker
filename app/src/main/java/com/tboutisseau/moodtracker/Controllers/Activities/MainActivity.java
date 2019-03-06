package com.tboutisseau.moodtracker.Controllers.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tboutisseau.moodtracker.Controllers.Fragments.PageAdapter;
import com.tboutisseau.moodtracker.R;
import com.tboutisseau.moodtracker.Models.Mood;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final FloatingActionButton historyButton = findViewById(R.id.open_history_button);
        final FloatingActionButton addCommentButton = findViewById(R.id.add_comment_button);

        ViewPager viewPager = findViewById(R.id.view_pager);

        PageAdapter adapter = new PageAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHistory();
            }
        });

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCommentDialog();
            }
        });
    }

    // Method to display the dialog box to add a comment to a mood
    private void openAddCommentDialog() {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_comment, null);
        EditText mComment = dialogView.findViewById(R.id.edit_text_comment);
        Button cancelButton = dialogView.findViewById(R.id.cancel_button);
        Button validateButton = dialogView.findViewById(R.id.validate_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something to validate the comment
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();

    }

    private void startHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
