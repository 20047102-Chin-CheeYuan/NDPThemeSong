package sg.edu.rp.c346.id20047102.ndpthemesong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShow;
    EditText etTitle, etSinger, etYear;
    RadioGroup rdgRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnDisplay);
        rdgRatings = findViewById(R.id.rdgStars);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        showSongs.class);
                startActivity(i);

            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numRating = 0;
                int checkedRadioButtonId = rdgRatings.getCheckedRadioButtonId();

                if (checkedRadioButtonId == R.id.star1) {
                    numRating = 1;
                } else if (checkedRadioButtonId == R.id.star2) {
                    numRating = 2;
                } else if (checkedRadioButtonId == R.id.star3) {
                    numRating = 3;
                } else if (checkedRadioButtonId == R.id.star4) {
                    numRating = 4;
                } else if (checkedRadioButtonId == R.id.star5) {
                    numRating = 5;
                }

                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = numRating;

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singer, year, stars);

                if (inserted_id != -1) {
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

                etTitle.setText("");
                etSinger.setText("");
                etYear.setText("");
                rdgRatings.clearCheck();

            }

        });
    }

}