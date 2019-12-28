package tw.mnya.rxnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import io.andref.rx.network.RxNetwork;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = findViewById(R.id.info);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        RxNetwork.connectivityChanges(this, connectivityManager)
                .subscribe(new Action1<Boolean>()
                {
                    @Override
                    public void call(Boolean connected)
                    {
                        if (connected) {
                            info.setText("有網路");
                            Toast.makeText(getApplicationContext(), "網路連線", Toast.LENGTH_SHORT).show();
                        } else {
                            info.setText("無網路");
                            Toast.makeText(getApplicationContext(), "網路中斷", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
