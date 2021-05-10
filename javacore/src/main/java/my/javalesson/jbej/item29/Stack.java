package my.javalesson.jbej.item29;

import com.sun.tools.jconsole.JConsoleContext;

import java.util.*;
import java.util.stream.Collectors;

/**
 * stack empty ||
 * push 1 -> |1|
 * push 2 -> |1 2|
 * pop -> |1|
 * pop -> ||
 * pop -> empty stack exception
 * @param <E>
 */
public class Stack<E>{
    private final List<E> container;

    public Stack() {
        container = new ArrayList<>();
    }

    public E pop(){
        if(container.size() == 0){
            throw new EmptyStackException();
        }
        E value = container.get(container.size() - 1);
        container.remove(container.size() - 1);
        return value;
    }

    public void push(E value){
        container.add(value);
    }

    public String toString(){
        final String collect = container.stream().map(Object::toString).collect(Collectors.joining(", "));
        return "[ " + collect + " ]";
    }

    public boolean pushAll(Collection<? extends E> src){
        return container.addAll(src);
    }

    public void popAll(Collection<? super E> dst){
        ListIterator<E> iter = container.listIterator(container.size());
        while(iter.hasPrevious()){
            dst.add(iter.previous());
        }
        container.clear();
    }
}
