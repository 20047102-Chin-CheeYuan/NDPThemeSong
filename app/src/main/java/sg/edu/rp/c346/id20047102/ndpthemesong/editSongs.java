package sg.edu.rp.c346.id20047102.ndpthemesong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class editSongs extends AppCompatActivity {

    EditText etID, etTitle, etSinger, etYear;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    RadioGroup rdgRatings;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_songs);

        etID = findViewById(R.id.etEditID);
        etTitle = findViewById(R.id.etEditTitle);
        etSinger = findViewById(R.id.etEditSinger);
        etYear = findViewById(R.id.etEditYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        rdgRatings = findViewById(R.id.rdgEditStars);
        rb1 = findViewById(R.id.star1edit);
        rb2 = findViewById(R.id.star2edit);
        rb3 = findViewById(R.id.star3edit);
        rb4 = findViewById(R.id.star4edit);
        rb5 = findViewById(R.id.star5edit);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        String id = String.valueOf(data.getId());
        String year = String.valueOf(data.getYear());

        etID.setText(id);
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(year);

        if (data.getStars() == 1) {
            rb1.setChecked(true);
        } else if (data.getStars() == 2) {
            rb2.setChecked(true);
        } else if (data.getStars() == 3) {
            rb3.setChecked(true);
        } else if (data.getStars() == 4) {
            rb4.setChecked(true);
        } else if (data.getStars() == 5) {
            rb5.setChecked(true);
        }

        etID.setEnabled(false);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedRadioButtonId = rdgRatings.getCheckedRadioButtonId();

                DBHelper dbh = new DBHelper(editSongs.this);
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSinger.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                if (checkedRadioButtonId == R.id.star1edit) {
                    data.setStars(1);
                } else if (checkedRadioButtonId == R.id.star2edit) {
                    data.setStars(2);
                } else if (checkedRadioButtonId == R.id.star3edit) {
                    data.setStars(3);
                } else if (checkedRadioButtonId == R.id.star4edit) {
                    data.setStars(4);
                } else if (checkedRadioButtonId == R.id.star5edit) {
                    data.setStars(5);
                }

                dbh.updateNote(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(editSongs.this);
                dbh.deleteNote(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}