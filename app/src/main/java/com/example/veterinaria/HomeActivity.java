package com.example.veterinaria;

    import android.os.Bundle;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.Menu;
    import android.widget.Toast;

    import com.google.android.material.navigation.NavigationView;

    import androidx.annotation.NonNull;
    import androidx.navigation.NavController;
    import androidx.navigation.Navigation;
    import androidx.navigation.ui.AppBarConfiguration;
    import androidx.navigation.ui.NavigationUI;
    import androidx.drawerlayout.widget.DrawerLayout;
    import androidx.appcompat.app.AppCompatActivity;

    import com.example.veterinaria.databinding.ActivityHomeBinding;

    public class HomeActivity extends AppCompatActivity {

        private AppBarConfiguration mAppBarConfiguration;
        private ActivityHomeBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            binding = ActivityHomeBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            setSupportActionBar(binding.appBarHome.toolbar);

            DrawerLayout drawer = binding.drawerLayout;
            NavigationView navigationView = binding.navView;

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                    .setOpenableLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.home, menu);
            return true;
        }

        @Override
        public boolean onSupportNavigateUp() {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
            return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                    || super.onSupportNavigateUp();
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId(); // Get the item ID once

            if (itemId == R.id.action_settings) {
                Toast.makeText(this, "Aun no hay nada", Toast.LENGTH_SHORT).show();
                return true; // Indicate that the event was handled
            }
            else if (itemId == R.id.mnSalir) {
                finishAffinity();
                return true; // Indica que el evento fue manejado
            } else {
                return super.onOptionsItemSelected(item); // Default handling
            }
        }
    }