package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLReaderAndWriter {

    private Document document;
    private TweetList tweetList;
    private SimpleDateFormat simpleDateFormat;

    public XMLReaderAndWriter() {
        DocumentBuilder docBuilder = createDocumentBuilder();
        this.document = docBuilder.newDocument();
        this.tweetList = new TweetList();
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
    }

    public void load(String filename) throws IOException, SAXException, ParseException {
        DocumentBuilder db = createDocumentBuilder();
        InputStream is = new FileInputStream(filename);
        document = db.parse(is);
        document.getDocumentElement().normalize();
        getTweetsFromFile();
    }

    public void save(String filename) throws TransformerException {
        Element root = document.createElement("tweets");
        document.appendChild(root);
        for (Tweet i : tweetList.getTweets()) {
            writeNode(i);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
    }

    public void writeNode(Tweet tweet) {
        Element tempElement = document.createElement("tweet");
        document.getDocumentElement().appendChild(tempElement);
        createElement("id", String.valueOf(tweet.getId()), tempElement);
        createElement("poster", tweet.getUser(), tempElement);
        createElement("post", tweet.getPost(), tempElement);
        createElement("date", simpleDateFormat.format(tweet.getDateOfPosting()), tempElement);
    }

    public TweetList getTweetList() {
        return tweetList;
    }

    public void getTweetsFromFile() throws ParseException {
        List<Element> elements = getElements(document.getDocumentElement());
        TweetList tweets = new TweetList();
        for (Element element : elements) {
            int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
            String poster = element.getElementsByTagName("poster").item(0).getTextContent();
            String post = element.getElementsByTagName("post").item(0).getTextContent();
            Date date = simpleDateFormat.parse(element.getElementsByTagName("date").item(0).getTextContent());
            tweets.addTweet(new Tweet(id, poster, post, date));
        }
        tweetList = tweets;
    }

    public void createElement(String tagName, String textContent, Element element) {
        Element newElement = document.createElement(tagName);
        newElement.setTextContent(textContent);
        element.appendChild(newElement);
    }

    public List<Element> getElements(Element parentNode) {
        ArrayList<Element> elements = new ArrayList<>();
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node childNode = parentNode.getChildNodes().item(i);
            if (childNode instanceof Element) {
                elements.add((Element) childNode);
            }
        }
        return elements;
    }

    public DocumentBuilder createDocumentBuilder() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return db;
    }


}
