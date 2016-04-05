package is1073.pitt.edu.contactbook;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.UUID;

public class EditContactActivity extends AppCompatActivity {

    EditText txtFirstName;
    EditText txtLastName;
    EditText txtAddress1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
    }

    private void initControls(){
        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        txtAddress1 = (EditText) findViewById(R.id.txtAddress1);


    }

    public void insertNewValues(String firstName, String lastName, String address1){
        SqliteUtilities db = new SqliteUtilities(this);
        ContentValues cv = new ContentValues();
        cv.put("contactID", UUID.randomUUID().toString());
        cv.put("firstName", firstName);
        cv.put("lastName", lastName);
        cv.put("address1", address1);
        db.insertRecord("addressbook", cv);
    }
}
