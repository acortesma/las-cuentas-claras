import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FriendsComponent } from './friends/friends.component';
import { PaymentsComponent } from './payments/payments.component';
import { BalancesComponent } from './balances/balances.component';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'friends', component: FriendsComponent },
      { path: 'payments', component: PaymentsComponent },
      { path: 'balances', component: BalancesComponent },
      { path: '**', redirectTo: 'friends' }
    ]

  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)
  ]
})
export class TemplateRoutingModule { }
