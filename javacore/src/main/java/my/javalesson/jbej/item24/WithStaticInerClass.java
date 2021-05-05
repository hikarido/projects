package my.javalesson.jbej.item24;

public class WithStaticInerClass {
    private int value;
    public WithStaticInerClass(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WithStaticInerClass{" +
                "value=" + value +
                '}';
    }

    public static class Inner{
        private int innerValue;

        public Inner(int value) {
            this.innerValue = value;
        }

        @Override
        public String toString() {
            return "Inner{" +
                    "value=" + innerValue +
                    '}';
        }
    }
}
