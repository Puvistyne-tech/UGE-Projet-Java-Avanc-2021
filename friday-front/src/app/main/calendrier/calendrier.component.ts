import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ApiCallService, Events } from 'src/app/api-call.service';
import { FullCalendarComponent, CalendarOptions} from '@fullcalendar/angular';

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
    let today = this.datepipe.transform(Date.now(),"yyyy-MM-dd");
    this.loadEventsByDate(today!.toString());
   }

  focus: any;
  Events: Array<Events> = [];

  calendarOptions: CalendarOptions = {
    timeZone: 'UTC',
    initialView: 'customWeekGrid',
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
    this.loadEventsByDate(info.dateStr);
  }

  loadAllEvents(){
    this.apiService.getAllEvents().subscribe(response =>{
      if(response.status == 200){
      response.body!.map(d => {
        this.calendarComponent.getApi().addEvent({
          id:d.id,
          title:d.name,
          start:d.startDate,
          end:d.endDate
        });
      });
    }
    });
  }

  loadEventsByDate(date:string){
    this.apiService.getEventsByDate(date).subscribe(response =>{
      if(response.status == 200){
        this.Events = response.body!;
        this.sendToParent();
      }
    });
  }


  ngOnInit(): void {
  }

}
