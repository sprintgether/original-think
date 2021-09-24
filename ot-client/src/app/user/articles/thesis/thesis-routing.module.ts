import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddThesisComponent } from './add-thesis/add-thesis.component';
import { DisplayThesisComponent } from './display-thesis/display-thesis.component';
import { EditThesisComponent } from './edit-thesis/edit-thesis.component';
import { ThesisComponent } from './thesis.component';


const routes: Routes = [
  {
    path: '',
    component: ThesisComponent,
    children: [
      { path: 'display-thesis', component: DisplayThesisComponent },
      { path: 'add-thesis', component: AddThesisComponent },
      { path: 'Edit-thesis', component: EditThesisComponent },
      { path: '', redirectTo: 'add-thesis', pathMatch: 'full' },
      { path: '**', redirectTo: 'add-thesis' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ThesisRoutingModule { }
