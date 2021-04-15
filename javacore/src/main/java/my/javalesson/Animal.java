package my.javalesson;

public class Animal {
    private static TalkativeString staticInitesManualy = new TalkativeString("static in field");
    private static TalkativeString staticInitedInInitBlock;
    private static TalkativeString staticInitedInCtor;
    private final TalkativeString nonStaticInitedManualy = new TalkativeString("non static in field");
    private TalkativeString nonStaticInitedInInitBlock;
    private TalkativeString nonStaticInitedInCtor;

    static {
        System.out.println("static init block");
        System.out.println(staticInitesManualy);
        System.out.println(staticInitedInInitBlock);
        System.out.println(staticInitedInCtor);
        staticInitedInInitBlock = new TalkativeString("static in static init block");
    }

    {
        System.out.println("non static init block");
        System.out.println(nonStaticInitedManualy);
        System.out.println(nonStaticInitedInInitBlock);
        System.out.println(nonStaticInitedInCtor);
        nonStaticInitedInInitBlock = new TalkativeString("non static in non static ini block");
    }

    public Animal(){
        System.out.println("ctor of animal");
        nonStaticInitedInCtor = new TalkativeString("non static in ctor");
        staticInitedInCtor = new TalkativeString("static in ctor");
    }
}
