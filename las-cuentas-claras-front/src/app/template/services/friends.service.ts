import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Friend } from '../entity/friend';

@Injectable({
  providedIn: 'root'
})
export class FriendsService {

  constructor(private http: HttpClient) { }

  private url = 'http://localhost:8080'

  guardar(friend: Friend): Observable<Friend> {

    let friendJson = JSON.stringify(friend);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post<Friend>(this.url + '/friends', friendJson, { headers: headers });
  }

  getFriends(): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.get(this.url + '/friends', { headers: headers });
  }
}
