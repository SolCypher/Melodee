package com.example.projectux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    // Image Carousel + Greeting
    private TextView greeting;
    ViewFlipper carousel;

    int[] images = {
            R.drawable.microphone,
            R.drawable.vinyl,
            R.drawable.musicnotes
    };

    // Top Seller
    int[] listImgs = {
            R.drawable.bruno24kmagic,
            R.drawable.bintanglima,
            R.drawable.hollywoodsbleeding
    };

    String[] artist = {
            "Bruno Mars",
            "Dewa 19",
            "Post Malone"
    };
    String[] title = {
            "24K Magic",
            "Bintang Lima",
            "Hollywood's Bleeding"
    };
    String[] category = {
            "R&B; soul; funk; pop; new jack swing",
            "Pop",
            "Hip hop, Pop"
    };
    String[] price = {
            "32.50 USD",
            "7.55 USD",
            "27.95 USD"
    };
    String[] totalSold = {
            "78.298.726 pcs",
            "17.131.853 pcs",
            "67.295.859 pcs"
    };

    String username;
    ListView listview;

    // Bot Nav Buttons
    private ImageButton homeBtn, albumBtn, aboutBtn, offBtn;

    // Map
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Greeting
        initializeItems();
        username = getIntent().getStringExtra("USERNAME_KEY");
        greeting.setText("Welcome, " + username);

        // Image Carousel
        carousel = findViewById(R.id.imgCarousel);

        for (int image : images) {
            insertImg(image);
        }

        // Top Seller
        listview = findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();
        listview.setAdapter(customAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), "You clicked on " + artist[position], Toast.LENGTH_SHORT).show();
                if (position == 0){
                    position = 3;
                }else if (position == 1){
                    position = 2;
                }else {
                    position = 5;
                }
                openDetailPage(position);
            }
        });

    // Bot Nav Buttons
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

    private void initializeItems() {

        this.greeting = findViewById(R.id.greeting);

    }

    protected void insertImg(int image){
        ImageView imageView = new ImageView(HomeActivity.this);

        imageView.setBackgroundResource(image);

        carousel.addView(imageView);
        carousel.setFlipInterval(3000);
        carousel.setAutoStart(true);
        carousel.setInAnimation(HomeActivity.this, R.anim.slide_in_right);
        carousel.setOutAnimation(HomeActivity.this, R.anim.slide_out_left);

    }

    //Google Map
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//        map = googleMap;
//
//        LatLng Jakarta = new LatLng(-6.208441740949514, 106.84688977295306);
//        map.addMarker(new MarkerOptions().position(Jakarta).title("Jakarta"));
//        map.moveCamera(CameraUpdateFactory.newLatLng(Jakarta));
//
//    }

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