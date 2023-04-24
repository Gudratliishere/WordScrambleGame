package com.gudratli.wordscramblegame.repository.custom.impl;

import com.gudratli.wordscramblegame.repository.custom.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void decreasePoint(String username, Integer point)
    {
        String query = """
                update User u
                set u.point = (
                select
                    uu.point
                from User uu
                where uu.username = :username) - :point
                where u.username = :username
                """;

        Query resultQuery = entityManager.createQuery(query);
        resultQuery.setParameter("username", username);
        resultQuery.setParameter("point", point);

        resultQuery.executeUpdate();
    }

    @Override
    @Transactional
    public void increasePoint(String username, Integer point)
    {
        String query = """
                update User u
                set u.point = (
                select
                    uu.point
                from User uu
                where uu.username = :username) + :point
                where u.username = :username
                """;

        Query resultQuery = entityManager.createQuery(query);
        resultQuery.setParameter("username", username);
        resultQuery.setParameter("point", point);

        resultQuery.executeUpdate();
    }
}
