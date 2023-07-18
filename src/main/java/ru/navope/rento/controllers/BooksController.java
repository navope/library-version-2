package ru.navope.rento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.navope.rento.models.Book;
import ru.navope.rento.models.Person;
import ru.navope.rento.services.BookService;
import ru.navope.rento.services.PersonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final PersonService personService;
    private final BookService bookService;

    @Autowired
    public BooksController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book",bookService.getBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookService.update(book, id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping()
    public String showBooks(Model model){
        model.addAttribute("books", bookService.getBooks());
        return "books/showBooks";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person){
        Book book = bookService.getBook(id);
        Person human = book.getOwner();
        model.addAttribute("book", book);
        model.addAttribute("human", human);
        model.addAttribute("people", personService.getPeople());
        return "books/show";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@ModelAttribute("person") Person person ,@PathVariable("id") int id){
        bookService.assign(id, person.getId());
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/free")
    public String toFree(@PathVariable("id") int id){
        bookService.toFree(id);
        return "redirect:/books/{id}";
    }
}
