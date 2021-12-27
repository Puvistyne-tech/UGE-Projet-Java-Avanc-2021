import { Component, Input, OnInit } from '@angular/core';
import { Events } from 'src/app/api-call.service';

@Component({
  selector: 'app-fiche-rdv',
  templateUrl: './fiche-rdv.component.html',
  styleUrls: ['./fiche-rdv.component.css']
})
export class FicheRdvComponent implements OnInit {
  @Input()
  events!: Events;

  constructor() { }

  ngOnInit(): void {
  }

}
