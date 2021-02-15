import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Friend } from '../entity/friend';

@Injectable({
  providedIn: 'root'
})
export class FriendsService {

  constructor(private http: HttpClient) { }

  private url = environment.HOST_PATH_BACK;

  guardar(friend: Friend): Observable<Friend> {
    console.log('Host: '+ this.url);
    let friendJson = JSON.stringify(friend);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post<Friend>(this.url + '/friends', friendJson, { headers: headers });
  }

  getFriends(): Observable<any> {
    console.log('Host: '+ this.url);
    console.log('Enviroment:: '+ environment.production);
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.get(this.url + '/friends', { headers: headers });
  }
}
