package com.demo.parseXML;

import com.demo.UserAction.UserAction;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ParseXML {

    static Document doc = null;

    static {
        SAXReader saxReader = new SAXReader();
        try {
            doc = saxReader.read(new File("./src/main/resources/application.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    public static String getBeanClass(String id) {

        String className = null;

        List list = doc.selectNodes("beans/bean");
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            if (element.attributeValue("id").equals(id)) {
                className = element.attributeValue("class");
            }
        }

        return className;
    }

    public static Hashtable getPropertyClass(String id) {

        Hashtable<String, String> hashtable = new Hashtable<String, String>();

        List list = doc.selectNodes("beans/bean");
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            if (element.attributeValue("id").equals(id)) {
                List list2 = element.selectNodes("property");

                Iterator iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    Element selement = (Element) iterator2.next();
                    hashtable.put(selement.attributeValue("name"), selement.attributeValue("ref"));
                }
            }
        }
        return hashtable;
    }

    public static Object createInstance(String id) {
        String className = getBeanClass(id);
        Hashtable hashtable = getPropertyClass(id);

        Object object = null;

        try {
            Class c = Class.forName(className);
            object = c.newInstance();

            Enumeration e = hashtable.keys();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String methodName = "set" + key;
                String className2 = getBeanClass((String) hashtable.get(key));
                Object object2 = Class.forName(className2).newInstance();

                Method[] methods=c.getDeclaredMethods();
                for (int i = 0; i <methods.length ; i++) {
                    if(methods[i].getName().equals(methodName)){
                        methods[i].invoke(object,object2);
                    }
                }
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }  catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void main(String[] args) {

        UserAction a = (UserAction) ParseXML.createInstance("UserAction");
        a.exec();
    }
}
