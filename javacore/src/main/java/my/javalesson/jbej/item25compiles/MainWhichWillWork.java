package my.javalesson.jbej.item25compiles;

/**
 * compile it as
 * ananevkv@ananev-nb MINGW64 ~/IdeaProjects/projects/javacore/src/main/java (master)
 * $ "C:\Users\ananevkv\.jdks\adopt-openjdk-11.0.11\bin\javac" my/javalesson/jbej/item25/MainWhichWillWork.java my/javalesson/jbej/item25/Utensil.java
 *
 * run it like this
 *
 * ananevkv@ananev-nb MINGW64 ~/IdeaProjects/projects/javacore/src/main/java (master)
 * $ "C:\Users\ananevkv\.jdks\adopt-openjdk-11.0.11\bin\java" my.javalesson.jbej.item25.MainWhichWillWork
 */
public class MainWhichWillWork {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Desert.NAME);
    }
}
