import { CommonModule } from '@angular/common';
import { ViewChild, ChangeDetectionStrategy, Component, OnInit, signal, Renderer2, ElementRef } from '@angular/core';
import { Receive } from '@interfaces/receive.interface';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field'
import { FormsModule, ReactiveFormsModule, Validators, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { CurrencyMaskModule } from "ng2-currency-mask";
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { Utils } from '@commons/utils';


@Component({
  selector: 'app-edit',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatFormFieldModule,
    MatLabel, MatInputModule, MatSelectModule, CurrencyMaskModule, MatDatepickerModule,
    MatIconModule, MatButtonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditReceiveComponent implements OnInit {

  form!:FormGroup

  errorMessage = ""
  successMessage = ""
  isSubmitted = false
  isLoading = false

  receive?: Receive;
  action?: string;
  
  types = ['Salary', 'Biling', 'Benefits'];
  customers = ['SIS', 'MXN', 'Stefanini', 'Sixpro'];

  constructor(private fb:FormBuilder) { }

  ngOnInit() {

    this.form = this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      customer: ['', Validators.required],
      date: ['', Validators.required],
      amount: ['', Validators.required]
    });

    const state = history.state;
    this.receive = state.receive;
    this.action = state.action;

    
    if (this.receive){
      this.form.get('name')?.setValue(this.receive.name);
      this.form.get('type')?.setValue(this.receive.type);   
      this.form.get('customer')?.setValue(this.receive.customer);
      this.form.get('date')?.setValue(this.receive.date);
      this.form.get('amount')?.setValue(this.receive.amount);
    }


  }

  save() {
    if (this.form.invalid) {
      this.form.markAllAsTouched(); // 🔥 Marca todos os campos como "tocados"
      return;
    }

    Utils.toShowSuccess(this, 'Receive saved successfully!')
    this.cleanFields()
  }

  nameErrorMessage() {
    if (this.form.get('name')?.hasError('required')) {
      return 'Name is required';
    }
    return '';
  }

  typeErrorMessage() {
    if (this.form.get('type')?.hasError('required')) {
      return 'Type is required';
    }
    return '';
  }

  customerErrorMessage() {
    if (this.form.get('customer')?.hasError('required')) {
      return 'Customer is required';
    }
    return '';
  }

  dateErrorMessage() {
    if (this.form.get('date')?.hasError('required')) {
      return 'Date is required';
    }
    return '';
  }

  amountErrorMessage() {
    if (this.form.get('amount')?.hasError('required') || this.form.get('amount')?.value == 0) {
      return 'Amount is required';
    }
    return '';
  }

  cleanFields() {
  }


  goBack() {
    window.history.back();
  }
}
