package com.codecool.web.listener;

import com.codecool.web.service.XMLReaderAndWriter;
import org.xml.sax.SAXException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.ParseException;

@WebListener
public final class WebappContextListener implements ServletContextListener {

    public static final XMLReaderAndWriter xmlReaderAndWriter = new XMLReaderAndWriter();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets deployed.");
        try {
            xmlReaderAndWriter.load(sce.getServletContext().getRealPath("/") + "tweets.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e2) {
            e2.printStackTrace();
        } catch (ParseException e3) {
            e3.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets undeployed.");
        try {
            xmlReaderAndWriter.save(sce.getServletContext().getRealPath("/") + "tweets.xml");
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
