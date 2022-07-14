package sg.edu.rp.c346.id20047102.ndpthemesong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class showSongs extends AppCompatActivity {

    Button btn5Stars;
    ArrayList<Song> al;
    ArrayList<Song> filteredList;
    ListView lv;
    ArrayAdapter<Song> aa;
    Song data;
    Boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_songs);

        btn5Stars = findViewById(R.id.btn5Stars);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Song>();
        filteredList = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, filteredList);
        lv.setAdapter(aa);

        DBHelper dbh = new DBHelper(showSongs.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        filteredList.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = filteredList.get(position);
                Intent i = new Intent(showSongs.this,
                        editSongs.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = !state;
                filteredList.clear();
                if (state) {
                    btn5Stars.setText("Reset list");
                    for (Song element : al
                    ) {
                        if (element.getStars() == 5) {
                            filteredList.add(element);
                        }
                    }
                } else {
                    btn5Stars.setText("show all songs with 5 stars");
                    filteredList.addAll(dbh.getAllSongs());
                }

                aa.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(showSongs.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();
    }


}