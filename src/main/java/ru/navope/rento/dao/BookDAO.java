package ru.navope.rento.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.navope.rento.models.Book;
import ru.navope.rento.models.Person;

import java.util.List;

@Component
public class BookDAO {

    public List<Book> getBooks(){
        return null;
    }

    public Book getBook(int id){
        return null;
    }
    public List<Book> getPersonBooks(int id){
        return null;
    }
    public void save(Book book){
    }

    public void update(Book updateBook, int id){
    }

    public Person getPerson(int id){
        return null;
    }

    public void assign(int bookId, int personId){
    }

    public void toFree(int id){
    }

    public void delete(int id){
    }
}
