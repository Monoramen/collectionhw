import com.a2.GenHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

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

    @DisplayName("Test max capacity table clear Method")
    @Test
    void maxCapacityTableClearMethodTest() {
        int maxCapacity = 1 << 31;
        try {
            GenHashMap<Integer, String> list = new GenHashMap<>(maxCapacity);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(null, e.getMessage());
        }
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

        list.clear();
        expected = new StringBuilder();
        expected.append("HashMap = {}");
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


    @DisplayName("Test size Method")
    @Test
    void sizeMethodTest() {
        GenHashMap<String, String> list = new GenHashMap<>(100);
        list.put("Key", "Value");
        list.put("Key1", "Value1");
        assertEquals(2, list.size());
    }

    @DisplayName("Test size with null table Method")
    @Test
    void sizeNullMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>();
        assertEquals(0, list.size());

    }

    @DisplayName("Test equals Method")
    @Test
    void equalsMethodTest() {
        GenHashMap<Integer, String> list1 = new GenHashMap<>(100);
        list1.put(0, "Value");
        GenHashMap<Integer, String> list2 = new GenHashMap<>(100);
        list2.put(0, "Value");
        assertEquals(true, list1.equals(list1));
        assertEquals(true, list1.get(0).equals(list2.get(0)));

    }


    @DisplayName("Test equals Method")
    @Test
    void equalsfalseMethodTest() {
        GenHashMap<Integer, String> list1 = new GenHashMap<>(100);
        list1.put(0, "Value");
        GenHashMap<Integer, String> list2 = new GenHashMap<>(100);
        list2.put(10, "ValueDiff");
        assertEquals(false, list1.get(0).equals(list2.get(10)));
        assertEquals(false, list1.equals(list2));

    }

    @DisplayName("Test toString Method")
    @Test
    void toStringMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(1);
        list.put(0, "Value");
        assertEquals("Value", list.get(0).toString());
    }

    @DisplayName("Test entryset Method")
    @Test
    void getKeyMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(1);


    }

}
