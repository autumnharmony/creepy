/**
* Created with IntelliJ IDEA.
* User: anton
* Date: 11/5/12
* Time: 4:30 AM
* To change this template use File | Settings | File Templates.
*/
import org.junit.Test;

import static org.junit.Assert.*;

public class CreepyMatcherTest {

    private CreepyMatcher creepyMatcher;

    @org.junit.Before
    public void setUp() throws Exception {
        creepyMatcher = new CreepyMatcher();
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }
    @Test
    public void test(){
        String string1 ="avazvvb";
        String string2 ="zzzuddm";
        assertTrue(creepyMatcher.match(string1, string2));
    }
}
