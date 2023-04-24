package com.gudratli.wordscramblegame.repository.custom.impl;

import com.gudratli.wordscramblegame.dto.response.UserRankResponse;
import com.gudratli.wordscramblegame.repository.custom.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @Override
    public UserRankResponse getUserRankByUsername(String username)
    {
        String query = """
                select * from(
                    select
                        row_number() over (ORDER BY point desc ) as rank,
                        username,
                        point
                    from users) as a
                where a.username = :username
                """;

        Query resultQuery = entityManager.createNativeQuery(query);
        resultQuery.setParameter("username", username);

        List<?> users = resultQuery.getResultList();
        Iterator<?> iterator = users.iterator();
        UserRankResponse userRankResponse = new UserRankResponse();
        while (iterator.hasNext())
        {
            Object[] object = (Object[]) iterator.next();
            userRankResponse.setRank(Integer.parseInt(object[0].toString()));
            userRankResponse.setUsername(object[1].toString());
            userRankResponse.setPoint(Integer.parseInt(object[2].toString()));
        }

        return userRankResponse;
    }

    @Override
    public List<UserRankResponse> getTopUserRank()
    {
        String query = """
                select
                    top 10
                    row_number() over (ORDER BY point desc ) as rank,
                    username,
                    point
                from users
                """;

        Query resultQuery = entityManager.createNativeQuery(query);

        List<?> users = resultQuery.getResultList();
        Iterator<?> iterator = users.iterator();
        List<UserRankResponse> userRankResponseList = new ArrayList<>();
        while (iterator.hasNext())
        {
            Object[] object = (Object[]) iterator.next();
            UserRankResponse userRankResponse = new UserRankResponse();
            userRankResponse.setRank(Integer.parseInt(object[0].toString()));
            userRankResponse.setUsername(object[1].toString());
            userRankResponse.setPoint(Integer.parseInt(object[2].toString()));
            userRankResponseList.add(userRankResponse);
        }

        return userRankResponseList;
    }
}
