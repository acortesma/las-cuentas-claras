import { Component } from '@angular/core';

interface MenuItem {
  texto: string;
  ruta: string;
}

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent {

  templateMenu: MenuItem[] = [
    {
      texto: 'Amigos',
      ruta: './template/friends'
    },
    {
      texto: 'Pagos',
      ruta: './template/payments'
    },
    {
      texto: 'Balances',
      ruta: './template/balances'
    }
  ]


}
