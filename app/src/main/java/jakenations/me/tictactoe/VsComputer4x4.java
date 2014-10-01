package jakenations.me.tictactoe;

import android.os.Bundle;
import android.view.Window;

import me.jakenations.Board4x4;
import me.jakenations.ComputerPlayer;
import me.jakenations.GameRules4x4;


public class VsComputer4x4 extends VsComputer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_vs_computer4x4);
        this.touchables = findViewById(R.id.tableLayout).getTouchables();
        this.board = new Board4x4();
        this.rules = new GameRules4x4(board);
        this.computerPlayer = new ComputerPlayer('O', board, rules);

    }
}
