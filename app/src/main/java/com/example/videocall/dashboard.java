package com.example.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class dashboard extends AppCompatActivity {
    private EditText code;
    private Button jb;
    private Button sb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        code = (EditText) findViewById(R.id.code);
        jb = (Button) findViewById(R.id.jb);
        sb = (Button) findViewById(R.id.sb);

        URL serverURL;
        try {
            serverURL=new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultopation=
                    new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL)
                    .setWelcomePageEnabled(false).build();
            JitsiMeet.setDefaultConferenceOptions(defaultopation );

        } catch (MalformedURLException e) {
            e.printStackTrace();

        }

        jb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                 JitsiMeetConferenceOptions option= new JitsiMeetConferenceOptions.Builder()
                         .setRoom(code.getText().toString())
                         .setWelcomePageEnabled(false)
                         .build();
                JitsiMeetActivity.launch(dashboard.this,option);

            }
        });


    }
}