package kr.hs.entrydsm.main.configuration;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.File;
import java.net.URL;
import java.util.*;

public class MunchkinApplicationBuilder {

    private static final String MODULE_CONFIGURATION_PATH = "kr.hs.entrydsm.main.configuration.module";

    private SpringApplicationBuilder springBuilder;
    private final Queue<Class<?>> registeredModules = new LinkedList<>();

    public MunchkinApplicationBuilder(Class<?> mainClass) {
        springBuilder = new SpringApplicationBuilder()
                .sources(mainClass)
                .sources(MunchkinConfiguration.class);

    }

    public MunchkinApplicationBuilder properties(String properties) {
        springBuilder = springBuilder.properties(properties);
        return this;
    }

    @SneakyThrows
    public SpringApplication build(String... args) {
        registerModules();
        return springBuilder.build(args);
    }

    public void registerModules() {
        getModuleConfiguration().forEach(this::registerDependency);
        springBuilder = springBuilder.sibling(WebApplicationConfigure.class).web(WebApplicationType.SERVLET);
    }

    private void registerDependency(Class<?> module) {
        if (isAlreadyRegistered(module)) return;
        if (isTopLevelDependency(module)) {
            springBuilder = springBuilder.child(module).web(WebApplicationType.NONE);
        } else {
            registerDependency(getDependencyModule(module));
            springBuilder = springBuilder.sibling(module).web(WebApplicationType.NONE);
        }
        registeredModules.add(module);
    }

    private Class<?> getDependencyModule(Class<?> module) {
        return module.getAnnotation(DependentModule.class).value();
    }

    private boolean isTopLevelDependency(Class<?> clazz) {
        return clazz.getAnnotation(DependentModule.class) == null;
    }

    private boolean isAlreadyRegistered(Class<?> clazz) {
        return registeredModules.contains(clazz);
    }

    @SneakyThrows
    private static List<Class<?>> getModuleConfiguration() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;

        String path = MODULE_CONFIGURATION_PATH.replace('.', '/');
        URL directoryUrl = classLoader.getResource(path);
        assert directoryUrl != null;

        File directory = new File(directoryUrl.toURI());
        return new ArrayList<>(findClasses(directory, MODULE_CONFIGURATION_PATH));
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

}
