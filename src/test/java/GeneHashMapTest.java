import com.a2.GenHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeneHashMapTest {

    GenHashMap genHashMap;

    @BeforeEach
    void setUp() {
        genHashMap = new GenHashMap();
    }

    @DisplayName("Test null table entryset Method")
    @Test
    void nullTableEntrysetMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>();
        assertEquals("HashMap = {}", list.entrySet());
    }

    @DisplayName("Test null table keyset Method")
    @Test
    void nullTableKeysetMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>();
        assertEquals("Keys = {}", list.keyset());
    }

    @DisplayName("Test null table values Method")
    @Test
    void nullTableValuesMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>();
        assertEquals("Values = {}", list.values());
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

    @DisplayName("Test put Method with null key")
    @Test
    void putMethodWithNullKeyTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        list.put(null, "Value");
        assertEquals("Key cannot be null", outContent.toString().trim());
    }

    @DisplayName("Test put Method with existing key")
    @Test
    void putMethodWithExistingKeyTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(0, "Zero");
        list.put(0, "Value");
        assertEquals("Value", list.get(0));
    }

    @DisplayName("Test get Method")
    @Test
    void getMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(0, "Zero");
        assertEquals("Zero", list.get(0));
        assertEquals(null, list.get(1));
    }


    @DisplayName("Test delete Method")
    @Test
    void deleteMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(0, "Zero");
        list.put(1, "One");
        list.delete(1);
        assertEquals(null, list.get(1));
        assertEquals("Zero", list.get(0));
    }

    @DisplayName("Test delete Method with null key")
    @Test
    void deleteMethodWithNullKeyTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        list.delete(null);
        assertEquals("Key cannot be null", outContent.toString().trim());
    }

    @DisplayName("Test delete Method with non-existent key")
    @Test
    void deleteMethodWithNonExistentKeyTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        list.delete(100);
        assertEquals("Key does not exist: 100", outContent.toString().trim());
    }

    @DisplayName("Test values Method")
    @Test
    void valuesMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        StringBuilder expected = new StringBuilder();
        expected.append("Values = {");
        for (int i = 100; i == 0; i--) {
            list.put(i, "Value" + i);
            expected.append(i).append("=Value").append(i);
            if (i < 99) {
                expected.append(", ");
            }
        }
        expected.append("}");
        assertEquals(expected.toString(), list.values());
    }

    @DisplayName("Test values Method")
    @Test
    void keysetMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        StringBuilder expected = new StringBuilder();
        expected.append("Keys = {");
        for (int i = 100; i == 0; i--) {
            list.put(i, "Value" + i);
            expected.append(i).append("=Value").append(i);
            if (i < 99) {
                expected.append(", ");
            }
        }
        expected.append("}");
        assertEquals(expected.toString(), list.keyset());
    }

    @DisplayName("Test clear Method")
    @Test
    void clearMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(0, "Zero");
        list.put(1, "One");
        list.clear();
        assertEquals(null, list.get(0));
        assertEquals(null, list.get(1));
    }

}
