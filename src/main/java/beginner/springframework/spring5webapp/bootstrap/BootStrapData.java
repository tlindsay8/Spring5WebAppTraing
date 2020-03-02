package beginner.springframework.spring5webapp.bootstrap;


import beginner.springframework.spring5webapp.domain.Author;
import beginner.springframework.spring5webapp.domain.Book;
import beginner.springframework.spring5webapp.domain.Publisher;
import beginner.springframework.spring5webapp.repositories.AuthorRepository;
import beginner.springframework.spring5webapp.repositories.BookRepository;
import beginner.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;

    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        Publisher LindsayHouse = new Publisher("2003 Chita Ct", "Temple Hills", "MD", "20748");

        publisherRepository.save(LindsayHouse);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(LindsayHouse);
        LindsayHouse.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(LindsayHouse);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE", "3456789");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(LindsayHouse);
        LindsayHouse.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(LindsayHouse);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Publisher Number of Books:" + LindsayHouse.getBooks().size());

    }
}
