import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ThinkNotificationRoutingModule } from './think-notification-routing.module';
import { ThinkNotificationComponent } from './think-notification.component';
import { EditThinkNotificationComponent } from './edit-think-notification/edit-think-notification.component';
import { AddThinkNotificationComponent } from './add-think-notification/add-think-notification.component';
import { DisplayThinkNotificationComponent } from './display-think-notification/display-think-notification.component';


@NgModule({
  declarations: [ThinkNotificationComponent, EditThinkNotificationComponent, AddThinkNotificationComponent, DisplayThinkNotificationComponent],
  imports: [
    CommonModule,
    ThinkNotificationRoutingModule
  ]
})
export class ThinkNotificationModule { }
