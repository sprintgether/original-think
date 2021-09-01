import { Component, ElementRef, EventEmitter, HostListener, Input, Output } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';


@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: FileUploadComponent,
      multi: true
    }
  ],
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent implements ControlValueAccessor {
  @Input() progress;
  @Output() fileChangeEvent = new EventEmitter<boolean>();
  onChange: Function;
  public file: File | null = null;

  constructor(private host: ElementRef) { }

  @HostListener('change', ['$event.target.files']) emitFiles( event: FileList) {
    const file = event && event.item(0);

    console.log("file")
    console.log(file)
    
    console.log("event && event.item(0)")
    console.log(event && event.item(0))

    console.log("event.item(0)")
    console.log(event.item(0))
    console.log(event)

    this.onChange(file);
    this.file = file;
    this.fileChangeEvent.emit(true);
  }

  writeValue( value: null) {
    // clear file input
    this.host.nativeElement.value = '';
    this.file = null;
  }

  registerOnChange( fn: Function) {
    this.onChange = fn;
  }

  registerOnTouched(fn: Function) {}


}
