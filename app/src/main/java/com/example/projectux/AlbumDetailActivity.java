package com.example.projectux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AlbumDetailActivity extends AppCompatActivity {
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

    private ImageButton backBtn;
    private ListView detailLV;
    private EditText inputQuantity;
    private Button buyButton;
    private TextView errorText;
    private int position;
    String username, from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        username = getIntent().getStringExtra("USERNAME_KEY");
        position = getIntent().getIntExtra("POSITION", 0);

        detailLV = findViewById(R.id.detailLV);
        buyButton = findViewById(R.id.buyButton);
        backBtn = findViewById(R.id.backButton);

        CustomAdapter customAdapter = new CustomAdapter();
        detailLV.setAdapter(customAdapter);

        //back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlbumPage();
            }
        });

        //buy button
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkQuantity();
            }
        });

    }

    private void checkQuantity(){
        inputQuantity = findViewById(R.id.inputQuantity);
        String temp = inputQuantity.getText().toString();
        errorText = findViewById(R.id.errorMsg);

        if (temp.isEmpty()){
            errorText.setText("Please input quantity");
            errorText.setVisibility(View.VISIBLE);
        }else {
            if (temp.equals("0")) {
                errorText.setText("Quantity must be more than 0");
                errorText.setVisibility(View.VISIBLE);
            }
            else {
                errorText.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void openAlbumPage() {
        Intent toAlbumPage = new Intent(this, AlbumsActivity.class);
        toAlbumPage.putExtra("USERNAME_KEY", username);
        startActivity(toAlbumPage);
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return  getIntent().getIntExtra("POSITION", 0);
        }

        @Override
        public long getItemId(int position) {
            return  getIntent().getIntExtra("POSITION", 0);
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

            position = getIntent().getIntExtra("POSITION", 3);

            imageView.setImageResource(listImgs[position]);
            artistView.setText("Artist: " + artist[position]);
            titleView.setText("Title: " + title[position]);
            categoryView.setText("Category: " + category[position]);
            priceView.setText("Price: " + price[position]);
            totalSoldView.setText("Sold: " + totalSold[position]);

            return convertView;

        }
    }
}