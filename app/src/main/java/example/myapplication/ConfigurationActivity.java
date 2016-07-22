package example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigurationActivity extends AppCompatActivity {

    private Button btnSave;
    private EditText editHost;
    private EditText editPort;
    private SharedPreferences sharedPrefs;

    private void setupUI(){
        btnSave = (Button) findViewById(R.id.btnSave);
        editHost = (EditText) findViewById(R.id.txtHost);
        editPort = (EditText) findViewById(R.id.txtPort);
        sharedPrefs = getSharedPreferences(
                getResources().getString(R.string.configurationsPref),
                Context.MODE_PRIVATE);

        editHost.setText(sharedPrefs.getString(getResources().getString(R.string.hostKey), ""));
        editPort.setText(sharedPrefs.getString(getResources().getString(R.string.portKey), ""));
    }

    private void editConfigurations(){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("hostKey", editHost.getText().toString());
        editor.putString("portKey", editPort.getText().toString());
        editor.commit();

        Toast.makeText(ConfigurationActivity.this,
                getResources().getString(R.string.saved_settings),
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupUI();

        btnSave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                editConfigurations();
            }
        });
    }
}
