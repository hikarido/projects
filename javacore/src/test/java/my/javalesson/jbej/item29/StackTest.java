package my.javalesson.jbej.item29;

import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

@Test
public class StackTest {
    @Test
    public void testStackMustCreatesEmpty() {
        Stack<Integer> integerStack = new Stack<>();
    }

    @Test(expectedExceptions = {EmptyStackException.class})
    public void testPopFromEmptyStackMustThrowException() {
        Stack<String> stringStack = new Stack<>();
        stringStack.pop();
    }

    @Test
    public void testPushAndPopBalance() {
        Stack<String> stringStack = new Stack<>();
        ArrayList<String> expected = new ArrayList<>();
        expected.addAll(List.of("4", "3", "2", "1"));
        stringStack.push("1");
        stringStack.push("2");
        stringStack.push("3");
        stringStack.push("4");
        ArrayList<String> actual = new ArrayList<>();
        for(int i = 0; i < expected.size(); i++){
            actual.add(stringStack.pop());
        }
        assertThat(actual, equalTo(expected));
    }

    private static class Base{

    }

    private static class Child extends Base{

    }

    @Test
    public void testAddSubTypeOfDeclaredStaksGenericType(){
        Stack<Base> numberStack = new Stack<>();
        numberStack.push(new Child());
    }

    @Test
    public void testPopAllTest() {
        Stack<Base> numberStack = new Stack<>();
        numberStack.push(new Child());
        numberStack.push(new Base());
        List<Base> actual = new ArrayList<>();
        numberStack.popAll(actual);
    }
}