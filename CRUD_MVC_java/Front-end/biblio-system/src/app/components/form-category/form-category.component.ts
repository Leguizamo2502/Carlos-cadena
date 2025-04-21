import { CategoryService } from './../../services/category.service';
import { category, categoryCrear } from './../../models/category.model';
import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { Router, RouterLink } from '@angular/router';
import { FormLoanComponent } from '../form-loan/form-loan.component';
import { TopCardsComponent } from '../top-cards/top-cards.component';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { FormBookComponent } from '../form-book/form-book.component';

@Component({
  selector: 'app-form-category',
  imports: [CommonModule,
    MatTableModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    FormBookComponent,
    RouterLink],
  templateUrl: './form-category.component.html',
  styleUrl: './form-category.component.css'
})
export class FormCategoryComponent {
  constructor(){}

  categoryService = inject(CategoryService);
  categorys: category[] =[];


  private readonly FormBuilder = inject(FormBuilder);
  private readonly router = inject(Router);

  form = this.FormBuilder.group({
    name: ['', [Validators.required, Validators.minLength(3)]]
  });

  guardar(){
    let categorys = this.form.value as categoryCrear;
    this.categoryService.createCategory(categorys).subscribe(()=>{
      this.router.navigate(['/manage-books'])
    })
  }




}
