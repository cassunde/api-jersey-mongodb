package br.com.cassunde.pessistence.impl;

import static br.com.cassunde.pessistence.impl.JpaUtils.transactional;

import java.util.List;

import javax.inject.Inject;

import br.com.cassunde.api.BasicDAO;
import br.com.cassunde.api.PostDAO;
import br.com.cassunde.model.Post;

public class PostDAOImpl implements PostDAO {

    private final BasicDAO basicDAO;

    @Inject
    public PostDAOImpl(BasicDAO basicDAO) {
        this.basicDAO = basicDAO;
    }

    @Override
    public Post create(Post post) {
        basicDAO.create(post);
        return post;
    }

    @Override
    public Post get(String key) {
        return (Post) transactional(em -> em.createQuery("from Post u where u.id = :key",Post.class)
        		.setParameter("key", key)
        		.getSingleResult(),"error");
    }

    @Override
    public Post update(Post post) {
        return (Post) basicDAO.update(post);
    }

    @Override
    public List<Post> list() {
    	@SuppressWarnings("unchecked")
		List<Post> re = (List<Post>) transactional(em -> em.createQuery("from Post",Post.class).getResultList(),"error");
    	return re;
    }

    @Override
    public long count() {
        return basicDAO.count(Post.class);
    }

    @Override
    public void delete(String key) {
        transactional(entityManager -> {
            final Post post = entityManager.find(Post.class, key);
            entityManager.remove(post);
            return null;
        }, String.format("Error ao deletar ", key));
    }
}
