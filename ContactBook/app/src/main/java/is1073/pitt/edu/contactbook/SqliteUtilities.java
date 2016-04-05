package is1073.pitt.edu.contactbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteUtilities extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "addressbook";


	
	/**
	 * Default constructor.  Currently doesn't do anything except initializing
	 * the constructor of its superclass
	 * @param context - refers to the Android activity that creates an instance of this class
	 */
	public SqliteUtilities(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * This method is triggered every time an instance of this class is created.
	 * If the listed in this method do not exist in local database, they are created.
	 */
	public void onCreate(SQLiteDatabase db) {
		// Create tables
		String sql = "CREATE TABLE addressbook (";
		sql += "contactID varchar(36) NOT NULL, ";
		sql += "lastName varchar(100) NOT NULL, ";
		sql += "firstName varchar(100) NOT NULL, ";
		sql += "address1 varchar(100) NOT NULL, ";
		sql += "address2 varchar(100) NOT NULL, ";
		sql += "city varchar(100) NOT NULL, ";
		sql += "state varchar(100) NOT NULL,";
		sql += "zip varchar(100) NOT NULL,";
		sql += "country varchar(100) NOT NULL,";
		sql += "phoneNumber varchar(100) NOT NULL,";
		sql += "email varchar(100) NOT NULL, ";
		sql += "PRIMARY KEY (contactID)); ";
		
		db.execSQL(sql);
	}

	@Override
	/**
	 * We did not implement this method.  Normally this method is used to propagate
	 * changes to the database structure between versions of an application. 
	 * When a version changes, this method gets triggered
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Inserts record into a specified table
	 * @param tableName - table into which the record should be inserted
	 * @param values - values to be inserted into a specified table
	 * Note that ContentValues is essentially a wrapper for Hashtable and works in
	 * a similar fashion
	 */
	public void insertRecord(String tableName, ContentValues values){
		SQLiteDatabase db = this.getWritableDatabase();
		db.insert(tableName, null, values);
		db.close();
	}
	
	/**
	 * Updates record in a specified table
	 * @param tableName - table into which the record should be inserted
	 * @param values - values to be inserted into a specified table
	 * @param whereClause - where clause in a format "field1=? AND field2 = ?" 
	 * @param whereArgs - String array containing arguments for the where clause.  
	 * 						These arguments must be in the same order as fields in the 
	 * 						where clause and will replace question marks in where clause
	 * 						parameters. 
	 */
	public void updateRecord(String tableName, ContentValues values, String whereClause, String[] whereArgs){
		SQLiteDatabase db = this.getWritableDatabase();
		db.update(tableName, values, whereClause, whereArgs);
	}
	
	/**
	 * Deletes record in a specified table
	 * @param tableName - table from which the record should be deleted
	 * @param whereClause - where clause in a format "field1=? AND field2 = ?" 
	 * @param whereArgs - String array containing arguments for the where clause.  
	 * 						These arguments must be in the same order as fields in the 
	 * 						where clause and will replace question marks in where clause
	 * 						parameters.
	 */
	public void deleteRecord(String tableName, String whereClause, String[] whereArgs){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(tableName, whereClause, whereArgs);
	}
	
	/**
	 * Gets a resultset for a SELECT query
	 * @param sql - SELECT query
	 * @return Cursor - this is Android equivalent of Java's ResultSet
	 */
	public Cursor getResultSet(String sql){
		SQLiteDatabase db = this.getReadableDatabase();
		return db.rawQuery(sql, null);
	}

	public void dropTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS addressbook");
	}

}
