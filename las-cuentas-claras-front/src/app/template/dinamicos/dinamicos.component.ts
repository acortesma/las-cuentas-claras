import { ParseSourceFile } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Friend } from '../entity/friend';
import { Payment } from '../entity/payment';
import { PaymentsService } from '../services/payments.service';

@Component({
  selector: 'app-dinamicos',
  templateUrl: './dinamicos.component.html',
  styles: [
  ]
})
export class DinamicosComponent implements OnInit {

  constructor(private paymentService: PaymentsService) { }

  ngOnInit(): void {
  }

  guardar(miFormulario: NgForm) {

    let payment = miFormulario.value as Payment;
    let friend = new Friend();
    friend.id= "35431";
    friend.name = miFormulario.value.name;
    payment.payer= friend ;
    let retorno = JSON.stringify(payment);

    this.paymentService.guardar(payment).subscribe(
      res => {
        let retorno = JSON.stringify(res);
        console.log("RetornoLlamada " + retorno);
      },
      error => {
        console.log("Retorno Error " + error);
      }
    );
  }

}
