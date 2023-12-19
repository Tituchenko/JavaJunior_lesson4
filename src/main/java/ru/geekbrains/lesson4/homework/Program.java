package ru.geekbrains.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson4.models.Student;

public class Program {

    /**
     * Задание
     * =======
     * Создайте базу данных (например, SchoolDB).
     * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
     * Настройте Hibernate для работы с вашей базой данных.
     * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
     * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
     * Убедитесь, что каждая операция выполняется в отдельной транзакции.
     */

    public static void main(String[] args) {
        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernateCourse.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()){

            // Создание объекта
            Course course = new Course("Мат анализ",120);
            System.out.println(course);
            save(course,sessionFactory);
            System.out.println("Объект курс успешно сохранен");

            Course retrievedCourse=load(course.getId(),sessionFactory);
            System.out.println("Объект Куср успешно получен");
            System.out.println("Полученный объект: " + retrievedCourse);

            // Обновление объекта
            retrievedCourse.setTitle("Сопрамат");
            retrievedCourse.setDuration(240);

            update(retrievedCourse,sessionFactory);
            System.out.println("Объект успешно обновлен");

            delete(retrievedCourse,sessionFactory);
            System.out.println("Объект курс успешно удален");


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void save(Course course,SessionFactory sessionFactory) {
        // Создание сессии
        Session session = sessionFactory.getCurrentSession();
        // Начало транзакции
        session.beginTransaction();

        session.save(course);
        session.getTransaction().commit();

    }
    public static Course load(int id,SessionFactory sessionFactory) {
        // Создание сессии
        Session session = sessionFactory.getCurrentSession();
        // Начало транзакции
        session.beginTransaction();

        Course retrievedCourse = session.get(Course.class, id);
        session.getTransaction().commit();


        return retrievedCourse;
    }

    public static void update(Course course,SessionFactory sessionFactory) {
        // Создание сессии
        Session session = sessionFactory.getCurrentSession();
        // Начало транзакции
        session.beginTransaction();

        session.update(course);

        session.getTransaction().commit();
        System.out.println("Объект курс успешно сохранен");
    }
    public static void delete(Course course,SessionFactory sessionFactory) {
        // Создание сессии
        Session session = sessionFactory.getCurrentSession();
        // Начало транзакции
        session.beginTransaction();

        session.delete(course);

        session.getTransaction().commit();

    }

}
