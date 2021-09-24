import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddTalkComponent } from './add-talk/add-talk.component';
import { DisplayTalkComponent } from './display-talk/display-talk.component';
import { EditTalkComponent } from './edit-talk/edit-talk.component';
import { TalkComponent } from './talk.component';


const routes: Routes = [
  {
    path: '',
    component: TalkComponent,
    children: [
      { path: 'display-talk', component: DisplayTalkComponent },
      { path: 'add-talk', component: AddTalkComponent },
      { path: 'Edit-talk', component: EditTalkComponent },
      { path: '', redirectTo: 'add-talk', pathMatch: 'full' },
      { path: '**', redirectTo: 'add-talk' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TalkRoutingModule { }
