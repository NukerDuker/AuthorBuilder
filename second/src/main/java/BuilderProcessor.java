import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("BuilderField")
@SupportedSourceVersion(SourceVersion.RELEASE_18)
public class BuilderProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementsAnnotatedWith =
                roundEnv.getElementsAnnotatedWith(BuilderField.class);

        List<Field> fields = elementsAnnotatedWith.stream()
                .map(field -> new Field(field.getSimpleName().toString(),
                        field.asType().toString(),
                        field.getEnclosingElement().getSimpleName().toString()))
                .collect(Collectors.toList());

        if (!fields.isEmpty()) {
            try {
                createBuilderClass(fields.get(0).getClassName(), fields);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private void createBuilderClass(String className, List<Field> fields) throws IOException {
        String builderClassName = className + "Builder";

        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(builderClassName);

        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

            out.print("public class ");
            out.print(builderClassName);
            out.println(" {");
            out.println();

            out.print("    private ");
            out.print(className);
            out.print(" instance = new ");
            out.print(className);
            out.println("();");
            out.println();

            out.print("    public ");
            out.print(className);
            out.println(" build() {");
            out.println("        return instance;");
            out.println("    }");
            out.println();

            fields.forEach(field -> {
                out.print("    public ");
                out.print(builderClassName);
                out.print(" ");
                out.print("set");
                out.print(capitalize(field.getName()));

                out.print("(");

                out.print(field.getArgumentType());
                out.println(" value) {");
                out.print("        instance.");
                out.print("set");
                out.print(capitalize(field.getName()));
                out.println("(value);");
                out.println("        return this;");
                out.println("    }");
                out.println();
            });

            out.println("}");
        }
    }

    private String capitalize(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}

