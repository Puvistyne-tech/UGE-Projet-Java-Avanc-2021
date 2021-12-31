import { Component, Input, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { ApiCallService,Events } from 'src/app/api-call.service';

@Component({
  selector: 'app-fiche-rdv',
  templateUrl: './fiche-rdv.component.html',
  styleUrls: ['./fiche-rdv.component.css']
})
export class FicheRdvComponent implements OnInit {
  @Input()
  events!: Events;

  form = new FormGroup({
    title: new FormControl('', [Validators.minLength(2),Validators.required]),
    description: new FormControl('', Validators.required),
    location: new FormControl('', Validators.required),
    allDay : new FormControl(false),
    start: new FormControl(Validators.required),
    end : new FormControl()
  });
  dateValid = true;

  get title(): any {
    return this.form.get('title');
  }

  constructor(private apiService : ApiCallService) { }

  ngOnInit(): void {
  }

  delete(){
    this.apiService.delEvent(this.events.id!).subscribe(() =>{
      this.apiService.sendClickEvent();
    });
  }

  validate(event : Events){
    if(event.allDay && event.start.toString() != ''){
      return true;
    }
    if(!event.allDay && event.start.toString() != ''&& event.end.toString() != ''){
      return event.start < event.end;
    }
    return false;
  }

  onSubmitUpdate(){
    let tmp = this.form.value;
    let evt= <Events> {
      id: this.events.id,
      title: tmp.title,
      location: tmp.location,
      description: tmp.description,
      start:tmp.start,
      end: tmp.allDay? tmp.start : tmp.end,
      allDay:tmp.allDay
    };
    this.dateValid = this.validate(evt);
    console.log(evt);
    if(this.dateValid){
      this.apiService.putEvent(evt).subscribe(() => {
        this.apiService.sendClickEvent();
      })
    }
  }

}
