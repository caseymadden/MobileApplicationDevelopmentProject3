package is1073.pitt.edu.contactbook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

public class ContactListActivity extends AppCompatActivity {

    private String debugTag = "ADDRESS_";

    private ListView lstContactList;
    private ArrayList<User> contactList;
    private ArrayList<String> contactNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        SqliteUtilities db = new SqliteUtilities(this);
        insertTestValues("Casey", "Madden", "3603 Bates Street", "Apartment 2", "Pittsburgh", "PA", "15213", "USA", "412-721-8107", "ccm46@pitt.edu");
        initControls();

    }

    private void insertTestValues(String firstName, String lastName, String address1, String address2, String city, String state, String zip, String country, String phoneNumber, String email){
        SqliteUtilities db = new SqliteUtilities(this);
        ContentValues cv = new ContentValues();
        cv.put("contactID", UUID.randomUUID().toString());
        cv.put("firstName", firstName);
        cv.put("lastName", lastName);
        cv.put("address1", address1);
        cv.put("address2", address2);
        cv.put("city", city);
        cv.put("state", state);
        cv.put("zip", zip);
        cv.put("country", country);
        cv.put("phoneNumber", phoneNumber);
        cv.put("email", email);
        db.insertRecord("addressbook", cv);
    }

    private void initControls(){
        lstContactList = (ListView) findViewById(R.id.lstContactList);

        contactList = new ArrayList<User>();
        contactNames = new ArrayList<>();

        String sql = "SELECT contactID, lastName, firstName FROM addressbook ORDER BY lastName;";
        SqliteUtilities db = new SqliteUtilities(this);
        Cursor cursor = db.getResultSet(sql);
        String[] debug = cursor.getColumnNames();
        Log.d(debugTag, debug[0]);
        Log.d(debugTag, debug[1]);
        Log.d(debugTag, debug[2]);
        while(cursor.moveToNext()){
            User contact = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
            contactList.add(contact);
            contactNames.add(contact.toString());
        }
        // contactList.add("Dmitriy Babichenko");
        // contactList.add("John Doe");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactNames);
        lstContactList.setAdapter(adapter);

        lstContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedContact = contactList.get(position);
                String selectedContactID = selectedContact.getUserID();
                //Toast.makeText(getApplicationContext(), selectedContactID, Toast.LENGTH_LONG).show();
                viewContactInfo(view, selectedContactID);
            }
        });
        cursor.close();
    }

    public void addNewContact(View view){
        Intent intent = new Intent(ContactListActivity.this, EditContactActivity.class);
        ContactListActivity.this.startActivity(intent);

    }

    public void viewContactInfo(View view, String selectedContactID){
        Intent intent = new Intent(ContactListActivity.this, DisplayContactInformation.class);
        Log.d(debugTag, selectedContactID);
        intent.putExtra("contactID", selectedContactID);
        ContactListActivity.this.startActivity(intent);
    }

    public void dropTable(View view){
        SqliteUtilities db = new SqliteUtilities(this);
        db.dropTable();
    }

}
