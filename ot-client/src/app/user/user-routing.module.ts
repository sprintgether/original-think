import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './user.component';


const routes: Routes = [
  {
    path: '',
    component: UserComponent,
    
    /*canActivate:[LoginGuard],*/
    children: [
      { path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
      { path: 'think', loadChildren: () => import('./articles/think/think.module').then(m => m.ThinkModule) },
      { path: 'thesis', loadChildren: () => import('./articles/thesis/thesis.module').then(m => m.ThesisModule) },
      { path: 'talk', loadChildren: () => import('./articles/talk/talk.module').then(m => m.TalkModule) },
      { path: 'studytrip', loadChildren: () => import('./articles/studytrip/studytrip.module').then(m => m.StudytripModule) },
      { path: 'think-notification', loadChildren: () => import('./articles/think-notification/think-notification.module').then(m => m.ThinkNotificationModule) },
      /*{ path: 'home', component: HomeComponent },
      { path: 'think', component: ThinkComponent },
      { path: 'thesis', component: ThesisComponent },
      { path: 'talk', component: TalkComponent },
      { path: 'think-notification', component: ThinkNotificationComponent },
      { path: 'studytrip', component: StudytripComponent },*/
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: '**', redirectTo: 'home' }
    ],
  },
  
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
