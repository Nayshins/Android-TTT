package jakenations.me.tictactoe;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;


public class VsHumanTest extends ActivityInstrumentationTestCase2 {
        private VsHuman vsHuman;

        public VsHumanTest() {
            super(VsHuman3x3.class);
        }

        @Override
        protected void setUp() throws Exception {
            super.setUp();

            setActivityInitialTouchMode(true);
            vsHuman = (VsHuman) getActivity();

        }

        @MediumTest
        public void testCell() {
            final View view = vsHuman.getWindow().getDecorView();

            Button cell = (Button) view.findViewById(R.id.c1);
            TouchUtils.clickView(this, cell);
            assertEquals("X", cell.getText());
        }

        @MediumTest
        public void testCellLocks() {
            final View view = vsHuman.getWindow().getDecorView();

            Button cell = (Button) view.findViewById(R.id.c1);
            TouchUtils.clickView(this, cell);;
            assertFalse(cell.isClickable());
        }

        @MediumTest
        public void testTurnIncrementedOnClick() {
            final View view = vsHuman.getWindow().getDecorView();
            Button cell = (Button) view.findViewById(R.id.c1);
            TouchUtils.clickView(this, cell);
            assertEquals(1, vsHuman.turnCount);
        }

        @MediumTest
        public void testGetCurrentPlayer() {
            assertEquals('X', vsHuman.getCurrentPlayer());
            final View view = vsHuman.getWindow().getDecorView();
            Button cell = (Button) view.findViewById(R.id.c1);
            TouchUtils.clickView(this, cell);
            assertEquals('O', vsHuman.getCurrentPlayer());
        }

        @MediumTest
        public  void testGetCellNumber(){
            assertEquals(0,vsHuman.convertCellToInt("c0"));
        }

        @UiThreadTest
        public void testSetButtonStatus() {
            vsHuman.setButtonStatus(false);
            final View view = vsHuman.getWindow().getDecorView();
            Button cell = (Button) view.findViewById(R.id.c1);
            assertFalse(cell.isEnabled());
        }

        @MediumTest
        public void testGameOver() {
            final View view = vsHuman.getWindow().getDecorView();
            for (int i = 0; i < 9 ; i++) {
                Button cell = (Button) view.findViewWithTag("c" + i);
                TouchUtils.clickView(this, cell);
            }
            assertTrue(vsHuman.rules.isGameOver());
        }
}
