import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddStudytripComponent } from './add-studytrip/add-studytrip.component';
import { DisplayStudytripComponent } from './display-studytrip/display-studytrip.component';
import { EditStudytripComponent } from './edit-studytrip/edit-studytrip.component';
import { StudytripComponent } from './studytrip.component';


const routes: Routes = [
  {
    path: '',
    component: StudytripComponent,
    children: [
      { path: 'display-studytrip', component: DisplayStudytripComponent },
      { path: 'add-studytrip', component: AddStudytripComponent },
      { path: 'Edit-studytrip', component: EditStudytripComponent },
      { path: '', redirectTo: 'add-studytrip', pathMatch: 'full' },
      { path: '**', redirectTo: 'add-studytrip' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudytripRoutingModule { }
