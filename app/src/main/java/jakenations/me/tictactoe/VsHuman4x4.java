package jakenations.me.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import me.jakenations.Board;
import me.jakenations.Board4x4;
import me.jakenations.GameRules4x4;
import me.jakenations.Rules;


public class VsHuman4x4 extends Activity {
    private static final HashMap<String, Integer> cellToMoveMap;
    static {
        cellToMoveMap = new HashMap<String, Integer>();
        for (int i = 0; i < 16; i++) {
            cellToMoveMap.put("c" + i, i);
        }
    }
    private Board board = new Board4x4();
    private Rules rules = new GameRules4x4(board);
    private ArrayList<View> touchables;

    private int turnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_human4x4);
        this.touchables = findViewById(R.id.tableLayout).getTouchables();
    }

    public void runGame(View view) throws Exception {
        char marker = getCurrentPlayer();
        setButtonStatus(false);
        setButtonText((Button) view, marker);
        int move = getButtonIndex((Button) view);
        setHumanMove(move, marker);
        setButtonStatus(true);
        if (rules.isGameOver()) {
            setButtonStatus(false);
            gameOver(marker);
        }
        turnCount ++;
    }

    private char getCurrentPlayer() {
        if (turnCount % 2 == 0) {
            return 'X';
        } else {
            return 'O';
        }
    }

    private void setButtonStatus(boolean state) {
        for (View touchable : touchables) {
            if (touchable instanceof Button) {
                touchable.setEnabled(state);
            }
        }
    }

    private void setButtonText(Button view, char marker) {
        if (marker == 'X') {
            view.setText(R.string.marker_X);
        } else if (marker == 'O') {
            view.setText(R.string.marker_O);
        }
    }

    private int getButtonIndex(Button view) {
        view.setClickable(false);
        return convertCellToInt((String) view.getTag());
    }

    public void setHumanMove(int move, char marker) throws Exception {
        board.setCell(marker, move);
    }

    public int convertCellToInt(String cellID) {
        return cellToMoveMap.get(cellID);
    }

    private void gameOver(char marker) {
        TextView textView = (TextView) findViewById(R.id.winner);
        if (rules.isDraw()) {
            textView.setText(getString(R.string.draw));
        } else {
            textView.setText(getString(R.string.gameOver) + marker + getString(R.string.isthewinner));
        }
    }

    public void returnToMenu(View view) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
