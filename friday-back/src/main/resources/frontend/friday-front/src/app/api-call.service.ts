import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';

export interface Events{
    id?:string,
    title:string,
    description:String,
    location:string,
    start:Date,
    end:Date,
    allDay?:boolean
}

@Injectable({
  providedIn: 'root'
})
export class ApiCallService {

  constructor(private http : HttpClient) { }
  private subject = new Subject<any>();

  getAllEvents(){
    return this.http.get<Events[]>("/events/event",
                                    {observe: 'response'});
  }

  getEventsByDate(date:string){
    return this.http.get<Events[]>("/events/findByDate?date="+date,
                                    {observe: 'response'});
  }

  postEvent(evt:Events){
    return this.http.post<Event>("/events/event",evt,
                                  {observe: 'response'});
  }

  putEvent(evt:Events){
    return this.http.put<Event>("/events/event",evt,
                                  {observe: 'response'});
  }

  delEvent(id:string){
    return this.http.delete<number>("/events/"+id);
  }

  postIcs(file:File){
    const formData = new FormData();
    formData.append('file', file)
    return this.http.post<File>("/ical/uploadIcs",formData,
                                  {observe: 'response'});
  }

  postIcsUrl(url:string){
    return this.http.post<string>("/ical/IcsUrl?url="+url,{},
                                  {observe: 'response'});
  }

  sendClickEvent() {
    this.subject.next();
  }

  reloadEvent(): Observable<any>{
    return this.subject.asObservable();
  }
}
