import { Component, OnInit } from '@angular/core';
import { Events } from 'src/app/api-call.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  Events: Array<Events> = [];

  childToParent($event:any) {this.Events = $event; console.log(this.Events)}
}
