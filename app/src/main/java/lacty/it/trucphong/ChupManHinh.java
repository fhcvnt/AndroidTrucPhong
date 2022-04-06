package lacty.it.trucphong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChupManHinh extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chup_man_hinh);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndRequestPermissions();
                // takeScreenshot();
                //Bitmap bitmap = takeScreenshot2();
                //  Bitmap bitmap = takeScreenshot3();
                // Calendar now=Calendar.getInstance();
                // String a="trucphong"+now.get(Calendar.DATE)+"-"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.YEAR);
                //saveBitmap(bitmap,a);

                Bitmap bitmap2 = screenShot(findViewById(R.id.table));
                saveBitmap(bitmap2,"u77");

            }
        });
    }



    // đây là cách bạn có thể mở hình ảnh được tạo gần đây
    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    // Ham chup man hinh
    private void takeScreenshot() {
        Calendar nowday = Calendar.getInstance();
        String now = "trucphong" + nowday.get(Calendar.DATE) + "-" + nowday.get(Calendar.MONTH) + "-" + nowday.get(Calendar.YEAR);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            // String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
            String mPath = Environment.getExternalStorageDirectory() + "/img" + now + ".jpg";
            Log.d("IN", "Xin chao = " + Environment.getExternalStorageDirectory().toString());
            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    // Kiem tra xem ban co chap nhan cap quyen hay khong
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // hien thi hop thoai cap quyen cho ung dung de truy cap vao bo nho de luu tru ngoai
    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }

    // *********************************
    public Bitmap takeScreenshot2() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    // *********************************
    public Bitmap takeScreenshot3() {
        //   Button v1 = findViewById(R.id.button);
        // v1.setDrawingCacheEnabled(true);
        // Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
        //v1.setDrawingCacheEnabled(false);


        TableLayout v1 = findViewById(R.id.table);
        v1.setDrawingCacheEnabled(true);

        return v1.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap, String name) {
        File imagePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + name + ".jpg");

//        File imagePath;
//        if (android.os.Build.VERSION.SDK_INT >= 29) {
//            // ==> /storage/emulated/0/Android/data/org.o7planning.externalstoragedemo/files
//            File dir = this.getExternalFilesDir( null);
//            String chuoi=dir.getAbsolutePath()+"/" + name + ".jpg";
//            imagePath=new File(chuoi);
//
//        }
//        else{
////            File dir = this.getExternalFilesDir( null);
////            String chuoi=dir.getAbsolutePath()+"/" + name + ".jpg";
////            imagePath=new File(chuoi);
//           imagePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + name + ".jpg");
//        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    // ham chup man hinh theo cach khac (dung duoc cho android 11)
    public Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    // kiem tra xem co bo nho ngoai khong
    public boolean isExternalStorgareAvailable(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            return true;
        }
        return false;
    }
}