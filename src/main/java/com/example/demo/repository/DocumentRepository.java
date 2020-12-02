package com.example.demo.repository;

import com.example.demo.entity.Document;
import com.example.demo.entity.Keyword;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document,Integer> {

    @Query("select document from Document document where document.id = :id")
    Document findDocumentById(@Param(value = "id") Integer id);

    @Query("select document from Document document where document.name = :name")
    Document findDocumentByName(@Param(value = "name") String name);

    @Query("select document from Document document where document.date = :date")
    Document findDocumentByDate(@Param(value = "date") Date date);

    @Query("select document from Document document where document.id = :id and document.name = :name")
    Document findDocumentByIdAndName(@Param(value = "id")Integer id,@Param(value = "name")String name);

    @Query("select document from Document document where document.id = :id and document.date = :date")
    Document findDocumentByIdAndDate(@Param(value = "id")Integer id,@Param(value = "date")Date date);

    @Query("select document from Document document where document.name = :name and document.date = :date")
    Document findDocumentByNameAndDate(@Param(value = "name")String name,@Param(value = "date")Date date);

    @Modifying
    @Transactional
    @Query("update Document document set document.id = :newId where document.id = :id")
    void updateDocumentId(@Param(value = "id")Integer id,@Param(value = "newId")Integer newId);

    @Modifying
    @Transactional
    @Query("update Document document set document.name = :name where document.id = :id")
    void updateDocumentName(@Param(value = "id")Integer id,@Param(value = "name")String name);

    @Modifying
    @Transactional
    @Query("update Document document set document.date = :date where document.name =:name")
    void updateDocumentDate(@Param(value = "name")String name,@Param(value = "date")Date date);

    @Modifying
    @Transactional
    @Query("update Document document set document.id =:id, document.name = :name where document.date = :date")
    void updateDocumentIdAndName(@Param(value = "date")Date date,@Param(value = "id")Integer id,@Param(value = "name")String name);

    @Modifying
    @Transactional
    @Query("update Document document set document.id =:id, document.date = :date where document.name = :name")
    void updateDocumentIdAndDate(@Param(value = "name")String name,@Param(value = "id")Integer id,@Param(value = "date")Date date);

    @Modifying
    @Transactional
    @Query("update Document document set document.date =:date, document.name = :name where document.id = :id")
    void updateDocumentNameAndDate(@Param(value = "id")Integer id,@Param(value = "date")Date date,@Param(value = "name")String name);

    @Modifying
    @Transactional
    @Query("delete from Document where id = :id")
    void deleteDocumentById(@Param(value = "id")Integer id);

    @Modifying
    @Transactional
    @Query("delete from Document where name = :name")
    void deleteDocumentByName(@Param(value = "name")String name);

    @Modifying
    @Transactional
    @Query("delete from Document where date = :date")
    void deleteDocumentByDate(@Param(value = "date")Date date);

    @Modifying
    @Transactional
    @Query("delete from Document where id = :id and name = :name")
    void deleteDocumentByIdAndName(@Param(value = "id")Integer id,@Param(value = "name")String name);

    @Modifying
    @Transactional
    @Query("delete from Document where id = :id and date = :date")
    void deleteDocumentByIdAndDate(@Param(value = "id")Integer id,@Param(value = "date")Date date);

    @Modifying
    @Transactional
    @Query("delete from Document where name = :name and date = :date")
    void deleteDocumentByNameAndDate(@Param(value = "name")String name,@Param(value = "date")Date date);

    @Modifying
    @Transactional
    @Query("delete from Document")
    void deleteAllDocuments();
}
