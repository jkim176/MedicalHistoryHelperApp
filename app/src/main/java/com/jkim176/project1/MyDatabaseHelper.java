package com.jkim176.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 1;
    // Table Names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_MEDICATIONS = "medications";
    private static final String TABLE_ALLERGIES = "allergies";
    private static final String TABLE_HOSPITALIZATIONS = "hospitalizations";
    private static final String TABLE_VACCINATIONS = "vaccinations";
    private static final String TABLE_SYMPTOMS = "symptoms";
    private static final String TABLE_PRENATAL = "prenatal";
    // Users Column Names
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "userName";
    // Medications Column Names
    private static final String KEY_MEDS_ID = "id";
    private static final String KEY_MEDS_USER_ID_FK = "userId";
    private static final String KEY_MEDS_NAME = "medName";
    private static final String KEY_MEDS_DOSE = "dose";
    private static final String KEY_MEDS_FREQ = "frequency";
    private static final String KEY_MEDS_START_DATE = "startDate";
    private static final String KEY_MEDS_END_DATE = "endDate";
    // Allergies Column Names
    private static final String KEY_ALLERGS_ID = "id";
    private static final String KEY_ALLERGS_USER_ID_FK = "userId";
    private static final String KEY_ALLERGS_NAME = "allergyName";
    private static final String KEY_ALLERGS_AREA = "area";
    private static final String KEY_ALLERGS_SEVERITY = "severity";
    private static final String KEY_ALLERGS_START_DATE = "startDate";
    private static final String KEY_ALLERGS_END_DATE = "endDate";
    // Hospitalizations Column Names
    private static final String KEY_HOSPS_ID = "id";
    private static final String KEY_HOSPS_USER_ID_FK = "userId";
    private static final String KEY_HOSPS_NAME = "hospitalizationName";
    private static final String KEY_HOSPS_COMPLICATION = "complication";    // separate table?
    private static final String KEY_HOSPS_START_DATE = "startDate";
    private static final String KEY_HOSPS_END_DATE = "endDate";
    // Vaccinations Column Names
    private static final String KEY_VACS_ID = "id";
    private static final String KEY_VACS_USER_ID_FK = "userId";
    private static final String KEY_VACS_NAME = "vaccinationName";
    private static final String KEY_VACS_BOOSTER_NUMBER = "boosterNumber";
    private static final String KEY_VACS_DATE = "date";
    // Symptoms Column Names
    private static final String KEY_SYMPS_ID = "id";
    private static final String KEY_SYMPS_USER_ID_FK = "userId";
    private static final String KEY_SYMPS_NAME = "symptomName";
    private static final String KEY_SYMPS_AREA = "area";
    private static final String KEY_SYMPS_DURATION = "duration";
    private static final String KEY_SYMPS_START_DATE = "startDate";
    private static final String KEY_SYMPS_END_DATE = "endDate";
    // ... MEDIA
    // PreNatal Column Names
    private static final String KEY_PRENATAL_ID = "id";
    private static final String KEY_PRENATAL_USER_ID_FK = "userId";
    private static final String KEY_PRENATAL_WEIGHT = "weight";
    private static final String KEY_PRENATAL_WEEK = "week";
    public static final String TAG = "MyDatabaseHelper";

    private static MyDatabaseHelper sInstance;

    public static synchronized MyDatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
            sInstance = new MyDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "(" +
                KEY_USER_ID + " INTEGER PRIMARY KEY," +
                KEY_USER_NAME + " TEXT" +
                ")";

        String CREATE_TABLE_MEDICATIONS = "CREATE TABLE " + TABLE_MEDICATIONS + "(" +
                KEY_MEDS_ID + " INTEGER PRIMARY KEY," +
                KEY_MEDS_USER_ID_FK + " INTEGER," +
                KEY_MEDS_NAME + " TEXT," +
                KEY_MEDS_DOSE + " TEXT," +
                KEY_MEDS_FREQ + " TEXT," +
                KEY_MEDS_START_DATE + " TEXT," +
                KEY_MEDS_END_DATE + " TEXT," +
                "FOREIGN KEY (" + KEY_MEDS_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + KEY_USER_ID + ")" +
                ")";

        String CREATE_TABLE_ALLERGIES = "CREATE TABLE " + TABLE_ALLERGIES + "(" +
                KEY_ALLERGS_ID + " INTEGER PRIMARY KEY," +
                KEY_ALLERGS_USER_ID_FK + " INTEGER," +
                KEY_ALLERGS_NAME + " TEXT," +
                KEY_ALLERGS_AREA + " TEXT," +
                KEY_ALLERGS_SEVERITY + " TEXT," +
                KEY_ALLERGS_START_DATE + " TEXT," +
                KEY_ALLERGS_END_DATE + " TEXT," +
                "FOREIGN KEY (" + KEY_ALLERGS_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + KEY_USER_ID + ")" +
                ")";

        String CREATE_TABLE_HOSPITALIZATIONS = "CREATE TABLE " + TABLE_HOSPITALIZATIONS + "(" +
                KEY_HOSPS_ID + " INTEGER PRIMARY KEY," +
                KEY_HOSPS_USER_ID_FK + " INTEGER," +
                KEY_HOSPS_NAME + " TEXT," +
                KEY_HOSPS_COMPLICATION + " TEXT," +
                KEY_HOSPS_START_DATE + " TEXT," +
                KEY_HOSPS_END_DATE + " TEXT," +
                "FOREIGN KEY (" + KEY_HOSPS_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + KEY_USER_ID + ")" +
                ")";

        String CREATE_TABLE_VACCINATIONS = "CREATE TABLE " + TABLE_VACCINATIONS + "(" +
                KEY_VACS_ID + " INTEGER PRIMARY KEY," +
                KEY_VACS_USER_ID_FK + " INTEGER," +
                KEY_VACS_NAME + " TEXT," +
                KEY_VACS_BOOSTER_NUMBER + " TEXT," +
                KEY_VACS_DATE + " TEXT," +
                "FOREIGN KEY (" + KEY_VACS_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + KEY_USER_ID + ")" +
                ")";

        String CREATE_TABLE_SYMPTOMS = "CREATE TABLE " + TABLE_SYMPTOMS + "(" +
                KEY_SYMPS_ID + " INTEGER PRIMARY KEY," +
                KEY_SYMPS_USER_ID_FK + " INTEGER," +
                KEY_SYMPS_NAME + " TEXT," +
                KEY_SYMPS_AREA + " TEXT," +
                KEY_SYMPS_DURATION + " TEXT," +
                KEY_SYMPS_START_DATE + " TEXT," +
                KEY_SYMPS_END_DATE + " TEXT," +
                "FOREIGN KEY (" + KEY_SYMPS_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + KEY_USER_ID + ")" +
                ")";

        String CREATE_TABLE_PRENATAL = "CREATE TABLE " + TABLE_PRENATAL + "(" +
                KEY_PRENATAL_ID + " INTEGER PRIMARY KEY," +
                KEY_PRENATAL_USER_ID_FK + " INTEGER," +
                KEY_PRENATAL_WEIGHT + " TEXT," +
                KEY_PRENATAL_WEEK + " INTEGER," +
                "FOREIGN KEY (" + KEY_PRENATAL_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + KEY_USER_ID + ")" +
                ")";

        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_MEDICATIONS);
        db.execSQL(CREATE_TABLE_ALLERGIES);
        db.execSQL(CREATE_TABLE_HOSPITALIZATIONS);
        db.execSQL(CREATE_TABLE_VACCINATIONS);
        db.execSQL(CREATE_TABLE_SYMPTOMS);
        db.execSQL(CREATE_TABLE_PRENATAL);
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICATIONS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALLERGIES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOSPITALIZATIONS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_VACCINATIONS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYMPTOMS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRENATAL);
            onCreate(db);
        }
    }

    public void deleteAllTableRows() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_ALLERGIES, null, null);
        db.delete(TABLE_HOSPITALIZATIONS, null, null);
        db.delete(TABLE_MEDICATIONS, null, null);
        db.delete(TABLE_PRENATAL, null, null);
        db.delete(TABLE_SYMPTOMS, null, null);
        db.delete(TABLE_VACCINATIONS, null, null);
        db.delete(TABLE_USERS, null, null);
    }

    public void addAllergy(Allergy allergy) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).

            ContentValues values = new ContentValues();
            values.put(KEY_ALLERGS_USER_ID_FK, allergy.getFk());
            values.put(KEY_ALLERGS_NAME, allergy.getAllergyName());
            values.put(KEY_ALLERGS_AREA, allergy.getArea());
            values.put(KEY_ALLERGS_SEVERITY, allergy.getSeverity());
            values.put(KEY_ALLERGS_START_DATE, allergy.getStartDate());
            values.put(KEY_ALLERGS_END_DATE, allergy.getEndDate());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_ALLERGIES, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add ALLERGY to database");
        } finally {
            db.endTransaction();
        }
    }

    public void addHospitalization(Hospitalization hospitalization) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).

            ContentValues values = new ContentValues();
            values.put(KEY_HOSPS_USER_ID_FK, hospitalization.getFk());
            values.put(KEY_HOSPS_NAME, hospitalization.getHospitalizationName());
            values.put(KEY_HOSPS_COMPLICATION, hospitalization.getComplication());
            values.put(KEY_HOSPS_START_DATE, hospitalization.getStartDate());
            values.put(KEY_HOSPS_END_DATE, hospitalization.getEndDate());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_HOSPITALIZATIONS, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add HOSPITALIZATION to database");
        } finally {
            db.endTransaction();
        }
    }

    public void addMedication(Medication medication) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {

            ContentValues values = new ContentValues();
            values.put(KEY_MEDS_USER_ID_FK, medication.getFk());
            values.put(KEY_MEDS_NAME, medication.getMedicationName());
            values.put(KEY_MEDS_DOSE, medication.getDose());
            values.put(KEY_MEDS_FREQ, medication.getFrequency());
            values.put(KEY_MEDS_START_DATE, medication.getStartDate());
            values.put(KEY_MEDS_END_DATE, medication.getEndDate());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_MEDICATIONS, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add MEDICATION to database");
        } finally {
            db.endTransaction();
        }
    }

    public void addPrenatal(Prenatal prenatal) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).

            ContentValues values = new ContentValues();
            values.put(KEY_PRENATAL_USER_ID_FK, prenatal.getFk());
            values.put(KEY_PRENATAL_WEIGHT, prenatal.getWeight());
            values.put(KEY_PRENATAL_WEEK, prenatal.getWeek());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_PRENATAL, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add PRENATAL to database");
        } finally {
            db.endTransaction();
        }
    }

    public void addSymptom(Symptom symptom) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).

            ContentValues values = new ContentValues();
            values.put(KEY_SYMPS_USER_ID_FK, symptom.getFk());
            values.put(KEY_SYMPS_NAME, symptom.getSymptomName());
            values.put(KEY_SYMPS_AREA, symptom.getArea());
            values.put(KEY_SYMPS_DURATION, symptom.getDuration());
            values.put(KEY_SYMPS_START_DATE, symptom.getStartDate());
            values.put(KEY_SYMPS_END_DATE, symptom.getEndDate());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_SYMPTOMS, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add SYMPTOM to database");
        } finally {
            db.endTransaction();
        }
    }

    public void addVaccination(Vaccination vaccination) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).

            ContentValues values = new ContentValues();
            values.put(KEY_VACS_USER_ID_FK, vaccination.getFk());
            values.put(KEY_VACS_NAME, vaccination.getVaccinationName());
            values.put(KEY_VACS_BOOSTER_NUMBER, vaccination.getBoosterNumber());
            values.put(KEY_VACS_DATE, vaccination.getDate());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_VACCINATIONS, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add VACCINATION to database");
        } finally {
            db.endTransaction();
        }
    }

    public long addOrUpdateUser(User user) {
        // The database connection is cached so it's not expensive to call getWriteableDatabase() multiple times.
        SQLiteDatabase db = getWritableDatabase();
        long userId = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_USER_NAME, user.getUserName());

            // First try to update the user in case the user already exists in the database
            // This assumes userNames are unique
            int rows = db.update(TABLE_USERS, values, KEY_USER_NAME + "= ?", new String[]{user.getUserName()});

            // Check if update succeeded
            if (rows == 1) {
                // Get the primary key of the user we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        KEY_USER_ID, TABLE_USERS, KEY_USER_NAME);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(user.getUserName())});
                try {
                    if (cursor.moveToFirst()) {
                        userId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // user with this userName did not already exist, so insert new user
                userId = db.insertOrThrow(TABLE_USERS, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add or update USER");
        } finally {
            db.endTransaction();
        }
        return userId;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        String SELECT_QUERY = String.format("SELECT * FROM %s", TABLE_USERS);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    User item = new User();
                    item.setId(cursor.getInt(cursor.getColumnIndex(KEY_USER_ID)));
                    item.setUserName(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
                    list.add(item);

                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get USERS from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return list;
    }

    public List<Medication> getAllMedicationsByUserId(long userId) {
        List<Medication> list = new ArrayList<>();

        // SELECT *
        String SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = %d",
                        TABLE_MEDICATIONS,
                        KEY_MEDS_USER_ID_FK,
                        userId);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Medication item = new Medication();
                    item.setFk(userId);
                    item.setMedicationName(cursor.getString(cursor.getColumnIndex(KEY_MEDS_NAME)));
                    item.setDose(cursor.getString(cursor.getColumnIndex(KEY_MEDS_DOSE)));
                    item.setFrequency(cursor.getString(cursor.getColumnIndex(KEY_MEDS_FREQ)));
                    item.setStartDate(cursor.getString(cursor.getColumnIndex(KEY_MEDS_START_DATE)));
                    item.setEndDate(cursor.getString(cursor.getColumnIndex(KEY_MEDS_END_DATE)));
                    list.add(item);

                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get MEDICATIONS from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }

    public List<Allergy> getAllAllergiesByUserId(long userId) {
        List<Allergy> list = new ArrayList<>();

        // SELECT *
        String SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = %d",
                        TABLE_ALLERGIES,
                        KEY_ALLERGS_USER_ID_FK,
                        userId);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Allergy item = new Allergy();
                    item.setFk(userId);
                    item.setAllergyName(cursor.getString(cursor.getColumnIndex(KEY_ALLERGS_NAME)));
                    item.setArea(cursor.getString(cursor.getColumnIndex(KEY_ALLERGS_AREA)));
                    item.setSeverity(cursor.getString(cursor.getColumnIndex(KEY_ALLERGS_SEVERITY)));
                    item.setStartDate(cursor.getString(cursor.getColumnIndex(KEY_ALLERGS_START_DATE)));
                    item.setEndDate(cursor.getString(cursor.getColumnIndex(KEY_ALLERGS_END_DATE)));
                    list.add(item);

                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get ALLERGIES from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }

    public List<Hospitalization> getAllHospitalizationsByUserId(long userId) {
        List<Hospitalization> list = new ArrayList<>();

        // SELECT *
        String SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = %d",
                        TABLE_HOSPITALIZATIONS,
                        KEY_HOSPS_USER_ID_FK,
                        userId);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Hospitalization item = new Hospitalization();
                    item.setFk(userId);
                    item.setHospitalizationName(cursor.getString(cursor.getColumnIndex(KEY_HOSPS_NAME)));
                    item.setComplication(cursor.getString(cursor.getColumnIndex(KEY_HOSPS_COMPLICATION)));
                    item.setStartDate(cursor.getString(cursor.getColumnIndex(KEY_HOSPS_START_DATE)));
                    item.setEndDate(cursor.getString(cursor.getColumnIndex(KEY_HOSPS_END_DATE)));
                    list.add(item);

                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get HOSPITALIZATIONS from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }

    public List<Prenatal> getAllPrenatalsByUserId(long userId) {
        List<Prenatal> list = new ArrayList<>();

        // SELECT *
        String SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = %d",
                        TABLE_PRENATAL,
                        KEY_PRENATAL_USER_ID_FK,
                        userId);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Prenatal item = new Prenatal();
                    item.setFk(userId);
                    item.setWeight(cursor.getString(cursor.getColumnIndex(KEY_PRENATAL_WEIGHT)));
                    item.setWeek(cursor.getString(cursor.getColumnIndex(KEY_PRENATAL_WEEK)));
                    list.add(item);

                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get PRENATALS from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }

    public List<Symptom> getAllSymptomsByUserId(long userId) {
        List<Symptom> list = new ArrayList<>();

        // SELECT *
        String SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = %d",
                        TABLE_SYMPTOMS,
                        KEY_SYMPS_USER_ID_FK,
                        userId);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Symptom item = new Symptom();
                    item.setFk(userId);
                    item.setSymptomName(cursor.getString(cursor.getColumnIndex(KEY_SYMPS_NAME)));
                    item.setArea(cursor.getString(cursor.getColumnIndex(KEY_SYMPS_AREA)));
                    item.setDuration(cursor.getString(cursor.getColumnIndex(KEY_SYMPS_DURATION)));
                    item.setStartDate(cursor.getString(cursor.getColumnIndex(KEY_SYMPS_START_DATE)));
                    item.setEndDate(cursor.getString(cursor.getColumnIndex(KEY_SYMPS_END_DATE)));
                    list.add(item);

                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get SYMPTOMS from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }

    public List<Vaccination> getAllVaccinationsByUserId(long userId) {
        List<Vaccination> list = new ArrayList<>();

        // SELECT *
        String SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = %d",
                        TABLE_VACCINATIONS,
                        KEY_VACS_USER_ID_FK,
                        userId);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Vaccination item = new Vaccination();
                    item.setFk(userId);
                    item.setVaccinationName(cursor.getString(cursor.getColumnIndex(KEY_VACS_NAME)));
                    item.setBoosterNumber(cursor.getString(cursor.getColumnIndex(KEY_VACS_BOOSTER_NUMBER)));
                    item.setDate(cursor.getString(cursor.getColumnIndex(KEY_VACS_DATE)));
                    list.add(item);

                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get VACCINATIONS from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }
}
