import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    public static final SessionFactory sessionFactory;

    static {
        Configuration config = new Configuration();

        Properties props = new Properties();
        props.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sneaky_db");
        props.setProperty("hibernate.connection.username", "jdbc:mysql://localhost:3306/test");
        props.setProperty("hibernate.connection.password", "jdbc:mysql://localhost:3306/heythere");

        String hibernateConfig = "hibernate.cfg.xml";

        config.addProperties(props);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(config.getProperties())
            .configure(hibernateConfig)
            .build();

        Metadata metaData = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

        sessionFactory = metaData.getSessionFactoryBuilder().build();
    }
}
