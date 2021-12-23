import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-rdv',
  templateUrl: './list-rdv.component.html',
  styleUrls: ['./list-rdv.component.css']
})
export class ListRdvComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  select(id : string){
    var elements = document.getElementsByClassName('active'); // get all elements
    for(var i = 0; i < elements.length; i++){
      elements[i].setAttribute("class","list-group-item list-group-item-action")
    }
    document.getElementById(id)?.setAttribute("class",document.getElementById(id)?.getAttribute("class") + " active");
  }

}
