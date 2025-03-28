import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDatepickerModule } from '@angular/material/datepicker';

import {provideMomentDateAdapter} from '@angular/material-moment-adapter';

import { Router } from '@angular/router';

import { Revenue } from '@interfaces/revenue.interface';
import { revenueService } from '@service/revenue/revenueService';
import { Response } from '@interfaces/response.interface';

import _moment from 'moment';
const moment =  _moment;

export const MY_FORMATS = {
  parse: {
    dateInput: 'MM/YYYY',
  },
  display: {
    dateInput: 'MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@Component({
  selector: 'app-receive-list',
  standalone: true,
  providers: [ provideMomentDateAdapter(MY_FORMATS) ],
  imports: [CommonModule, MatInputModule, MatButtonModule, MatButtonToggleModule, MatTableModule, MatIconModule, MatDatepickerModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit {
  displayedColumns = ['item', 'date', 'type', 'customer', 'amount', 'action'];
  transactions: Revenue[] = [];

  constructor(
    private route: Router,
    private revenueService: revenueService) { }

  ngOnInit(): void {
    this.loadRevenues()
  }

  loadRevenues(): void {

    this.revenueService.find().subscribe({
      next: result => {
        let response = result as Response
        if (response.code == 200){
            this.transactions = response.data as Revenue[]
        }
      },
      error: err => {
        alert("err: " + JSON.stringify(err))

      }
    })
  }

  /** Gets the total cost of all transactions. */
  getTotalCost() {
    return this.transactions.map(t => t.vlAmount).reduce((acc, value) => acc + value, 0);
  }

  onAdd() {
    this.route.navigate(['/receive/edit'], { state: { action: 'Add' } });
  }

  onEdit(transaction: Revenue) {
    this.route.navigate(['/receive/edit'], { state: { receive: transaction, action: 'Edit' } });
  }
}