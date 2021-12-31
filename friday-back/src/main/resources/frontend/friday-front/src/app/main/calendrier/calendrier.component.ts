import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ApiCallService, Events } from 'src/app/api-call.service';
import { FullCalendarComponent, CalendarOptions} from '@fullcalendar/angular';
import { Subscription } from 'rxjs';

declare var $:any;

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
  file: File = File.prototype;
  url: string="";

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
      uploadIcsFile: {
        text: 'upload Ics File',
        click: function() {
          $("#uploadIcsFileModal").modal('show');
        }
      },
      uploadIcsUrl: {
        text: 'upload Ics Url',
        click: function() {
          $("#uploadIcsUrlModal").modal('show');
        }
      },
    },
    locale: 'fr',
    headerToolbar: {
      start: 'title',
      center: '',
      end: 'today'
    },
    footerToolbar: {
      start: 'uploadIcsFile,uploadIcsUrl',
      center: '',
      end: 'prev,next'
    },
    eventClick: (info)=>{
      info.jsEvent.preventDefault();
      let year = info.event.start!.getFullYear();
      let month = info.event.start!.getMonth() + 1 > 10? info.event.start!.getMonth() + 1: "0"+ (info.event.start!.getMonth()+1); // les mois sont de 00 a 11 et 1 digit si < 10... pas bien!
      let day = info.event.start!.getUTCDate()> 10? info.event.start?.getUTCDate(): "0"+info.event.start?.getUTCDate(); //1 digit si < 10... pas bien!
      this.dateCallback(year + "-" + month + "-" + day);
    },
    dateClick: (info) => {
      this.dateCallback(info.dateStr)
    },
    showNonCurrentDates: false,
    themeSystem: 'bootstrap',
    height: '40%'
  }

  dateCallback(info: any) {
    this.dateClick = info;
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

  handleFileInput(event: any) {
    this.file = event.target.files[0];
  }

  onSubmitFile(){
    this.apiService.postIcs(this.file).subscribe(response=>{
      if(response.status == 200){
        this.loadAllEvents();
        this.loadEventsByDate();
        $("#uploadIcsFileModal").modal('hide');
      }
    })
  }

  onSubmitUrl(){
    this.apiService.postIcsUrl(this.url).subscribe(response=>{
      if(response.status == 200){
        this.loadAllEvents();
        this.loadEventsByDate();
        $("#uploadIcsUrlModal").modal('hide');
      }
    })
  }
}
