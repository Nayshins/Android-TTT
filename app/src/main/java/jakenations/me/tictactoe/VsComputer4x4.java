package jakenations.me.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import me.jakenations.Board;
import me.jakenations.Board4x4;
import me.jakenations.ComputerPlayer;
import me.jakenations.GameRules4x4;
import me.jakenations.Player;
import me.jakenations.Rules;


public class VsComputer4x4 extends Activity {
    private static final HashMap<String, Integer> cellToMoveMap;
    static {
        cellToMoveMap = new HashMap<String, Integer>();
        cellToMoveMap.put("c0", 0);
        cellToMoveMap.put("c1", 1);
        cellToMoveMap.put("c2", 2);
        cellToMoveMap.put("c3", 3);
        cellToMoveMap.put("c4", 4);
        cellToMoveMap.put("c5", 5);
        cellToMoveMap.put("c6", 6);
        cellToMoveMap.put("c7", 7);
        cellToMoveMap.put("c8", 8);
        cellToMoveMap.put("c9", 9);
        cellToMoveMap.put("c10", 10);
        cellToMoveMap.put("c11", 11);
        cellToMoveMap.put("c12", 12);
        cellToMoveMap.put("c13", 13);
        cellToMoveMap.put("c14", 14);
        cellToMoveMap.put("c15", 15);
    }

    private Board board = new Board4x4();
    private Rules rules = new GameRules4x4(board);
    private Player player2 = new ComputerPlayer('O', board, rules);
    private ArrayList<View> touchables;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_vs_computer4x4);
        this.touchables = findViewById(R.id.tableLayout).getTouchables();
    }

    public void runGame(View view) throws Exception {
        int move = setButtonText((Button) view);
        setHumanMove(move);
        setButtonStatus(false);
        if (!rules.isGameOver()) {
            getComputerMove();
        } else {
            gameOver("X");
            setButtonStatus(false);
        }

    }

    private void setButtonStatus(boolean state) {
        for (View touchable: touchables) {
            if( touchable instanceof Button) {
                touchable.setEnabled(state);
            }
        }
    }

    private int setButtonText(Button view) {
        view.setText(R.string.marker_X);
        view.setClickable(false);
        return convertCellToInt((String) view.getTag());
    }

    private void gameOver(String marker) {
        TextView textView = (TextView)findViewById(R.id.winner);
        if (rules.isDraw()) {
            textView.setText(getString(R.string.draw));
        } else {
            textView.setText("Game over! " + marker + " is the winner");
        }

    }

    public void mapCells(char[] grid) {
        for (int i = 0; i < grid.length; i ++) {
            if (grid[i] == 'O') {
                String buttonID = "c" + i;
                Button button = (Button) findViewById(R.id.tableLayout).findViewWithTag(buttonID);
                button.setText(R.string.marker_O);
                button.setClickable(false);
            }
        }
    }



    public int convertCellToInt(String cellID) {
        return cellToMoveMap.get(cellID);
    }

    public void setHumanMove(int move) throws Exception {
        board.setCell('X', move);
    }

    public void getComputerMove() throws Exception{
        new ComputerMoveTask().execute();
    }

    public void returnToMenu(View view) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    private class ComputerMoveTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                player2.selectMove();
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
        protected void onPostExecute(Integer result) {
            mapCells(board.getGrid());
            if (rules.isGameOver()) {
                setButtonStatus(false);
                gameOver("O");
            } else {
                setButtonStatus(true);
            }
        }



    }
}
