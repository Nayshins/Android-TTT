package jakenations.me.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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



    public void runGame(View view) {

    }
}
