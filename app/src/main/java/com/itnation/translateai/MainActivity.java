package com.itnation.translateai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {


    Spinner fromLangu, toLangu;


    String[] allCountry = {
            "From", "Afrikaans", "Albanian", "Amharic", "Arabic", "Armenian", "Azerbaijani",
            "Basque", "Belarusian", "Bengali", "Bosnian", "Bulgarian", "Burmese",
            "Catalan", "Cebuano", "Chichewa", "Chinese", "Corsican", "Croatian",
            "Czech", "Danish", "Dutch", "English", "Esperanto", "Estonian",
            "Filipino", "Finnish", "French", "Frisian", "Galician", "Georgian",
            "German", "Greek", "Gujarati", "Haitian Creole", "Hausa", "Hawaiian",
            "Hebrew", "Hindi", "Hmong", "Hungarian", "Icelandic", "Igbo",
            "Indonesian", "Irish", "Italian", "Japanese", "Javanese", "Kannada",
            "Kazakh", "Khmer", "Korean", "Kurdish", "Kyrgyz", "Lao",
            "Latin", "Latvian", "Lithuanian", "Luxembourgish", "Macedonian", "Malagasy",
            "Malay", "Malayalam", "Maltese", "Maori", "Marathi", "Mongolian",
            "Nepali", "Norwegian", "Pashto", "Persian", "Polish", "Portuguese",
            "Punjabi", "Romanian", "Russian", "Samoan", "Scots Gaelic", "Serbian",
            "Sesotho", "Shona", "Sindhi", "Sinhala", "Slovak", "Slovenian",
            "Somali", "Spanish", "Sundanese", "Swahili", "Swedish", "Tajik",
            "Tamil", "Telugu", "Thai", "Turkish", "Turkmen", "Ukrainian",
            "Urdu", "Uyghur", "Uzbek", "Vietnamese", "Welsh", "Xhosa",
            "Yiddish", "Yoruba", "Zulu"
    };




    String[] allToCountry = {
            "To", "Afrikaans", "Albanian", "Amharic", "Arabic", "Armenian", "Azerbaijani",
            "Basque", "Belarusian", "Bengali", "Bosnian", "Bulgarian", "Burmese",
            "Catalan", "Cebuano", "Chichewa", "Chinese", "Corsican", "Croatian",
            "Czech", "Danish", "Dutch", "English", "Esperanto", "Estonian",
            "Filipino", "Finnish", "French", "Frisian", "Galician", "Georgian",
            "German", "Greek", "Gujarati", "Haitian Creole", "Hausa", "Hawaiian",
            "Hebrew", "Hindi", "Hmong", "Hungarian", "Icelandic", "Igbo",
            "Indonesian", "Irish", "Italian", "Japanese", "Javanese", "Kannada",
            "Kazakh", "Khmer", "Korean", "Kurdish", "Kyrgyz", "Lao",
            "Latin", "Latvian", "Lithuanian", "Luxembourgish", "Macedonian", "Malagasy",
            "Malay", "Malayalam", "Maltese", "Maori", "Marathi", "Mongolian",
            "Nepali", "Norwegian", "Pashto", "Persian", "Polish", "Portuguese",
            "Punjabi", "Romanian", "Russian", "Samoan", "Scots Gaelic", "Serbian",
            "Sesotho", "Shona", "Sindhi", "Sinhala", "Slovak", "Slovenian",
            "Somali", "Spanish", "Sundanese", "Swahili", "Swedish", "Tajik",
            "Tamil", "Telugu", "Thai", "Turkish", "Turkmen", "Ukrainian",
            "Urdu", "Uyghur", "Uzbek", "Vietnamese", "Welsh", "Xhosa",
            "Yiddish", "Yoruba", "Zulu"
    };






    String fromLanguage, toLanguage;
    String queryTxt;
    EditText entredTxt;
    MaterialButton button;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromLangu= findViewById(R.id.fromLangu);
        toLangu= findViewById(R.id.toLangu);
        entredTxt= findViewById(R.id.entredTxt);
        button= findViewById(R.id.button);
        progressBar=findViewById(R.id.progressBar);







        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,allCountry);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromLangu.setAdapter(countryAdapter);

        ArrayAdapter<String> countryToAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,allToCountry);
        countryToAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toLangu.setAdapter(countryToAdapter);



        fromLangu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).toString() == "From"){
                    fromLanguage = "";
                }else {
                    fromLanguage = parent.getItemAtPosition(position).toString();

                }

                //Toast.makeText(MainActivity.this, "from " + fromLanguage, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        toLangu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).toString() == "To"){
                    toLanguage = "";
                }else {
                    toLanguage = parent.getItemAtPosition(position).toString();
                }

                //Toast.makeText(MainActivity.this, "To " + toLanguage, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });











        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             if (entredTxt.getText().toString().length()>=3){

                 loadTranslate();
             }else {

                 entredTxt.setError("Please entre your text");

             }








    }//------ close OnCreate-----------------------------


            void loadTranslate(){

                progressBar.setVisibility(View.VISIBLE);
                String inputTxt = entredTxt.getText().toString();
                queryTxt = "Translate this text \"" + inputTxt +"\"" + " From " + fromLanguage + " To " + toLanguage;

                //Toast.makeText(MainActivity.this, queryTxt, Toast.LENGTH_SHORT).show();

                AiModel model = new AiModel();

                String query = queryTxt;




                model.getResponse(query, new ResponseCallback() {
                    @Override
                    public void onResponse(String response) {


                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(MainActivity.this, TranslatedActivity.class);
                        intent.putExtra("resultTxt",response);
                        intent.putExtra("fromLanguage",fromLanguage);
                        intent.putExtra("toLanguage",toLanguage);
                        startActivity(intent);

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Toast.makeText(MainActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });


    }






}