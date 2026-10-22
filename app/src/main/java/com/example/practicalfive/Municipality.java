package com.example.practicalfive;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Municipality extends AppCompatActivity {

    private TextView municipalityName;
    private ImageView municipalityImage;
    private TextView municipalityInfo;
    private Button visitSiteButton;

    private TextProcessor textProcessor;
    private String selectedMunicipality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        municipalityName = findViewById(R.id.municipalityName);
        municipalityImage = findViewById(R.id.municipalityImage);
        municipalityInfo = findViewById(R.id.municipalityInfo);
        visitSiteButton = findViewById(R.id.visitSiteButton);

        textProcessor = new TextProcessor();

        Intent intent = getIntent();
        String municipality = intent.getStringExtra(MainActivity.EXTRA_MUNICIPALITY);

        selectedMunicipality = textProcessor.processMunicipality(municipality);

        if (selectedMunicipality == null) {
            Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        displayMunicipality();

        visitSiteButton.setOnClickListener(v -> openOfficialWebsite());
    }

    private void displayMunicipality() {
        Resources res = getResources();
        String[] infoArray = res.getStringArray(R.array.municipalities_array);

        int colorRes;
        int imageRes;
        int infoIndex;

        switch (selectedMunicipality) {
            case TextProcessor.MUNICIPALITY_BEIJING:
                colorRes = R.color.municipality_beijing;
                imageRes = R.drawable.beijing;
                infoIndex = 0;
                break;
            case TextProcessor.MUNICIPALITY_SHANGHAI:
                colorRes = R.color.municipality_shanghai;
                imageRes = R.drawable.shanghai;
                infoIndex = 1;
                break;
            case TextProcessor.MUNICIPALITY_CHONGQING:
                colorRes = R.color.municipality_chongqing;
                imageRes = R.drawable.chongqing;
                infoIndex = 2;
                break;
            case TextProcessor.MUNICIPALITY_TIANJIN:
            default:
                colorRes = R.color.municipality_tianjin;
                imageRes = R.drawable.tianjin;
                infoIndex = 3;
                break;
        }

        int color = res.getColor(colorRes, null);
        int textColor = res.getColor(R.color.color_text_light, null);

        municipalityName.setText(selectedMunicipality);
        municipalityName.setBackgroundColor(color);
        municipalityName.setTextColor(textColor);

        municipalityImage.setImageResource(imageRes);

        municipalityInfo.setText(infoArray[infoIndex]);
        municipalityInfo.setTextColor(color);

        visitSiteButton.setBackgroundColor(color);
        visitSiteButton.setTextColor(textColor);
    }

    private void openOfficialWebsite() {
        String url;

        switch (selectedMunicipality) {
            case TextProcessor.MUNICIPALITY_BEIJING:
                url = "https://english.beijing.gov.cn/";
                break;
            case TextProcessor.MUNICIPALITY_SHANGHAI:
                url = "https://english.shanghai.gov.cn/";
                break;
            case TextProcessor.MUNICIPALITY_TIANJIN:
                url = "https://en.tj.gov.cn/";
                break;
            case TextProcessor.MUNICIPALITY_CHONGQING:
            default:
                url = "https://english.cq.gov.cn/";
                break;
        }

        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        try {
            startActivity(webIntent);
        } catch (Exception e) {
            Toast.makeText(this, "No application available to open the website", Toast.LENGTH_SHORT).show();
        }
    }
}
