package data.personal.coursefinalversionfour;

import android.app.IntentService;
import android.content.Intent;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import org.jibble.simpleftp.SimpleFTP;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Random;


public class FileUploader extends IntentService {

    private int result = Activity.RESULT_CANCELED;
    public static final String NOTIFICATION = "data.personal.coursefinal";
    public FileUploader() {
        super("FileUploader");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Calendar c = Calendar.getInstance();
            DBHandler dbHandler = new DBHandler(this);
            UserConfig user = dbHandler.getSingleUser();
            String sDate="";
            if(user != null)
            {
                //sDate =  user.getName() + "M" + c.get(Calendar.MONTH) + "D" + c.get(Calendar.DAY_OF_MONTH) + "H" + c.get(Calendar.HOUR_OF_DAY)+ "M" + c.get(Calendar.MINUTE)+ "S" + c.get(Calendar.SECOND);
                sDate =  user.getName() + "-" + (c.get(Calendar.MONTH ) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "-" + c.get(Calendar.HOUR_OF_DAY)+ "-" + c.get(Calendar.MINUTE)+ "-" + c.get(Calendar.SECOND);
            }
            else {
                String ipaddress = "";
                for (Enumeration<NetworkInterface> en = NetworkInterface
                        .getNetworkInterfaces(); en.hasMoreElements(); ) {
                    NetworkInterface intf = (NetworkInterface) en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf
                            .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            ipaddress = inetAddress.getHostAddress().toString();
                        }
                    }
                }
                sDate =  ipaddress.replace(".","A") + "M" + c.get(Calendar.MONTH) + "D" + c.get(Calendar.DAY_OF_MONTH) + "H" + c.get(Calendar.HOUR_OF_DAY)+ "M" + c.get(Calendar.MINUTE)+ "S" + c.get(Calendar.SECOND);

            }


            System.out.println("File with DateTime: " + sDate);

            SimpleFTP ftp = new SimpleFTP();
            // Connect to an FTP server on port 21.
            ftp.connect("ftp.cleanify.dk", 21, "cleanify.dk", "Cleanify#16");
            // Set binary mode.
            ftp.bin();

            // Change to a new working directory on the FTP server.
            ftp.cwd("Personaldata/uploads");

            // Upload some files.
            // You can also upload from an InputStream, e.g.
            //File file = new File(getAssets().open("smart.jpg")));
            Random rnd = new Random();
            int file = rnd.nextInt();

            String fileName = "smart" + String.valueOf(file);
            //ftp.stor(getAssets().open("smart.jpg"), sDate + ".jpg");
            File f = new File("data/data/data.personal.coursefinalversionfour/databases/Data.db");

            FileInputStream db = new FileInputStream(f);
            ftp.stor(db, sDate+ ".db");

            // Quit from the FTP server.
            ftp.disconnect();
            Handler mHandler = new Handler(getMainLooper());
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(FileUploader.this, "File Upload Complete.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (IOException ex) {
            ex.printStackTrace();
            Handler mHandler = new Handler(getMainLooper());
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(FileUploader.this,"Something went wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            });
            Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
        }
    }
}
