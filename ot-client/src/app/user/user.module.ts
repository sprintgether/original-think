import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import { HomeComponent } from './home/home.component';
import { ShareModule } from '../share/share.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ThinkComponent } from './articles/think/think.component';
import { ThesisComponent } from './articles/thesis/thesis.component';
import { TalkComponent } from './articles/talk/talk.component';
import { StudytripComponent } from './articles/studytrip/studytrip.component';


@NgModule({
  declarations: [UserComponent, HomeComponent, ThinkComponent, ThesisComponent, TalkComponent, StudytripComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    ShareModule
  ]
})
export class UserModule { }
