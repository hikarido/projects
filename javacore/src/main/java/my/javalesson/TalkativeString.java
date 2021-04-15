package my.javalesson;

public class TalkativeString {
    private final String value;
    public TalkativeString(String value){
        this.value = value;
        System.out.println("I say: " + this);
    }

    @Override
    public String toString() {
        return value;
    }
}
