import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { VerifyEmailComponent } from './verify-email/verify-email.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ShareModule } from '../share/share.module';
import { RegisterComponent } from './register/register.component';


@NgModule({
  declarations: [LoginComponent, VerifyEmailComponent, ResetPasswordComponent, ForgotPasswordComponent, RegisterComponent],
  imports: [
    CommonModule,
    AuthRoutingModule,
    ShareModule
  ]
})
export class AuthModule { }
