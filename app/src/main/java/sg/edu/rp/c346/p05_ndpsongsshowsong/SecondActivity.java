package sg.edu.rp.c346.p05_ndpsongsshowsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {
    ListView lv;

    ArrayList<Song> al = new ArrayList<>();
    ArrayList<String> years = new ArrayList<>(Arrays.asList(""));
    SongArrayAdapter aa;
    ArrayAdapter spinnerAdapter;
    Button btnShow5Stars;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lv = findViewById(R.id.lv);
        btnShow5Stars = findViewById(R.id.btnShow5Stars);
        DBHelper dbh = new DBHelper(SecondActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs(""));
        for(int i =0;i < al.size(); i++){
            years.add(al.get(i).getYear() + "");
        }

        dbh.close();
        ArrayAdapter aa = new SongArrayAdapter(getApplicationContext(),R.layout.row,al);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Song songObj = al.get(position);

                Intent i = new Intent(SecondActivity.this,ThirdActivity.class);

                i.putExtra("data", songObj);
                startActivityForResult(i, 9);

            }
        });



        btnShow5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongs("5"));
                dbh.close();
                ArrayAdapter aa = new SongArrayAdapter(getApplicationContext(),R.layout.row,al);
                lv.setAdapter(aa);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            DBHelper dbh = new DBHelper(SecondActivity.this);
            al.clear();
            al.addAll(dbh.getAllSongs(""));
            dbh.close();
            ArrayAdapter aa = new SongArrayAdapter(getApplicationContext(),R.layout.row,al);
            lv.setAdapter(aa);
            for(int i =0;i < al.size(); i++){
                years.add(al.get(i).getYear() + "");
            }

        }
    }
}
