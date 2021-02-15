import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { TemplateRoutingModule } from './template-routing.module';
import { FriendsComponent } from './friends/friends.component';
import { PaymentsComponent } from './payments/payments.component';
import { BalancesComponent } from './balances/balances.component';
import { DateAgoPipe } from '../pipes/date-ago.pipe';


@NgModule({
  declarations: [
    FriendsComponent,
    PaymentsComponent,
    BalancesComponent,
    DateAgoPipe
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    TemplateRoutingModule
  ]
})
export class TemplateModule { }
