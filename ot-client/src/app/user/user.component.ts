import { Component, OnInit } from '@angular/core';
import { UserService } from '../_service/user.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  username: string;
   //gestion des dates
   today: number = Date.now();

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getCurrentUser();

    /** jquery */
     /*-------------------------------------
          menu
      -------------------------------------*/
      $('.navbar-area .menu').on('click', function() {
        $(this).toggleClass('open');
        $('.navbar-area .navbar-collapse').toggleClass('sopen');
    });

    /**end jquery */
  }

  getCurrentUser() {
    this.userService.getCurrentUser().subscribe(
      (response) => {
        this.username = response.data['email'];
      },
      (error) => {
        console.log("USER");
      }
    );
  }





}
