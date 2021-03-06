package jakenations.me.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_menu);
    }


    public void vsComputer(View view) {
        Intent intent = new Intent(this, VsComputer3x3.class);
        startActivity(intent);
    }

    public void vsHuman(View view) {
        Intent intent = new Intent(this, VsHuman3x3.class);
        startActivity(intent);

    }

    public void vsComputer4x4(View view) {
        Intent intent = new Intent(this, VsComputer4x4.class);
        startActivity(intent);
}

    public void vsHuman4x4 (View view) {
        Intent intent = new Intent(this, VsHuman4x4.class);
        startActivity(intent);
    }
}
