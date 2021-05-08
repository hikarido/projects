package jbej.item26;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Item26 {

    @Test
    public void testUseRowListAsMethodParameter() {
        List<String> strings = new ArrayList<>();
        unsaveAdd(strings, BigInteger.valueOf(12L));
        System.out.println(strings);
    }

    @Test
    public void testUseParametrizedArgument() {
        List<String> strings = new ArrayList<>();
        // would not compile! And it is good.
//        saveAdd(strings, BigInteger.valueOf(12L));
//        System.out.println(strings);
    }

    @Test
    public void testUseWildCardWhenIDontKnowAboutTypeOfParameter() {
        Set<Integer> ints = Set.of(1);
        saveUnmodifiedOperationOnSet(ints);
    }

    private void unsaveAdd(List l, Object o){
        l.add(o);
    }

    private void saveAdd(List<Object> l, Object o){
        l.add(o);
    }

    private void saveUnmodifiedOperationOnSet(Set<?> set){
        Iterator<?> i = set.iterator();
        int count = 0;
        while (i.hasNext()){
            count++;
        }
        set.add(null);
    }
}
