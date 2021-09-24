import { Component, OnInit, isDevMode } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Studytrip } from 'src/app/_model/studytrip.model';
import { ArticleService } from 'src/app/_service/article.service.';
import { TokenStorageService } from 'src/app/_service/token-storage.service';

@Component({
  selector: 'app-add-studytrip',
  templateUrl: './add-studytrip.component.html',
  styleUrls: ['./add-studytrip.component.scss']
})
export class AddStudytripComponent implements OnInit {

  document: File;
  cover: File;
  studytrip: Studytrip;

  studytripForm: FormGroup;

  constructor(private articleService: ArticleService,
    private tokenStorage: TokenStorageService,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    this.studytripForm = this.fb.group({
      theme: [''],
      domain: [''],
      description: [''],
      abstracts: [''],
      locality: [''],
      mentorName: [''],
      mentorEmail: [''],

      document: [''],
      cover: [''],

      /*document: ['', [Validators.required, requiredFileType(['png', 'jpg', 'pdf', 'docx', 'jpeg', 'xls', 'xlxs'])]],   
      cover: ['', [Validators.required, requiredFileType(['png', 'jpg', 'jpeg'])]],*/
    });
  }

  get f() { return this.studytripForm.controls; }

  onStudytripSubmit(){
    let newStudytrip = new Studytrip()
    newStudytrip.theme =  this.f.theme.value
    newStudytrip.domain = this.f.domain.value
    newStudytrip.description = this.f.description.value
    newStudytrip.abstracts = this.f.abstracts.value
    newStudytrip.locality = this.f.locality.value
    newStudytrip.mentorName = this.f.mentorName.value
    newStudytrip.mentorEmail = this.f.mentorEmail.value

    this.studytrip = newStudytrip
    if(isDevMode)
      console.log(this.studytrip)
      console.log(this.f.document)
      console.log(this.f.cover)

    this.articleService.createThink(this.f.document.value, this.f.cover.value, this.studytrip).subscribe(
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
