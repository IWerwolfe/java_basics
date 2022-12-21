import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> query = criteriaBuilder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);
        List<PurchaseList> purchaseList = session.createQuery(query).getResultList();

        Transaction transaction = session.beginTransaction();

        purchaseList.forEach(p -> {
            PurchaseKey key = new PurchaseKey(p.getStudentName(), p.getCourseName());
            session.saveOrUpdate(new LinkedPurchaseList(
                    key,
                    p.getStudentName(),
                    p.getCourseName())
            );
        });

        transaction.commit();
        HibernateSessionFactory.shutdown();
    }
}
