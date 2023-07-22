package ru.navope.rento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.navope.rento.models.Book;
import ru.navope.rento.models.Person;
import ru.navope.rento.repositories.BookRepository;
import ru.navope.rento.repositories.PersonRepository;

import java.util.Date;
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

    public List<Book> getBooksWithPaginationAndPossiblySorted(int page, int booksPerPage, boolean sortByYear) {
        if (page < 0 || booksPerPage < 0){
            return bookRepository.findAll();
        }
        if (sortByYear){
            return bookRepository.findAll(PageRequest.of(page,booksPerPage, Sort.by("year"))).getContent();
        }else {
            return bookRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();
        }
    }

    public List<Book> getBooksWithSorting(boolean sortByYear) {
        if (sortByYear){
            return bookRepository.findAll(Sort.by("year"));
        }else {
            return bookRepository.findAll();
        }
    }

    public List<Book> findBooks(String content){
        return  bookRepository.findByNameStartingWithIgnoreCase(content);
    }


    public Book getBook(int id){
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book){
        book.setTakeAt(new Date());
        bookRepository.save(book);
    }

    @Transactional
    public void update(Book updateBook, int id){
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            updateBook.setOwner(book.get().getOwner());
        }
        updateBook.setId(id);
        bookRepository.save(updateBook);
    }

    @Transactional
    public void assign(int bookId, int personId){
        bookRepository.findById(bookId).ifPresent(
            book->{
                book.setOwner(personRepository.findById(personId).orElse(null));
                book.setTakeAt(new Date());
            }
        );
    }

    @Transactional
    public void toFree(int id) {
        bookRepository.findById(id).ifPresent(
            book -> {
                book.setOwner(null);
                book.setTakeAt(null);
        });
    }


    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }
}
