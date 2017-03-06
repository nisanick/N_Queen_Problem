import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Robert Hudec on 29/01/2017.
 */
public class ProblemTest {
    Problem problem;

    @Test
    public void osem(){
        problem = new Problem(8);
        problem.solve();
        assertEquals(92,problem.getSolutions());


    }

    @Test
    public void dva(){
        problem = new Problem(2);
        problem.solve();
        assertEquals(0,problem.getSolutions());
    }

    @Test
    public void styri(){
        problem = new Problem(4);
        problem.solve();
        assertEquals(2,problem.getSolutions());
    }
}