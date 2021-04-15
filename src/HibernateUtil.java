import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HibernateUtil {
    public static final SessionFactory sessionFactory;

    static {
        Configuration config = new Configuration();

        Properties props = new Properties();
        props.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sneaky_db");
        props.setProperty("hibernate.connection.username", "test");
        props.setProperty("hibernate.connection.password", "heythere");

        String hibernateConfig = "hibernate.cfg.xml";

        config.addProperties(props);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(config.getProperties())
            .configure(hibernateConfig)
            .build();

        Metadata metaData = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

        sessionFactory = metaData.getSessionFactoryBuilder().build();
    }

    public static List<String> doSecretQuery(String injectMe) {
        Session hSession = sessionFactory.getCurrentSession();

        Query query = hSession.createSQLQuery("select userEmail FROM User u WHERE userEmail like '%" + injectMe + "%'");
        ArrayList<String> returnVals = new ArrayList<>();
        List<?> rows = query.list();
        
        for (Object obj : rows) {
            Object[] row = (Object[]) obj;
            returnVals.add(row[0].toString() + ":  " + row[1].toString());
        }
        return returnVals;
    }
    
    public static void oldSchool() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sneaky_db", "test", "heythere");
        
        String category = System.getenv("ITEM_CATEGORY");
        Statement statement = connection.createStatement();
        String query1 = "SELECT ITEM,PRICE FROM PRODUCT WHERE ITEM_CATEGORY='"
            + category + "' ORDER BY PRICE";
        ResultSet results = statement.executeQuery(query1);
    }
}
