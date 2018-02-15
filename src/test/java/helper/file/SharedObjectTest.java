package helper.file;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class SharedObjectTest {

    private String testFileUrl = "tmp/test/testFile.ser";

    @AfterMethod
    public void tearDown() {
        new File(testFileUrl).delete();
    }

    @Test
    public void testLoad() {
        Assertions
            .assertThat(SharedObject.load(testFileUrl, Integer.class))
            .isNull();
    }

    @Test
    public void testSaveAndLoad() {
        SharedObject.save(testFileUrl, 1);
        Assertions
            .assertThat(SharedObject.load(testFileUrl, Integer.class))
            .isEqualTo(1);
    }

    @Test
    public void testLoadList() {
        Assertions
            .assertThat(SharedObject.loadList(testFileUrl))
            .isEqualTo(new ArrayList<>());
    }

    @Test
    public void testSaveAndLoadList() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        SharedObject.save(testFileUrl, strings);
        Assertions
            .assertThat(SharedObject.loadList(testFileUrl))
            .isEqualTo(strings);
    }

    @Test
    public void testLoadListWithDefaultValue() {
        Assertions
            .assertThat(SharedObject.loadList(testFileUrl, new ArrayList<>()))
            .isEqualTo(new ArrayList<>());
    }

    @Test
    public void testSaveAndLoadListWithDefaultValue() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        SharedObject.save(testFileUrl, strings);
        Assertions
            .assertThat(SharedObject.loadList(testFileUrl, new ArrayList<>()))
            .isEqualTo(strings);
    }

    @Test
    public void testLoadMap() {
        Assertions
            .assertThat(SharedObject.loadMap(testFileUrl))
            .isEqualTo(new HashMap<String, String>());
    }

    @Test
    public void testSaveAndLoadMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("test", "test");
        SharedObject.save(testFileUrl, map);
        Assertions
            .assertThat(SharedObject.loadMap(testFileUrl))
            .isEqualTo(map);
    }

    @Test
    public void testSaveAndLoadMapWithDefaultValue() {
        HashMap<String, String> map = new HashMap<>();
        map.put("test", "test");
        SharedObject.save(testFileUrl, map);
        Assertions
            .assertThat(SharedObject.loadMap(testFileUrl, new HashMap<String, String>()))
            .isEqualTo(map);
    }

    @Test
    public void testLoadMapWithDefaultValue() {
        Assertions
            .assertThat(SharedObject.loadMap(testFileUrl, new HashMap<String, String>()))
            .isEqualTo(new HashMap<String, String>());
    }

    @Test
    public void testLoadMapWithDefaultValueIsNotEqualToLocalMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("test", "test");
        SharedObject.save(testFileUrl, map);
        map.remove("test");
        Assertions
            .assertThat(SharedObject.loadMap(testFileUrl, new HashMap<String, String>()))
            .isNotEqualTo(map);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testRemoveNegativeTest() {
        testRemove("/testFile.ser");
    }

    @Test
    public void testRemoveLocalToProjectFileInRoot() {
        testRemove("./testFile.ser");
    }

    @Test
    public void testRemoveLocalToProjectFileInRoot2() {
        testRemove("testFile.ser");
    }

    @Test
    public void testRemoveLocalToProjectFile() {
        testRemove(testFileUrl);
    }

    @Test
    public void testRemoveLocalToSystemFile() {
        testRemove("/tmp/testFile.ser");
    }

    private void testRemove(String url) {
        SharedObject.save(url, new ArrayList<>());
        Assertions
            .assertThat(SharedObject.load(url, ArrayList.class))
            .isNotNull();

        SharedObject.remove(url);

        Assertions
            .assertThat(SharedObject.load(url, ArrayList.class))
            .isNull();
    }
}