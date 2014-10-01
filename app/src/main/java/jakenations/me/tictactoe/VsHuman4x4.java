package jakenations.me.tictactoe;

import android.os.Bundle;
import android.view.Window;

import me.jakenations.Board4x4;
import me.jakenations.GameRules4x4;


public class VsHuman4x4 extends VsHuman {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.board = new Board4x4();
        this.rules = new GameRules4x4(board);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_human4x4);
        this.touchables = findViewById(R.id.tableLayout).getTouchables();
    }
}
