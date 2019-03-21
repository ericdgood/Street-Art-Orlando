package streetart.CFO.orlandostreetart.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import streetart.CFO.orlandostreetart.R;
import streetart.CFO.orlandostreetart.presenters.DetailsPresenter;

/**
 * Created by Eric on 3/15/2019.
 */
public class Details extends AppCompatActivity implements OnMapReadyCallback{

    @BindView(R.id.imgArtwork)
    ImageView imgArtwork;
    @BindView(R.id.tvImageTitle)
    TextView tvImageTitle;
    @BindView(R.id.tvArtistName)
    TextView tvArtistName;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvLocationNotes)
    TextView tvLocationNotes;
    @BindView(R.id.tvDescriptionLabel)
    TextView tvDescriptionLabel;
    @BindView(R.id.divider1)
    View viewDivider1;
    @BindView(R.id.divider2)
    View viewDivider2;

    private static final String TAG = "Details";
    DetailsPresenter detailPresenter = new DetailsPresenter(this);
    private GoogleMap mMap;

    public Details() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        ButterKnife.bind(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapViewFragment);
        mapFragment.getMapAsync(this);

        detailPresenter.getPassedDetails();
    }

    public void displayDetails(String photoUrl, String title, String artist, String description, String locationNotes) {
//        Set text to fields
        tvImageTitle.setText(title);
        tvArtistName.setText(artist);
        tvDescription.setText(description);
        tvLocationNotes.setText(locationNotes);

//        Load image
        Glide.with(this)
                .load(photoUrl)
                .into(imgArtwork);

//        Check if field is empty and set visibility to gone.
        detailPresenter.nullDisplay(tvImageTitle, tvArtistName, tvDescription,
                tvLocationNotes, tvDescriptionLabel, viewDivider1, viewDivider2);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setIndoorEnabled(true);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        //seattle coordinates
        LatLng seattle = new LatLng(47.6062095, -122.3320708);
        mMap.addMarker(new MarkerOptions().position(seattle));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seattle, 15));
    }
}