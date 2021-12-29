import { Component, Input, OnInit , Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { ApiCallService,Events } from 'src/app/api-call.service';

@Component({
  selector: 'app-list-rdv',
  templateUrl: './list-rdv.component.html',
  styleUrls: ['./list-rdv.component.css']
})
export class ListRdvComponent implements OnInit {
  constructor( private apiService : ApiCallService) { }
  ngOnInit(): void {}
  @Input() events : Array<Events> = [];
  @Output() clickForDetails = new EventEmitter();

  sendToParent(evt:Events){
    
    this.clickForDetails.emit(evt);
  }

  form = new FormGroup({
    title: new FormControl('', [Validators.minLength(2),Validators.required]),
    description: new FormControl('', Validators.required),
    location: new FormControl('', Validators.required),
    allDay : new FormControl(false),
    start: new FormControl(new Date().toISOString().slice(0, 16),Validators.required),
    end : new FormControl(new Date().toISOString().slice(0, 16))
  });
  dateValid = true;

  get title(): any {
    return this.form.get('title');
  }

  select(id : number){
    var elements = document.getElementsByClassName('active'); // get all elements
    for(var i = 0; i < elements.length; i++){
      elements[i].setAttribute("class","list-group-item list-group-item-action")
    }
    document.getElementById(id+"")?.setAttribute("class",document.getElementById(id+"")?.getAttribute("class") + " active");
    this.sendToParent(this.events[id]);
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

  onSubmit(){
    let tmp = this.form.value;
    let evt= <Events> {
      title: tmp.title,
      location: tmp.location,
      description: tmp.description,
      start:tmp.start,
      end: tmp.allDay? tmp.start : tmp.end,
      allDay:tmp.allDay
    };
    this.dateValid = this.validate(evt);
    if(this.dateValid){
      this.apiService.postEvent(evt).subscribe(reponse => {
        if(reponse.status == 200){
          this.apiService.sendClickEvent();
        }
        
      })
    }
  }

}
