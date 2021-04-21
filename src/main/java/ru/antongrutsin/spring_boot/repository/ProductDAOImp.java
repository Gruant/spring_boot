package ru.antongrutsin.spring_boot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antongrutsin.spring_boot.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class ProductDAOImp implements ProductDAO{

    private final EntityManager entityManager;

    @Autowired
    public ProductDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //entityManager.find()
    @Override
    public Product findById(Long id) {
        try {
            return entityManager.createQuery("SELECT a FROM Product a WHERE a.id = :id", Product.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("SELECT a FROM Product a", Product.class).getResultList();
    }

    //entityManager.remove()
    @Override
    @Transactional
    public void deleteById(Long id) {
        try{
        Product product = findById(id);
        entityManager.createQuery("DELETE FROM Product WHERE id=:id").setParameter("id", product.getId()).executeUpdate();
        } catch (NoResultException e) {
            System.out.println("No such product");
        }
    }

    //session.merge()
    @Override
    @Transactional
    public void saveOrUpdate(Product product) {
        if (findById(product.getId()) != null) {
            entityManager.createQuery("UPDATE Product p SET p.title = :title, p.cost = :cost WHERE p.id = :id")
                    .setParameter("id", product.getId())
                    .setParameter("title", product.getTitle())
                    .setParameter("cost", product.getCost())
                    .executeUpdate();
        } else {
            entityManager.persist(product);
        }
    }
}
