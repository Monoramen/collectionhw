import com.a2.GenHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.NoSuchElementException;


import static org.junit.jupiter.api.Assertions.*;

public class GeneHashMapTest {

    private static final int MAXIMUM_CAPACITY =  1 << 24;
    GenHashMap genHashMap;

    @BeforeEach
    void setUp() {
        genHashMap = new GenHashMap();
    }

    @DisplayName("Test different type of table")
    @Test
    void differentTypeOfTableTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(0, "Value");
        GenHashMap<String, String> list1 = new GenHashMap<>(0);
        list1.put("0", "Value");
        HashMap<String, String> list2 = new HashMap<>();
        list2.put("0", "Value");
        assertEquals(false, list.equals(list2));
        assertEquals(true, list.get(0).equals(list1.get("0")));
        assertEquals(false, list.equals(list1));
    }

    @Test
    void nullValueInPutMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>();
        list.put(0, null);
        assertEquals(null, list.get(0));
        assertEquals("HashMap = {0=null}", list.entrySet());
    }

    @DisplayName("Test null table entryset Method")
    @Test
    void nullTableEntrysetMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>();
        assertEquals("HashMap = {}", list.entrySet());
    }

    @DisplayName("Test min capacity table clear Method")
    @Test
    void minCapacityTableClearMethodTest() {
        int maxCapacity = -10;

        try {
            GenHashMap<Integer, String> map = new GenHashMap<>(maxCapacity);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(null, e.getMessage());
        }
    }

    @DisplayName("Test max capacity table creation")
    @Test
    void testMaxCapacityTableCreation() {
        try {
            GenHashMap<Integer, String> map = new GenHashMap<>(MAXIMUM_CAPACITY);
            assertNotNull(map);
        } catch (OutOfMemoryError e) {
            fail("Expected to create map with maximum capacity, but encountered OutOfMemoryError");
        }
    }

    @DisplayName("Test empty table keyset Method")
    @Test
    void emptyTableKeysetMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>();
        assertEquals("Keys = {}", list.keyset());
    }

    @DisplayName("Test empty table values Method")
    @Test
    void emptyTableValuesMethodTest() {
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



    @DisplayName("Test NoSuchElementException for non-existing key")
    @Test
    void getMethodWithNonExistingKeyTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(14, "Value");

        assertThrows(NoSuchElementException.class, () -> {
            list.get(17);
        });
    }

    @DisplayName("Test NoSuchElementException for non-existing key with ToString Method")
    @Test
    void getToStringMethodWithNonExistingKeyTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);

        assertThrows(NoSuchElementException.class, () -> {
            list.get(17).toString();
        });
    }



    @DisplayName("Test NullPointerException for null key")
    @Test
    void getMethodWithNullKeyTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals(null, list.get(null));
        assertEquals("Key cannot be null", outContent.toString().trim());

    }

    @DisplayName("Test put Method with null key")
    @Test
    void putMethodWithNullKeyTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals(null, list.put(null, "Value"));
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
        assertThrows(NoSuchElementException.class, () -> {
            list.get(1);
        });
    }


    @DisplayName("Test delete Method")
    @Test
    void deleteMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(0, "Zero");
        list.put(1, "One");
        list.delete(1);

        assertThrows(NoSuchElementException.class, () -> {
            list.get(1);
        });
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
        list.put(0, "Zero");
        list.put(1, "One");
        assertEquals("Values = {Zero, One}", list.values());
        list.put(0, "Two");

        assertEquals("Values = {Two, One}", list.values());}


    @DisplayName("Test values Method")
    @Test
    void keysetMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(0, "Zero");
        list.put(1, "One");
        assertEquals("Keys = {0, 1}", list.keyset());
    }

    @DisplayName("Test clear Method")
    @Test
    void clearMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(0, "Zero");
        list.put(1, "One");
        list.clear();
        assertThrows(NoSuchElementException.class, () -> {
            list.get(1);
        });
        assertThrows(NoSuchElementException.class, () -> {
            list.get(0);
        });
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

    @Test
    void hashCodeTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(100);
        list.put(0, "Zero");
        list.put(1, "One");
        GenHashMap<Integer, String> list1 = new GenHashMap<>(100);
        list1.put(0, "equal");
        list1.put(1, "One");
        assertEquals(96757556, list1.get(0).hashCode());
        assertEquals(false, list1.get(1).hashCode() != list.get(1).hashCode());
        assertEquals(2781896, list.get(0).hashCode());
        assertEquals(false, list.get(0).equals(list1.get(0)));
    }

    @Test
    void hashCodeEqualTest() {
        GenHashMap<Integer, String> list2 = new GenHashMap<>();
        GenHashMap<Integer, String> list = new GenHashMap<>();
        list2.put(0, "Zero");
        list.put(0, "Zero");
        var h1 = list.get(0).hashCode();
        var h2 = list2.get(0).hashCode();
        assertEquals(true , h1 == h2);
        assertEquals(true , h2 == h1);
        list.put(0, "Value");
        var h3 = list.get(0).hashCode();
        assertEquals(false , h1 == h3);
        assertEquals(false , h3 == h1);
        assertThrows(NoSuchElementException.class, () -> list.get(10).hashCode());
        assertThrows(NoSuchElementException.class, () -> list2.get(10).hashCode());
    }


    @DisplayName("Test equals Method")
    @Test
    void equalsfalseMethodTest() {
        GenHashMap<Integer, String> list1 = new GenHashMap<>(100);
        list1.put(0, "Value");
        list1.put(10, "ValueDif");
        GenHashMap<Integer, String> list2 = new GenHashMap<>(100);
        list2.put(0, "Value");
        list2.put(10, "ValueDiff");
        assertEquals(false, list1.get(0).equals(list2.get(10)));
        assertEquals(false, list2.get(10).equals(list1.get(0)));
        assertEquals(true, list2.get(0).equals(list1.get(0)));
        assertEquals(false, list2.get(10).equals(list1.get(10)));
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
    void entrysetMethodTest() {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);
        list.put(0, "Zero");
        list.put(1, "One");
        assertEquals("HashMap = {0=Zero, 1=One}", list.entrySet());
    }

}
