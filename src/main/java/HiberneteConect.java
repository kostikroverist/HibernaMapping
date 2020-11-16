import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.imageio.spi.ServiceRegistry;
import java.util.HashSet;
import java.util.Set;

public class HiberneteConect {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory factory = configuration.buildSessionFactory();

        Session session = factory.openSession();


        Post post  = new Post();
        post.setTitle("Спорт це Життя");

        Comment comment = new Comment();
        comment.setAuthorName("Andrew");
        comment.setPost(post);


        Comment comment2 = new Comment();
        comment2.setAuthorName("Oleg");
        comment2.setPost(post);

    Set<Comment> comme = new HashSet<>();
    comme.add(comment);
    comme.add(comment2);

    post.setComments(comme);
        Transaction transaction = session.beginTransaction();

    session.save(post);
    transaction.commit();

    Post postDB = (Post) session.get(Post.class,1);
        System.out.println(postDB + "--->" + postDB.getComments());

        Comment commentDB = (Comment) session.get(Comment.class,1);
        System.out.println(commentDB + "---->"+ commentDB.getPost());
    }
}
