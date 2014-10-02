package jakenations.me.tictactoe;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;

/**
 * Created by Nayshins on 10/2/14.
 */
public class VsComputerTest extends ActivityInstrumentationTestCase2 {
    private VsComputer vsComputer;

    public VsComputerTest() {
        super(VsComputer3x3.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);
        vsComputer = (VsComputer) getActivity();

    }

    @MediumTest
    public void testCell() {
        final View view = vsComputer.getWindow().getDecorView();

        Button cell = (Button) view.findViewById(R.id.c0);
        TouchUtils.clickView(this, cell);
        assertEquals("X", cell.getText());
        assertFalse(cell.isClickable());
    }
    @UiThreadTest
    public void testComputer() throws Exception {
        final View view = vsComputer.getWindow().getDecorView();

        Button cell = (Button) view.findViewById(R.id.c0);
        char[] grid = new char[1];
        grid[0] = 'O';
        vsComputer.mapCells(grid);
        assertEquals("O", cell.getText());
    }

    @MediumTest
    public  void testGetCellNumber(){
        assertEquals(0,vsComputer.convertCellToInt("c0"));
    }

    @UiThreadTest
    public void testSetButtonStatus() {
        vsComputer.setButtonStatus(false);
        final View view = vsComputer.getWindow().getDecorView();
        Button cell = (Button) view.findViewById(R.id.c1);
        assertFalse(cell.isEnabled());
    }
}