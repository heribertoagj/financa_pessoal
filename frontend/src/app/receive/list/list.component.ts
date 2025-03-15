import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';

import { Receive } from '@interfaces/receive.interface';

@Component({
  selector: 'app-receive-list',
  standalone: true,
  imports: [CommonModule, MatInputModule, MatButtonModule, MatButtonToggleModule, MatTableModule, MatIconModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent {
  displayedColumns = ['item', 'date','type', 'customer', 'amount', 'action'];
  transactions: Receive[] = [
    { id: 'teste', date: new Date(), name: 'Faturamento', type: 'Biling', customer: 'MXN', amount: 4 },
    { id: 'teste', date: new Date(), name: 'Faturamento', type: 'Biling', customer: 'SIS', amount: 4 },
    { id: 'teste', date: new Date(), name: 'Faturamento', type: 'Biling', customer: 'SIS', amount: 4 },
    { id: 'teste', date: new Date(), name: 'Faturamento', type: 'Biling', customer: 'SIS', amount: 4 },
    { id: 'teste', date: new Date(), name: 'Faturamento', type: 'Biling', customer: 'SIS', amount: 4 },
    { id: 'teste', date: new Date(), name: 'Salário', type: 'Salary', customer: 'Sixpro', amount: 4 }
  ];

  constructor (private route: Router) {}

  /** Gets the total cost of all transactions. */
  getTotalCost() {
    return this.transactions.map(t => t.amount).reduce((acc, value) => acc + value, 0);
  }

  onAdd(){
    this.route.navigate(['/receive/edit'], {state: { action: 'Add'}});
  }

  onEdit(transaction: Receive){
    this.route.navigate(['/receive/edit'], {state: {receive: transaction, action: 'Edit'}});
  }
}