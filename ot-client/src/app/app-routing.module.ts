import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
    { path: '', redirectTo: 'auth', pathMatch: 'full'},
    { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)},
    { path: 'user', loadChildren: () => import('./user/user.module').then(m => m.UserModule)},
    { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)},
    { path: '**', redirectTo: 'auth' },
];


@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }
  