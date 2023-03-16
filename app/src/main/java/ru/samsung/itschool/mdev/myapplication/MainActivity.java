package ru.samsung.itschool.mdev.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import ru.samsung.itschool.mdev.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
        loginViewModel.getUser().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                User user = (User) o;
                if (user != null)
                    if (user.getEmail().length() > 0 || user.getPassword().length() > 0)
                        Toast.makeText(getApplicationContext(), "Email : " + user.getEmail() + " Password " + user.getPassword(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}