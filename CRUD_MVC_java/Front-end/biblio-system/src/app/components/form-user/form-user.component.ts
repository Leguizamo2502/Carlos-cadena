import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Output, Input, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormBuilder, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { Router, RouterLink } from '@angular/router';
import { TopCardsComponent } from '../top-cards/top-cards.component';
import { user, userCreated } from '../../models/user.model';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-form-user',
  imports: [CommonModule,
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
    MatCheckboxModule,],
  templateUrl: './form-user.component.html',
  styleUrl: './form-user.component.css'
})
export class FormUserComponent implements OnInit{
  

  // userService = inject(UserService)
  private readonly formBuilder = inject(FormBuilder);

  @Input()
  modelo?:user;
  
  @Output()
  posteoFormulario = new EventEmitter<userCreated>();

  ngOnInit(): void {
    if(this.modelo !== undefined){
      this.form.patchValue(this.modelo);
    }
  }

  form = this.formBuilder.group({
    first_name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
    last_name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
    identification: ['', [Validators.required, Validators.pattern('^[0-9]{8,12}$')]],
    email: ['', [Validators.required, Validators.email]],
  });

  // Getter methods for easy access to form controls in the template
  get firstNameControl() { return this.form.get('first_name'); }
  get lastNameControl() { return this.form.get('last_name'); }
  get identificationControl() { return this.form.get('identification'); }
  get emailControl() { return this.form.get('email'); }

  public guardar() {
      if (this.form.invalid) {
        // Mark all fields as touched to trigger validation errors display
        this.form.markAllAsTouched();
        return;
      }
      
      let users = this.form.value as userCreated;
      this.posteoFormulario.emit(users);
    }
}
