package jp.co.lbm.protox2;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String contentURL;
    // Activityからデータを戻す
    public static final String CONTENTS_URL = "jp.co.lbm.protox2.CONTENTS_URL";

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
       new ActivityResultContracts.StartActivityForResult(), result -> {
           Intent intent = result.getData();
           contentURL = intent.getStringExtra(MainActivity.CONTENTS_URL);
       }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webview);
        webView.loadUrl("https://www.google.co.jp");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_overflow_configuration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
        boolean returnVal = true;
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.overflowmenu_0:
                Intent intent = new Intent(this.getApplicationContext(), ConfigurationActivity.class);
                startActivity(intent);
                break;

            case R.id.overflowmenu_1:
                break;

            default:
                returnVal = false;
                break;
        }
        return returnVal;
    }
}