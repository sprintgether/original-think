import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/_service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
    //gestion des dates
    today: number = Date.now();
  
    // Gestion du formulaire
   forgotPasswordForm: FormGroup;
   forgotPasswordLoading = false;
   forgotPasswordSubmitted = false;
   forgotPasswordError = false;
   forgotPasswordMessage: string;
   forgotPasswordSuccess = false;
 
    // Gestion des alertes
    successClosed = false;
    warningClosed = false;
    dangerClosed = false;
    infoClosed = false;
 
   constructor(private fb: FormBuilder, private authservice: AuthService, private router: Router) { }


  ngOnInit() {
    this.forgotPasswordForm = this.fb.group({
      email:['', [Validators.required, Validators.email]],
    })
  }

  // Access rapide au formulaire
  get f() { return this.forgotPasswordForm.controls; }

  onPasswordForgotSubmit(){
    this.forgotPasswordSubmitted = true;

    // Arrêter les traitements ici si le formulaire n'est pas valide
    if (this.forgotPasswordForm.invalid) {
      this.forgotPasswordError = true;
      this.forgotPasswordMessage = "Please provide an email address appropriately"
      return;
    }

    this.forgotPasswordLoading = true;
    
    this.authservice.forgotPassword(this.forgotPasswordForm.get("email").value).subscribe(
      (resp) => {
        let status = resp.status;

        if(status == "SUCCESS") {
          this.forgotPasswordLoading = false;
          this.forgotPasswordSuccess = true;
          this.forgotPasswordMessage = resp.message;
          
          Swal.fire(
            'Forgot password',
            this.forgotPasswordMessage,
            'warning'
          )

          this.router.navigate(['auth/reset-password']);
        }    
      },
      (error) => {
        Swal.fire(
          'Forgot password',
          'error message : ' + error.message, 
          'error'
        );
      }
    );
  }

}