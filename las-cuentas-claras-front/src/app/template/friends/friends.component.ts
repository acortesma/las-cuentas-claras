import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Friend } from '../entity/friend';
import { FriendsService } from '../services/friends.service';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styles: [
  ]
})
export class FriendsComponent implements OnInit {

  friends: Array<Friend>;

  constructor(private friendService: FriendsService) { }

  ngOnInit(): void {
    this.getFriends();
  }

  guardar(miFormulario: NgForm) {

    console.log(miFormulario.value)

    let friend = new Friend();
    friend.name = miFormulario.value.name;

    let friendJson = JSON.stringify(friend);
    this.friendService.guardar(friend).subscribe(
      res => {
        let retorno = JSON.stringify(res);
        console.log("RetornoLlamada " + retorno);
        this.getFriends();
      },
      error => {
        let errorMsg = <any>error;
        if (errorMsg != null) {
          console.log("Retorno Error " + error);
        }
      }
    );

    miFormulario.resetForm();
  }

  getFriends() {
    this.friendService.getFriends().subscribe(
      res => {
        if (!res) {
          console.log('Error, no se recuperaron los animales');
        } else {
          let retorno = JSON.stringify(res);
          console.log("RetornoLlamada " + retorno);
          this.friends = res as Array<Friend>;
        }
      },
      error => {
        console.log(error);
      }
    );
  }

}
