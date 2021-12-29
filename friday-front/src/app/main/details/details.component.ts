import { Component, Input, OnInit } from '@angular/core';
import { Events } from 'src/app/api-call.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  @Input() eventsFromMain : Array<Events> = [];
  @Input()
  event!: Events;


  constructor() { }

  ngOnInit(): void {
  }

  clickForDetails($event:any) {this.event = $event;console.log($event)}

}
