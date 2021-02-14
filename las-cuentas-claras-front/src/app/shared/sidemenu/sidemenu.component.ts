import { Component } from '@angular/core';

interface MenuItem {
  texto: string;
  ruta: string;
}

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styles: [
  ]
})
export class SidemenuComponent {

  templateMenu: MenuItem[] = [
    {
      texto: 'Añadir Amigo',
      ruta: './template/basicos'
    },
    {
      texto: 'Añadir Pago',
      ruta: './template/dinamicos'
    },
    {
      texto: 'Balances',
      ruta: './template/switches'
    }
  ]


}
