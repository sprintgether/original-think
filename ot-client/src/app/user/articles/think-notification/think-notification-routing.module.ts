import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddThinkNotificationComponent } from './add-think-notification/add-think-notification.component';
import { DisplayThinkNotificationComponent } from './display-think-notification/display-think-notification.component';
import { EditThinkNotificationComponent } from './edit-think-notification/edit-think-notification.component';


const routes: Routes = [
  { path: 'display-studytrip', component: DisplayThinkNotificationComponent },
  { path: 'add-studytrip', component: AddThinkNotificationComponent },
  { path: 'Edit-studytrip', component: EditThinkNotificationComponent },
  { path: '', redirectTo: 'add-studytrip', pathMatch: 'full' },
  { path: '**', redirectTo: 'add-studytrip' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ThinkNotificationRoutingModule { }
