package lacty.it.trucphong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import lacty.it.trucphong.databinding.ActivityDanhSachTrucPhongBinding;


public class DanhSachTrucPhong extends AppCompatActivity {
    ActivityDanhSachTrucPhongBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_truc_phong);

        binding = ActivityDanhSachTrucPhongBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Sắp xếp người trực từ thứ 2 tới thứ 6
        // Khoa: 2, Tiền: 3, Hiếu: 4 Hết: 5, Bình: 6
        ArrayList<String> danhsachtruc = new ArrayList<>();
        danhsachtruc.add("Khoa");
        danhsachtruc.add("Tiền");
        danhsachtruc.add("Hiếu");
        danhsachtruc.add("Hết");
        danhsachtruc.add("Bình");

        ArrayList<String> danhsachsothe = new ArrayList<>();
        danhsachsothe.add("25985");
        danhsachsothe.add("20802");
        danhsachsothe.add("14562");
        danhsachsothe.add("15667");
        danhsachsothe.add("17430");

        Random rand = new Random();
        int nguoitructhu2 = 0, nguoitructhu3 = 0, nguoitructhu4 = 0, nguoitructhu5 = 0, nguoitructhu6 = 0;
        while (true) {
            nguoitructhu2 = rand.nextInt() % 15;
            nguoitructhu3 = rand.nextInt() % 15;
            nguoitructhu4 = rand.nextInt() % 15;
            nguoitructhu5 = rand.nextInt() % 15;
            nguoitructhu6 = rand.nextInt() % 15;
            if (nguoitructhu2 != nguoitructhu3 && nguoitructhu2 != nguoitructhu4 && nguoitructhu2 != nguoitructhu5 && nguoitructhu2 != nguoitructhu6 && nguoitructhu3 != nguoitructhu4 && nguoitructhu3 != nguoitructhu5 && nguoitructhu3 != nguoitructhu6 && nguoitructhu4 != nguoitructhu5 && nguoitructhu4 != nguoitructhu6 && nguoitructhu5 != nguoitructhu6
                    && nguoitructhu2 > 1 && nguoitructhu2 < 7 && nguoitructhu3 > 1 && nguoitructhu3 < 7 && nguoitructhu4 > 1 && nguoitructhu4 < 7 && nguoitructhu5 > 1 && nguoitructhu5 < 7 && nguoitructhu6 > 1 && nguoitructhu6 < 7) {
                break;
            }
        }

        // Đặt giá trị cho các textview Ten
        binding.textViewTen2.setText(danhsachtruc.get(nguoitructhu2 - 2));
        binding.textViewTen3.setText(danhsachtruc.get(nguoitructhu3 - 2));
        binding.textViewTen4.setText(danhsachtruc.get(nguoitructhu4 - 2));
        binding.textViewTen5.setText(danhsachtruc.get(nguoitructhu5 - 2));
        binding.textViewTen6.setText(danhsachtruc.get(nguoitructhu6 - 2));

        // Đặt giá trị cho các textview Sothe
        binding.textViewSothe2.setText(danhsachsothe.get(nguoitructhu2 - 2));
        binding.textViewSothe3.setText(danhsachsothe.get(nguoitructhu3 - 2));
        binding.textViewSothe4.setText(danhsachsothe.get(nguoitructhu4 - 2));
        binding.textViewSothe5.setText(danhsachsothe.get(nguoitructhu5 - 2));
        binding.textViewSothe6.setText(danhsachsothe.get(nguoitructhu6 - 2));

        // đặt giá trị cho textview ngày thứ 7
        Intent intent = getIntent();
        String ten7 = intent.getStringExtra("ten");
        binding.textViewTen7.setText(ten7);
        for (int i = 0; i < danhsachtruc.size(); i++) {
            if (danhsachtruc.get(i).equalsIgnoreCase(ten7)) {
                binding.textViewTen7.setText(ten7);
                binding.textViewSothe7.setText(danhsachsothe.get(i));
            }
        }

        // lấy ngày trực cho các thứ
        Calendar today = Calendar.getInstance();
        // Lấy thứ trong tuần: Monday, Tuesday, Wednesday, Thursday, Friday, Saturday,Sunday
        String thu = java.time.YearMonth.of(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1).atDay(today.get(Calendar.DATE)).getDayOfWeek().name();
        if (thu.equalsIgnoreCase("MONDAY")) {
            // cộng thêm 7 ngày vào ngày hiện tại để cho ra thứ 2
            today.add(Calendar.DAY_OF_YEAR, 7);
            binding.textViewNgay2.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay3.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay4.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay5.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay6.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay7.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
        } else if (thu.equalsIgnoreCase("TUESDAY")) {
            today.add(Calendar.DAY_OF_YEAR, 6);
            binding.textViewNgay2.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay3.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay4.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay5.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay6.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay7.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
        } else if (thu.equalsIgnoreCase("WEDNESDAY")) {
            today.add(Calendar.DAY_OF_YEAR, 5);
            binding.textViewNgay2.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay3.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay4.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay5.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay6.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay7.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
        } else if (thu.equalsIgnoreCase("THURDAY")) {
            today.add(Calendar.DAY_OF_YEAR, 4);
            binding.textViewNgay2.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay3.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay4.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay5.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay6.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay7.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
        } else if (thu.equalsIgnoreCase("FRIDAY")) {
            today.add(Calendar.DAY_OF_YEAR, 3);
            binding.textViewNgay2.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay3.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay4.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay5.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay6.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay7.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
        } else if (thu.equalsIgnoreCase("SATURDAY")) {
            today.add(Calendar.DAY_OF_YEAR, 2);
            binding.textViewNgay2.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay3.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay4.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay5.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay6.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay7.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
        } else if (thu.equalsIgnoreCase("SUNDAY")) {
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay2.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay3.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay4.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay5.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay6.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
            today.add(Calendar.DAY_OF_YEAR, 1);
            binding.textViewNgay7.setText(today.get(Calendar.DATE) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR));
        }
    }

    // tao menu screenshot
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_screenshot, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuitem_screenshot) {
            // chup man hinh
            //Bitmap bitmap = takeScreenshot(binding.tableLayout);
            Bitmap bitmap = screenShot(binding.tableLayout);
            Calendar now = Calendar.getInstance();
            String imagename = "tp" + now.get(Calendar.YEAR) + now.get(Calendar.MONTH) + now.get(Calendar.DATE) + now.get(Calendar.HOUR) + now.get(Calendar.MINUTE) + now.get(Calendar.SECOND);
            saveBitmap(bitmap, imagename);

            // Thu nho hinh sau khi chup
            Animation animationScale = AnimationUtils.loadAnimation(this, R.anim.animation_scale);
            binding.tableLayout.startAnimation(animationScale);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ham luu hinh
    public void saveBitmap(Bitmap bitmap, String name) {
        try {
            File foldertrucphong = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Trucphong/");
            if (!foldertrucphong.exists()) {
                // tao thu muc, create folder
                if(!foldertrucphong.mkdirs()){
                    Toast.makeText(this, "Thư mục Trucphong không được tạo!", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception fe) {
        }
        //File dirtrucphong = new File(getExternalFilesDir("Trucphong"),name+".png");
        File imagePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Trucphong/" + name + ".png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            Log.e("GREC", e.getMessage(), e);
        }

    }

    // chup man hinh
    public Bitmap takeScreenshot(View view) {
        //TableLayout v1 = findViewById(R.id.tableLayout);
        view.setDrawingCacheEnabled(true);

        return view.getDrawingCache();
    }

    // ham chup man hinh (dung duoc cho android 11)
    public Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    // kiem tra xem co bo nho ngoai khong
    public boolean isExternalStorgareAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }
}