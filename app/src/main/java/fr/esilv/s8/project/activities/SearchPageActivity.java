package fr.esilv.s8.project.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import fr.esilv.s8.project.R;

public class SearchPageActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "PingBusPrefs";
    public static final String PREFS_SEARCH_HISTORY = "SearchHistory";
    private SharedPreferences settings;
    private Set<String> history;

    private AutoCompleteTextView search;
    private Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        settings = getSharedPreferences(PREFS_NAME, 0);
        history = settings.getStringSet(PREFS_SEARCH_HISTORY, new HashSet<String>());

        setAutoCompleteSource();

        // Set the "Enter" event on the search input
        search = (AutoCompleteTextView) findViewById(R.id.search);
        search.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    addSearchInput(search.getText().toString());
                    result(v);
                    return true;
                }
                return false;
            }
        });

        go = (Button) findViewById(R.id.button);

        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addSearchInput(search.getText().toString());
                result(v);
            }
        });
    }

    public void result(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("text", search.getText().toString());
        startActivity(intent);
    }

    private void setAutoCompleteSource()
    {
        search = (AutoCompleteTextView) findViewById(R.id.search);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, history.toArray(new String[history.size()]));
        search.setAdapter(adapter);
    }

    private void addSearchInput(String input)
    {
        if (!history.contains(input))
        {
            history.add(input);
            setAutoCompleteSource();
        }
    }

    private void savePrefs()
    {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(PREFS_SEARCH_HISTORY, history);

        editor.commit();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        savePrefs();
    }
}
