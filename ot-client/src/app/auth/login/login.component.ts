import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthResponse } from 'src/app/_model/auth-response';
import { AuthService } from 'src/app/_service/auth.service';
import { TokenStorageService } from 'src/app/_service/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  //gestion des dates
  today: number = Date.now();

  //gestion des rôles
  roles: string[] = []


  //Gestion du formulaire
  loginForm: FormGroup;
  loginLoading = false;
  loginSubmitted = false;
  loginError = false;
  loginMessage: string;
  loginSuccess = false;

   
  constructor(private fb: FormBuilder, private authService: AuthService, 
    private tokenStorageService: TokenStorageService,
    private router: Router) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email:['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      rememberMe: []
    });
  }

  // Access rapide au formulaire
  get f() { return this.loginForm.controls; }
 

  // Connxion
  onLoginSubmit() {
    this.loginSubmitted = true;

    // Si le formulaire n'est pas valide
    if (this.loginForm.invalid){
      this.loginError = true;
      this.loginMessage = "Please fill the form appropriately"
      return;
    }

    this.loginLoading = true;

    console.log("Submitting ...")
    
    this.authService.login(this.loginForm.get('email').value, this.loginForm.get('password').value).subscribe(
      (resp) => {
        let status = resp.status;
        if(status == "SUCCESS"){
          this.loginLoading = false;
          this.loginSuccess = true;
          this.loginMessage = resp.message;

          // Comme tout s'est bien passé on récupère l'utilisateur et on le met dans le localStorage
          // authRespoonse = accessToken, refreshToken, expiryDuration, username, authoroties
          let authResponse = resp.data as AuthResponse;

          // recenser les rôles de l'utilisateur
          authResponse.authorities.forEach(
            (role) => {
              this.roles.push(role['authority']);
            }
          );

          if(this.roles.includes("ADMIN")) {
            this.tokenStorageService.saveAuthResponse(authResponse);
            this.router.navigate(['admin']);
          }
          else if (this.roles.includes("USER")) {
            this.tokenStorageService.saveAuthResponse(authResponse);
            this.router.navigate(['user']);
          }
          else{
            Swal.fire(
              'Connexion',
              'Vous avez été identifié, mais vous n\'avez pas droit approprié vous permettant de vous connecter !',
              'warning'
            )

          }


        }
      }














    );
  }

}
