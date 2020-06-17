package example.com.libary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Feedback  extends Fragment {
    EditText email,subject, msg;
    Button send1;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feedback,null);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email=(EditText) view.findViewById(R.id.email);
        subject=(EditText) view.findViewById(R.id.subject);
        msg=(EditText) view.findViewById(R.id.msh);
        send1=(Button) view.findViewById(R.id.btn);
        send1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oursubject=subject.getText().toString();
                String email_msg=email.getText().toString();
                String our_mail=email.getText().toString();
                String [] email_dv=our_mail.split(",");
                Intent send=new Intent(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_EMAIL,email_dv);
                send.putExtra(Intent.EXTRA_SUBJECT,oursubject);
                send.putExtra(Intent.EXTRA_TEXT,email_msg);
                send.setType("msg/rfc822");
                send.setPackage("com.google.android.gm");
                startActivity(send);
            }
        });
    }
}
