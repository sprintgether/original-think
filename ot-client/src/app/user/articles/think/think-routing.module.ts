import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddThinkComponent } from './add-think/add-think.component';
import { DisplayThinksComponent } from './display-thinks/display-thinks.component';
import { EditThinkComponent } from './edit-think/edit-think.component';
import { ThinkComponent } from './think.component';


const routes: Routes = [
  {
    path: '',
    component: ThinkComponent,
    children: [
      { path: 'display-think', component: DisplayThinksComponent },
      { path: 'add-think', component: AddThinkComponent },
      { path: 'Edit-think', component: EditThinkComponent },
      { path: '', redirectTo: 'add-think', pathMatch: 'full' },
      { path: '**', redirectTo: 'add-think' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ThinkRoutingModule { }
