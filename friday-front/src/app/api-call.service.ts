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
    return this.http.get<Events[]>("/api/events/event",
                                    {observe: 'response'});
  }

  getEventsByDate(date:string){
    return this.http.get<Events[]>("/api/events/findByDate?date="+date,
                                    {observe: 'response'});
  }

  postEvent(evt:Events){
    return this.http.post<Event>("/api/events/event",evt,
                                  {observe: 'response'});
  }

  putEvent(evt:Events){
    return this.http.put<Event>("/api/events/event",evt,
                                  {observe: 'response'});
  }

  delEvent(id:string){
    return this.http.delete<number>("/api/events/"+id);
  }

  sendClickEvent() {
    this.subject.next();
  }

  reloadEvent(): Observable<any>{ 
    return this.subject.asObservable();
  }
}
