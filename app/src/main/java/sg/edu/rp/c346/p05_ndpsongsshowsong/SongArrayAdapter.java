package sg.edu.rp.c346.p05_ndpsongsshowsong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongArrayAdapter extends ArrayAdapter {
    private ArrayList<Song> songs;
    private Context context;
    private TextView tvYear;
    private TextView tvName;
    private TextView tvSinger;
    private ImageView iv;
     ImageView iv1,iv2,iv3,iv4,iv5;


    public SongArrayAdapter(Context context, int resource, ArrayList<Song> objects){
        super(context, resource, objects);
        songs = objects;
        this.context = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);
        tvYear = (TextView)rowView.findViewById(R.id.tvYear);
        tvName = (TextView)rowView.findViewById(R.id.tvName);
        tvSinger = (TextView) rowView.findViewById(R.id.tvSinger);
        iv = (ImageView)rowView.findViewById(R.id.iv) ;
        iv.setImageResource(R.drawable.ic_library_music);

        Song songObj = songs.get(position);

        tvYear.setText(songObj.getYear() + "");
        tvName.setText(songObj.getTitle());
        tvSinger.setText(songObj.getSingers() + "");
        iv1 = rowView.findViewById(R.id.iv1);
        iv2 = rowView.findViewById(R.id.iv2);
        iv3 = rowView.findViewById(R.id.iv3);
        iv4 = rowView.findViewById(R.id.iv4);
        iv5 = rowView.findViewById(R.id.iv5);
        int rating =  songObj.getStars();
        if(rating >= 1){
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
            if(rating >= 2){
                iv2.setImageResource(android.R.drawable.btn_star_big_on);
                if(rating >= 3){
                    iv3.setImageResource(android.R.drawable.btn_star_big_on);
                    if(rating >= 4){
                        iv4.setImageResource(android.R.drawable.btn_star_big_on);
                        if(rating == 5){
                            iv5.setImageResource(android.R.drawable.btn_star_big_on);
                        }

                    }
                }
            }
        }

        return rowView;

    }
}
