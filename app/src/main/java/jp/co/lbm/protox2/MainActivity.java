package jp.co.lbm.protox2;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // WebViewで表示したいコンテンツのURL
    private String contentURL;

    // アクティビティの結果に対するコールバックの登録
    public static final String CONTENTS_URL = "jp.co.lbm.protox2.CONTENTS_URL";

    private WebView webView;

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
       new ActivityResultContracts.StartActivityForResult(), result -> {
           Intent intent = result.getData();

           if (result.getResultCode() == RESULT_OK) {
               contentURL = intent.getStringExtra(MainActivity.CONTENTS_URL);

               if (webView != null) {
                   webView.loadUrl(contentURL);
               }
           }

       }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.main_webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("https://www.google.co.jp");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
//                startActivity(intent);
                // 値を戻すためActivityResultLauncherのインスタンスのlaunchメソッドで起動する
                resultLauncher.launch(intent);
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