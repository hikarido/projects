package ru.ananev.study.projects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * first dummy implementation
 */
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static final PathMatcher javaFilesMatcher = FileSystems.getDefault().getPathMatcher("glob:**.java");

    public static void main(String[] args) throws IOException {
        List<String> arguments = Arrays.asList(args);
        if (arguments.isEmpty()) {
            log.error("Please, specify path where search should begin");
            return;
        }
        final String path = arguments.get(0);
        log.info("Parsings starts from: {}", path);

        List<Path> paths = Files.walk(Paths.get(path)).collect(Collectors.toList());

        List<String> allImports = new ArrayList<>();
        for (Path inspectedPath : paths) {
            List<String> importsInFile = getImports(inspectedPath);
            allImports.addAll(importsInFile);
        }

        allImports = allImports.stream()
                .map(Application::leaveOnlyLabel)
                .collect(Collectors.toList());

        Map<String, Integer> importsOverThemUsageCount = allImports.stream().
                collect(Collectors.groupingBy(
                        String::toString,
                        Collectors.summingInt((unused) -> {
                            return 1;
                        })));



//        log.info("Statistics:");
//        for (Map.Entry<String, Integer> entry : importsOverThemUsageCount.entrySet()) {
//            log.info("{}: {}", entry.getKey().replace(";", ""), entry.getValue());
//        }

        log.info("[by imports]");
        printSortedByImport(importsOverThemUsageCount);
        log.info("[by usage]");
        printSortedByFrequency(importsOverThemUsageCount);

    }

    public static List<String> getImports(Path path) {
        if (!javaFilesMatcher.matches(path)) {
            return Collections.EMPTY_LIST;
        }

        try {
            List<String> lines = Files.readAllLines(path);
            return lines.stream()
                    .filter((line) -> line.startsWith("import"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.warn("Can't process file: {}", path);
        }

        return Collections.EMPTY_LIST;
    }

    public static void printSortedByImport(Map<String, Integer> data){
            Map<String, Integer> map = new TreeMap<>(data);
            for(Map.Entry<String, Integer> entry: map.entrySet()){
                log.info("{}: {}", entry.getKey(), entry.getValue());
            }
    }

    public static void printSortedByFrequency(Map<String, Integer> data){
        List<ImportUsage> usages = new ArrayList<>(data.size());
        for(Map.Entry<String, Integer> entry: data.entrySet()){
            usages.add(new ImportUsage(entry.getKey(), entry.getValue()));
        }

        usages.sort(Comparator.comparing(ImportUsage::getCount));
        for(ImportUsage usage: usages){
            log.info(usage.toString());
        }
    }

    /**
     * <import static org.testng.Assert.assertEquals;> -> <org.testng.Assert.assertEquals>
     * @param importName
     * @return
     */
    public static String leaveOnlyLabel(String importName){
        return importName
                .replace(";", "")
                .replace("import", "")
                .replace("static", "")
                .replace(" ", "");
    }
}

class ImportUsage{
    private final String name;
    private final Integer count;

    public ImportUsage(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public String toString() {
        return name + ": " + count;
    }
}
