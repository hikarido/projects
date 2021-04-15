package my.javalesson;

import java.sql.Struct;

public class Dog extends Animal{
    private static TalkativeString staticInitesManualy = new TalkativeString("static in field");
    private static TalkativeString staticInitedInInitBlock;
    private static TalkativeString staticInitedInCtor;
    private final TalkativeString nonStaticInitedManualy = new TalkativeString("non static in field");
    private TalkativeString nonStaticInitedInInitBlock;
    private TalkativeString nonStaticInitedInCtor;

    static {
        System.out.println("static init block of Dog");
        System.out.println(staticInitesManualy);
        System.out.println(staticInitedInInitBlock);
        System.out.println(staticInitedInCtor);
        staticInitedInInitBlock = new TalkativeString("static in static init block");
    }

    {
        System.out.println("non static init block of Dog");
        System.out.println(nonStaticInitedManualy);
        System.out.println(nonStaticInitedInInitBlock);
        System.out.println(nonStaticInitedInCtor);
        nonStaticInitedInInitBlock = new TalkativeString("non static in non static ini block");
    }

    public Dog(){
        System.out.println("ctor of Dog");
        nonStaticInitedInCtor = new TalkativeString("non static in ctor");
        staticInitedInCtor = new TalkativeString("static in ctor");
    }

    public static void staticMethod() {
        System.out.println("Static method of Dog");
    }

    protected boolean protectedMethod(){
        return true;
    }

}
