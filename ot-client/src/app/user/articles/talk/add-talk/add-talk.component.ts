import { Component, isDevMode, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Talk } from 'src/app/_model/talk.model';
import { ArticleService } from 'src/app/_service/article.service.';
import { TokenStorageService } from 'src/app/_service/token-storage.service';

@Component({
  selector: 'app-add-talk',
  templateUrl: './add-talk.component.html',
  styleUrls: ['./add-talk.component.scss']
})
export class AddTalkComponent implements OnInit {

  document: File;
  cover: File;
  talk: Talk;

  talkForm: FormGroup;

  constructor(private articleService: ArticleService,
    private tokenStorage: TokenStorageService,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    this.talkForm = this.fb.group({
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

  get f() { return this.talkForm.controls; }

  onTalkSubmit(){
    let newTalk = new Talk()
    newTalk.theme =  this.f.theme.value
    newTalk.domain = this.f.domain.value
    newTalk.description = this.f.description.value
    newTalk.abstracts = this.f.abstracts.value
    newTalk.journal = this.f.journal.value

    this.talk = newTalk
    if(isDevMode)
      console.log(this.talk)
      console.log(this.f.document)
      console.log(this.f.cover)

    this.articleService.createThink(this.f.document.value, this.f.cover.value, this.talk).subscribe(
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
