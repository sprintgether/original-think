import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudytripComponent } from './articles/studytrip/studytrip.component';
import { TalkComponent } from './articles/talk/talk.component';
import { ThesisComponent } from './articles/thesis/thesis.component';
import { ThinkComponent } from './articles/think/think.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user.component';


const routes: Routes = [
  {
    path: '',
    component: UserComponent,
    
    /*canActivate:[LoginGuard],*/
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'think', component: ThinkComponent },
      { path: 'thesis', component: ThesisComponent },
      { path: 'talk', component: TalkComponent },
      { path: 'studytrip', component: StudytripComponent },
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
