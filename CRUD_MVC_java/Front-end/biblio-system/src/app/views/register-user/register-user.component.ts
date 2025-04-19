import { Component, inject } from '@angular/core';
import { UserService } from '../../services/user.service';
import { user } from '../../models/user.model';

@Component({
  selector: 'app-register-user',
  imports: [],
  templateUrl: './register-user.component.html',
  styleUrl: './register-user.component.css'
})
export class RegisterUserComponent {
  userService = inject(UserService);

  user?: user[];
  constructor(){
    this.userService.getUser().subscribe((data)=>{
      this.user = data;
    })
  }
}
