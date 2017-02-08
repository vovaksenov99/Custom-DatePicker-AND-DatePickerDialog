package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Created by vovaaksenov99 on 15.05.2016.
 */
public class MyUtility {

    public static void SaveImage(Bitmap finalBitmap) {

        String path = String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        File myDir = new File(path);
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File(myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param src
     * @return
     */
    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return myBitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch(OutOfMemoryError er) {
            er.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param srs
     * @return
     */
    public static String convertToUTF8(String srs) {
        String out = null;
        try {
            out = new String(srs.getBytes("UTF-8"), "ISO-8859-1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    /**
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        /*ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && isOnline();
        */
        return true;
    }

    public static Boolean isOnline() {

        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    /**
     *
     * @param view
     */
    public static void removeView(View view)
    {
        if(view==null)
            return;
        ViewGroup layout = (ViewGroup) view.getParent();
        if(null!=layout)
            layout.removeView(view);
    }
    /**
     *
     * @param s
     */
    public static String StringNormalize(String s)
    {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static float GetDpDimensions(Context context, float dp)
    {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float fpixels = metrics.density * dp;
        return (int) (fpixels + 0.5f);
    }

    public static boolean isImage(String path)
    {
        String extension = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
        String[] okFileExtensions =  new String[] {"jpg", "png", "gif","jpeg"};
        for(int i=0;i<okFileExtensions.length;i++)
        {
            if(extension.equals(okFileExtensions[i]) == true)
            {
                return true;
            }
        }
        return false;
    }
    public static boolean isImage(File file)
    {
        String filename = file.getName();
        String filenameArray[] = filename.split("\\.");
        String extension = filenameArray[filenameArray.length-1].toLowerCase();
        String[] okFileExtensions =  new String[] {"jpg", "png", "gif","jpeg"};
        for(int i=0;i<okFileExtensions.length;i++)
        {
            if(extension.equals(okFileExtensions[i]) == true)
            {
                return true;
            }
        }
        return false;
    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static Bitmap cropBitmap(Bitmap bmp2)
    {
        Bitmap comp;
        if (bmp2.getWidth() >= bmp2.getHeight()){

            comp = Bitmap.createBitmap(
                    bmp2,
                    bmp2.getWidth()/2 - bmp2.getHeight()/2,
                    0,
                    bmp2.getHeight(),
                    bmp2.getHeight()
            );

        }else{

            comp = Bitmap.createBitmap(
                    bmp2,
                    0,
                    bmp2.getHeight()/2 - bmp2.getWidth()/2,
                    bmp2.getWidth(),
                    bmp2.getWidth()
            );
        }
        return comp;
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public static String[] String_split_by_delims(String input, String delims)
    {
        final String[] tokens = input.split(delims);
        return tokens;
    }


}