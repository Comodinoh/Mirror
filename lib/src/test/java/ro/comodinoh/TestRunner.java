package ro.comodinoh;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import ro.comodinoh.TestClass;

import ro.comodinoh.mirror.Mirror;
import ro.comodinoh.mirror.UnsafeFieldAccessor;

public class TestRunner {


    public static void main(String[] args) throws IOException {
        PrintStream io = System.out;

        File dir = new File(System.getProperty("java.io.tmpdir"));
        Mirror.init(dir);

        UnsafeFieldAccessor<String> testField = Mirror.getUnsafeField(TestClass.class, "test");

        TestClass test = new TestClass("Meow!");

        assert test.getTest().equals("Meow!") : "Base case failed";

        testField.set(test, "Test!");

        assert test.getTest().equals("Test!") : "Field accessor case failed";

        io.println("All tests successfully passed!");
    }
}
