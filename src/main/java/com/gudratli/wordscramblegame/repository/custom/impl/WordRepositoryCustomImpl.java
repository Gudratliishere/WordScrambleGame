package com.gudratli.wordscramblegame.repository.custom.impl;

import com.gudratli.wordscramblegame.entity.Word;
import com.gudratli.wordscramblegame.repository.custom.WordRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class WordRepositoryCustomImpl implements WordRepositoryCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Word getRandomWord ()
    {
        String query = """
                select
                    w
                from Word w
                where w.id not in (select
                                    us.word.id
                               from UserWord us
                               where us.user.username = :username)
                """;

        Query resultQuery = entityManager.createQuery(query, Word.class);
        resultQuery.setParameter("username", getUsername());

        @SuppressWarnings("unchecked")
        var list = (List<Word>) resultQuery.getResultList();
        return list.get(new Random().nextInt(list.size()));
    }


    private String getUsername()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();
        else
            return principal.toString();
    }
}
