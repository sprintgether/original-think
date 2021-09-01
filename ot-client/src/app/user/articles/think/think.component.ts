import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Think } from 'src/app/_model/think.model';
import { ArticleService } from 'src/app/_service/article.service.';
import { TokenStorageService } from 'src/app/_service/token-storage.service';

@Component({
  selector: 'app-think',
  templateUrl: './think.component.html',
  styleUrls: ['./think.component.scss']
})
export class ThinkComponent implements OnInit {

  document : File;
  cover: File;

  //Gestion du formulaire
  thinkForm: FormGroup;
  thinkLoading = false;
  thinkSubmitted = false;
  thinkError = false;
  thinkMessage: string;
  thinkSuccess = false;

  customUserDetails = null;

  thinkDto : Think;


  constructor(private articleService: ArticleService, private tokenStorage: TokenStorageService, private fb: FormBuilder) { }
  ngOnInit(): void {
    this.customUserDetails = this.tokenStorage.getAccessToken();

    this.thinkForm = this.fb.group({
      document:[''],
      cover: [''],
      journal:[''],
      theme: [''],
      description: [''],
      domain:[''],
      abstract:['']
    });
  }

   // Access rapide au formulaire
   get f() { return this.thinkForm.controls; }
 



  onThinkSubmit(){
      this.thinkDto = {
      journal : this.f.journal.value,
      theme : this.f.theme.value,
      domain : this.f.domain.value,
      description : this.f.description.value,
      abstract : this.f.abstract.value
    } 

    /*console.log("this.thinkDto");
    console.log(this.thinkDto);
    console.log("journal = " + this.f.journal.value) // this.thinkForm.get('journal').value);
    console.log("theme = " + this.f.theme.value);
    console.log("customUserDetails = " + this.customUserDetails);
    console.log("-----------------this.document-----------------");
    console.log(this.document);
    console.log(this.f.document.value);
    console.log("-----------------this.cover-----------------"); 
    console.log(this.cover); 
    console.log(this.f.cover.value); */

 

    this.articleService.createThink(this.f.document.value, this.f.cover.value, this.thinkDto).subscribe( 
      (response) => {
          console.log("---response---");
          console.log(response);
      },
      (error) => {
        console.log("---------------------------------error---------------------------------")
        console.log(error)
        console.log("---------------------------------error---------------------------------")
      }
    );
  }

onDocumentChanged(event: any): void {
    this.document =  event.target.files;
}

onCoverChanged(event: any): void {
  this.cover = event.target.files;
}

}
