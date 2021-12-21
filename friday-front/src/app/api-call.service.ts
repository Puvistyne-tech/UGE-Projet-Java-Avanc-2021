import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 

export interface Config{}

@Injectable({
  providedIn: 'root'
})
export class ApiCallService {

  constructor(private http : HttpClient) { }

  getConfig(){
    return this.http.get<Config>("/api/test/okJson");
  }
}
