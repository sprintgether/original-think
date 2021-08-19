import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [FooterComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    //AngularFontAwesomeModule,
    RouterModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
],
  exports: [
    FooterComponent,
    HttpClientModule,
    //AngularFontAwesomeModule,
    ReactiveFormsModule,
    RouterModule,
    NgbModule,
    FormsModule,
    
]
})
export class ShareModule { }
