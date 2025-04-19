import { Component } from '@angular/core';
import { RouterLink, RouterModule } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';


@Component({
  selector: 'app-sidebar',
  imports: [RouterLink, RouterModule,MatToolbarModule, MatButtonModule, MatIconModule,MatSidenavModule,MatListModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  

}
