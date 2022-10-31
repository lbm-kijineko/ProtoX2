package jp.co.lbm.protox2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // WebViewで表示したいコンテンツのURL
    private String contentsURL;

    // アクティビティの結果に対するコールバックの登録
    public static final String CONTENTS_URL = "jp.co.lbm.protox2.CONTENTS_URL";

    private WebView webView;
    private DatabaseHelper dbHelper;

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
       new ActivityResultContracts.StartActivityForResult(), result -> {
           Intent intent = result.getData();

           if (result.getResultCode() == RESULT_OK) {
               contentsURL = Objects.requireNonNull(intent).getStringExtra(MainActivity.CONTENTS_URL);

               if (contentsURL != null && !contentsURL.isEmpty()) {

                   if (webView != null) {
                       webView.loadUrl(contentsURL);

                       SQLiteDatabase db = dbHelper.getWritableDatabase();
//                       String sql = "update settings set contents_url = \"" + contentURL + "\"";
                       ContentValues values = new ContentValues();
                       values.put("contents_url", contentsURL);
                       long id = db.insert("settings", null, values);
//                       String sql = "insert into settings(contents_url) values ('" + contentURL + "');";
//                       db.execSQL(sql);
                       db.close();
                   }
               }
           }

       }
    );

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.main_webview);
        //リンクをタップしたときに標準ブラウザを起動させない
        webView.setWebViewClient(new WebViewClient());
        //JavaScriptを許可
        webView.getSettings().setJavaScriptEnabled(true);


        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.createDatabase();
        }
        catch (IOException e) {
            throw new Error("Unable to create database");
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select contents_url from settings";

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        if (0 < count) {
            boolean result = cursor.moveToNext();
            contentsURL = "";
            contentsURL = cursor.getString(0);

            webView.loadUrl(contentsURL);
        }

        db.close();

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