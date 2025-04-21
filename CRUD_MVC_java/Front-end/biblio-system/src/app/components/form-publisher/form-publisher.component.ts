import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { PublisherService } from '../../services/publisher.service';
import { publisher, publisherCrear } from './../../models/publisher.model';
import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { FormBookComponent } from '../form-book/form-book.component';

@Component({
  selector: 'app-form-publisher',
  imports: [CommonModule,
    MatTableModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    FormBookComponent,
    RouterLink],
  templateUrl: './form-publisher.component.html',
  styleUrl: './form-publisher.component.css'
})
export class FormPublisherComponent {
  publisherService = inject(PublisherService)
  publishers: publisher[]=[];

  private readonly FormBuilder = inject(FormBuilder);
  private readonly router = inject(Router);

  form = this.FormBuilder.group({
    name: ['', [Validators.required, Validators.minLength(3)]]
  });
  

  guardar(){
    let publishers = this.form.value as publisherCrear;
    this.publisherService.createdPublisher(publishers).subscribe(()=>{
      this.router.navigate(['/manage-books'])
    })
  }
}
