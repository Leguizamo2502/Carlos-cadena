
import { publisher } from './../../models/publisher.model';
import { category } from './../../models/category.model';
import { book, bookCreated } from './../../models/book.model';
import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormBuilder, FormArray, Validators, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Router, RouterLink } from '@angular/router';
import { BookService } from '../../services/book.service';
import { CategoryService } from '../../services/category.service';
import { PublisherService } from '../../services/publisher.service';
import { AuthorService } from '../../services/author.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { author } from '../../models/author,model';

@Component({
  selector: 'app-form-book',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    RouterLink,
    MatSelectModule,
    FormsModule,
    MatCheckboxModule,
    MatSnackBarModule
  ],
  templateUrl: './form-book.component.html',
  styleUrl: './form-book.component.css',
})
export class FormBookComponent {
  private readonly fb = inject(FormBuilder);
  bookService = inject(BookService);
  categoryService = inject(CategoryService);
  publisherService = inject(PublisherService);
  authorService = inject(AuthorService);
  snackBar = inject(MatSnackBar);

  router = inject(Router);

  categorys: category[] = [];
  publishers: publisher[] = [];
  authors: author[] = [];
  isSubmitting = false;

  // Validador personalizado para verificar que al menos un elemento esté seleccionado
  minSelectedCheckboxes(min = 1): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (control instanceof FormArray) {
        const totalSelected = control.controls
          .map(control => control.value)
          .reduce((prev, next) => next ? prev + 1 : prev, 0);
        return totalSelected >= min ? null : { required: true };
      }
      return null;
    };
  }

  form = this.fb.group({
    title: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    description: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(500)]],
    // Arrays con validación de selección mínima
    id_category: this.fb.array([], [this.minSelectedCheckboxes(1)]),
    id_publisher: this.fb.array([], [this.minSelectedCheckboxes(1)]),
    id_author: this.fb.array([], [this.minSelectedCheckboxes(1)]),
  });

  constructor() {
    this.categoryService.getCategory().subscribe((data) => {
      this.categorys = data;
    });
    this.publisherService.getPublisher().subscribe((data) => {
      this.publishers = data;
    });
    this.authorService.getAuthor().subscribe((data) => {
      this.authors = data;
    });
  }

  get title() { return this.form.get('title'); }
  get description() { return this.form.get('description'); }
  get id_category() { return this.form.get('id_category') as FormArray; }
  get id_publisher() { return this.form.get('id_publisher') as FormArray; }
  get id_author() { return this.form.get('id_author') as FormArray; }

  onCategoryChange(event: any, categoryId: number) {
    const categoriesArray = this.form.get('id_category') as FormArray;

    if (event.checked) {
      categoriesArray.push(this.fb.control(categoryId));
    } else {
      const index = categoriesArray.controls.findIndex(
        (x) => x.value === categoryId
      );
      if (index !== -1) categoriesArray.removeAt(index);
    }
  }

  onPublisherChange(event: any, publisherId: number) {
    const publisherArray = this.form.get('id_publisher') as FormArray;

    if (event.checked) {
      publisherArray.push(this.fb.control(publisherId));
    } else {
      const index = publisherArray.controls.findIndex(
        (x) => x.value === publisherId
      );
      if (index !== -1) publisherArray.removeAt(index);
    }
  }

  onAuthorChange(event: any, authorId: number) {
    const authorArray = this.form.get('id_author') as FormArray;

    if (event.checked) {
      authorArray.push(this.fb.control(authorId));
    } else {
      const index = authorArray.controls.findIndex(
        (x) => x.value === authorId
      );
      if (index !== -1) authorArray.removeAt(index);
    }
  }

  guardar() {
    if (this.form.invalid || this.isSubmitting) {
      this.form.markAllAsTouched();
      this.snackBar.open('Por favor complete correctamente todos los campos requeridos', 'Cerrar', {
        duration: 5000,
      });
      return;
    }

    this.isSubmitting = true;
    
    const book = this.form.value as bookCreated;
    this.bookService.createBook(book).subscribe({
      next: () => {
        this.snackBar.open('Libro creado exitosamente', 'Cerrar', {
          duration: 3000,
        });
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.isSubmitting = false;
        this.snackBar.open('Error al crear el libro: ' + err.message, 'Cerrar', {
          duration: 5000,
        });
      }
    });
  }
}