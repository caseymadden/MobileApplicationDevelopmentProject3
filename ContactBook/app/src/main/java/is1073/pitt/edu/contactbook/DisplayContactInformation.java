package is1073.pitt.edu.contactbook;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayContactInformation extends AppCompatActivity {
    private ListView lstContactInfo;
    private ArrayList<User> contactInfo;
    private ArrayList<String> contactInfoString;
    private String tag = "TEST_";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact_information);
        SqliteUtilities db = new SqliteUtilities(this);

        Intent intent = getIntent();
        String contactID = intent.getStringExtra("contactID");
        initControls(contactID);
    }

    private void initControls(String contactID){
        lstContactInfo = (ListView) findViewById(R.id.lstContactInfo);
        contactInfo = new ArrayList<User>();
        contactInfoString = new ArrayList<>();

        String sql = "SELECT contactID, firstName, lastName, address1, address2 FROM addressbook WHERE contactID = '" + contactID + "';";
        SqliteUtilities db = new SqliteUtilities(this);
        Cursor cursor = db.getResultSet(sql);
        while(cursor.moveToNext()){
            User contact = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
            contactInfo.add(contact);
            contactInfoString.add(contact.getLastName() + "_" + contact.getFirstName()+ "_" + contact.getAddress1() + "_");
        }
        // contactList.add("Dmitriy Babichenko");
        // contactList.add("John Doe");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactInfoString);
        lstContactInfo.setAdapter(adapter);
    }

}
