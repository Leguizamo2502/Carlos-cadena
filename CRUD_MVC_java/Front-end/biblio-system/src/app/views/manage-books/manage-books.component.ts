import { Component, inject } from '@angular/core';
import { BookService } from '../../services/book.service';
import { book } from '../../models/book.model';
import { TopCardsComponent } from '../../components/top-cards/top-cards.component';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FilterbookComponent } from '../../components/filterbook/filterbook.component';
import { FormBookComponent } from '../../components/form-book/form-book.component';

@Component({
  selector: 'app-manage-books',
  standalone: true,
  imports: [
    TopCardsComponent,
    CommonModule,
    MatTableModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    FilterbookComponent,
    FormBookComponent,
    FormBookComponent
  ],
  templateUrl: './manage-books.component.html',
  styleUrl: './manage-books.component.css',
})
export class ManageBooksComponent {
  bookService = inject(BookService);
  book: book[] = [];

  stats: { title: string; value: number; icon: string; isImage: boolean }[] =
    [];

  displayedColumns: string[] = [
    'title',
    'author',
    'publisher',
    'category',
    'actions',
  ];

  constructor() {
    this.loadBooks();
  }

  loadBooks() {
    this.bookService.getBook().subscribe({
      next: (data) => {
        this.book = data;
        this.updateStats();
      },
      error: (err) => {
        console.error('Error loading books:', err);
      },
    });
  }

  deleteBook(bookId: number) {
    this.bookService.deleteBook(bookId).subscribe(() => {
      console.log('Libro eliminado correctamente');
      // Aquí puedes actualizar la lista de libros si es necesario
      // this.updateStats();
      this.loadBooks();
    });
  }

  updateStats() {
    this.stats = [
      {
        title: 'Total Libros',
        value: this.book.length,
        icon: 'book',
        isImage: false,
      },
    ];
  }

  
  
}
