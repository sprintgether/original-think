import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MustMatch } from 'src/app/_helper/must-match.validator';
import { AuthService } from 'src/app/_service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

    // Gestion du formulaire
    token: string;
    resetPasswordForm: FormGroup;
    resetPasswordLoading = false;
    resetPasswordSubmitted = false;
    resetPasswordError = false;
    resetPasswordMessage: string;
    resetPasswordSuccess = false;
  
     // Gestion des alertes
     successClosed = false;
     warningClosed = false;
     dangerClosed = false;
     infoClosed = false;
  
    constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {}
  

    ngOnInit(){
      this.resetPasswordForm = this.fb.group({
        password: ['', [Validators.required, Validators.minLength(6)]],
        passwordConfirmation: ['', [Validators.required, Validators.minLength(6)]],
        code: ['', [Validators.required, Validators.minLength(12)]],
      }, 
      {
        validator: MustMatch('password', 'passwordConfirmation')
      });
    } 

    // Access rapide au formulaire
    get f() { return this.resetPasswordForm.controls; }

    onPasswordResetSubmit() {
      this.resetPasswordSubmitted = true;
  
      // Arrêter les traitements ici si le formulaire n'est pas valide
      if (this.resetPasswordForm.invalid) {
        this.resetPasswordError = true;
        this.resetPasswordMessage = "Please fill the form appropriately";
        return;
      }
      console.log("submitting");
      this.resetPasswordLoading = true;

      this.authService.resetPassword(this.resetPasswordForm.get('password').value, 
                                     this.resetPasswordForm.get('passwordConfirmation').value,
                                     this.resetPasswordForm.get('code').value).subscribe(
        (resp) => {
          let status = resp.status;
          if(status == "SUCCESS") {
            this.resetPasswordLoading = false;
            this.resetPasswordSuccess = true;
            this.resetPasswordMessage = resp.message;
  
            Swal.fire(
                'Reset password',
                 this.resetPasswordMessage,
                'success'
            )

            this.router.navigate(['auth/login']);
          }
        },
        (error) => {
          console.log(this.resetPasswordForm.get('password').value);
          console.log(this.resetPasswordForm.get('passwordConfirmation').value);
          console.log(this.resetPasswordForm.get('code').value);
          
          Swal.fire(
            'Reset password',
            'error message : ' + error.message, 
            'error'
          );
        }
      );

    } 

}
