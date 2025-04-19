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
  imports: [RouterModule,TopCardsComponent, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {
  bookService = inject(BookService);
  book: book[] = [];

  userService = inject(UserService);
  user:user[] = [];
  
  stats = [
    { title: 'Total Libros', value: 0, icon: 'book', isImage: false },
    { title: 'Usuarios Registrados', value: 0, icon: 'group', isImage: false },
    { title: 'Préstamos Activos', value: 0, icon: 'assignment', isImage: false },
  ];

  constructor() {
    this.bookService.getBook().subscribe((data) => {
      this.book = data;
      this.updateStats(); // Actualiza el total de libros cuando los datos se cargan
    });

    this.userService.getUser().subscribe((data)=>{
      this.user = data;
      this.updateStats;
    })

  }

  // Función que actualiza el valor de los stats
  updateStats() {
    this.stats[0].value = this.book.length;
    this.stats[1].value = this.user.length;
  }
}
