import { Component, inject, Input, numberAttribute, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { user, userCreated } from '../../models/user.model';
import { Router } from '@angular/router';
import { FormUserComponent } from '../form-user/form-user.component';

@Component({
  selector: 'app-update-user',
  imports: [FormUserComponent],
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.css'
})
export class UpdateUserComponent implements OnInit{
  
  @Input({transform:numberAttribute})
  id!:number

  userService = inject(UserService);
  modelo?:user;
  route = inject(Router);

  ngOnInit(): void {
    this.userService.getIdUser(this.id).subscribe(user=>{
      this.modelo = user;
    })
  }

  // guardar(user:userCreated){
  //   this.userService.updateUser(this.id,user).subscribe(()=>{
  //     this.route.navigate(['/register-user'])
  //   }
  // }

  guardar(user:userCreated){
    this.userService.updateUser(this.id,user).subscribe(()=>{
      this.route.navigate(['/register-user'])
    })
  }

}
