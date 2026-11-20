package com.yogesh.androidlabpracticals.practical7;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.yogesh.androidlabpracticals.R;

public class InternetEmailActivity extends AppCompatActivity {

    private EditText etUrl, etTo, etSubject, etBody;
    private TextView tvConnStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_email);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Practical 7 – Internet & Email");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etUrl = findViewById(R.id.et_url);
        etTo = findViewById(R.id.et_email_to);
        etSubject = findViewById(R.id.et_email_subject);
        etBody = findViewById(R.id.et_email_body);
        tvConnStatus = findViewById(R.id.tv_conn_status);

        Button btnCheckConn = findViewById(R.id.btn_check_conn);
        Button btnOpenUrl = findViewById(R.id.btn_open_url);
        Button btnSendEmail = findViewById(R.id.btn_send_email);

        btnCheckConn.setOnClickListener(v -> checkConnectivity());
        btnOpenUrl.setOnClickListener(v -> openUrl());
        btnSendEmail.setOnClickListener(v -> sendEmail());

        checkConnectivity();
    }

    private void checkConnectivity() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        boolean connected = info != null && info.isConnected();
        tvConnStatus.setText(connected ? "🟢 Internet Connected" : "🔴 No Internet Connection");
        tvConnStatus.setTextColor(connected ?
                getResources().getColor(R.color.p9_color) :
                getResources().getColor(R.color.p3_color));
    }

    private void openUrl() {
        String url = etUrl.getText().toString().trim();
        if (url.isEmpty()) url = "https://www.google.com";
        if (!url.startsWith("http://") && !url.startsWith("https://")) url = "https://" + url;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Cannot open URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void sendEmail() {
        String to = etTo.getText().toString().trim();
        String subject = etSubject.getText().toString().trim();
        String body = etBody.getText().toString().trim();

        if (to.isEmpty()) {
            Toast.makeText(this, "Please enter recipient email.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.isEmpty() ? "Hello from Android Lab" : subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body.isEmpty() ? "Sent from Android Lab Practicals app by Yogesh." : body);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send Email via..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
