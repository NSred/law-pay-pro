import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Offer} from "../model/offer";

@Injectable({
  providedIn: 'root'
})
export class OffersService {
  constructor(private httpClient: HttpClient) {}
  private readonly baseUrl: string = 'http://localhost:8070/api/offers'

  getOffers(): Observable<Offer[]>{
    return this.httpClient.get<Offer[]>(`${this.baseUrl}`)
  }
}
