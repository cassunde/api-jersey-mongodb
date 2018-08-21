package br.com.cassunde.pessistence.impl;

import static br.com.cassunde.pessistence.impl.JpaUtils.transactional;

import java.util.List;

import javax.inject.Inject;

import br.com.cassunde.api.BasicDAO;
import br.com.cassunde.api.UserDAO;
import br.com.cassunde.model.User;

public class UserDAOImpl implements UserDAO {

    private final BasicDAO basicDAO;

    @Inject
    public UserDAOImpl(BasicDAO basicDAO) {
        this.basicDAO = basicDAO;
    }

    @Override
    public User create(User people) {
        basicDAO.create(people);
        return people;
    }

    @Override
    public User get(String key) {
        return (User) transactional(em -> em.createQuery("from User u where u.id = :key",User.class)
        		.setParameter("key", key)
        		.getSingleResult(),"error");
    }

    @Override
    public User update(User people) {
        return (User) basicDAO.update(people);
    }

    @Override
    public List<User> list() {
    	@SuppressWarnings("unchecked")
		List<User> re = (List<User>) transactional(em -> em.createQuery("from User",User.class).getResultList(),"error");
    	return re;
    }

    @Override
    public long count() {
        return basicDAO.count(User.class);
    }

    @Override
    public void delete(String key) {
        transactional(entityManager -> {
            final User people = entityManager.find(User.class, key);
            entityManager.remove(people);
            return null;
        }, String.format("Error ao deletar ", key));
    }
}
