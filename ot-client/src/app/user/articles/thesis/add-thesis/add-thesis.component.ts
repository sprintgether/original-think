import { Component, isDevMode, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Thesis } from 'src/app/_model/thesis.model';
import { ArticleService } from 'src/app/_service/article.service.';
import { TokenStorageService } from 'src/app/_service/token-storage.service';

@Component({
  selector: 'app-add-thesis',
  templateUrl: './add-thesis.component.html',
  styleUrls: ['./add-thesis.component.scss']
})
export class AddThesisComponent implements OnInit {
  document: File;
  cover: File;
  thesis: Thesis;

  thesisForm: FormGroup;

  constructor(private articleService: ArticleService,
    private tokenStorage: TokenStorageService,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    this.thesisForm = this.fb.group({
      theme: ['', [Validators.required]],
      domain: [''],
      description: [''],
      abstracts: [''],
      studyLevel: [''],

      document: [''],
      cover: [''],

      /*document: ['', [Validators.required, requiredFileType(['png', 'jpg', 'pdf', 'docx', 'jpeg', 'xls', 'xlxs'])]],
      cover: ['', [Validators.required, requiredFileType(['png', 'jpg', 'jpeg'])]],*/
    });
  }

  get f() { return this.thesisForm.controls; }

  onThesisSubmit(){
    let newThesis = new Thesis()
    newThesis.theme =  this.f.theme.value
    newThesis.domain = this.f.domain.value
    newThesis.description = this.f.description.value
    newThesis.abstracts = this.f.abstracts.value
    newThesis.studyLevel = this.f.studyLevel.value

    this.thesis = newThesis
    if(isDevMode)
      console.log(this.thesis)
      console.log(this.f.document)
      console.log(this.f.cover)

    this.articleService.createThink(this.f.document.value, this.f.cover.value, this.thesis).subscribe(
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
