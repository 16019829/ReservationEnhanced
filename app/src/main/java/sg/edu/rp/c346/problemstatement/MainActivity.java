package sg.edu.rp.c346.problemstatement;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText number;
    EditText pax;
    EditText etInputDay;
    EditText etInputTime;
    TextView tvDay;
    TextView tvTime;
    CheckBox smoke;
    Button reserve;
    Button reset;
    final Calendar now = Calendar.getInstance();
    int day = now.get(Calendar.DAY_OF_MONTH);
    int mth = now.get(Calendar.MONTH);
    int Year = now.get(Calendar.YEAR);
    int hour = now.get(Calendar.HOUR_OF_DAY);
    int min = now.get(Calendar.MINUTE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.Name);
        number = findViewById(R.id.number);
        pax = findViewById(R.id.pax);
        smoke = findViewById(R.id.smoking);
        reserve = findViewById(R.id.reserve);
        reset = findViewById(R.id.reset);
        etInputDay = findViewById(R.id.editTextDay);
        etInputTime = findViewById(R.id.editTextTime);
        tvDay = findViewById(R.id.textViewDay);
        tvTime = findViewById(R.id.textViewTime);


        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MyActivity","Inside onClick()");
                String time = "\nTime is " + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
                String date = "\nDate is " + now.get(Calendar.DAY_OF_MONTH) + "/" + (now.get(Calendar.MONTH)+1) + "/" + now.get(Calendar.YEAR);

                String message = "name:" + name.getText() + "\nphone number:" + number.getText() + "\nnumber of people:" + pax.getText() + "\nday and time:"+ etInputDay.getText() + etInputTime.getText() ;

                if(name.getText().toString().length() == 0 || number.getText().toString().length() == 0 || pax.getText().toString().length() == 0)
                {
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                    myBuilder.setTitle("Error");
                    myBuilder.setMessage("Please write something at the selected fields");
                    myBuilder.setCancelable(false);
                    myBuilder.setPositiveButton("Dismiss", null);

                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();

                }
                else
                {
                    if (smoke.isChecked()) {
                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                        myBuilder.setTitle("Reserve");
                        myBuilder.setMessage(message + "\nSmoking Table");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Dismiss", null);

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else {
                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                        myBuilder.setTitle("Reserve");
                        myBuilder.setMessage(message + "\n Non-smoking Table");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Dismiss", null);

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }
                }
            }
        });
        etInputDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        etInputDay.setText("Date: " + dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                        day = dayOfMonth;
                        mth = monthOfYear;
                        Year = year;
                    }
                };

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, Year, mth, day);
                myDateDialog.show();
            }
        });
        etInputTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        etInputTime.setText("Time: " + hourOfDay + minute);
                        min = minute;
                        hour = hourOfDay;
                    }
                };

                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener, hour, min, true);

                myTimeDialog.show();
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText(null);
                number.setText(null);
                pax.setText(null);
                if (smoke.isChecked()) {
                    smoke.toggle();
                }
            }
        });
    }


}
