import { Component, inject } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { CategoryService } from './services/category.service';
import { category } from './models/category.model';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,RouterModule,MatToolbarModule, MatButtonModule, MatIconModule,MatSidenavModule,MatListModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  // title = 'biblio-system';
  categoryService = inject(CategoryService);
  
  category?: category[];
   constructor(){
    // this.categoryService.getCategory().subscribe((data)=>{
    //   this.category= data;
    // })

   }

}
