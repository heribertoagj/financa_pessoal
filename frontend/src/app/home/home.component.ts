import { Component } from '@angular/core';
import { ListComponent as ListReceiveComponent} from "../revenue/list/list.component";
import { ListComponent as ListExpensesComponent } from "../expenses/list/list.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ListReceiveComponent, ListExpensesComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  
}
