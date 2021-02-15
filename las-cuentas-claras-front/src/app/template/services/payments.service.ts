import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Payment } from '../entity/payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentsService {

  constructor(private http: HttpClient) { }

  private url = environment.HOST_PATH_BACK;

  guardar(payment: Payment): Observable<Payment> {

    let paymentJson = JSON.stringify(payment);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post<Payment>(this.url + '/payments', paymentJson, { headers: headers });
  }

  getPayments(): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.get(this.url + '/payments', { headers: headers });
  }

  getFriendsBalance(): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.get(this.url + '/payments/balance', { headers: headers });
  }
}
