package com.example.projectux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class AlbumsActivity extends AppCompatActivity {

    int[] listImgs = {
            R.drawable.folklore,
            R.drawable.brunomarsujalbumcover,
            R.drawable.bintanglima,
            R.drawable.bruno24kmagic,
            R.drawable.thebestofrossa,
            R.drawable.hollywoodsbleeding,
            R.drawable.mantramantra
    };

    String[] artist = {
            "Taylor Swift",
            "Bruno Mars",
            "Dewa 19",
            "Bruno Mars",
            "Rossa",
            "Post Malone",
            "Kunto Anji"
    };
    String[] title = {
            "Folklore",
            "Unorthodox Jukebox",
            "Bintang Lima",
            "24K Magic",
            "The Best of Rossa",
            "Hollywood's Bleeding",
            "Mantra Mantra"
    };
    String[] category = {
            "Alternative rock, Indie folk, Baroque pop, Folktronic",
            "Pop; R&B; rock; funk; soul; reggae; disco",
            "Pop",
            "R&B; soul; funk; pop; new jack swing",
            "Contemporary R&B, Dance-pop",
            "Hip hop, Pop",
            "Pop"
    };
    String[] price = {
            "24,45 USD",
            "24.55 USD",
            "7.55 USD",
            "32.50 USD",
            "12.55 USD",
            "27.95 USD",
            "9.65 USD"
    };
    String[] totalSold = {
            "12.001.743 pcs",
            "32.495.123 pcs",
            "17.131.853 pcs",
            "78.298.726 pcs",
            "9.319.735 pcs",
            "67.295.859 pcs",
            "11.825.963 pcs"
    };

    String username;
    private ImageButton homeBtn, albumBtn, aboutBtn, offBtn;

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        username = getIntent().getStringExtra("USERNAME_KEY");
        listview = findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();
        listview.setAdapter(customAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), "You clicked on " + artist[position], Toast.LENGTH_SHORT).show();
                openDetailPage(position);
            }
        });
        homeBtn = findViewById(R.id.homeBtn);
        albumBtn = findViewById(R.id.albumBtn);
        aboutBtn = findViewById(R.id.aboutBtn);
        offBtn = findViewById(R.id.offBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });
        albumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbumPage();
            }
        });
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutPage();
            }
        });
        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginPage();
            }
        });

    }

    private void openDetailPage(int position) {
        Intent toDetailPage = new Intent(this, AlbumDetailActivity.class);
        toDetailPage.putExtra("USERNAME_KEY", username);
        toDetailPage.putExtra("POSITION", position);
        startActivity(toDetailPage);
    }

    private void openLoginPage() {
        Intent toLoginPage = new Intent(this, LoginActivity.class);
        startActivity(toLoginPage);
    }

    private void openAboutPage() {
        Intent toAboutPage = new Intent(this, AboutUsActivity.class);
        toAboutPage.putExtra("USERNAME_KEY", username);
        startActivity(toAboutPage);
    }

    private void openAlbumPage() {
        Intent toAlbumPage = new Intent(this, AlbumsActivity.class);
        toAlbumPage.putExtra("USERNAME_KEY", username);
        startActivity(toAlbumPage);
    }


    private void openHomePage() {
        Intent toHomePage = new Intent(this, HomeActivity.class);
        toHomePage.putExtra("USERNAME_KEY", username);
        startActivity(toHomePage);
    }


    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return listImgs.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.listview_content,null);
            ImageView imageView = convertView.findViewById(R.id.listImage);
            TextView artistView = convertView.findViewById(R.id.artist);
            TextView titleView = convertView.findViewById(R.id.titleView);
            TextView categoryView = convertView.findViewById(R.id.category);
            TextView priceView = convertView.findViewById(R.id.price);
            TextView totalSoldView = convertView.findViewById(R.id.totalSold);

            imageView.setImageResource(listImgs[position]);
            artistView.setText(artist[position]);
            titleView.setText(title[position]);
            categoryView.setText(category[position]);
            priceView.setText(price[position]);
            totalSoldView.setText(totalSold[position]);

            return convertView;

        }
    }

}