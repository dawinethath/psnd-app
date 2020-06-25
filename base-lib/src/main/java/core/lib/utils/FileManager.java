package core.lib.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import lombok.val;

public class FileManager {

    public static String readTextFileInAssets(Context context, String FileName) {
        String       result       = "";
        AssetManager assetManager = context.getAssets();
        InputStream  inputStream  = null;
        try {
            inputStream = assetManager.open(FileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = readInputStream(inputStream);
        return result;
    }

    public static String readTextFileInContext(Context context, String fileName) {
        String result = "";
        try {
            fileName = context.getFilesDir().getAbsolutePath() + "/" + fileName;

            FileInputStream fstream = new FileInputStream(fileName);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader  br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                result += strLine;
            }

            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return result;
    }

    public static boolean deleteFileInContext(Context context, String fileName) {
        File file = new File(context.getFilesDir().getAbsolutePath() + "/" + fileName);
        return file.delete();
    }

    private static String readInputStream(InputStream inputStream) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte                  buf[]        = new byte[1024];
        int                   len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toString();
    }

    public static void copyFromAssets(String FromFile, String TO_PATH,
                                      Context context) throws IOException {
        //
        // //Open your local db as the input stream
        AssetManager assetManager = context.getAssets();
        InputStream  myInput      = assetManager.open(FromFile);
        //
        // // Path to the just created empty db

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(TO_PATH);
        //
        // //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int    length;
        // while ((length = myInput.read(buffer))>0){
        // myOutput.write(buffer, 0, length);
        // }
        while ((length = myInput.read(buffer)) != -1) {
            myOutput.write(buffer, 0, length);
        }
        buffer = null;
        System.gc();
        //
        // //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        //
    }

    public static Bitmap readImageFromContext(String fileName, Context context) {
        File myDir = new File(context.getFilesDir().getAbsolutePath());
        File f     = new File(myDir, fileName);
        if (f.exists() == false) {
            return null;
        }
        Bitmap bm = BitmapFactory.decodeFile(f.getAbsolutePath());
        return bm;
    }

    public static void saveImageToFileInContext(Bitmap bm, String fileName,
                                                Context context) {
        try {
            File myDir = new File(context.getFilesDir().getAbsolutePath());
            File f     = new File(myDir, fileName);
            saveImage(bm, f.getAbsolutePath());
        } catch (Exception e) {
        }
    }

    public static void saveImage(Bitmap bm, String filePath) {
        try {
            File             f   = new File(filePath);
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
        } catch (Exception e) {
        }
    }

    public static void saveImageJPG(Bitmap bm, String filePath) {
        try {
            File             f   = new File(filePath);
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
        } catch (Exception e) {
        }
    }

    public static void writeTextToFileInContext(Context con, String fileName, String text) {
        try {
            @SuppressWarnings("deprecation")
            FileOutputStream fOut = con.openFileOutput(fileName, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(text);
            osw.flush();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTextToFileInContext(Context con, String fileName, byte[] bytes) {
        try {
            FileOutputStream fOut = con.openFileOutput(fileName, Context.MODE_PRIVATE);
            fOut.write(bytes);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readFileFromContextToBytes(Context con, String fileName) {
        val    path  = con.getFilesDir().getAbsolutePath() + "/" + fileName;
        File   file  = new File(path);
        int    size  = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (Throwable e) {
            Log.e(e);
        }
        return bytes;
    }

    /**
     * This method will write text to the file with mode overwrite or append
     * depend on variable append
     *
     * @param data     : content to write in file
     * @param fileName : file name with full path
     * @param append   : true if you want to append content to file. If not, it will
     *                 overwrite.
     */
    public static void writeTextFile(String data, String fileName, boolean append) {
        try {
            // Create file - argument true mean it will append data to file, it
            // will not override old data
            FileWriter     fstream = new FileWriter(fileName, append);
            BufferedWriter out     = new BufferedWriter(fstream);
            out.write(data);
            // Close the output stream
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy a folder which is in folder assets into SDCard or fileDir of your
     * application
     *
     * @param context    : application mContext
     * @param folderSrc  : the name of folder in your folder assets
     * @param folderDest : folder destination like
     *                   getFileDir().getAbsolutePath()+"/folderName" or sdcard...
     * @throws IOException : throw IOException
     */

    public static void copyFolderFromAssets(Context context, String folderSrc,
                                            String folderDest) throws IOException {
        File dest = new File(folderDest);
        if (!dest.exists()) {
            dest.mkdir();
        }

        AssetManager asset = context.getAssets();
        String[]     files = asset.list(folderSrc);

        for (String file : files) {

            File f = new File(folderDest + "/" + file);
            if (!f.exists()) {
                InputStream  in  = asset.open(folderSrc + "/" + file);
                OutputStream out = new FileOutputStream(folderDest + "/" + file);

                byte[] buffer = new byte[1024];
                int    length;
                // copy file content in bytes
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                buffer = null;
                System.gc();
                in.close();
                out.close();
            }
        }
    }
}
