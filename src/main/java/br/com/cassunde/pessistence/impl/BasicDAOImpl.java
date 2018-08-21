package br.com.cassunde.pessistence.impl;

import static br.com.cassunde.pessistence.impl.JpaUtils.transactional;

import java.util.List;

import br.com.cassunde.api.BasicDAO;

public class BasicDAOImpl implements BasicDAO {
    @Override
    public Object create(Object newEntity) {
        return transactional(session -> {
            session.persist(newEntity);
            return newEntity;
        }, "Could not create entity!");
    }

    @Override
    public Object get(Class entityClass, String key) {
        return transactional(em -> em.find(entityClass, key),
                String.format("Could not find entity with Id %s", key));
    }

    @Override
    public Object update(Object entity) {
        return transactional(em -> {
            return em.merge(entity);
        }, String.format("Something went wrong when trying to update entity %s", entity));
    }

    @Override
    public List list(Class entityClass) {
        return (List) transactional(em ->
                        em.createQuery("from " + entityClass.getSimpleName()).getResultList(),
                String.format("Could not get list of entity %s", entityClass.getSimpleName()));
    }

    @Override
    public long count(Class entityClass) {
        return (long) transactional(
                em -> em.createQuery("select count(*) from " + entityClass.getSimpleName()).getSingleResult(),
                "Could not get count of entity");
    }

    @Override
    public void deleteAll(Class entityClass) {
        transactional(em -> {
            em.createQuery("delete from " + entityClass.getSimpleName()).executeUpdate();
            return null;
        }, "Could not delete all entries of" + entityClass.getSimpleName());
    }
}
