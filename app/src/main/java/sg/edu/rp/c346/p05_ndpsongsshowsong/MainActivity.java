package sg.edu.rp.c346.p05_ndpsongsshowsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etTitle,etSinger,etYear;
    RadioGroup rg;
    RadioButton selectedRB;
    Button btnInsert,btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.radioGroupStars);
        btnInsert = findViewById(R.id.btnUpdate);
        btnShow = findViewById(R.id.btnDelete);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int chosentratingId = rg.getCheckedRadioButtonId();
                selectedRB = (RadioButton)findViewById(chosentratingId);


                String nameOfSong = etTitle.getText().toString();
                String nameOfSinger = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int valueOfRating = Integer.parseInt(selectedRB.getText().toString());

                DBHelper dbh = new DBHelper(getApplicationContext());
                dbh.addSong(nameOfSong,nameOfSinger,year,valueOfRating);
                Toast.makeText(getApplicationContext(),"Successfully inserted",Toast.LENGTH_LONG).show();



            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(i);
            }
        });





    }
}
