package net.simplifiedcoding.customlistviewsample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Database {
    public static volatile String dataBaseName = "deivce_db";

    public static volatile String databaseDirectory = null;

    public static volatile SQLiteDatabase sqlLite = null;

    public static synchronized boolean init(Context context) {
        try {
            databaseDirectory = "/data/data/" + context.getPackageName()

                    + "/databases/";

            File f = new File(databaseDirectory);

            if (!f.exists()) {
                boolean b = f.mkdirs();

                CopyDB(context.getAssets().open(dataBaseName),

                        new FileOutputStream(databaseDirectory + dataBaseName

                                + ""));
            } else {
                CopyDB(context.getAssets().open(dataBaseName),

                        new FileOutputStream(databaseDirectory + dataBaseName
                                + ""));
            }

            openConnection();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static synchronized void CopyDB(InputStream inputStream, OutputStream outputStream)

            throws IOException {
        byte[] buffer = new byte[1024];

        int length;

        while ((length = inputStream.read(buffer)) > 0)

        {

            outputStream.write(buffer, 0, length);

        }

        inputStream.close();

        outputStream.close();
    }

    public static synchronized void openConnection() throws Exception {
        try {
            sqlLite = SQLiteDatabase.openDatabase(databaseDirectory

                    + dataBaseName, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception ex) {

            throw new Exception("Unable To Open Database");

        }
    }

    public static synchronized void closeConnection() {
        sqlLite.close();
    }

}
