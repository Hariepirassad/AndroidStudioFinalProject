package fr.esilv.s8.project.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.esilv.s8.project.R;

public class SearchPageActivity extends AppCompatActivity {
    private TextView search;
    private Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        search = (TextView) findViewById(R.id.search);
        go = (Button) findViewById(R.id.button);

        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                result(v);
            }
        });
    }

    public void result(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("text", search.getText().toString());
        startActivity(intent);
    }
}
