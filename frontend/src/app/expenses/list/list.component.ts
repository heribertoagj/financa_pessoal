import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Exepense } from '@interfaces/expense.interface';

@Component({
  selector: 'app-exepenses-list',
  standalone: true,
  imports: [CommonModule, MatInputModule, MatButtonModule, MatButtonToggleModule, MatTableModule, MatIconModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent {
    displayedColumns = ['item', 'date', 'type', 'establishment', 'amount', 'action'];
    transactions: Exepense[] = [
      { id: 'teste', date: new Date(), name: 'Mensalidade', type: 'Educação', establishment: 'Colégio Innovativo', amount: 4 },
      { id: 'teste', date: new Date(), name: 'Material', type: 'Educação', establishment: 'Colégio innovativo', amount: 4 },
      { id: 'teste', date: new Date(), name: 'Trilha', type: 'Salário', establishment: 'Colégio innovativo', amount: 4 }
    ];
  
    /** Gets the total cost of all transactions. */
    getTotalCost() {
      return this.transactions.map(t => t.amount).reduce((acc, value) => acc + value, 0);
    }
  
}
