import { Component, Input, numberAttribute } from '@angular/core';

@Component({
  selector: 'app-update-user',
  imports: [],
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.css'
})
export class UpdateUserComponent {
  @Input({transform:numberAttribute})
  id!:number;
}
