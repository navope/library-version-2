package ru.navope.rento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.navope.rento.models.Book;
import ru.navope.rento.models.Person;
import ru.navope.rento.repositories.BookRepository;
import ru.navope.rento.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book getBook(int id){
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void update(Book updateBook, int id){
        updateBook.setId(id);
        bookRepository.save(updateBook);
    }

    @Transactional
    public void assign(int bookId, int personId){
        Book book = getBook(bookId);
        Optional<Person> person = personRepository.findById(personId);
        person.ifPresent(book::setOwner);
    }

    @Transactional
    public void toFree(int id){
        Book book = getBook(id);
        book.setOwner(null);
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }
}
