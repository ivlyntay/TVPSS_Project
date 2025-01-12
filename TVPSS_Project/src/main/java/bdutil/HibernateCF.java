package bdutil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.model.Crew;
import com.example.model.User;
import com.example.model.Program;
public class HibernateCF {
    static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            config.addAnnotatedClass(Crew.class);
            config.addAnnotatedClass(Program.class);
            config.addAnnotatedClass(User.class);

            sessionFactory = config.buildSessionFactory();
        }
        return sessionFactory;
    }
}