package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> right = new AListNoResizing<>();
        right.addLast(4);
        right.addLast(5);
        right.addLast(6);
        BuggyAList<Integer> wrong = new BuggyAList<>();
        wrong.addLast(4);
        wrong.addLast(5);
        wrong.addLast(6);

        assertEquals(right.removeLast(), wrong.removeLast());
        assertEquals(right.removeLast(), wrong.removeLast());
        assertEquals(right.removeLast(), wrong.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> R = new BuggyAList<>();

        int N = 100000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                R.addLast(randVal);
            }
            else if (operationNumber == 1) {
                // size
                assertEquals(L.size(), R.size());
            }
            else if (operationNumber == 2) {
                // getLast
                if (L.size() == 0) continue;
                assertEquals(L.getLast(), R.getLast());
            }
            else if (operationNumber == 3) {
                // getLast
                if (L.size() == 0) continue;
                int LRemove= L.removeLast();
                int RRemove= R.removeLast();
            }
        }
    }
}
