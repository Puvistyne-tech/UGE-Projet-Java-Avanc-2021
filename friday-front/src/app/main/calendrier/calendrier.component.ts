import { Component, OnInit, ViewChild } from '@angular/core';
import { DatePipe } from '@angular/common';
import googleCalendarPlugin from '@fullcalendar/google-calendar';
import { ApiCallService } from 'src/app/api-call.service';
import { environment } from 'src/environments/environment';
import { FullCalendarComponent, CalendarOptions } from '@fullcalendar/angular';

declare var $:any;

@Component({
  selector: 'app-calendrier',
  templateUrl: './calendrier.component.html',
  styleUrls: ['./calendrier.component.css']
})
export class CalendrierComponent implements OnInit {
  constructor(private datepipe: DatePipe, private apiService : ApiCallService) { }

  focus: any;
  googleId: string='';
  googleIdDel: string='';
  googleCalendars : Array<{
    googleCalendarId : string
  }> = [];
  
  @ViewChild('calendar')
  calendarComponent!: FullCalendarComponent;

  calendarOptions: CalendarOptions = {
    plugins: [googleCalendarPlugin],
    googleCalendarApiKey: environment.apiKey,
    events: this.googleCalendars,
    initialView: 'customWeekGrid',
    views: {
      customWeekGrid: {
        type: 'dayGridWeek',
        dayCount: 7,
      }
    },
    customButtons :{
      addGoogleCalendarBtn :{
        text: "add Google Calendar",
        click : ()=>{
          this.showModalGoogleId();
        }
      },
      delGoogleCalendarBtn :{
        text: "remove Google Calendar",
        click : ()=>{
          this.showModalGoogleIdDel();
        }
      }
    },
    locale: 'fr',
    headerToolbar: {
      start: 'title',
      center: '',
      end: 'today'
    },
    footerToolbar: {
      start: 'addGoogleCalendarBtn delGoogleCalendarBtn',
      center: '',
      end: 'prev,next'
    },
    eventClick: function (info) {
      info.jsEvent.preventDefault();
    },
    dateClick: (info) => {
      this.dateCallback(info)
    },
    showNonCurrentDates: false,
    themeSystem: 'bootstrap',
    height: '40%'
  }

  dateCallback(info: any) {
    this.testingefef();
  }

  testingefef(){
    this.apiService.getConfig().subscribe(data =>{
      console.log(data);
    });
  }

  addGoogleCalendar(id : string){
    var isInside= false;
    this.googleCalendars.forEach(x =>{
      if(x.googleCalendarId === id){
        isInside = true;
        return;
      }
    });
    if(isInside){
      return;
    }
    this.googleCalendars.push({googleCalendarId:id});
    this.calendarComponent.getApi().addEventSource({googleCalendarId:id});
  }

  showModalGoogleId(){
    $('#ModalGoogleId').modal('show');
  }

  hideModalGoogleId(){
    $('#ModalGoogleId').modal('hide');
  }

  showModalGoogleIdDel(){
    $('#ModalGoogleIdDel').modal('show');
  }

  hideModalGoogleIdDel(){
    $('#ModalGoogleIdDel').modal('hide');
  }

  onSubmitGoogleId(){
    this.addGoogleCalendar(this.googleId);
    $('#ModalGoogleId').modal('hide');
  }

  onSubmitGoogleIdDel(){
    this.calendarComponent.getApi().removeAllEventSources();
    var newArr = new Array;
    this.googleCalendars.forEach(x =>{
      if(!(x.googleCalendarId === this.googleIdDel)){
        newArr.push(x);
        this.calendarComponent.getApi().addEventSource(x);
      }
    });
    this.googleCalendars = newArr;
    $('#ModalGoogleIdDel').modal('hide');
  }

  ngOnInit(): void {
  }

}
