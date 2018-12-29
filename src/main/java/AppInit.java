
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    //Как я понял здесь мы указываем класс в котором идет конфигурация приложения
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                MvcConfiguration.class
        };
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class<?>[]{
                MvcConfiguration.class
        };
    }

    //это получается точка входа
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}