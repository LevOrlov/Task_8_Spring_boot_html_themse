import dao.UserDao;
import dao.daoImpl.UserDaoHibernateImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.Properties;

//показывает что в этмо классе будут бины
@Configuration
//включает веб версию
@EnableWebMvc
//TODO что длелает?
@EnableTransactionManagement
//мы показываем где искать аннотированные класс
@ComponentScan("control")
@PropertySource("classpath:config.properties")
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    Environment env;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/views/**").addResourceLocations("/views/");
    }

    //показывает что это метода возвращает конкретный объект, и показывает спрингу
    // что это его компнент.Метод, аннотированный этой аннотацией, работает как идентификатор компонента,

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan("model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        System.out.println("## getSessionFactory: " + sessionFactory);
        return sessionFactory;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driver"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("user"));
        dataSource.setPassword(env.getProperty("password"));

        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.dialect",
                env.getRequiredProperty("dialect"));
        properties.put("current_session_context_class",
                env.getRequiredProperty("current_session_context_class"));
        properties.put("hibernate.enable_lazy_load_no_trans",
                env.getRequiredProperty("hibernate.enable_lazy_load_no_trans"));
        return properties;
    }

    @Bean
    @Autowired
    //для чего это метод и где он используется?какой то он сука автоматический
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    @Bean
    public UserDao getDAO() {

        return new UserDaoHibernateImpl();
    }


}
