package com.example.demo.repository;

import com.example.demo.entity.Document;
import com.example.demo.entity.Keyword;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface KeywordRepository extends CrudRepository<Keyword,Integer> {

    @Query("select keyword from Keyword keyword where keyword.id = :id")
    Keyword findKeywordById(@Param(value = "id")Integer id);

    @Query("select keyword from Keyword keyword where keyword.title = :title")
    Keyword findKeywordByTitle(@Param(value = "title")String title);

    @Query("select keyword from Keyword keyword where keyword.id = :id and keyword.title = :title")
    Keyword findKeywordByIdAndTitle(@Param(value = "id")Integer id,@Param(value = "title")String title);

    @Modifying
    @Transactional
    @Query("update Keyword keyword set keyword.id = :id where keyword.title = :title")
    void updateKeywordId(@Param(value = "title")String title, @Param(value = "id")Integer id);

    @Modifying
    @Transactional
    @Query("update Keyword keyword set keyword.title = :title where keyword.id = :id")
    void updateKeywordTitle(@Param(value = "id")Integer id, @Param(value = "title")String title);

    @Modifying
    @Transactional
    @Query("update Keyword keyword set keyword.id = :newId, keyword.title = :title where keyword.id = :id")
    void updateKeywordIdAndTitle(@Param(value = "id")Integer id,@Param(value = "newId")Integer newId,@Param(value = "title")String title);

    @Modifying
    @Transactional
    @Query("delete from Keyword where id = :id")
    void deleteKeywordById(@Param(value = "id")Integer id);

    @Modifying
    @Transactional
    @Query("delete from Keyword where title = :title")
    void deleteKeywordByTitle(@Param(value = "title")String title);

    @Modifying
    @Transactional
    @Query("delete from Keyword where id = :id and title = :title")
    void deleteKeywordByIdAndTitle(@Param(value = "id")Integer id,@Param(value = "title")String title);

    @Modifying
    @Transactional
    @Query("delete from Keyword")
    void deleteAllKeywords();
}
