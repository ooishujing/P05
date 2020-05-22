package sg.edu.rp.c346.p05_ndpsongsshowsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    TextView tvSongID;
    EditText etTitle;
    EditText etSinger;
    EditText etYear;

    RadioButton r1,r2,r3,r4,r5;
    Song song;

    Button btnUpdate;
    Button btnDelete;
    RadioGroup rg;
    RadioButton selectedRB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        tvSongID = findViewById(R.id.tvSongID);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.radioGroupStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        r1 = findViewById(R.id.radio1);
        r2 = findViewById(R.id.radio2);
        r3 = findViewById(R.id.radio3);
        r4 = findViewById(R.id.radio4);
        r5 = findViewById(R.id.radio5);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("data");
        tvSongID.setText(song.getId() + "");
        etTitle.setText(song.getTitle());
        etSinger.setText(song.getSingers());
        etYear.setText(song.getYear() + "");
        if(song.getStars() == 1){
            r1.setChecked(true);
        }else if (song.getStars() == 2){
            r2.setChecked(true);
        } else if (song.getStars() == 3){
            r3.setChecked(true);
        } else if (song.getStars() == 4){
            r4.setChecked(true);
        }else if (song.getStars() == 5){
            r5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameOfSong = etTitle.getText().toString();
                String nameOfSinger = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());

                //for the star value
                int selectedID = rg.getCheckedRadioButtonId();
                selectedRB = (RadioButton)findViewById(selectedID);


                int valueOfRating = Integer.parseInt(selectedRB.getText().toString());
                Song newObj = new Song(nameOfSong,nameOfSinger,year,valueOfRating);
                newObj.setId(song.getId());
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.updateSong(newObj);
                dbh.close();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK, i);
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(song.getId());

            }
        });

    }
}
