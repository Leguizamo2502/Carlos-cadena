import { Component, inject } from '@angular/core';
import { BookService } from '../../services/book.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-filterbook',
  imports: [CommonModule,RouterModule,FormsModule],
  templateUrl: './filterbook.component.html',
  styleUrl: './filterbook.component.css'
})
export class FilterbookComponent {
  bookService = inject(BookService);
  books: string[] = [];
  searchName: string = '';

  constructor() {}

  searchBooks(): void {
    if (this.searchName.trim()) {
      this.bookService.getFilter(this.searchName).subscribe({
        next: (data) => this.books = data,
        error: (err) => console.error('Error al buscar libros:', err)
      });
    }
  }
}
