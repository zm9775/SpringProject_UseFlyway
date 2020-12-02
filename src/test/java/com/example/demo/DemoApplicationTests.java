package com.example.demo;

import com.example.demo.entity.Document;
import com.example.demo.entity.Keyword;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.KeywordRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    private DateFormat formatter;

    @BeforeEach
    void setup() throws ParseException {
        formatter = new SimpleDateFormat("dd/MM/yyyy");

        Keyword keyword1 = new Keyword(1, "title1");
        Keyword keyword2 = new Keyword(2, "title2");
        Keyword keyword3 = new Keyword(3, "title3");
        Keyword keyword4 = new Keyword(4, "title4");
        Keyword keyword5 = new Keyword(5, "title5");
        Keyword keyword6 = new Keyword(6, "title6");
        Keyword keyword7 = new Keyword(7, "title7");
        Keyword keyword8 = new Keyword(8, "title8");
        Keyword keyword9 = new Keyword(9, "title9");
        Keyword keyword10 = new Keyword(10, "title10");

        Document document1 = new Document(1, formatter.parse("12/11/2020"), "doc1",keyword2,keyword3);
        documentRepository.save(document1);
        Document document2 = new Document(2, formatter.parse("15/11/2020"), "doc2",keyword1);
        documentRepository.save(document2);
        Document document3 = new Document(3, formatter.parse("19/10/2020"), "doc3",keyword7,keyword2);
        documentRepository.save(document3);
        Document document4 = new Document(4, formatter.parse("13/09/2020"), "doc4",keyword7, keyword2, keyword5, keyword6);
        documentRepository.save(document4);
        Document document5 = new Document(5, formatter.parse("18/08/2020"), "doc5",keyword1, keyword4, keyword8);
        documentRepository.save(document5);
        Document document6 = new Document(6, formatter.parse("7/10/2020"), "doc6");
        documentRepository.save(document6);
        Document document7 = new Document(7, formatter.parse("27/05/2020"), "doc7",keyword9);
        documentRepository.save(document7);
        Document document8 = new Document(8, formatter.parse("22/04/2020"), "doc8",keyword10,keyword5);
        documentRepository.save(document8);

    }
   @Test
    void test() throws ParseException {

        //test find methods in documents table
        Document document1 = documentRepository.findDocumentById(3);
        Assert.assertEquals(3,document1.getId());
        Assert.assertEquals("19/10/2020",formatter.format(document1.getDate()));
        Assert.assertEquals("doc3",document1.getName());
        Assert.assertEquals("title2",document1.getKeywords().get(0).getTitle());

        Document document2 = documentRepository.findDocumentByName("doc7");
        Assert.assertEquals(7,document2.getId());
        Assert.assertEquals("27/05/2020",formatter.format(document2.getDate()));
        Assert.assertEquals("doc7",document2.getName());

        Document document3 = documentRepository.findDocumentByIdAndDate(5,formatter.parse("18/08/2020"));
        Assert.assertEquals(5,document3.getId());
        Assert.assertEquals("18/08/2020",formatter.format(document3.getDate()));
        Assert.assertEquals("doc5",document3.getName());
        Assert.assertEquals(1,document3.getKeywords().get(0).getId());
        Assert.assertEquals("title4",document3.getKeywords().get(1).getTitle());

       Document document4 = documentRepository.findDocumentByNameAndDate("doc8",formatter.parse("22/04/2020"));
       Assert.assertEquals(8, document4.getId());
       //**************************************************************

        //test update methods in documents table
        documentRepository.updateDocumentName(3,"doc3.2");
        Document document5 = documentRepository.findDocumentByName("doc3.2");
        Assert.assertEquals(3, document5.getId());
        Assert.assertEquals("19/10/2020",formatter.format(document5.getDate()));

        documentRepository.updateDocumentDate("doc1",formatter.parse("12/10/2020"));
        Document document6 = documentRepository.findDocumentByDate(formatter.parse("12/10/2020"));
        Assert.assertEquals("doc1", document6.getName());
        Assert.assertEquals(1, document6.getId());

        documentRepository.updateDocumentId(5,9);
        Document document7 = documentRepository.findDocumentById(9);
        Assert.assertEquals("doc5", document7.getName());
        Assert.assertEquals("18/08/2020",formatter.format(document7.getDate()));
        Assert.assertEquals("title8", document7.getKeywords().get(2).getTitle());

        documentRepository.updateDocumentIdAndName(formatter.parse("13/09/2020"),10,"doc4");
        Document document8 = documentRepository.findDocumentByIdAndName(10,"doc4");
        Assert.assertEquals("13/09/2020",formatter.format(document8.getDate()));

        documentRepository.updateDocumentIdAndDate("doc6",11,formatter.parse("19/10/2020"));
        Document document9 = documentRepository.findDocumentByIdAndDate(11,formatter.parse("19/10/2020"));
        Assert.assertEquals("doc6", document9.getName());

        documentRepository.updateDocumentNameAndDate(9,formatter.parse("28/01/2020"),"doc9");
        Document document10 = documentRepository.findDocumentByName("doc9");
        Assert.assertEquals("28/01/2020",formatter.format(document10.getDate()));
        //***********************************************************************

        //test delete methods in documents table
        documentRepository.deleteDocumentByName("doc2");
        Assert.assertNull(documentRepository.findDocumentById(2));

        documentRepository.deleteDocumentByIdAndDate(3,formatter.parse("19/10/2020"));
        Assert.assertNull(documentRepository.findDocumentByName("doc3"));
        Assert.assertEquals(11,documentRepository.findDocumentByDate(formatter.parse("19/10/2020")).getId());

        documentRepository.deleteDocumentById(11);
        Assert.assertNull(documentRepository.findDocumentByDate(formatter.parse("19/10/2020")));
        //*****************************************************************************

       //test find all elements of documents table
       List<Document> documentList = (List<Document>) documentRepository.findAll();
       Assert.assertEquals(1,documentList.get(0).getId());
       Assert.assertEquals("12/10/2020",formatter.format(documentList.get(0).getDate()));
       Assert.assertEquals("doc1",documentList.get(0).getName());

       Assert.assertEquals(7,documentList.get(1).getId());
       Assert.assertEquals("27/05/2020",formatter.format(documentList.get(1).getDate()));
       Assert.assertEquals("doc7",documentList.get(1).getName());

       Assert.assertEquals(8,documentList.get(2).getId());
       Assert.assertEquals("22/04/2020",formatter.format(documentList.get(2).getDate()));
       Assert.assertEquals("doc8",documentList.get(2).getName());

       Assert.assertEquals(9,documentList.get(3).getId());
       Assert.assertEquals("28/01/2020",formatter.format(documentList.get(3).getDate()));
       Assert.assertEquals("doc9",documentList.get(3).getName());

       Assert.assertEquals(10,documentList.get(4).getId());
       Assert.assertEquals("13/09/2020",formatter.format(documentList.get(4).getDate()));
       Assert.assertEquals("doc4",documentList.get(4).getName());
       //*****************************************************

       //test find methods in keywords table
       Keyword keyword1 = keywordRepository.findKeywordById(5);
       Assert.assertEquals("title5",keyword1.getTitle());
       Assert.assertEquals(8,keyword1.getDocuments().get(0).getId());
       Assert.assertEquals("doc4",keyword1.getDocuments().get(1).getName());

       keywordRepository.updateKeywordTitle(8,"title8.1");
       Keyword keyword2 = keywordRepository.findKeywordByIdAndTitle(8,"title8.1");
       Assert.assertEquals(9,keyword2.getDocuments().get(0).getId());
       Assert.assertEquals("28/01/2020",formatter.format(keyword2.getDocuments().get(0).getDate()));
       Assert.assertEquals("doc9",keyword2.getDocuments().get(0).getName());

       keywordRepository.updateKeywordId("title4",11);
       Assert.assertEquals(11,keywordRepository.findKeywordByTitle("title4").getId());

       keywordRepository.updateKeywordIdAndTitle(5,4,"title5.4");
       Keyword keyword3 = keywordRepository.findKeywordByTitle("title5.4");
       Assert.assertEquals(4,keyword3.getId());
       Assert.assertEquals("doc8",keyword3.getDocuments().get(0).getName());
       Assert.assertEquals("13/09/2020",formatter.format(keyword3.getDocuments().get(1).getDate()));
       Assert.assertEquals(10,keyword3.getDocuments().get(1).getId());

       keywordRepository.deleteKeywordById(2);
       Assert.assertNull(keywordRepository.findKeywordByTitle("title2"));

       keywordRepository.deleteKeywordByTitle("title6");
       Assert.assertNull(keywordRepository.findKeywordById(6));

       keywordRepository.deleteKeywordByIdAndTitle(11,"title4");
       Assert.assertNull(keywordRepository.findKeywordByIdAndTitle(11,"title4"));

       List<Keyword> keywordList = (List<Keyword>) keywordRepository.findAll();
       Assert.assertEquals(1,keywordList.get(0).getId());
       Assert.assertEquals("title1",keywordList.get(0).getTitle());
       Assert.assertEquals(9,keywordList.get(0).getDocuments().get(0).getId());

       Assert.assertEquals(3,keywordList.get(1).getId());
       Assert.assertEquals("title3",keywordList.get(1).getTitle());
       Assert.assertEquals("doc1",keywordList.get(1).getDocuments().get(0).getName());

       Assert.assertEquals(4,keywordList.get(2).getId());
       Assert.assertEquals("title5.4",keywordList.get(2).getTitle());
       Assert.assertEquals("doc8",keywordList.get(2).getDocuments().get(0).getName());
       Assert.assertEquals(10,keywordList.get(2).getDocuments().get(1).getId());

       Assert.assertEquals(7,keywordList.get(3).getId());
       Assert.assertEquals("title7",keywordList.get(3).getTitle());
       Assert.assertEquals("doc4",keywordList.get(3).getDocuments().get(0).getName());

       Assert.assertEquals(8,keywordList.get(4).getId());
       Assert.assertEquals("title8.1",keywordList.get(4).getTitle());
       Assert.assertEquals("28/01/2020",formatter.format(keywordList.get(4).getDocuments().get(0).getDate()));

       Assert.assertEquals(9,keywordList.get(5).getId());
       Assert.assertEquals("title9",keywordList.get(5).getTitle());
       Assert.assertEquals(7,keywordList.get(5).getDocuments().get(0).getId());

       Assert.assertEquals(10,keywordList.get(6).getId());
       Assert.assertEquals("title10",keywordList.get(6).getTitle());
       Assert.assertEquals("22/04/2020",formatter.format(keywordList.get(6).getDocuments().get(0).getDate()));
       //********************************************************************************
       //test delete methods in documents table
       documentRepository.deleteDocumentByIdAndName(9,"doc9");
       Assert.assertNull(documentRepository.findDocumentByDate(formatter.parse("28/01/2020")));

       documentRepository.deleteDocumentByDate(formatter.parse("12/10/2020"));
       Assert.assertNull(documentRepository.findDocumentByName("doc1"));

       documentRepository.deleteDocumentByNameAndDate("doc7",formatter.parse("27/05/2020"));
       Assert.assertNull(documentRepository.findDocumentById(7));
       //**************************************************
    }

    @AfterEach
    void teardown(){
       documentRepository.deleteAllDocuments();
       keywordRepository.deleteAllKeywords();
    }
}
