package kz.gvs.spring;

import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Launcher {
    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            RecordDAO recordDAO = (RecordDAO) context.getBean("RecordDAO");
            
            // Выводим все строки в БД
            System.out.println("Изначальное состояние БД:");
            printAll(recordDAO.selectAll());
            
            // Удаляем запись по фамилии
            System.out.println("Удаляем запись с фамилией 'Федоров'...");
            recordDAO.deleteByLastName("Федоров");
            
            // Создание новой записи
            System.out.println("Добавление записи...");
            Record r1 = new Record("Игорь", "Федор", "77052457654");
            // Занесение записи в БД
            recordDAO.insert(r1);
            
            // Выводим все строки в БД
            System.out.println("Состояние после:");
            printAll(recordDAO.selectAll());
            
            // Изменение записи
            System.out.println("Изменяем фамилию...");
            recordDAO.update("Федор", "Федоров");
            
            // Выводим все строки в БД
            System.out.println("Состояние после:");
            printAll(recordDAO.selectAll());
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
    
    private static void printAll(List<Record> recordList) {
        for (Record r : recordList) {
            System.out.println(r);
        }
        System.out.println("");
    }
}
