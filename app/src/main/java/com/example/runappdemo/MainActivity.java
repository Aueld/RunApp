package com.example.runappdemo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void runFileInfoDemo(View view) {
        String pkg = "com.example.fileinfodemo";
        Intent intent = getPackageManager().getLaunchIntentForPackage(pkg);
        //전에는 위 방법이 가능했으나 지금은 안되는것같다
        if (intent == null) {
            Toast.makeText(this, "intent is null", Toast.LENGTH_LONG).show();
            String cls = "com.example.fileinfodemo.MainActivity";
            intent = new Intent();
            intent.setClassName(pkg, cls);
        }
        startActivity(intent);
    }

    public void runSubactivityMain(View view) {
        String pkg = "com.example.requestdemo";
        Intent intent = getPackageManager().getLaunchIntentForPackage(pkg);

        if (intent == null) {
            Toast.makeText(this, "intent is null", Toast.LENGTH_LONG).show();
            String cls = "com.example.requestdemo.MainActivity";
            intent = new Intent();
            intent.putExtra("DATA", "Run App Demo에서 데이터 넘김");
            
            intent.setClassName(pkg, cls);
        }

        startActivity(intent);
    }
    private static final int REQUEST_CODE = 0;
    public void runSubactivitySub(View view) {
        // 아래 방법 사용 할 것
        String packageName = "com.example.requestdemo";

        String className = "com.example.requestdemo.AnswerActivity";
        Intent intent = new Intent();
        intent.setClassName(packageName, className);

        intent.putExtra("TYPE", "Int");

        startActivity(intent);
    }

    public void runGetText(View view) {
        String packageName = "com.example.requestdemo";

        String className = "com.example.requestdemo.AnswerActivity";
        Intent intent = new Intent();
        intent.setClassName(packageName, className);

        intent.putExtra("TYPE", "Str");

        resultLauncher.launch(intent);

//        TextView tv = findViewById(R.id.text2);
//        tv.setText(intent.getStringExtra("DATA"));

        //startActivity(intent);

    }

    private ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    TextView tv = findViewById(R.id.text2);
                    if(result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        int CallType = intent.getIntExtra("CallType", 0);

                        if(CallType == 0){
                            tv.setText(result.getData().getStringExtra("DATA"));
                        }else if(CallType == 1){

                        }else if(CallType == 2){
                        }
                    }
                    else

                        tv.setText("취소");
                }
            });;

}