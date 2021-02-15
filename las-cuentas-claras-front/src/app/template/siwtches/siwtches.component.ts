import { Component, OnInit } from '@angular/core';
import { FriendBalance } from '../entity/friendBalance';
import { Payment } from '../entity/payment';
import { PaymentsService } from '../services/payments.service';

@Component({
  selector: 'app-siwtches',
  templateUrl: './siwtches.component.html',
  styles: [
  ]
})
export class SiwtchesComponent implements OnInit {

  payments: Array<Payment>;
  balanceFriends: Array<FriendBalance>;

  constructor(private paymentService: PaymentsService) { }

  ngOnInit(): void {
    this.getpayments();
    this.getBalanceFriends();

  }

  refresh (){
    this.getpayments();
    this.getBalanceFriends();
  }

  getpayments() {
    this.paymentService.getPayments().subscribe(
      res => {
        if (!res) {
          console.log('Error, no se recuperaron los animales');
        } else {
          let retorno = JSON.stringify(res);
          console.log("RetornoLlamada " + retorno);
          this.payments = res as Array<Payment>;
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  getBalanceFriends() {
    this.paymentService.getFriendsBalance().subscribe(
      res => {
        if (!res) {
          console.log('Error, no se recuperaron los animales');
        } else {
          let retorno = JSON.stringify(res);
          console.log("RetornoLlamada " + retorno);
          this.balanceFriends = res as Array<FriendBalance>;
        }
      },
      error => {
        console.log(error);
      }
    );

  }

}
