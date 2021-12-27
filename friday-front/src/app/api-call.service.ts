import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 

export interface Events{
    id:string,
    name:string,
    description:String,
    startDate:Date,
    endDate:Date,
    startTime:String,
    endTime:String
}

@Injectable({
  providedIn: 'root'
})
export class ApiCallService {

  constructor(private http : HttpClient) { }

  getAllEvents(){
    return this.http.get<Events[]>("/api/events/event",
    {observe: 'response'});
  }

  getEventsByDate(date:string){
    return this.http.get<Events[]>("/api/events/findByDate?date="+date,
    {observe: 'response'});
  }
}
