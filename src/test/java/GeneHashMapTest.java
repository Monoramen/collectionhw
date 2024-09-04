import com.a2.GenHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneHashMapTest {

    GenHashMap genHashMap;

    @BeforeEach
    void setUp() {
        genHashMap = new GenHashMap();
    }

    @DisplayName("Test put Method")
    @Test
    void putMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        StringBuilder expected = new StringBuilder();
        expected.append("HashMap = {");
        for (int i = 1000; i == 0; i--) {
            list.put(i, "Value" + i);
            expected.append(i).append("=Value").append(i);
            if (i < 999) {
                expected.append(", ");
            }
        }
        expected.append("}");
        assertEquals(expected.toString(), list.entrySet());
    }

    @DisplayName("Test get Method")
    @Test
    void getMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(0, "Zero");
        assertEquals("Zero", list.get(0));
        assertEquals(null, list.get(1));
    }


    @DisplayName("Test SetValue Method")
    @Test
    void setValueMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(0, "Zero");
        list.setValue(0, "New Value");
        assertEquals("New Value", list.get(0));
        list.setValue(10, "Zero");
        assertEquals(null, list.get(10));
        System.out.println(list.entrySet());
    }

}
