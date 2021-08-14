import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/_service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

   //gestion des rôles
   roles: string[] = []


   //Gestion du formulaire
   registerForm: FormGroup;
   registerLoading = false;
   registerSubmitted = false;
   registerError = false;
   registerMessage: string;
   registerSuccess = false;
 
    
   constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) { }
 
   ngOnInit() {
     this.registerForm = this.fb.group({
       email:['', [Validators.required, Validators.email]],
       password: ['', Validators.required],
       rememberMe: []
     });
   }
 
   // Access rapide au formulaire
   get f() { return this.registerForm.controls; }
 
 
 
   // Validation de l'inscription
   onRegisterSubmit() {
    this.registerSubmitted = true;

    // Si le formulaire n'est pas valide
    if (this.registerForm.invalid) {
      this.registerError = true;
      this.registerMessage = "Please fill the form appropriately";
      return;
    }

    this.registerLoading = true;

    console.log("Submitting ...");

    this.authService.register(this.registerForm.get('email').value, this.registerForm.get('password').value).subscribe(
      (resp) => {
        let status = resp.status;
        if(status == "SUCCESS"){
          this.registerLoading = false;
          this.registerSuccess = true;
          this.registerMessage = resp.message;

          this.registerLoading = false;

          Swal.fire(
              'Register',
               this.registerMessage,
              'success'
          );

          this.router.navigate(['auth/login']);
        }
      },
      (error) => {
        Swal.fire(
          'Register',
          'error message : ' + error.message, 
          'error'
        )
      }
    );
  }
    
}  