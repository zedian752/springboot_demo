package test.pack;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * class 扫描器
 *
 * @author zhangyunan
 */
public class ClassScanner {

    private final String basePackage;
    private final boolean recursive;
    private final Predicate<String> packagePredicate;
    private final Predicate<Class> classPredicate;

    /**
     * Instantiates a new Class scanner.
     *
     * @param basePackage      the base package
     * @param recursive        是否递归扫描
     * @param packagePredicate the package predicate
     * @param classPredicate   the class predicate
     */
    public ClassScanner(String basePackage, boolean recursive, Predicate<String> packagePredicate,
                        Predicate<Class> classPredicate) {
        this.basePackage = basePackage;
        this.recursive = recursive;
        this.packagePredicate = packagePredicate;
        this.classPredicate = classPredicate;
    }

    Set<Class<?>> startTetsdt(String packageName) throws IOException, ClassNotFoundException {
          Set<Class<?>> set = new HashSet<>();
          Enumeration<URL>  urlEnumeration = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".","/"));
          while(urlEnumeration.hasMoreElements()) {
              URL url = urlEnumeration.nextElement();
              if ("file".equals(url.getProtocol())) {
                  String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                  // 扫描文件夹中的包和类
                  scan(set, packageName, filePath);
              }
          }

        return set;
    }
    void scan(Set<Class<?>> set,  String packagePath, String filePath) throws ClassNotFoundException {
            File dir = new File(filePath);
            if (dir == null || !dir.isDirectory()) return;
            File[] files = dir.listFiles();
            for(File file: files) {
                if (file.isDirectory()) {
                    scan(set, packagePath + "." + file.getName(), file.getAbsolutePath());
                } else {
                    Class clz = Thread.currentThread().getContextClassLoader().loadClass(packagePath + "." + file.getName().substring(0, file.getName().length() - 6));
                    set.add(clz);
                }
            }

    }

    /**
     * Do scan all classes set.
     *
     * @return the set
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Set<Class<?>> doScanAllClasses() throws IOException, ClassNotFoundException {

        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();

        String packageName = basePackage;

        // 如果最后一个字符是“.”，则去掉
        if (packageName.endsWith(".")) {
            packageName = packageName.substring(0, packageName.lastIndexOf('.'));
        }

        // 将包名中的“.”换成系统文件夹的“/”
        String basePackageFilePath = packageName.replace('.', '/');

        // 通过包获取文件的绝对路径
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(basePackageFilePath);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            String protocol = resource.getProtocol();
            if ("file".equals(protocol)) {
                String filePath = URLDecoder.decode(resource.getFile(), "UTF-8");
                // 扫描文件夹中的包和类
                doScanPackageClassesByFile(classes, packageName, filePath);
            }
        }

        return classes;
    }

    /**
     * 在文件夹中扫描包和类
     */
    private void doScanPackageClassesByFile(Set<Class<?>> classes, String packageName, String packagePath
                                            ) throws ClassNotFoundException {
        // 转为文件/目录
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 列出文件，进行过滤
        // 自定义文件过滤规则
        File[] dirFiles = dir.listFiles(file -> {
            String filename = file.getName();

            if (file.isDirectory()) {
                if (!recursive) {
                    return false;
                }

                if (packagePredicate != null) {
                    return packagePredicate.test(packageName + "." + filename);
                }
                return true;
            }

            return filename.endsWith(".class");
        });

        if (null == dirFiles) {
            return;
        }

        for (File file : dirFiles) {
            if (file.isDirectory()) {
                // 如果是目录，则递归
                doScanPackageClassesByFile(classes, packageName + "." + file.getName(), file.getAbsolutePath());
            } else {
                // 用当前类加载器加载 去除 fileName 的 .class 6 位
                String className = file.getName().substring(0, file.getName().length() - 6);
                Class<?> loadClass = Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className);
                if (classPredicate == null || classPredicate.test(loadClass)) {
                    classes.add(loadClass);
                }
            }
        }
    }
    public static void test() throws IOException, ClassNotFoundException {
        Predicate<String> packagePredicate = s -> true;

        ClassScanner scanner = new ClassScanner("test.pack", true, packagePredicate, null);
//        Set<Class<?>> packageAllClasses = scanner.doScanAllClasses();
        Set<Class<?>> packageAllClasses = scanner.startTetsdt("test.pack");
        packageAllClasses.forEach(it -> {
            System.out.println(it.getName());
        });
    }
}