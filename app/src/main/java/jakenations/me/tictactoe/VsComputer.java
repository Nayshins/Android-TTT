package jakenations.me.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import me.jakenations.Board;
import me.jakenations.Player;
import me.jakenations.Rules;


public abstract class VsComputer extends Activity {
    public Board board;
    public Rules rules;
    public Player computerPlayer;
    public ArrayList<View> touchables;
    private static final HashMap<String, Integer> cellToMoveMap;

    static {
        cellToMoveMap = new HashMap<String, Integer>();
        for (int i = 0; i < 16; i++) {
            cellToMoveMap.put("c" + i, i);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        view.setTextColor(Color.parseColor("#FF4444"));
        view.setText(R.string.marker_X);
        view.setClickable(false);
        return convertCellToInt((String) view.getTag());
    }

    private void gameOver(String marker) {
        TextView textView = (TextView)findViewById(R.id.winner);
        if (rules.isDraw()) {
            textView.setText(getString(R.string.draw));
        } else {
            textView.setText(getString(R.string.gameOver) + marker + getString(R.string.isthewinner));
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
                computerPlayer.selectMove();
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
