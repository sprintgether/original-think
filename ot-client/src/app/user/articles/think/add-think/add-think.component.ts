import { Component, isDevMode, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Think } from 'src/app/_model/think.model';
import { ArticleService } from 'src/app/_service/article.service.';
import { TokenStorageService } from 'src/app/_service/token-storage.service';

@Component({
  selector: 'app-add-think',
  templateUrl: './add-think.component.html',
  styleUrls: ['./add-think.component.scss']
})
export class AddThinkComponent implements OnInit {

  
  document: File;
  cover: File;
  think: Think;

  thinkForm: FormGroup;

  constructor(
    private articleService: ArticleService,
    private tokenStorage: TokenStorageService,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    this.thinkForm = this.fb.group({
      theme: ['', [Validators.required]],
      domain: [''],
      description: [''],
      abstracts: [''],
      journal: [''],

      document: [''],
      cover: [''],

      /*document: ['', [Validators.required, requiredFileType(['png', 'jpg', 'pdf', 'docx', 'jpeg', 'xls', 'xlxs'])]],
      cover: ['', [Validators.required, requiredFileType(['png', 'jpg', 'jpeg'])]],*/
    });
  }

  get f() { return this.thinkForm.controls; }


  onThinkSubmit() {
    let newThink = new Think()
    newThink.theme =  this.f.theme.value
    newThink.domain = this.f.domain.value
    newThink.description = this.f.description.value
    newThink.abstracts = this.f.abstracts.value
    newThink.journal = this.f.journal.value

    this.think = newThink
    if(isDevMode)
      console.log(this.think)
      console.log(this.f.document)
      console.log(this.f.cover)

    this.articleService.createThink(this.f.document.value, this.f.cover.value, this.think)
    .subscribe(
      (response) => {
        if(isDevMode)
          console.log(response);
      },
      (error) => {
        console.log(error)
      }
    );
  }

  onDocumentChanged(event: any): void {
    // TODO
  }

  onCoverChanged(event: any): void {
    // TODO
  }

}
