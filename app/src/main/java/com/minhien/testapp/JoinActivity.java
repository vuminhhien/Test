package com.minhien.testapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minhien.testapp.model.NguoiDung;

import java.util.HashMap;


public class JoinActivity extends AppCompatActivity {
    EditText edtId, edtPass, edtConfirmPass, edtEmail, edtBirthDay;
    TextView tv;
    Button btnCheckId, btnJoin, btnBack, btnSuaid;
    DatabaseReference databaseReference;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edtId = findViewById(R.id.edtId);
        edtPass = findViewById(R.id.edtNhapPassword);
        edtConfirmPass = findViewById(R.id.edtNhapLaiPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtBirthDay = findViewById(R.id.edtNgaySinh);

        tv = findViewById(R.id.tv);

        btnCheckId = findViewById(R.id.btnCheckId);
        btnJoin = findViewById(R.id.btnDangKyTaiKhoan);
        btnBack = findViewById(R.id.btnBack);
        btnSuaid = findViewById(R.id.btnSuaId);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("NguoiDung");
//       Check validation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
//       Check tên người dùng
//        awesomeValidation.addValidation(this, R.id.edtNhapTenNguoiDung, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
//       Check userid
//        awesomeValidation.addValidation(this, R.id.edtId, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
//       Check số điện thoại
        awesomeValidation.addValidation(this, R.id.edtNgaySinh, "dd-MM-yyyy", R.string.invalid_birthday);
//       Check gmail
        awesomeValidation.addValidation(this, R.id.edtEmail, Patterns.EMAIL_ADDRESS, R.string.invalid_email);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btnCheckId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkId();
            }
        });

        btnSuaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtId.setFocusableInTouchMode(true);
                tv.setVisibility(View.VISIBLE);
                btnJoin.setVisibility(View.GONE);
                btnSuaid.setVisibility(View.INVISIBLE);
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString();
                String password = edtPass.getText().toString();
                String passwordss2 = edtConfirmPass.getText().toString();
                String email = edtEmail.getText().toString();
                String birthday = edtBirthDay.getText().toString();
                if (password.length() < 6) {
                    Toast.makeText(JoinActivity.this, "Mật Khẩu Phải Từ 6 Kí Tự Trở Lên", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(passwordss2)) {
                        if (!id.equals("") && !password.equals("") && !passwordss2.equals("") && !email.equals("") && !birthday.equals("")) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("userId", id);
                            hashMap.put("password", password);
                            hashMap.put("email", email);
                            hashMap.put("birthday", birthday);
                            databaseReference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(JoinActivity.this, "Đăng Kí Thành Công!!!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(JoinActivity.this, LoginActivity.class));
                                    } else {
                                        Toast.makeText(JoinActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            awesomeValidation.validate();
                        }
                    } else {
                        Toast.makeText(JoinActivity.this, "Hai Mật Khẩu Phải Trùng Nhau", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void checkId(){
        String userid = edtId.getText().toString();
        if (!userid.equals("")) {
            databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung");
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    NguoiDung mode = snapshot.getValue(NguoiDung.class);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (userid.equals(mode.getUserId())) {
                                Toast.makeText(JoinActivity.this, "User Id đã có", Toast.LENGTH_SHORT).show();
                                Toast.makeText(JoinActivity.this, "Mời bạn nhập User ID khác", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(JoinActivity.this, "User Id này chưa có", Toast.LENGTH_SHORT).show();
                                Toast.makeText(JoinActivity.this, "Bạn có thể tiếp tục đang ký", Toast.LENGTH_SHORT).show();
                                edtId.setFocusable(false);
                                tv.setVisibility(View.GONE);
                                btnJoin.setVisibility(View.VISIBLE);
                                btnSuaid.setVisibility(View.VISIBLE);
                            }
                        }
                    }, 500);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String
                        previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String
                        previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            Toast.makeText(JoinActivity.this, "Không bỏ trống ID", Toast.LENGTH_SHORT).show();
        }
    }
}