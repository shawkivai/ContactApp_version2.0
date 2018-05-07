package com.example.user04.contactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {



    EditText u_name;
    EditText u_email;
    EditText u_company;
    EditText u_number;
//  EditText u_id;

    String prev_name;

    public Bundle bundle;

    Button update;
    Button btndel;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        db =new DatabaseHelper(this);

        u_name = (EditText) findViewById(R.id.contact_name);
        u_email =(EditText) findViewById(R.id.contact_email);
        u_company=(EditText) findViewById(R.id.contact_company);
        u_number = (EditText) findViewById(R.id.contact_number);
//        u_id = (EditText) findViewById(R.id.id);


        update= (Button) findViewById(R.id.submit);







        bundle = getIntent().getExtras();
        prev_name=bundle.getString("name");

        u_name.setText(bundle.getString("name"));
        u_email.setText(bundle.getString("email"));
        u_company.setText(bundle.getString("company"));
        u_number.setText(bundle.getString("number"));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isupdated =db.updateData(prev_name,u_name.getText().toString(), u_email.getText().toString(), u_company.getText().toString(), u_number.getText().toString());
                if(isupdated==true) {
                    Toast.makeText(EditContact.this, "updated", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText( EditContact.this,"not updated", Toast.LENGTH_SHORT).show();



            }
        });

        btndel=(Button) findViewById(R.id.btndelete);
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedrows= db.deletedata(prev_name);
                if(deletedrows > 0){
                    Toast.makeText(EditContact.this, "deleted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText( EditContact.this,"not deleted", Toast.LENGTH_SHORT).show();





            }
        });






//        u_id.toString();



//        u_id.setText(bundle.getString("id"));








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
