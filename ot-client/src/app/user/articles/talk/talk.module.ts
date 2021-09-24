import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TalkRoutingModule } from './talk-routing.module';
import { TalkComponent } from './talk.component';
import { DisplayTalkComponent } from './display-talk/display-talk.component';
import { AddTalkComponent } from './add-talk/add-talk.component';
import { EditTalkComponent } from './edit-talk/edit-talk.component';
import { ShareModule } from 'src/app/share/share.module';


@NgModule({
  declarations: [TalkComponent, DisplayTalkComponent, AddTalkComponent, EditTalkComponent],
  imports: [
    CommonModule,
    ShareModule,
    TalkRoutingModule
  ]
})
export class TalkModule { }
