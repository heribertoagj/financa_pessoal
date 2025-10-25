import { Component } from '@angular/core';
import { ListComponent as ListReceitaComponent} from "../receita/list/list.component";
import { ListComponent as ListDespesaComponent } from "../despesa/list/list.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ListReceitaComponent, ListDespesaComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  
}
