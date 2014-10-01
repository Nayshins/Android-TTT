package jakenations.me.tictactoe;

import android.os.Bundle;
import android.view.Window;

import me.jakenations.Board3x3;
import me.jakenations.GameRules;


public class VsHuman3x3 extends VsHuman {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.board = new Board3x3();
        this.rules = new GameRules(board);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tic_tac_toe);
        this.touchables = findViewById(R.id.tableLayout).getTouchables();
    }
}