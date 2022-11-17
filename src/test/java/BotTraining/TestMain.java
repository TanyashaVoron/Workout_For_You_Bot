package BotTraining;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static BotTraining.Main.InputFilter;

public class TestMain {

    @Test
    void InputFilterTestInputFromMethodHello() {
        String[] strArray = new String[]{ "go","info" };
        InputStream sysInBackup = System.in;

        ByteArrayInputStream in = new ByteArrayInputStream("go".getBytes());
        System.setIn(in);
        assertEquals("go", InputFilter(strArray));

        in = new ByteArrayInputStream("info".getBytes());
        System.setIn(in);
        assertEquals("info", InputFilter(strArray));
        System.setIn(sysInBackup);
    }

    @Test
    void InputFilterTestInputFromMethodInfo() {
        String[] strArray = new String[]{ "go","exit" };
        InputStream sysInBackup = System.in;

        ByteArrayInputStream in = new ByteArrayInputStream("go".getBytes());
        System.setIn(in);
        assertEquals("go", InputFilter(strArray));

        in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);
        assertEquals("exit", InputFilter(strArray));
        System.setIn(sysInBackup);
    }

    @Test
    void InputFilterTestInputFromMethodGo() {
        String[] strArray = new String[]{ "up","down" };
        InputStream sysInBackup = System.in;

        ByteArrayInputStream in = new ByteArrayInputStream("up".getBytes());
        System.setIn(in);
        assertEquals("up", InputFilter(strArray));

        in = new ByteArrayInputStream("down".getBytes());
        System.setIn(in);
        assertEquals("down", InputFilter(strArray));
        System.setIn(sysInBackup);
    }

    @Test
    void InputFilterTestInputFromMethodUp() {
        String[] strArray = new String[]{ "done" };
        InputStream sysInBackup = System.in;

        ByteArrayInputStream in = new ByteArrayInputStream("done".getBytes());
        System.setIn(in);
        assertEquals("done", InputFilter(strArray));
        System.setIn(sysInBackup);
    }

    @Test
    void InputFilterTestInputFromMethodDown() {
        String[] strArray = new String[]{ "done" };
        InputStream sysInBackup = System.in;

        ByteArrayInputStream in = new ByteArrayInputStream("done".getBytes());
        System.setIn(in);
        assertEquals("done", InputFilter(strArray));
        System.setIn(sysInBackup);
    }

    @Test
    void InputFilterTestInputFromMethodDone() {
        String[] strArray = new String[]{ "go","exit","info" };
        InputStream sysInBackup = System.in;

        ByteArrayInputStream in = new ByteArrayInputStream("go".getBytes());
        System.setIn(in);
        assertEquals("go", InputFilter(strArray));

        in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);
        assertEquals("exit", InputFilter(strArray));

        in = new ByteArrayInputStream("info".getBytes());
        System.setIn(in);
        assertEquals("info", InputFilter(strArray));
        System.setIn(sysInBackup);
    }
}


