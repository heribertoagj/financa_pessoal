import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Receive } from '@interfaces/receive.interface';

@Component({
  selector: 'app-edit',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './edit.component.html',
  styleUrl: './edit.component.css'
})
export class EditReceiveComponent implements OnInit {

  
  receive?: Receive;
  action?: string;

  ngOnInit() {
    const state = history.state;
    this.receive = state.receive;
    this.action = state.action;

    alert(JSON.stringify(this.receive));
  }

}
