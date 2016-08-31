package ash.com.sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by AsH on 24/10/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = "DBHelper";
    private static final String DB_NAME = "FavPlaces";
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> arrayList1 = new ArrayList<>();
    private ArrayList<String> arrayList12 = new ArrayList<>();
    private ArrayList<String> arrayList13 = new ArrayList<>();
    private ArrayList<String> arrayList14 = new ArrayList<>();
    private ArrayList<String> arrayList15 = new ArrayList<>();
    private ArrayList<String> arrayList16 = new ArrayList<>();
    private ArrayList<String> arrayList17 = new ArrayList<>();
    private ArrayList<String> arrayList18 = new ArrayList<>();
    private ArrayList<LatLng> arrayList19 = new ArrayList<>();
    private ArrayList<String> arrayList20 = new ArrayList<>();
    private ArrayList<String> arrayList21 = new ArrayList<>();

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Fav" + "(lat varchar,lon  varchar,address varchar,city varchar,street varchar,country varchar,postalCode varchar,KnownName varchar, Placeplace varchar, pnt varchar )");
        sqLiteDatabase.execSQL("create table LoginDB" + "(user varchar, password varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Fav");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LoginDB");
        onCreate(sqLiteDatabase);
    }

    public Boolean Insert(double lat, double lon, String add, String city, String street, String country, String postalcode, String name, String Placeplace, String pnt) {

        SQLiteDatabase sql = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("lat", lat);
        contentValues.put("lon", lon);
        contentValues.put("address", add);
        contentValues.put("city", city);
        contentValues.put("street", street);
        contentValues.put("country", country);
        contentValues.put("postalCode", postalcode);
        contentValues.put("KnownName", name);
        contentValues.put("Placeplace", Placeplace);
        contentValues.put("pnt", pnt);
        sql.insert("Fav", null, contentValues);

        return true;
    }

    public Boolean InsertLogin(String user, String password) {


        SQLiteDatabase sqLiteDatabase1 = this.getReadableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("user", user);
        contentValues1.put("password", password);
        sqLiteDatabase1.insert("LoginDB", null, contentValues1);
        return true;

    }

    public ArrayList<String> getUserName() {


        SQLiteDatabase sqLiteDatabaseuser = this.getReadableDatabase();
        Cursor UserCursor = sqLiteDatabaseuser.rawQuery("select user from LoginDB", null);
        UserCursor.moveToFirst();
        int a = UserCursor.getCount();
        try {

            for (int i = 0; i < a; i++) {
                arrayList20.add(UserCursor.getString(UserCursor.getColumnIndex("user")));
                UserCursor.moveToNext();
            }
        } catch (IndexOutOfBoundsException e) {

        }
        UserCursor.close();
        return arrayList20;


    }


    public ArrayList<String> getPassword() {


        SQLiteDatabase sqLiteDatabasepwd = this.getReadableDatabase();
        Cursor PwdCursor = sqLiteDatabasepwd.rawQuery("select password from LoginDB", null);
        PwdCursor.moveToFirst();
        int a = PwdCursor.getCount();
        try {
            for (int i = 0; i < a; i++) {
                arrayList21.add(PwdCursor.getString(PwdCursor.getColumnIndex("password")));
                PwdCursor.moveToNext();
            }
        } catch (IndexOutOfBoundsException e) {

        }
        PwdCursor.close();
        return arrayList21;


    }


    public ArrayList<String> getLat() {

        SQLiteDatabase sqdd = this.getReadableDatabase();
        Cursor crsr = sqdd.rawQuery("select lat from Fav", null);
        crsr.moveToFirst();
        int p = crsr.getCount();

        Log.v(LOG_TAG, "no of fav is" + p);

        try {

            for (int i = 0; i < p; i++) {
                arrayList.add(crsr.getString(crsr.getColumnIndex("lat")));
                crsr.moveToNext();
            }
        } catch (IndexOutOfBoundsException e) {

        }
        crsr.close();
        return arrayList;

    }


    public ArrayList<String> getLon() {

        SQLiteDatabase sqddd = this.getReadableDatabase();
        Cursor crsr1 = sqddd.rawQuery("select lon from Fav", null);
        crsr1.moveToFirst();
        int p = crsr1.getCount();

        Log.v(LOG_TAG, "no of fav is" + p);

        try {

            for (int i = 0; i < p; i++) {
                arrayList1.add(crsr1.getString(crsr1.getColumnIndex("lon")));
                crsr1.moveToNext();
            }
        } catch (IndexOutOfBoundsException e) {

        }
        crsr1.close();
        return arrayList1;

    }


    public ArrayList<String> getAdd() {

        SQLiteDatabase sqddd1 = this.getReadableDatabase();
        Cursor crsr2 = sqddd1.rawQuery("select address from Fav", null);
        crsr2.moveToFirst();
        int p = crsr2.getCount();

        Log.v(LOG_TAG, "no of fav is" + p);

        try {

            for (int i = 0; i < p; i++) {
                arrayList12.add(crsr2.getString(crsr2.getColumnIndex("address")));
                crsr2.moveToNext();
            }
        } catch (IndexOutOfBoundsException e) {

        }
        crsr2.close();
        return arrayList12;

    }

    public ArrayList<String> getcity() {

        SQLiteDatabase sqddd2 = this.getReadableDatabase();
        Cursor crsr3 = sqddd2.rawQuery("select city from Fav", null);
        crsr3.moveToFirst();
        int p = crsr3.getCount();

        Log.v(LOG_TAG, "no of fav is" + p);

        try {

            for (int i = 0; i < p; i++) {
                arrayList13.add(crsr3.getString(crsr3.getColumnIndex("city")));
                crsr3.moveToNext();
            }
        } catch (IndexOutOfBoundsException e) {

        }
        crsr3.close();
        return arrayList13;

    }

    public ArrayList<String> getstreet() {

        SQLiteDatabase sqddd3 = this.getReadableDatabase();
        Cursor crsr4 = sqddd3.rawQuery("select street from Fav", null);
        crsr4.moveToFirst();
        int p = crsr4.getCount();

        Log.v(LOG_TAG, "no of fav is" + p);

        try {

            for (int i = 0; i < p; i++) {
                arrayList14.add(crsr4.getString(crsr4.getColumnIndex("street")));
                crsr4.moveToNext();
            }
        } catch (IndexOutOfBoundsException e) {

        }
        crsr4.close();
        return arrayList14;

    }

    public ArrayList<String> getcountry() {

        SQLiteDatabase sqddd4 = this.getReadableDatabase();
        Cursor crsr5 = sqddd4.rawQuery("select country from Fav", null);
        crsr5.moveToFirst();
        int p = crsr5.getCount();

        Log.v(LOG_TAG, "no of fav is" + p);

        try {

            for (int i = 0; i < p; i++) {
                arrayList15.add(crsr5.getString(crsr5.getColumnIndex("country")));
                crsr5.moveToNext();
            }
        } catch (IndexOutOfBoundsException e) {

        }
        crsr5.close();
        return arrayList15;

    }

    public ArrayList<String> getpostalCode() {

        SQLiteDatabase sqddd5 = this.getReadableDatabase();
        Cursor crsr6 = sqddd5.rawQuery("select postalCode from Fav", null);
        crsr6.moveToFirst();
        int p = crsr6.getCount();

        Log.v(LOG_TAG, "no of fav is" + p);

        try {

            for (int i = 0; i < p; i++) {
                arrayList16.add(crsr6.getString(crsr6.getColumnIndex("postalCode")));
                crsr6.moveToNext();
            }
        } catch (IndexOutOfBoundsException e) {

        }
        crsr6.close();
        return arrayList16;

    }

    public ArrayList<String> getKnownName() {

        SQLiteDatabase sqddd6 = this.getReadableDatabase();
        Cursor crsr17 = sqddd6.rawQuery("select KnownName from Fav", null);
        crsr17.moveToFirst();
        int p = crsr17.getCount();

        Log.v(LOG_TAG, "no of fav is" + p);

        try {

            for (int i = 0; i < p; i++) {
                arrayList17.add(crsr17.getString(crsr17.getColumnIndex("KnownName")));
                crsr17.moveToNext();
            }
        } catch (IndexOutOfBoundsException e) {

        }
        crsr17.close();
        return arrayList17;

    }

    public ArrayList<String> getPlaceplace() {

        SQLiteDatabase sqddd7 = this.getReadableDatabase();
        Cursor crsr18 = sqddd7.rawQuery("select Placeplace from Fav", null);
        crsr18.moveToFirst();
        int p = crsr18.getCount();

        Log.v(LOG_TAG, "no of fav is" + p);

        try {

            for (int i = 0; i < p; i++) {
                arrayList18.add(crsr18.getString(crsr18.getColumnIndex("Placeplace")));
                crsr18.moveToNext();
            }
        } catch (IndexOutOfBoundsException e) {

        }
        crsr18.close();
        return arrayList18;

    }


}
