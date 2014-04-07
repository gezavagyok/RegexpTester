package hu.geza.regexptester.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void update(){
        Pattern pattern = null;
        Matcher matcher = null;
        try {
            Editable editable = ((EditText) findViewById(R.id.et_pattern)).getText();
            if (editable != null) {
                pattern = Pattern.compile(editable.toString());
            }

            editable = ((EditText) findViewById(R.id.et_regexp)).getText();
            if (editable != null) {
                matcher = pattern.matcher(editable.toString());
            }
            boolean found = false;
            while (matcher.find()) {
                ((TextView) findViewById(R.id.tv_output)).setText("I found the text " +
                                matcher.group() + " starting at " +
                                "index " + matcher.start() + " and ending at index " + matcher.end()
                );
                found = true;
            }
            if (!found) {
                ((TextView) findViewById(R.id.tv_output)).setText("No match found.");
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void check(View view) {
        update();
    }
}
