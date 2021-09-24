import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudytripRoutingModule } from './studytrip-routing.module';
import { StudytripComponent } from './studytrip.component';
import { DisplayStudytripComponent } from './display-studytrip/display-studytrip.component';
import { AddStudytripComponent } from './add-studytrip/add-studytrip.component';
import { EditStudytripComponent } from './edit-studytrip/edit-studytrip.component';
import { ShareModule } from 'src/app/share/share.module';


@NgModule({
  declarations: [StudytripComponent, DisplayStudytripComponent, AddStudytripComponent, EditStudytripComponent],
  imports: [
    CommonModule,
    ShareModule,
    StudytripRoutingModule
  ]
})
export class StudytripModule { }
