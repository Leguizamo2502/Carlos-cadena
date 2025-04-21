import { Component, inject } from '@angular/core';
import { UserService } from '../../services/user.service';
import { user, userCreated } from '../../models/user.model';
import { CommonModule } from '@angular/common';
import { TopCardsComponent } from '../../components/top-cards/top-cards.component';
import { MatTableModule } from '@angular/material/table';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';

@Component({
  selector: 'app-register-user',
  imports: [
    CommonModule,
    TopCardsComponent,
    MatTableModule,
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    RouterLink,
    MatSelectModule,
    FormsModule,
    MatCheckboxModule,
  ],
  templateUrl: './register-user.component.html',
  styleUrl: './register-user.component.css',
})
export class RegisterUserComponent {
  userService = inject(UserService);
  user: user[] = [];
  stats: { title: string; value: number; icon: string; isImage: boolean }[] =
    [];

  constructor() {
    this.userService.getUser().subscribe((data) => {
      this.user = data;
      this.stats = [
        {
          title: 'Usuarios Registrados',
          value: this.user.length,
          icon: 'group',
          isImage: false,
        },
      ];
    });
  }

  displayedColumns: string[] = [
    'Nombre',
    'Identificacion',
    'email',
    'acciones',
  ];
  private readonly formBuilder = inject(FormBuilder);
  private readonly router = inject(Router);

  form = this.formBuilder.group({
    first_name: [''],
    last_name: [''],
    identification: [''],
    email: [''],
  });

  deleteUser(userId: number) {
    this.userService.deleteUser(userId).subscribe(() => {
      console.log('Libro eliminado correctamente');
      this.loadUsers();
    });
  }

  loadUsers() {
    this.userService.getUser().subscribe((data) => {
      this.user = data;
      this.stats = [
        {
          title: 'Usuarios Registrados',
          value: this.user.length,
          icon: 'group',
          isImage: false,
        },
      ];
    });
  }

  

  public guardar() {
    let users = this.form.value as userCreated;
    this.userService.createdUser(users).subscribe(() => {
      this.router.navigate(['/dashboard']);
    });
  }
}
