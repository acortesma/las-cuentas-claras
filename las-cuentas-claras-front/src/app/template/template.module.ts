import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { TemplateRoutingModule } from './template-routing.module';
import { BasicosComponent } from './basicos/basicos.component';
import { DinamicosComponent } from './dinamicos/dinamicos.component';
import { SiwtchesComponent } from './siwtches/siwtches.component';
import { DateAgoPipe } from '../pipes/date-ago.pipe';


@NgModule({
  declarations: [
    BasicosComponent,
    DinamicosComponent,
    SiwtchesComponent,
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
