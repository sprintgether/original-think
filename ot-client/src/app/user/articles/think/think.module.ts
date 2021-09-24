import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ThinkRoutingModule } from './think-routing.module';
import { ThinkComponent } from './think.component';
import { AddThinkComponent } from './add-think/add-think.component';
import { EditThinkComponent } from './edit-think/edit-think.component';
import { ShareModule } from 'src/app/share/share.module';
import { DisplayThinksComponent } from './display-thinks/display-thinks.component';


@NgModule({
  declarations: [ThinkComponent, AddThinkComponent, EditThinkComponent, DisplayThinksComponent],
  imports: [
    CommonModule,
    ShareModule,
    ThinkRoutingModule
  ]
})
export class ThinkModule { }
