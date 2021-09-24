import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ThesisRoutingModule } from './thesis-routing.module';
import { ThesisComponent } from './thesis.component';
import { EditThesisComponent } from './edit-thesis/edit-thesis.component';
import { AddThesisComponent } from './add-thesis/add-thesis.component';
import { DisplayThesisComponent } from './display-thesis/display-thesis.component';
import { ShareModule } from 'src/app/share/share.module';


@NgModule({
  declarations: [ThesisComponent, EditThesisComponent, AddThesisComponent, DisplayThesisComponent],
  imports: [
    CommonModule,
    ShareModule,
    ThesisRoutingModule
  ]
})
export class ThesisModule { }
