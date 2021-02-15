import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BasicosComponent } from './basicos/basicos.component';
import { DinamicosComponent } from './dinamicos/dinamicos.component';
import { SiwtchesComponent } from './siwtches/siwtches.component';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'friends', component: BasicosComponent },
      { path: 'payments', component: DinamicosComponent },
      { path: 'balances', component: SiwtchesComponent },
      { path: '**', redirectTo: 'friends' }
    ]

  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)
  ]
})
export class TemplateRoutingModule { }
