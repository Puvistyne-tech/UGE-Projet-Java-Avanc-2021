import { Component, Input, OnInit } from '@angular/core';
import { Events } from 'src/app/api-call.service';

@Component({
  selector: 'app-list-rdv',
  templateUrl: './list-rdv.component.html',
  styleUrls: ['./list-rdv.component.css']
})
export class ListRdvComponent implements OnInit {
  @Input() events : Array<Events> = [];

  constructor() { }

  ngOnInit(): void {
  }

  select(id : number){
    var elements = document.getElementsByClassName('active'); // get all elements
    for(var i = 0; i < elements.length; i++){
      elements[i].setAttribute("class","list-group-item list-group-item-action")
    }
    document.getElementById(id+"")?.setAttribute("class",document.getElementById(id+"")?.getAttribute("class") + " active");
  }

}
