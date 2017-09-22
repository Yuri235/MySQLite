package ua.init.team.mysqlite.activity;

import android.database.CursorIndexOutOfBoundsException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import ua.init.team.mysqlite.R;
import ua.init.team.mysqlite.sqlite.MySQLiteDB;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "myLogs";


    EditText name;
    EditText secondName;
    EditText bDay;

    TextView print;

    Button set;
    Button get;

    MySQLiteDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        secondName = (EditText)findViewById(R.id.secondName);
        bDay = (EditText)findViewById(R.id.b_day);

        print = (TextView)findViewById(R.id.textView);

        set = (Button)findViewById(R.id.bt_set);
        get = (Button)findViewById(R.id.bt_get);

        set.setOnClickListener(this);
        get.setOnClickListener(this);

        db = new MySQLiteDB(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.bt_set:

                db.addModels(name.getText().toString(),secondName.getText().toString()
                        ,bDay.getText().toString());


                break;
            case R.id.bt_get:

                try {

                    print.setText(db.getModels(3));
                }catch(CursorIndexOutOfBoundsException e){
                    Log.d(TAG , "You enter wrong mean");
                    print.setText("You enter wrong mean");
                }


                break;
        }
    }
}
