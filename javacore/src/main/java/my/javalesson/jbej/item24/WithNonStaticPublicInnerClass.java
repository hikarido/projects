package my.javalesson.jbej.item24;

public class WithNonStaticPublicInnerClass {
    private int value;

    public WithNonStaticPublicInnerClass(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WithNonStaticPublicInnerClass{" +
                "value=" + value +
                '}';
    }

    public class Inner{
        private int innerValue;

        public Inner() {
            this.innerValue = value + 1;
        }

        @Override
        public String toString() {
            return "Inner{" +
                    "innerValue=" + innerValue +
                    '}';
        }

        public WithNonStaticPublicInnerClass getOuter(){
            return WithNonStaticPublicInnerClass.this;
        }
    }
}
