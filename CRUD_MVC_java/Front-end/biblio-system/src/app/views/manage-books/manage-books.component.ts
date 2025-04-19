import { Component, inject } from '@angular/core';
import { BookService } from '../../services/book.service';
import { book } from '../../models/book.model';
import { TopCardsComponent } from '../../components/top-cards/top-cards.component';
import { CommonModule } from '@angular/common';
import { AuthorService } from '../../services/author.service';
import { author } from '../../models/author,model';
import { PublisherService } from '../../services/publisher.service';
import { publisher } from '../../models/publisher.model';
import { CategoryService } from '../../services/category.service';
import { category } from '../../models/category.model';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-manage-books',
  imports: [TopCardsComponent,CommonModule,MatTableModule],
  templateUrl: './manage-books.component.html',
  styleUrl: './manage-books.component.css',
})
export class ManageBooksComponent {
  bookService = inject(BookService);
  book: book[] = [];

  authorService = inject(AuthorService);
  author: author[] = [];

  publisherService = inject(PublisherService);
  publisher: publisher[] = [];  

  categoryService = inject(CategoryService);
  category: category[] = [];
  
  stats = [{ title: 'Total Libros', value: 0, icon: 'book', isImage: false }];

  constructor() {
    this.bookService.getBook().subscribe((data) => {
      this.book = data;
      this.updateStats();
    });

    this.authorService.getAuthor().subscribe((data)=>{
      this.author = data
    })

    this.publisherService.getPublisher().subscribe((data)=>{
      this.publisher = data;
    })

    this.categoryService.getCategory().subscribe((data)=>{
      this.category = data;
    })

    
  }
  displayedColumns: string[] = ['title', 'author', 'publisher', 'category'];

  updateStats() {
    this.stats[0].value = this.book.length;
  }

}
