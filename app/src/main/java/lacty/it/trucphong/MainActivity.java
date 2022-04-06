package lacty.it.trucphong;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imgVongxoay, imgTamxoay;
    int dem = -1;
    int gocquay = 0;
    int gocquaydautien = 0; // goc quay khi moi mo len
    String trungthuong = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // kiem tra cap quyen luu hinh anh
        checkAndRequestPermissions();

        imgVongxoay = (ImageView) findViewById(R.id.imageViewVongxoay);
        imgTamxoay = (ImageView) findViewById(R.id.imageViewTamqoay);
        //Animation animationRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_vongquay);

        // thuc hien xoay vong quay mot goc ngau nhien
        Random randstart = new Random();
        gocquaydautien = randstart.nextInt(360);
        // Cho tâm quay nằm ở giữa vòng quay
        RotateAnimation rotateAnimationstart = new RotateAnimation(0, gocquaydautien, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimationstart.setDuration(0);
        rotateAnimationstart.setFillAfter(true);
        Animation rotationstart = (Animation) rotateAnimationstart;
        imgVongxoay.startAnimation(rotationstart);

        imgTamxoay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dem == -1) {
                    Random rand = new Random();
                    int vitri = rand.nextInt(360) + rand.nextInt(360) + rand.nextInt(360);
                    gocquay = vitri + 1080;

                    // Cho tâm quay nằm ở giữa vòng quay
                    RotateAnimation rotateAnimation = new RotateAnimation(gocquaydautien, gocquay, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(3600);
                    rotateAnimation.setFillAfter(true);
                    Animation rotation = (Animation) rotateAnimation;
                    imgVongxoay.startAnimation(rotation);
                    int goc = gocquay % 360;
                    if ((goc >= 0 && goc <= 36) || (goc >= 324 && goc <= 360)) {
                        trungthuong = "Khoa";
                    } else if (goc > 36 && goc <= 108) {
                        trungthuong = "Bình";
                    } else if (goc > 108 && goc <= 180) {
                        trungthuong = "Hết";
                    } else if (goc > 180 && goc <= 252) {
                        trungthuong = "Hiếu";
                    } else {
                        trungthuong = "Tiền";
                    }
                    // Sau khi quay xong 3 giây thì chuyển sang màn hình danh sách trực phòng
                    CountDownTimer count = new CountDownTimer(4000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            Intent intent = new Intent();
                            intent.setClass(v.getContext(), DanhSachTrucPhong.class);
                            intent.putExtra("ten", trungthuong);
                            startActivity(intent);
                            finish(); // không cho mở lại cửa sổ này nữa, đóng nó luôn
                        }
                    };
                    count.start();
                }
            }
        });
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

    // Kiem tra xem ban co chap nhan cap quyen hay khong
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}