import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-top-cards',
  imports: [CommonModule,MatCardModule,MatIconModule],
  templateUrl: './top-cards.component.html',
  styleUrl: './top-cards.component.css'
})
export class TopCardsComponent {
  @Input() title: string = '';
  @Input() value: number = 0;
  @Input() icon: string = ''; // puede ser el nombre de un icono de Material o una imagen externa
  @Input() isImage: boolean = false; 
}
