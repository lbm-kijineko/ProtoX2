package jp.co.lbm.protox2;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        // アクションバーに戻るを表示
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);

        boolean returnVal = true;
        int itemId = item.getItemId();
        // 戻る矢印押下
        if(itemId == android.R.id.home) {

            SettingFragment settingFragment = (SettingFragment)getSupportFragmentManager().findFragmentById(R.id.SettingFragment);
            if (settingFragment != null) {
                String contentUrlString = settingFragment.editText.getText().toString();

                // MainActivityに戻るので、設定されたコンテンツURLを渡す。
                Intent intent = new Intent();
                intent.putExtra(MainActivity.CONTENTS_URL, contentUrlString);

                setResult(Activity.RESULT_OK, intent);
            }

            finish();
        }
        else {
            returnVal = super.onOptionsItemSelected(item);
        }
        return returnVal;

    }
}