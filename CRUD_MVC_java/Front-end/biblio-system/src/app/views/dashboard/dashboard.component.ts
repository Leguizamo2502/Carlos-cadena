import { Component, inject } from '@angular/core';
import { BookService } from '../../services/book.service';
import { book } from '../../models/book.model';
import { TopCardsComponent } from '../../components/top-cards/top-cards.component';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user.service';
import { user } from '../../models/user.model';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [RouterModule, TopCardsComponent, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {
  bookService = inject(BookService);
  userService = inject(UserService);
  book: book[] = [];
  user: user[] = [];

  // Inicializamos stats vacío y lo llenamos después
  stats: { title: string; value: number; icon: string; isImage: boolean }[] = [];

  constructor() {
    this.bookService.getBook().subscribe((data) => {
      this.book = data;
      this.updateStats(); // Actualiza libros
    });

    this.userService.getUser().subscribe((data) => {
      this.user = data;
      this.updateStats(); // Actualiza usuarios (¡corregido!)
    });
  }

  updateStats() {
    this.stats = [
      { title: 'Total Libros', value: this.book.length, icon: 'book', isImage: false },
      { title: 'Usuarios Registrados', value: this.user.length, icon: 'group', isImage: false },
      { title: 'Préstamos Activos', value: 0, icon: 'assignment', isImage: false }, // Si no hay datos, se queda en 0
    ];
  }
}