import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthorService } from '../../services/author.service';
import { author, authorCrear } from './../../models/author,model';
import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { FormBookComponent } from '../form-book/form-book.component';

@Component({
  selector: 'app-form-author',
  imports: [CommonModule,
    MatTableModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    FormBookComponent,
    RouterLink],
  templateUrl: './form-author.component.html',
  styleUrl: './form-author.component.css',
})
export class FormAuthorComponent {
  constructor() {}
  authorService = inject(AuthorService);
  authors:author[]=[];

  private readonly FormBuilder = inject(FormBuilder);
  private readonly router = inject(Router);

  form = this.FormBuilder.group({
    first_name: ['', [Validators.required, Validators.minLength(3)]],
    last_name: ['', [Validators.required, Validators.minLength(3)]],
  });

  guardar(){
    let authors = this.form.value as authorCrear;
    this.authorService.createdAuthor(authors).subscribe(()=>{
      this.router.navigate(['/manage-books'])
    })
  }


}
