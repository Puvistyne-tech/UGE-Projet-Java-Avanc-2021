import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ApiCallService, Events } from 'src/app/api-call.service';
import { FullCalendarComponent, CalendarOptions} from '@fullcalendar/angular';
import { Subscription } from 'rxjs';

//declare var $:any;

@Component({
  selector: 'app-calendrier',
  templateUrl: './calendrier.component.html',
  styleUrls: ['./calendrier.component.css']
})
export class CalendrierComponent implements OnInit {
  @Output() childToParent = new EventEmitter();

  sendToParent(){this.childToParent.emit(this.Events);}

  @ViewChild('calendar')
  calendarComponent!: FullCalendarComponent;

  constructor(private datepipe: DatePipe, private apiService : ApiCallService) {
    this.loadAllEvents();
    this.dateClick = this.datepipe.transform(Date.now(),"yyyy-MM-dd")!.toString();
    this.loadEventsByDate();
    this.clickEventsubscription=this.apiService.reloadEvent().subscribe(()=>{
      this.loadAllEvents();
      this.loadEventsByDate();
    });
  }

  ngOnInit(): void {
  }

  clickEventsubscription:Subscription;
  focus: any;
  Events: Array<Events> = [];
  EventsCalendar: Array<Events> = [];
  dateClick:string ="";

  calendarOptions: CalendarOptions = {
    timeZone: 'UTC',
    initialView: 'customWeekGrid',
    events: this.EventsCalendar,
    views: {
      customWeekGrid: {
        type: 'dayGridWeek',
        dayCount: 7,
      }
    },
    customButtons :{
    },
    locale: 'fr',
    headerToolbar: {
      start: 'title',
      center: '',
      end: 'today'
    },
    footerToolbar: {
      start: '',
      center: '',
      end: 'prev,next'
    },
    eventClick: function (info) {
      info.jsEvent.preventDefault();
      console.log(info);
    },
    dateClick: (info) => {
      this.dateCallback(info)
    },
    showNonCurrentDates: false,
    themeSystem: 'bootstrap',
    height: '40%'
  }

  dateCallback(info: any) {
    this.dateClick = info.dateStr;
    this.loadEventsByDate();
  }

  loadAllEvents(){
    this.apiService.getAllEvents().subscribe(response =>{
      if(response.status == 200){
      this.EventsCalendar = [];
      response.body!.map(d => {
        this.EventsCalendar.push(d);
      });
    }else if(response.status ==204){
      this.EventsCalendar =[];
    }
    this.calendarOptions.events = this.EventsCalendar;
    });
  }

  loadEventsByDate(){
    this.apiService.getEventsByDate(this.dateClick).subscribe(response =>{
      if(response.status == 200){
        this.Events = response.body!;
        this.sendToParent();
      }else if(response.status == 204){
        this.Events = [];
        this.sendToParent();
      }
    });
  }
}
