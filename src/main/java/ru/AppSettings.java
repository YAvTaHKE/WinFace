package ru;

import com.sun.org.apache.xerces.internal.dom.DOMImplementationImpl;
import com.sun.xml.internal.txw2.output.SaxSerializer;
import com.sun.xml.internal.txw2.output.Dom2SaxAdapter;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

//import com.sun.org.apache.xml.internal.serialize.DOMSerializer;
//import org.exist.util.serializer.DOMSerializer;

public class AppSettings {
    private HashMap fHashMap;
    private static AppSettings SINGLETON;
    static {
        SINGLETON = new AppSettings();
    }

    private AppSettings(){
        fHashMap = new HashMap();
    }

    //Извлечение объекта из коллекции
    public static Object get(String key) {
        return SINGLETON.fHashMap.get(key);
    }

    //Извлечение объекта из коллекции
    //при отсутствии данных возвращается значение по умолчанию
    public static Object get(String key, Object deflt) {
        Object obj = SINGLETON.fHashMap.get(key);
        if (obj == null) {
            return deflt;
        }else {
            return obj;
        }
    }

    //Для упрощения извлечения данных типа int
    public static int getInt(String key, int deflt) {
        Object obj = SINGLETON.fHashMap.get(key);
        if (obj == null) {
            return deflt;
        } else {
            return new Integer((String) obj).intValue();
        }
    }

    //Добавление объекта в коллекцию
    public static void put(String key, Object data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else {
            SINGLETON.fHashMap.put(key, data);
        }
    }

    //Сохранение XML файла
    public static boolean save(File file) throws Exception {
        //Создаем новое DOM дерево
        DOMImplementation domImpl = new DOMImplementationImpl();

        Document doc = domImpl.createDocument(null, "app-settings", null);

        Element root = doc.getDocumentElement();
        Element propertiesElement = doc.createElement("properties");
        root.appendChild(propertiesElement);
        Set set = SINGLETON.fHashMap.keySet();
        if (set != null) {
            for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
                String key = iterator.next().toString();
                Element propertyElement = doc.createElement("property");
                propertyElement.setAttribute("key", key);
                Text nameText = doc.createTextNode(get(key).toString());
                propertyElement.appendChild((Node) nameText);
                propertyElement.appendChild(propertiesElement);
            }
        }

        //Сериализация DOM дерево в файл
        //DOMSerializer serializer = new DOMSerializer();
        //serializer.serialize(doc, file);
        return true;
    }

    //Сохранение XML файла
    public static boolean saveX(File file) throws Exception {
        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //Установка анализа пространства имен
        factory.setNamespaceAware(true);
        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Создаем пустой документ
        Document doc = builder.newDocument();

        Element root = doc.getDocumentElement();
        Element propertiesElement = doc.createElement("properties");
        root.appendChild(propertiesElement);
        Set set = SINGLETON.fHashMap.keySet();
        if (set != null) {
            for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
                String key = iterator.next().toString();
                Element propertyElement = doc.createElement("property");
                propertyElement.setAttribute("key", key);
                Text nameText = doc.createTextNode(get(key).toString());
                propertyElement.appendChild((Node) nameText);
                propertyElement.appendChild(propertiesElement);
            }
        }

        Dom2SaxAdapter adapter = new Dom2SaxAdapter(node);
        serializer = new SaxSerializer(adapter,adapter,false);

        SaxSerializer serializer = new SaxSerializer(new Dom2SaxAdapter(doc), null, false);
        //Создаем новое DOM дерево

        return true;
    }
}
